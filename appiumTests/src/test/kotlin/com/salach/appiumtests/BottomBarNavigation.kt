package com.salach.appiumtests

import io.appium.java_client.AppiumBy
import org.junit.Before
import org.junit.Test

class BottomBarNavigation : BaseTest() {

    @Before
    fun setUp() {
        setupDriver()
    }

    @Test
    fun goThroughNavigationBar() {
        saveScreenshot("Navigation_init")
        listOf("Dashboard", "Journals").forEach{
            driver.findElement(AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"${it}\")")).click()
            saveScreenshot("Navigation_$it")
        }
    }
}