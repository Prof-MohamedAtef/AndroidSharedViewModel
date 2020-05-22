package mo.ed.aad.fragmentssharedviewmodel.fragment;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.aad.fragmentssharedviewmodel.MainActivity;
import mo.ed.aad.fragmentssharedviewmodel.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

//    @Rule
//    public FragmentTestRule<MainActivity, HomeFragment> fragmentTestRule =
//            new FragmentTestRule<>(MainActivity.class, HomeFragment.class);

    private MainActivity mACtivity=null;

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        mACtivity=mActivityTestRule.getActivity();
    }

    @Test
    public void verifyViewExists(){
        CircleImageView profile_pic=(CircleImageView) mACtivity.findViewById(R.id.profile_pic);
        assertNotNull(profile_pic);

        ViewInteraction TextView2 = onView(
                allOf(withText("Mohamed Atef"), isDisplayed()));

        ViewInteraction TextView3= onView(
                allOf(withText("Associate Android Developer"), isDisplayed()));

        onView(withId(R.id.txt_like)).check(matches(isClickable()));
        ViewInteraction donate=onView(withId(R.id.txt_donate)).check(matches(allOf(withText("Donate"),isDisplayed())));
    }

}