package com.kami.guess

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {
    //先取得MaterialActivity class
    @Rule
    @JvmField   //代表類別內屬性
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)


    @Test
    fun guessWrong(){
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
//        val n = 5
        for (n in 1..10) {
            if(n != secret) {
//        Espresso.onView(ViewMatchers.withId(R.id.ed_number))
//            .perform(ViewActions.clearText())
//        Espresso.onView(ViewMatchers.withId(R.id.ed_number))
//            .perform(ViewActions.typeText("5"))
//        Espresso.onView(ViewMatchers.withId(R.id.ok_btn))
//            .perform(ViewActions.click())
                5        //簡化上面 import Espresso   ViewMatches ViewActions
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView((withId(R.id.ok_btn))).perform(click())

                val message = if (n < secret) resources.getString(R.string.Bigger)
                else resources.getString(R.string.smaller)

                onView(ViewMatchers.withText(message))
                    .check(ViewAssertions.matches(isDisplayed()))
                //判斷是否出現OK按鈕
                onView(ViewMatchers.withText(resources.getString(R.string.OK))).perform(click())
            }
        }
    }
}