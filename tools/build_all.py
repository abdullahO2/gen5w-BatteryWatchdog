#!/usr/bin/env python3
import os
import shutil
import subprocess
import sys
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
AJ = r"C:\Users\k\.gemini\antigravity\scratch\android-30.jar"
R8 = r"C:\Users\k\.gemini\antigravity\scratch\r8.jar"
BT = r"C:\Users\k\.gemini\antigravity\scratch\bt29\android-10"
AAPT = os.path.join(BT, "aapt.exe")
ZIPALIGN = os.path.join(BT, "zipalign.exe")
APKSIGNER = os.path.join(BT, "lib", "apksigner.jar")
KEYSTORE = r"C:\Users\k\.gemini\antigravity\scratch\debug.keystore"

with open(os.path.join(ROOT, "VERSION"), "r", encoding="utf-8") as f:
    VERSION = f.read().strip()

print(f"=== Building Battery Watchdog v{VERSION} ===")

APP_DIR = os.path.join(ROOT, "app")
BUILD_DIR = os.path.join(APP_DIR, "build")
CLASSES_DIR = os.path.join(BUILD_DIR, "classes")
DEX_DIR = os.path.join(BUILD_DIR, "dex")

shutil.rmtree(BUILD_DIR, ignore_errors=True)
os.makedirs(CLASSES_DIR, exist_ok=True)
os.makedirs(DEX_DIR, exist_ok=True)

# 1. Compile Java files
java_files = []
src_dir = os.path.join(APP_DIR, "src")
for root, _, files in os.walk(src_dir):
    for file in files:
        if file.endswith(".java"):
            java_files.append(os.path.join(root, file))

print(f"Compiling {len(java_files)} Java files...")
cmd_javac = [
    "javac",
    "--release", "8",
    "-classpath", AJ,
    "-d", CLASSES_DIR
] + java_files
subprocess.run(cmd_javac, check=True)

# 2. D8 / R8 dexing for KitKat (min-api 19)
print("Dexing with D8 (min-api 19)...")
class_files = []
for root, _, files in os.walk(CLASSES_DIR):
    for file in files:
        if file.endswith(".class"):
            class_files.append(os.path.join(root, file))

cmd_d8 = [
    "java", "-jar", R8,
    "d8",
    "--release",
    "--min-api", "19",
    "--output", DEX_DIR
] + class_files
subprocess.run(cmd_d8, check=True)

# 3. Update AndroidManifest.xml with version
manifest_src = os.path.join(APP_DIR, "AndroidManifest.xml")
with open(manifest_src, "r", encoding="utf-8") as f:
    manifest_content = f.read()

import re
manifest_content = re.sub(r'android:versionName="[^"]*"', f'android:versionName="{VERSION}"', manifest_content)
manifest_content = re.sub(r'android:versionCode="[^"]*"', 'android:versionCode="8"', manifest_content)

manifest_out = os.path.join(BUILD_DIR, "AndroidManifest.xml")
with open(manifest_out, "w", encoding="utf-8") as f:
    f.write(manifest_content)

# 4. Package resources with aapt
print("Packaging with aapt...")
res_dir = os.path.join(APP_DIR, "res")
base_apk = os.path.join(BUILD_DIR, "base.apk")
cmd_aapt = [
    AAPT, "package", "-f",
    "-M", manifest_out,
    "-S", res_dir,
    "-I", AJ,
    "-F", base_apk
]
subprocess.run(cmd_aapt, check=True)

# Add classes.dex into base.apk
print("Adding classes.dex...")
cmd_aapt_add = [
    AAPT, "add", "-k", base_apk,
    os.path.join(DEX_DIR, "classes.dex")
]
# aapt add expects relative path from cwd or specific syntax, or we can use zipfile!
with zipfile.ZipFile(base_apk, "a") as z:
    z.write(os.path.join(DEX_DIR, "classes.dex"), "classes.dex")

# 5. Zipalign
print("Aligning APK...")
aligned_apk = os.path.join(BUILD_DIR, "aligned.apk")
cmd_zipalign = [ZIPALIGN, "-f", "4", base_apk, aligned_apk]
subprocess.run(cmd_zipalign, check=True)

