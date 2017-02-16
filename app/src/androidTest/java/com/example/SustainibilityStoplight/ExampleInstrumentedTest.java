package com.example.SustainibilityStoplight;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.SustainablitytSpotlight", appContext.getPackageName());
    }

    @Test
    public void surveyMapTest() throws Exception {
        SurveyMap s = new SurveyMap(InstrumentationRegistry.getTargetContext());
        assertNotNull(s.getQuestions("water"));
        assertEquals(s.getQuestions("water").get(0).getDimension(), "water");
        assertEquals(s.getQuestions("water").get(0).getId(), 1);
    }
}
