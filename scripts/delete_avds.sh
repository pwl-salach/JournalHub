for configuredDevice in $(emulator -list-avds); do
  avdmanager delete avd --name "$configuredDevice"
done
