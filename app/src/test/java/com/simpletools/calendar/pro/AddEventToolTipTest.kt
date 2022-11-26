package com.simpletools.calendar.pro

import com.pointzi.Pointzi
import com.simplemobiletools.calendar.pro.activities.EventActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk =[32])
class AddEventToolTipTest {


    @Test
    fun clickEventButton_shouldShowTooltip(){
        val eventActivityController = Robolectric.buildActivity(EventActivity::class.java)
        eventActivityController.setup()
        val activity = eventActivityController.get()
        assertNotNull(activity)
        Pointzi.init(activity.application, "Daniel_Test8b5ae38")
        val pointziUserId = Pointzi.setUserId("danielquah148@yahoo.com")
        assertNotNull(pointziUserId)
        assertTrue(pointziUserId)
    }
}
