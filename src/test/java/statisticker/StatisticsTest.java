package statisticker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import statisticker.StatsChecker;
import statisticker.alerter.EmailAlert;
import statisticker.alerter.IAlerter;
import statisticker.alerter.LEDAlert;
import statisticker1.Statistics;

public class StatisticsTest {
    @Test
    public void reportsAverageMinMaxx() {
        Float[] numbers = { 1.5f, 8.9f, 3.2f, 4.5f };
        List<Float> numberList = Arrays.asList(numbers);
        Statistics.Stats stats = Statistics.getStatistics(numberList);

        float epsilon = 0.001f;
        assertEquals(stats.getAverage(), 4.525f, epsilon);
        assertEquals(stats.getMin(), 1.5f, epsilon);
        assertEquals(stats.getMax(), 8.9f, epsilon);
    }

    @Test
    public void reportsNaNForEmptyInput() {
        List<Float> emptyList = new ArrayList<Float>();
        Statistics.Stats stats = Statistics.getStatistics(emptyList);
        float epsilon = 0.001f;
        assertEquals(stats.getAverage(), Float.NaN, epsilon);
        assertEquals(stats.getMin(), Float.NaN, epsilon);
        assertEquals(stats.getMax(), Float.NaN, epsilon);
    }

    @Test
    public void reportsAlertsIfMaxIsMoreThanThreshold() {
        EmailAlert emailAlerter = new EmailAlert();
        LEDAlert ledAlerter = new LEDAlert();
        IAlerter alerters[] = { emailAlerter, ledAlerter };
        float maxThreshold = 10.2f;
        StatsChecker checker = new StatsChecker(maxThreshold, alerters);

        Float[] numbers = { 11.5f, 6.9f, 7.5f, 6.6f };
        checker.checkAndAlert(numbers);

        assertTrue(emailAlerter.isEmailSent());
        assertTrue(ledAlerter.isLedGlows());
    }
}
