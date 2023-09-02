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
        listOf("Dashboard", "Journals").forEach{
            clickOnElement(it)
            saveScreenshot("Navigation", it)
        }
    }
}