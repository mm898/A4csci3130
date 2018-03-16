package com.acme.a3csci3130;

/**
 * Created by mukhtaralhejji on 2018-03-14.
 *
 * These are the test cases for CRUD (create,read,update,delete).
 */

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;


import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

    private MyApplicationData appState ; // = MainActivity.;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * This test will create a business by typing the values into the form
     * then checking if they were added to the DB successfuly.
     */
    @Test
    public void create_business(){

        //click for creating a contact
        onView(withId(R.id.submitButton)).perform(click());

        //enter the info into the fields
        onView(withId(R.id.business_number)).perform(
                typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(
                typeText("Mukhtar"), closeSoftKeyboard());
        onView(withId(R.id.address)).perform(
                typeText("22 Parklane"), closeSoftKeyboard());
        onView(withId(R.id.primary_bznz_spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.province_spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());

        //when done writing in the field submit to DB
        onView(withId(R.id.submitButton)).perform(click());

        //get the size of the firebase to use it for finding last element added
        int i = MainActivity.firebaseAdapter.getCount();

        Assert.assertEquals(123456789, MainActivity.firebaseAdapter.getItem(i-1).business_number);
        Assert.assertEquals("Mukhtar", MainActivity.firebaseAdapter.getItem(i-1).name);
        Assert.assertEquals("22 Parklane", MainActivity.firebaseAdapter.getItem(i-1).address);
        Assert.assertEquals("Distributor", MainActivity.firebaseAdapter.getItem(i-1).primary_business);
        Assert.assertEquals("BC", MainActivity.firebaseAdapter.getItem(i-1).province);

    }

    /**
     * This test will remove the first business in the DB,
     * then it will check if the size of the DB decreased
     */
    @Test
    public void remove_business(){

        int total_business = MainActivity.firebaseAdapter.getCount();
        String Uid = MainActivity.firebaseAdapter.getItem(0).uid;

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());

        Assert.assertNotEquals(Uid, MainActivity.firebaseAdapter.getItem(0).uid);

    }

    /**
     * This test will update the first business in the DB with the new values,
     * then it will check if the first entry in the DB has changed.
     */
    @Test
    public void update_business(){

        SystemClock.sleep(2000);

        int i = MainActivity.firebaseAdapter.getCount();

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.business_number)).perform(
                clearText(), typeText(String.valueOf("123456789")), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(
                clearText(), typeText("Moka"), closeSoftKeyboard());
        onView(withId(R.id.address)).perform(
                clearText(), typeText("556 Hi St"), closeSoftKeyboard());
        onView(withId(R.id.primary_bznz_spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.province_spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.updateButton)).perform(click());


        Assert.assertEquals(123456789, MainActivity.firebaseAdapter.getItem(0).business_number);
        Assert.assertEquals("Moka", MainActivity.firebaseAdapter.getItem(0).name);
        Assert.assertEquals("556 Hi St", MainActivity.firebaseAdapter.getItem(0).address);
        Assert.assertEquals("Distributor", MainActivity.firebaseAdapter.getItem(0).primary_business);
        Assert.assertEquals("BC", MainActivity.firebaseAdapter.getItem(0).province);

    }

    /**
     * Test the read functionality on the app by selecting the first
     * item in the main view list, then make sure the first entry match
     * the data bellow.
     */
    @Test
    public void read_business(){

        SystemClock.sleep(2000);

        onView(withId(R.id.listView));
        onData(anything()).inAdapterView(allOf(withId(R.id.listView),isCompletelyDisplayed())).atPosition(0).perform(click());

        assertEquals(MainActivity.firebaseAdapter.getItem(0).business_number, 123456789);
        assertEquals(MainActivity.firebaseAdapter.getItem(0).name, "Mukhtar");
        assertEquals(MainActivity.firebaseAdapter.getItem(0).primary_business, "Distributor");
        assertEquals(MainActivity.firebaseAdapter.getItem(0).province, "BC");

    }

}
