package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class MainActivityTest {

    private val uiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)//Чистим бэкстек от запущенных ранее Активити
        context.startActivity(intent)

        uiDevice.wait(
            Until.hasObject(By.pkg(packageName).depth(0)),
            TIMEOUT
        )
    }

    @Test
    fun testButtonSearch_NotNull(){
        val buttonSearch = uiDevice.findObject(
            By.res(packageName,"toOpenSearchTextButton")
        )
        Assert.assertNotNull(buttonSearch)
    }

    @Test
    fun testButtonSearch_SearchIsPositive() {

        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val buttonSearch = uiDevice.findObject(
            By.res(packageName,"toOpenSearchTextButton")
        )

        buttonSearch.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text.toString(), "Number of results: 42")
    }

    @Test
    fun test_OpenDetailsScreen() {

        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"

        val buttonSearch = uiDevice.findObject(
            By.res(packageName,"toOpenSearchTextButton")
        )

        buttonSearch.click()

        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        //Кликаем по ней
        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: 42")
    }

    companion object {
        private const val TIMEOUT = 7000L
    }
}