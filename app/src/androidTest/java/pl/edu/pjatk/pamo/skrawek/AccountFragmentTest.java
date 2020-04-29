package pl.edu.pjatk.pamo.skrawek;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccountFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void Should_LoadAccountFragment() {
        // Step 1 - Click account icon
        onView(withId(R.id.navigation_account)).perform(click());
        onView(withId(R.id.navigation_account)).check(matches(isSelected()));

        // Step 2 - Verify that all necessary labels are visible
        onView(withId(R.id.nameHeaderTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.addressHeaderTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.phoneHeaderTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.accountStatusTextView)).check(matches(isDisplayed()));
    }
}
