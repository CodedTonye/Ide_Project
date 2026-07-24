package linearRegression;

public class LinearRegression {
    private double slope;
    private double intercept;

    public LinearRegression() {
    }

    public void fit(double[] x, double[] y) {
        if (x != null && y != null && x.length == y.length && x.length != 0) {
            int numberOfData = x.length;
            double sumX = (double)0.0F;
            double sumY = (double)0.0F;
            double sumXY = (double)0.0F;
            double sumXX = (double)0.0F;

            for(int index = 0; index < numberOfData; ++index) {
                sumX += x[index];
                sumY += y[index];
                sumXY += x[index] * y[index];
                sumXX += x[index] * x[index];
            }

            slope = ((double)numberOfData * sumXY - sumX * sumY) / ((double)numberOfData * sumXX - sumX * sumX);
            intercept = (sumY - slope * sumX) / (double)numberOfData;
        } else {
            throw new IllegalArgumentException("x and y must not be empty and they must be equal length");
        }
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double predict(double x) {
        return slope * x + intercept;
    }
}
