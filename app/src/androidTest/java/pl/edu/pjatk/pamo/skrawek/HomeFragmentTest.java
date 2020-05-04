package pl.edu.pjatk.pamo.skrawek;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static pl.edu.pjatk.pamo.skrawek.ViewActionUtils.waitFor;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void Should_LoadHomeFragment() {
        // Step 0 - Verify that snackbar appears after log in
        onView(allOf(withId(R.id.snackbar_text), withText("You have successfully logged in")))
                .check(matches(isDisplayed()));

        // Step 1 - Wait for Snackbar to disappear
        // Snackbar is visible for 1000 ms - we give some more time to include disappear animation
        onView(isRoot()).perform(waitFor(2000L));

        // Step - Verify that calendar is visible
        onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }
}
