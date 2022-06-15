package com.example.helloworld;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void canGoThroughForm() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 2 + 1, 5));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.name)).check(matches(withText("Victor")));
        onView(withId(R.id.description)).check(matches(withText("I am a student")));
        onView(withId(R.id.occupation)).check(matches(withText("Student")));
        onView(withId(R.id.age)).check(matches(withText("22")));
    }

    @Test
    public void checkBackButtonAndReset() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.name))
                .check(matches(withText("Victor")));

        onView(withId(R.id.backButton)).perform(click());

        //onView(withId(R.id.nameField)).check(matches(withText("")));

    }

    @Test
    public void checkingValidEmail() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        //onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("victor@gmail.com")).check(doesNotExist());
    }

    @Test
    public void checkingName() {
        //onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("Victor")).check(doesNotExist());
    }

    @Test
    public void checkingUserName() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        //onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("victornava")).check(doesNotExist());
    }

    @Test
    public void checkingDescription() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        //onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("I am a student")).check(doesNotExist());
    }

    @Test
    public void checkingOccupation() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victornava"));
        //onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("Student")).check(doesNotExist());
    }

    @Test
    public void cannotGoThroughFormWithYoungDob() {
        onView(withId(R.id.nameField)).perform(replaceText("Victor"));
        onView(withId(R.id.emailAddress)).perform(replaceText("victor@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("victorhugo"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));

        onView(withId(R.id.dobButton)).perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2021, 2 + 1, 5));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withText("3/5/2000")).check(doesNotExist());
    }

}

