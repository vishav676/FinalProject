package com.udacity.gradle.builditbigger;

import android.util.Log;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.runner.RunWith;
public class NonEmptyStringTest extends TestCase {

    private static final String LOG_TAG = "NonEmptyStringTest";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    @SuppressWarnings("unchecked")
    public void test() {

        // Testing that Async task successfully retrieves a non-empty string
        // You can test this from androidTest -> Run 'All Tests'
        Log.v("NonEmptyStringTest", "Running NonEmptyStringTest test");
        String result = null;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(mActivityRule.getActivity().getApplicationContext());
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}
