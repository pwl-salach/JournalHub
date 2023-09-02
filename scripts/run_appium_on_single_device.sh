emulatorPort=$1
deviceName=$2
echo "Parameters: port=$emulatorPort deviceName=$deviceName"
mkdir -p appiumTests/build/reports/tests/test
adb exec-out screencap -p > "appiumTests/build/reports/tests/test/InitScreen_${deviceName}.png"
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home/"
./gradlew --rerun-tasks appiumTests:test -Ddevice="emulator-$emulatorPort" -DdeviceName="${deviceName}"
unset JAVA_HOME
adb exec-out screencap -p > "appiumTests/build/reports/tests/test/EndScreen_${deviceName}.png"
