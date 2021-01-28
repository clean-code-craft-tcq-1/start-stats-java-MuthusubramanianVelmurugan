package statisticker;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Statistics {
    public static class Stats {

        private float average = Float.NaN;
        private float min = Float.NaN;
        private float max = Float.NaN;

        public Stats(float average, float min, float max) {
            this.average = average;
            this.min = min;
            this.max = max;
        }

        public Stats() {

        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

    }

    public static Stats getStatistics(List<Float> numbers) {
        if (numbers != null && !numbers.isEmpty()) {
            return new Stats();
        }
        DoubleSummaryStatistics summaryStats = numbers.stream().mapToDouble((a) -> a).summaryStatistics();
        Stats stats = new Stats((float) summaryStats.getAverage(), (float) summaryStats.getMin(), (float) summaryStats.getMax());
        return stats;
    }
}
