package string.class_problems;

public class BmiCalculator {

    public static void main(String[] args) {

        double[] heights = {1.75, 1.60, 1.80, 1.65, 1.70};
        double[] weights = {70, 90, 80, 55, 75};

        printWellnessReport(heights, weights);
    }

    public static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("Person | Height | Weight | BMI | Status");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            String status = getBmiStatus(bmi);

            System.out.println((i + 1) + " | "
                    + heights[i] + " | "
                    + weights[i] + " | "
                    + String.format("%.2f", bmi) + " | "
                    + status);
        }
    }
}
