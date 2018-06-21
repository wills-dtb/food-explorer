package com.ustwo.foodexplorer.view

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import com.ustwo.foodexplorer.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@LargeTest
@RunWith(AndroidJUnit4::class)
class InstrumentedTests {

    private var appContext:Context = InstrumentationRegistry.getTargetContext()

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //TODO: Need to explore the variety of different Cucumber <> Android options

    @Test
    fun testDetailsPageLaunchesWhenProductClicked() {
        val initialAppContext = appContext.applicationContext
        val recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //TODO: Currently fails as the functionality needs building
        assertNotEquals(appContext.applicationContext, initialAppContext)
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
