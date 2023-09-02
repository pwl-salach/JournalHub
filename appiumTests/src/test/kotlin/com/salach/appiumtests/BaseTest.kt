package com.salach.appiumtests

import io.appium.java_client.AppiumBy
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Keys
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import java.io.File
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths


open class BaseTest {
    protected lateinit var driver: AppiumDriver
    protected lateinit var deviceName: String
    private var screenshotCounter: Int = 0

    fun setupDriver() {
        // Set desired capabilities for the Android device
        deviceName = System.getProperty("deviceName")
        val device = System.getProperty("device")

        println("Parameters: device=${device} deviceName=${deviceName}")
        val caps = UiAutomator2Options()
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device)
        caps.setCapability(MobileCapabilityType.UDID, device)
        caps.setCapability(MobileCapabilityType.APP, getApkPath())
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.salach.journalhub")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.salach.journalhub.MainActivity")
        caps.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, 2*60000)
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2*60000)
        caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
        caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, false);
        // Add other desired capabilities as needed

        // Initialize AndroidDriver
        driver = AndroidDriver(URL("http://127.0.0.1:4723/"), caps)
    }

    fun getApkPath(): String {
        val relativePath = System.getProperty("apkPath", "app/build/outputs/apk/debug/app-debug.apk")
        val parentPath: Path = Paths.get(System.getProperty("user.dir")).parent
        return parentPath.resolve(relativePath).toString()
    }


    fun saveScreenshot(filePrefix: String, name: String){
        val screenshotFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val destinationPath = "build/reports/tests/test/${filePrefix}_${screenshotCounter}_${name}_${deviceName}.png"
        screenshotCounter += 1
        screenshotFile.renameTo(File(destinationPath))
    }

    fun findElement(contentDescription: String): WebElement {
        return driver.findElement(AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"${contentDescription}\")"))
    }
    fun clickOnElement(contentDescription: String){
        findElement(contentDescription).click()
    }

    fun type(text: String){
        Actions(driver).sendKeys(text).perform()
    }

    fun type(key: Keys){
        Actions(driver).sendKeys(key).perform()
    }
}
