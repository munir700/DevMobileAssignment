package assignment.cleancode.mobiledevassignment.utils;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class UtilsTest {

    @Test
    public void testGetAppVersionName() {
        // Setup
        Context context = InstrumentationRegistry.getTargetContext();
        final String expectedResult = "1.0";

        // Run the test
        final String result = Utils.getAppVersionName(context);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
