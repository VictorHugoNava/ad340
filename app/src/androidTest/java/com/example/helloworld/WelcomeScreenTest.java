package com.example.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.helloworld.RecyclerViewMatcher.withRecyclerView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WelcomeScreenTest {
    @Rule
    public ActivityScenarioRule<WelcomeScreen> activityTestRule
            = new ActivityScenarioRule<>(WelcomeScreen.class);

    @Test
    public void succesfulNavigationToSettings(){
        onView(withContentDescription(R.string.drawer_open)).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
    }

    @Test
    public void succesfulNavigationToMatches(){
        onView(withContentDescription(R.string.drawer_open)).perform(click());
        onView(withId(R.id.matches_menu_item)).perform(click());


        onView(isRoot()).perform(HelpersViewMatcher.waitId(R.id.matchesTextView, 5000));

        onView(withRecyclerView(R.id.matchesTextView).atPosition(0))
                .check(matches(hasDescendant(withText("Cool Guy Mike"))));
    }
}