# 6. Apksigner
final_apk = os.path.join(APP_DIR, "batterywatchdog.apk")
print(f"Signing APK -> {final_apk}...")
cmd_sign = [
    "java", "-jar", APKSIGNER,
    "sign",
    "--ks", KEYSTORE,
    "--ks-pass", "pass:android",
    "--key-pass", "pass:android",
    "--out", final_apk,
    aligned_apk
]
subprocess.run(cmd_sign, check=True)

# Also copy to package/app/batterywatchdog.apk
pkg_apk = os.path.join(ROOT, "package", "app", "batterywatchdog.apk")
shutil.copy2(final_apk, pkg_apk)
print(f"Copied APK to {pkg_apk}")

# 7. Package NEMESIS distributions
DIST_DIR = os.path.join(ROOT, "dist")
os.makedirs(DIST_DIR, exist_ok=True)

# Prepare staging dir
stage_dir = os.path.join(DIST_DIR, "package-stage")
shutil.rmtree(stage_dir, ignore_errors=True)
shutil.copytree(os.path.join(ROOT, "package"), stage_dir)

# Update version in stage
watchdog_sh = os.path.join(stage_dir, "svc", "watchdog.sh")
with open(watchdog_sh, "r", encoding="utf-8") as f:
    wsh = f.read()
wsh = re.sub(r'^VERSION=.*', f'VERSION={VERSION}', wsh, flags=re.MULTILINE)
with open(watchdog_sh, "w", encoding="utf-8", newline='\n') as f:
    f.write(wsh)

nemesis_data = os.path.join(stage_dir, "NEMESIS.data")
with open(nemesis_data, "r", encoding="utf-8") as f:
    nd = f.read()
nd = re.sub(r'^package-version=.*', f'package-version={VERSION}', nd, flags=re.MULTILINE)
with open(nemesis_data, "w", encoding="utf-8", newline='\n') as f:
    f.write(nd)

# Ensure LF line endings on all shell scripts in stage
for s in [os.path.join(stage_dir, "scripts", "preinstall.sh"),
          os.path.join(stage_dir, "scripts", "post.sh"),
          os.path.join(stage_dir, "svc", "watchdog.sh")]:
    if os.path.exists(s):
        with open(s, "r", encoding="utf-8") as f:
            c = f.read()
        with open(s, "w", encoding="utf-8", newline='\n') as f:
            f.write(c)

# Create battery.watchdog.zip
bwd_zip_path = os.path.join(DIST_DIR, "battery.watchdog.zip")
if os.path.exists(bwd_zip_path):
    os.remove(bwd_zip_path)

print(f"Creating {bwd_zip_path}...")
with zipfile.ZipFile(bwd_zip_path, "w", zipfile.ZIP_DEFLATED) as z:
    for root, _, files in os.walk(stage_dir):
        for file in files:
            if file == ".keep" or file.startswith("."):
                continue
            full_path = os.path.join(root, file)
            rel_path = os.path.relpath(full_path, stage_dir).replace("\\", "/")
            z.write(full_path, rel_path)

shutil.rmtree(stage_dir, ignore_errors=True)

# Create gen5w-BatteryWatchdog-USB.zip
usb_zip_path = os.path.join(DIST_DIR, "gen5w-BatteryWatchdog-USB.zip")
if os.path.exists(usb_zip_path):
    os.remove(usb_zip_path)

print(f"Creating {usb_zip_path}...")
usb_data = "[nemesis]\nversion=1.0\ntype=install\npath=packages/battery.watchdog.zip\n"
with zipfile.ZipFile(usb_zip_path, "w", zipfile.ZIP_DEFLATED) as z:
    z.writestr("NEMESIS.data", usb_data)
    z.write(bwd_zip_path, "packages/battery.watchdog.zip")

print("=== Build and packaging completed successfully! ===")
print(f"1. NEMESIS package: {bwd_zip_path} ({os.path.getsize(bwd_zip_path):,} bytes)")
print(f"2. USB flash bundle: {usb_zip_path} ({os.path.getsize(usb_zip_path):,} bytes)")
