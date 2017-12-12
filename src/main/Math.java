package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Math {
    public static Set<String> availableFunctions = new HashSet<>(Arrays.asList(
            GlobalConstants.ADD_FUNC,
            GlobalConstants.SUB_FUNC,
            GlobalConstants.MULT_FUNC,
            GlobalConstants.DIV_FUNC
            ));

    public double processCalculation(String mathFunction, double[] arguments) {
        double result = 0.0;

        switch (mathFunction) {
            case GlobalConstants.ADD_FUNC:
                result = add(arguments);
                break;
            case GlobalConstants.SUB_FUNC:
                result = sub(arguments);
                break;
            case GlobalConstants.MULT_FUNC:
                result = mult(arguments);
                break;
            case GlobalConstants.DIV_FUNC:
                result = div(arguments);
                break;
            default:
                break;
        }

        return result;
    }

    private double add(double[] arguments) {
        double sum = arguments[0];
        for (int i = 1; i < arguments.length; i++)
            sum += arguments[i];
        return sum;
    }

    private double sub(double[] arguments) {
        double difference = arguments[0];
        for (int i = 1; i < arguments.length; i++)
            difference -= arguments[i];
        return difference;
    }

    private double mult(double[] arguments) {
        double product = arguments[0];
        for (int i = 1; i < arguments.length; i++)
            product *= arguments[i];
        return product;
    }

    private double div(double[] arguments) {
        if (tailContainsZero(arguments))
            throw new IllegalArgumentException("Divide by zero error.");
        else if (arguments[0] == 0.0)
            return 0.0;
        else {
            double quotient = arguments[0];
            for (int i = 1; i < arguments.length; i++)
                quotient /= arguments[i];
            return quotient;
        }
    }

    private boolean tailContainsZero(double[] arguments) {
        for (int i = 1; i < arguments.length; i++) {
            if (arguments[i] == 0)
                return true;
        }
        return false;
    }
}
