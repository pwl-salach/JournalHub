package com.salach.appiumtests

import org.junit.Before
import org.junit.Test


class CreateJournal : BaseTest() {

    @Before
    fun setUp() {
        setupDriver()
    }

    @Test
    fun goToJournalsTab() {
        val ssPrefix = "CreateJournal"
        clickOnElement("Journals")
        saveScreenshot(ssPrefix, "Start")

        clickOnElement(ssPrefix)
        saveScreenshot(ssPrefix, "Init")

        clickOnElement("TitleInput")
        type("My title")
        saveScreenshot(ssPrefix, "TitleFilled")

        // hide keyboard
        driver.navigate().back()
        saveScreenshot(ssPrefix, "TitleFilledHiddenKeyboard")

        clickOnElement("SubtitleInput")
        type("Subtitle")
        saveScreenshot(ssPrefix, "SubtitleFilled")
        driver.navigate().back()
        saveScreenshot(ssPrefix, "SubtitleFilledHiddenKeyboard")

        clickOnElement("NextStep")
        saveScreenshot(ssPrefix, "CoverColorPicker")

        clickOnElement("NextStep")
        saveScreenshot(ssPrefix, "IconPicker")

        clickOnElement("NextStep")
        saveScreenshot(ssPrefix, "IconColorPicker")

    }
}
