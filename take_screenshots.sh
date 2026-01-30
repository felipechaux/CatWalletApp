#!/bin/bash

# Screenshot helper script for Cat Wallet App
# This script helps capture screenshots for the README

SCREENSHOTS_DIR="screenshots"
ADB="$ANDROID_HOME/platform-tools/adb"

# Ensure screenshots directory exists
mkdir -p "$SCREENSHOTS_DIR"

echo "🐱 Cat Wallet App - Screenshot Helper"
echo "======================================"
echo ""

# Check if adb is available
if [ ! -f "$ADB" ]; then
    echo "⚠️  ADB not found at $ADB"
    echo "📱 Please use Android Studio's screenshot tool instead:"
    echo "   1. Open Android Studio"
    echo "   2. Run the app (▶ button)"
    echo "   3. Use the Camera icon in the emulator toolbar"
    echo ""
    exit 1
fi

# Get connected devices
DEVICES=$($ADB devices | grep -v "List" | grep "device$" | awk '{print $1}')
DEVICE_COUNT=$(echo "$DEVICES" | wc -l | tr -d ' ')

if [ "$DEVICE_COUNT" -eq "0" ]; then
    echo "❌ No devices connected"
    echo "📱 Please start an emulator or connect a device"
    exit 1
fi

echo "✅ Found $DEVICE_COUNT device(s) connected"
echo ""

# Function to take screenshot
take_screenshot() {
    local name=$1
    local desc=$2
    
    echo "📸 Taking screenshot: $desc"
    echo "   Press ENTER when ready..."
    read
    
    $ADB shell screencap -p /sdcard/screenshot_temp.png
    $ADB pull /sdcard/screenshot_temp.png "$SCREENSHOTS_DIR/$name.png"
    $ADB shell rm /sdcard/screenshot_temp.png
    
    echo "   ✅ Saved to $SCREENSHOTS_DIR/$name.png"
    echo ""
}

echo "📋 Screenshots needed:"
echo "   1. Splash Screen (with Lottie animation)"
echo "   2. Cat List (main screen)"
echo "   3. Payment Bottom Sheet"
echo ""
echo "🎬 Starting screenshot capture..."
echo ""

# Take screenshots
take_screenshot "screenshot_splash" "Splash Screen"
take_screenshot "screenshot_cat_list" "Cat List Screen"
take_screenshot "screenshot_payment" "Payment Bottom Sheet"

echo "✨ All screenshots captured!"
echo "📁 Screenshots saved in: $SCREENSHOTS_DIR/"
echo ""
echo "Next steps:"
echo "  1. Review the screenshots"
echo "  2. The README.md is already configured to display them"
echo "  3. Commit and push to GitHub!"
echo ""
