package com.salach.appiumtests

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.File
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths


open class BaseTest {
    protected lateinit var driver: AppiumDriver
    protected lateinit var deviceName: String

    fun setupDriver() {
        // Set desired capabilities for the Android device
        deviceName = System.getProperty("deviceName")
        val device = System.getProperty("device")

        val caps = UiAutomator2Options()
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device)
        caps.setCapability(MobileCapabilityType.UDID, device)
        caps.setCapability(MobileCapabilityType.APP, getApkPath())
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.salach.journalhub")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.salach.journalhub.MainActivity")
        // Add other desired capabilities as needed

        // Initialize AndroidDriver
        driver = AndroidDriver(URL("http://127.0.0.1:4723/"), caps)
    }

    fun getApkPath(): String {
        val relativePath = System.getProperty("apkPath", "app/build/outputs/apk/debug/app-debug.apk")
        val parentPath: Path = Paths.get(System.getProperty("user.dir")).parent
        return parentPath.resolve(relativePath).toString()
    }


    fun saveScreenshot(filePrefix: String){
        val screenshotFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val destinationPath = "build/reports/tests/test/${filePrefix}_${deviceName}.png"
        screenshotFile.renameTo(File(destinationPath))
    }
}
