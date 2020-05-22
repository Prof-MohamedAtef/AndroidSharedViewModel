package mo.ed.aad.fragmentssharedviewmodel.fragment;

import androidx.fragment.app.Fragment;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static mo.ed.aad.fragmentssharedviewmodel.MainActivityTest4.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfilesListFragmentTest {

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

        ViewInteraction bottomNavigationItemView5 = onView(
                allOf(withId(R.id.navigation_profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView5.perform(click());

        // type in


        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_userName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                3)));
        appCompatEditText.perform(scrollTo(), replaceText("Ramy Bahgat"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_position),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                5)));
        appCompatEditText2.perform(scrollTo(), replaceText("Machine Learning Engineer"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_address),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                7)));
        appCompatEditText3.perform(scrollTo(), replaceText("6th Oct City"), closeSoftKeyboard());


        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.edit_distance),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                9)));
        appCompatEditText5.perform(scrollTo(), replaceText("80 km"), closeSoftKeyboard());


        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.edit_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                11)));
        appCompatEditText9.perform(scrollTo(), replaceText("05:53 AM"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_submit), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                12)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView6 = onView(
                allOf(withId(R.id.navigation_sessions), withContentDescription("Profiles"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView6.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.profile_pic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profiles_list),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction TextView = onView(
                allOf(withText("Ramy Bahgat"), isDisplayed()));
/*
*******************************
* *****************************
 */
        ViewInteraction textView = onView(
                allOf(withId(R.id.txt_userName), withText("Ramy Bahgat"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profiles_list),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Ramy Bahgat")));

        ViewInteraction TextView_ = onView(
                allOf(withText("Machine Learning Engineer"), isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.job_title), withText("Machine Learning Engineer"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profiles_list),
                                        0),
                                1),
                        isDisplayed()));

        ViewInteraction TextView4 = onView(
                allOf(withText("Machine Learning Engineer"), isDisplayed()));
//        textView2.check(matches(withText("Machine Learning Engineer")));
//
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.txt_address), withText("6th Oct City"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profiles_list),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction TextView5 = onView(
                allOf(withText("6th Oct City"), isDisplayed()));
//        textView3.check(matches(withText("6th Oct City")));


        // inserted by adding (espresso-contrib) in gradle file
        onView(allOf(isDisplayed(),withId(R.id.profiles_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }
}