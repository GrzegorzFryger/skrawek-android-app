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
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static pl.edu.pjatk.pamo.skrawek.ViewActionUtils.waitFor;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeFragmentBehaviorTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void Should_NavigateCorrectly_UsingBottomMenu() throws InterruptedException {
        // Step 0 - Verify that snackbar appears after log in
        onView(allOf(withId(R.id.snackbar_text), withText("You have successfully logged in")))
                .check(matches(isDisplayed()));

        // Step 1 - Wait for Snackbar to disappear
        // Snackbar is visible for 1000 ms - we give some more time to include disappear animation
        onView(isRoot()).perform(waitFor(2000L));

        // Step 2 - Start at home fragment
        onView(withId(R.id.navigation_home)).check(matches(isSelected()));

        // Step 3 - Click absence icon
        onView(withId(R.id.navigation_absence)).perform(click());
        onView(withId(R.id.navigation_absence)).check(matches(isSelected()));

        // Step 4 - Click finances icon
        onView(withId(R.id.navigation_finances)).perform(click());
        onView(withId(R.id.navigation_finances)).check(matches(isSelected()));

        // Step 5 - Click account icon
        onView(withId(R.id.navigation_account)).perform(click());
        onView(withId(R.id.navigation_account)).check(matches(isSelected()));

        // Step 6 - End at home fragment
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.navigation_home)).check(matches(isSelected()));
    }
}
