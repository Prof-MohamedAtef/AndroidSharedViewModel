package mo.ed.aad.fragmentssharedviewmodel.fragment;

import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mo.ed.aad.fragmentssharedviewmodel.MainActivity;
import mo.ed.aad.fragmentssharedviewmodel.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfilesListFragmentTest {

    @Rule
    public FragmentTestRule<MainActivity, ProfilesListFragment> fragmentTestRule =
            new FragmentTestRule<>(MainActivity.class, ProfilesListFragment.class);

    @Test
    public void verifyViewExists(){
        onView(withText("Mohamed Atef")).check(matches(isDisplayed()));
    }

}