package statisticker;

public class StatsChecker {

    private float maxThreshold;
    private IAlerter[] alerters;

    public StatsChecker(float maxThreshold, statisticker.IAlerter[] alerters) {
        this.maxThreshold = maxThreshold;
        this.alerters = alerters;
    }

    public void checkAndAlert(Float[] numbers) {
        for (Float number : numbers) {
            if (number > maxThreshold) {
                for (IAlerter alerter : alerters) {
                    if (alerter instanceof EmailAlert) {
                        ((statisticker.EmailAlert) alerter).setEmailSent(true);
                    } else if (alerter instanceof LEDAlert) {
                        ((statisticker.LEDAlert) alerter).setLedGlows(true);
                    }
                }
            }
        }
    }
}
