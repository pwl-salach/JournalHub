package com.salach.appiumtests

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.AndroidMobileCapabilityType
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.File
import java.net.URL


open class BaseTest {
    protected lateinit var driver: AppiumDriver
    protected lateinit var device: String

    fun setupDriver() {
        // Set desired capabilities for the Android device
        val caps = UiAutomator2Options()
        device = System.getProperty("device")

        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device)
        caps.setCapability(MobileCapabilityType.UDID, device)
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.salach.journalhub")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.salach.journalhub.MainActivity")
        // Add other desired capabilities as needed

        // Initialize AndroidDriver
        driver = AndroidDriver(URL("http://127.0.0.1:4723/"), caps)
    }

    fun saveScreenshot(filePrefix: String){
        val screenshotFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val destinationPath = "build/test-results/test/${filePrefix}_${device}.png"
        screenshotFile.renameTo(File(destinationPath))
    }
}
