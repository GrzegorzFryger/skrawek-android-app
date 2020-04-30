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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static pl.edu.pjatk.pamo.skrawek.ViewActionUtils.waitFor;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginFragmentTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void Should_LoadAccountFragment() {
        // Step 0 - Click Log in button without providing credentials
        onView(withId(R.id.signInButton)).perform(click());

        // Step 1 - Wait for REST API call timeout to occur
        onView(isRoot()).perform(waitFor(10000L));

        // Step 2 - Verify that snackbar appears with error
        onView((withId(R.id.snackbar_text))).check(matches(isDisplayed()));
    }
}
