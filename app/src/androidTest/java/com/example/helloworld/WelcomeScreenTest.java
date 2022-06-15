package com.example.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WelcomeScreenTest {
    @Rule
    public ActivityScenarioRule<WelcomeScreen> activityTestRule
            = new ActivityScenarioRule<>(WelcomeScreen.class);

    @Before
    public void setUp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "appops set " + ApplicationProvider.getApplicationContext().getPackageName() + " android:mock_location allow");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void succesfulNavigationToSettings(){
        onView(withContentDescription(R.string.drawer_open)).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());

        onView(withId(R.id.matches_reminder_time_label)).check(
                matches(withText("Pick your daily matches reminder time")));
    }

    @Test
    public void succesfulNavigationToMatches(){
        onView(withContentDescription(R.string.drawer_open)).perform(click());
        onView(withId(R.id.matches_menu_item)).perform(click());

    }
}