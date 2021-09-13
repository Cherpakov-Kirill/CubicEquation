public class AnswerFinder {
    private final int delta = 1;
    private final double epsilon = 1e-12;
    private final double a;
    private final double b;
    private final double c;
    public int countOfAnswers;
    public double firstAnswer;
    public double secondAnswer;
    public double thirdAnswer;

    private double function(double currentX) {
        return currentX * currentX * currentX + a * currentX * currentX + b * currentX + c;
    }

    private Interval findInterval(double start, int delta) {
        double currentX = start;
        boolean funcBiggerThenZero = function(currentX) > 0;
        while (funcBiggerThenZero == function(currentX) > 0) {
            currentX += delta;
        }
        Interval area;
        if (delta > 0) {
            area = new Interval((currentX - delta), currentX);
        } else {
            area = new Interval(currentX, (currentX - delta));
        }
        System.out.println(area);
        return area;
    }

    private double findValIncreaseFunction(Interval interval) {
        double central = (interval.a + interval.b) / 2;
        double distance = function(central);
        while (Math.abs(distance) > epsilon && Math.abs(interval.a - interval.b) >= epsilon) {
            if (distance < -epsilon) interval.a = central;
            if (distance > epsilon) interval.b = central;
            central = (interval.a + interval.b) / 2;
            distance = function(central);
        }
        return central;
    }

    private double findValDecreaseFunction(Interval interval) {
        double central = (interval.a + interval.b) / 2;
        double distance = function(central);
        while (Math.abs(function(central)) > epsilon && Math.abs(interval.a - interval.b) >= epsilon) {
            if (distance < -epsilon) interval.b = central;
            if (distance > epsilon) interval.a = central;
            central = (interval.a + interval.b) / 2;
            distance = function(central);
        }
        return central;
    }

    AnswerFinder(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        countOfAnswers = 0;
        double D = 4 * a * a - 12 * b;
        if (D <= 0) {
            countOfAnswers = 1;
            if (Math.abs(function(0)) < epsilon) {
                firstAnswer = 0;
            } else {
                Interval interval;
                if (function(0) < -epsilon) {
                    interval = findInterval(0, delta);
                } else interval = findInterval(0, -delta);
                firstAnswer = findValIncreaseFunction(interval);
            }
            System.out.println("Answer: " + firstAnswer);
            System.out.println("func(x) = " + function(firstAnswer));
            thirdAnswer = firstAnswer;
        } else {
            final double sqrt = Math.sqrt(a * a - 3 * b);
            double extremumMax = (-a - sqrt) / (double) 3;
            double extremumMin = (-a + sqrt) / (double) 3;
            if (function(extremumMin) > epsilon) {
                countOfAnswers = 1;
                Interval interval = findInterval(extremumMax, -delta);
                firstAnswer = findValIncreaseFunction(interval);
                thirdAnswer = firstAnswer;
            } else if (function(extremumMax) < -epsilon) {
                countOfAnswers = 1;
                Interval interval = findInterval(extremumMin, delta);
                firstAnswer = findValIncreaseFunction(interval);
                thirdAnswer = firstAnswer;
            } else if (function(extremumMin) < -epsilon && function(extremumMax) > epsilon) {
                countOfAnswers = 3;
                Interval first = findInterval(extremumMax, -delta);
                Interval second = findInterval(extremumMax, delta);
                Interval third = findInterval(extremumMin, delta);
                firstAnswer = findValIncreaseFunction(first);
                secondAnswer = findValDecreaseFunction(second);
                thirdAnswer = findValIncreaseFunction(third);
            } else if (function(extremumMin) < -epsilon && Math.abs(function(extremumMax)) <= epsilon) {
                countOfAnswers = 2;
                Interval interval = findInterval(extremumMax, +delta);
                firstAnswer = extremumMax;
                secondAnswer = findValDecreaseFunction(interval);
                thirdAnswer = secondAnswer;
            } else if (function(extremumMax) > epsilon && Math.abs(function(extremumMin)) <= epsilon) {
                countOfAnswers = 2;
                Interval interval = findInterval(extremumMax, -delta);
                firstAnswer = findValIncreaseFunction(interval);
                secondAnswer = extremumMin;
                thirdAnswer = secondAnswer;
            } else if (Math.abs(function(extremumMin)) <= epsilon && Math.abs(function(extremumMax)) <= epsilon) {
                countOfAnswers = 1;
                firstAnswer = extremumMax;
                thirdAnswer = extremumMin;
            }
        }
        switch (countOfAnswers) {
            case 1 -> System.out.println("Answer: " + firstAnswer);
            case 2 -> System.out.println("Answer 1: " + firstAnswer + "\nAnswer 2: " + secondAnswer);
            case 3 -> System.out.println("Answer 1: " + firstAnswer + "\nAnswer 2: " + secondAnswer + "\nAnswer 3: " + thirdAnswer);
        }
    }
}
