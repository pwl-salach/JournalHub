set -e

logsDir="./scripts/logs"

while IFS= read -r api ; do
  ver=$(echo "$api" | cut -d" " -f1)
  arch=$(echo "$api" | cut -d" " -f2)
  logFile="${logsDir}/sdkmanager_${ver}_${arch}.log"
  mkdir -p "$logsDir"
  package="system-images;android-${ver};google_apis;${arch}"
  echo "Downloading \"${package}\"... Check ${logFile} for more info"
  echo "y" | sdkmanager --install "${package}" 2> "${logFile}"
  echo "Done"

  while IFS= read -r device ; do
    deviceName=$(echo "${device}_API_${api}" | tr ' ' '_')
    logFile="${logsDir}/sdkmanager_${deviceName}.log"
    echo "Creating \"${deviceName}\" using \"${package}\"... Check ${logFile} for more info"
    avdmanager create avd --name "${deviceName}" --device "${device}" \
      --package "${package}" --abi "${arch}" > "${logFile}" 2>&1 \
      || echo Already exists...
  done < ./scripts/conf/devices.list
done < ./scripts/conf/apis.list
