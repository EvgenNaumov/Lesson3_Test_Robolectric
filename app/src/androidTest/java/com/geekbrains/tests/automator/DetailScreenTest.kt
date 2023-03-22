package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailScreenTest {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
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
    fun test_ButtonIncrementNotNull() {
        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val incrementButton = uiDevice.wait(
            Until.findObject(
                By.res(packageName,
                    "incrementButton")
            ), TIMEOUT
        )
        Assert.assertNotNull(incrementButton)
    }

    @Test
    fun test_ButtonDecrementNotNull() {

        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val buttonDecrement = uiDevice.wait(
            Until.findObject(
                By.res(packageName,
                "decrementButton")
            ), TIMEOUT
        )
        Assert.assertNotNull(buttonDecrement)
    }

    @Test
    fun test_ButtonIncrement() {

        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val textCount = uiDevice.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "totalCountTextView"
                )
            ), TIMEOUT
        )

        textCount.text = "Number of results: 0"

        val buttonIncrement = uiDevice.findObject(By.res(
            packageName,
            "incrementButton"
        ))

        buttonIncrement.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, "Number of results: 1")
    }

    @Test
    fun test_ButtonDecrement() {

        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val textCount = uiDevice.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "totalCountTextView"
                )
            ), TIMEOUT
        )

        textCount.text = "Number of results: 0"

        val decrementButton = uiDevice.findObject(By.res(
            packageName,
            "decrementButton"
        ))

        decrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, "Number of results: -1")

    }

    @Test
    fun test_ButtonDecrementNotEquals() {

        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val textCount = uiDevice.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "totalCountTextView"
                )
            ), TIMEOUT
        )

        textCount.text = "Number of results: 0"

        val decrementButton = uiDevice.findObject(By.res(
            packageName,
            "decrementButton"
        ))

        decrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertNotEquals(changedText.text, "Number of results: 1")

    }

    @Test
    fun test_ButtonIncrementNotEquals() {

        val buttonDetail =
            uiDevice.findObject(
                By.res(packageName, "toDetailsActivityButton"),
            )

        buttonDetail.click()

        val textCount = uiDevice.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "totalCountTextView"
                )
            ), TIMEOUT
        )

        textCount.text = "Number of results: 0"

        val buttonIncrement = uiDevice.findObject(By.res(
            packageName,
            "incrementButton"
        ))

        buttonIncrement.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertNotEquals(changedText.text, "Number of results: -1")
    }

    companion object {
        private const val TIMEOUT = 7000L
    }

}