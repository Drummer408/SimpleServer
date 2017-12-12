package main;

public class Math {
    public double processCalculation(String mathFunction, double[] arguments) throws IllegalArgumentException {
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
                throw new IllegalArgumentException("Invalid math function entered.");
        }

        return result;
    }

    private double add(double[] arguments) {
        double sum = arguments[0];
        for (double arg : arguments)
            sum += arg;
        return sum;
    }

    private double sub(double[] arguments) {
        double difference = arguments[0];
        for (double arg : arguments)
            difference -= arg;
        return difference;
    }

    private double mult(double[] arguments) {
        double product = arguments[0];
        for (double arg : arguments)
            product *= arg;
        return product;
    }

    private double div(double[] arguments) {
        if (tailContainsZero(arguments))
            throw new IllegalArgumentException("Divide by zero error.");
        else if (arguments[0] == 0.0)
            return 0.0;
        else {
            double quotient = arguments[0];
            for (double arg : arguments)
                quotient /= arg;
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
