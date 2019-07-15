package assignment.cleancode.mobiledevassignment.utils;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class NetworkUtilsTests {
    @Test
    public void testIsConnectedToInternet() {
        Context context = InstrumentationRegistry.getTargetContext();

        NetworkUtils networkUtilsUnderTest = new NetworkUtils(context);

        // Run the test
        final boolean result = networkUtilsUnderTest.isConnectedToInternet();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetLocalIpAddress() {
        // Setup
        final String expectedResult = "10.195.97.9";

        // Run the test
        final String result = NetworkUtils.getLocalIpAddress();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
