package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MyApplicationData appState;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

//    @Test
//    public void create_business(){
//        //String personID = appState.firebaseReference.push().getKey();
//
//        //Contact person = new Contact(123456789, personID, "Moka",
//        //        "Fisher", "22 South", "Ab");
////        onView(withId(R.id.submitButton)).perform(click());
////
////        onView(withId(R.id.business_number))
////                .perform(typeText("123456789"), closeSoftKeyboard());
//////        onView(withId(R.id.name))
////                .perform(typeText("Mukhtar"), closeSoftKeyboard());
////        onView(withId(R.id.address))
////                .perform(typeText("22 Parklane"), closeSoftKeyboard());
////
////        onView(withId(R.id.primary_bznz_spinner)).perform(click());
////        onData(anything()).atPosition(1).perform(click());
////
////        onView(withId(R.id.province_spinner)).perform(click());
////        onData(anything()).atPosition(1).perform(click());
////
////        onView(withId(R.id.submitButton)).perform(click());
//
//        assertEquals(123456789, 123456789);
//        //        .perform(typeText("password"), closeSoftKeyboard());
//
//    }


}
