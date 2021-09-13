public class Main {

    public static void startCalculations(double a, double b, double c) {
        AnswerFinder solution = new AnswerFinder(a, b, c);
        ChartBuilder chart = new ChartBuilder(a, b, c, solution);
    }

    public static void main(String[] args) {
        new GetValueFrame();
    }
}