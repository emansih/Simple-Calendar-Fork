package com.simpletools.calendar.pro

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.pointzi.Pointzi
import com.simplemobiletools.calendar.pro.R
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_PACKAGE_NAME = "com.simplemobiletools.calendar.pro.debug"
private const val UI_TIMEOUT = 5000L
private const val TOOLTIP_TITLE_TO_FIND = "Add new event"
private const val SCROLL_VIEW = "android.widget.ScrollView"

@RunWith(AndroidJUnit4::class)
class AddEventBehaviorTest {

    private lateinit var device: UiDevice
    private lateinit var context: Context

    @Before
    fun startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        context = ApplicationProvider.getApplicationContext()
        val intent = context.packageManager.getLaunchIntentForPackage(TEST_PACKAGE_NAME)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(TEST_PACKAGE_NAME).depth(0)), UI_TIMEOUT)
    }

    @Test
    fun goToAddEventAndFindToolTip(){
        goToEventScreen()
        device.findObject(By.text(TOOLTIP_TITLE_TO_FIND))
        assertThat(Pointzi.feedID, notNullValue())

        val scrollView = UiScrollable(UiSelector().className(SCROLL_VIEW))
        scrollView.click()
        scrollView.scrollToEnd(3)
        scrollView.scrollToBeginning(3)
        val eventTitle = device.findObject(By.res("$TEST_PACKAGE_NAME:id/event_title"))
        eventTitle.text = "This is a test"

        // Press back twice.
        // First time to hide keyboard
        // Second time for the save dialog to show up
        device.pressBack()
        device.pressBack()
        device.findObject(By.text(context.getString(R.string.discard))).click()

        device.wait(Until.hasObject(By.pkg(TEST_PACKAGE_NAME).depth(0)), UI_TIMEOUT)
        goToEventScreen()

        // Verify that tooltip is still visible
        device.findObject(By.text(TOOLTIP_TITLE_TO_FIND))
    }

    private fun goToEventScreen(){
        device.findObject(By.res("$TEST_PACKAGE_NAME:id/calendar_fab")).click()
        device.wait(Until.hasObject(By.text(context.getString(R.string.event))), UI_TIMEOUT)
        device.findObject(By.text(context.getString(R.string.event))).click()

    }
}
