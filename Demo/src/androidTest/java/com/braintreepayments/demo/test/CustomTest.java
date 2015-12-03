package com.braintreepayments.demo.test;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.braintreepayments.demo.test.utilities.TestHelper.clearPreferences;
import static com.braintreepayments.demo.test.utilities.TestHelper.ensureEnvironmentIsSandbox;
import static com.braintreepayments.demo.test.utilities.TestHelper.launchDemoApp;
import static com.lukekorth.deviceautomator.AutomatorAction.click;
import static com.lukekorth.deviceautomator.AutomatorAction.setText;
import static com.lukekorth.deviceautomator.AutomatorAssertion.text;
import static com.lukekorth.deviceautomator.DeviceAutomator.onDevice;
import static com.lukekorth.deviceautomator.UiObjectMatcher.withText;
import static com.lukekorth.deviceautomator.UiObjectMatcher.withTextStartingWith;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CustomTest {

    @Before
    public void setup() {
        clearPreferences();
        launchDemoApp();
        ensureEnvironmentIsSandbox();
        onDevice(withText("Custom")).waitForEnabled().perform(click());
    }

    @Test(timeout = 60000)
    public void tokenizesACard() {
        onDevice(withText("Card Number")).perform(setText("4111111111111111"));
        onDevice(withText("Expiration")).perform(setText("1220"));
        onDevice(withText("Purchase")).perform(click());
        onDevice(withTextStartingWith("Card Last Two:")).check(text(containsString("11")));
    }
}
