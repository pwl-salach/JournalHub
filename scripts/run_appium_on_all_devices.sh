logsDir="./scripts/logs"
port=5560

for configuredDevice in $(emulator -list-avds); do
  mkdir -p "${logsDir}"
  logFile="${logsDir}/emulator_${configuredDevice}.log"
  echo "Starting emulator: ${configuredDevice} on port ${port}"
  emulator -avd "${configuredDevice}" -gpu host -port $port > "${logFile}" 2>&1 &
  sleep 3
  while adb devices | grep "${port}" | grep "offline"; do
    echo "Device still starting..."
    sleep 3
  done
  ./scripts/run_appium_on_single_device.sh $port "$configuredDevice"
  mv appiumTests/build/reports/tests/test "appiumTests/build/reports/tests/${configuredDevice}"

  adb -s "emulator-${port}" emu kill
  sleep 3
  while adb devices | grep "${port}"; do
    echo "Device still running..."
    sleep 3
  done
  adb devices
  ((port++))
  echo "=============================================================="
done
