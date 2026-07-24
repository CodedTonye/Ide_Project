package linearRegression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinearRegressionTest {
    private LinearRegression model;

    public LinearRegressionTest() {
    }

    @BeforeEach
    public void setUp() {
        model = new LinearRegression();
    }

    @Test
    public void fitsAPerfectLineThroughTheOriginTest() {
        double[] x = new double[]{(double)1.0F, (double)2.0F, (double)3.0F, (double)4.0F, (double)5.0F};
        double[] y = new double[]{(double)2.0F, (double)4.0F, (double)6.0F, (double)8.0F, (double)10.0F};
        model.fit(x, y);
        assertEquals(2.0F, model.getSlope(), 1.0E-4);
        assertEquals(0.0F, model.getIntercept(), 1.0E-4);
    }

    @Test
    public void fitsALineWithAnInterceptTest() {
        double[] x = new double[]{(double)1.0F, (double)2.0F, (double)3.0F, (double)4.0F};
        double[] y = new double[]{(double)3.0F, (double)5.0F, (double)7.0F, (double)9.0F};
        model.fit(x, y);
        assertEquals(2.0F, model.getSlope(), 1.0E-4);
        assertEquals(1.0F, model.getIntercept(), 1.0E-4);
    }

    @Test
    public void predictsYForANewXTest() {
        double[] x = new double[]{(double)1.0F, (double)2.0F, (double)3.0F};
        double[] y = new double[]{(double)1.0F, (double)2.0F, (double)3.0F};
        model.fit(x, y);
        assertEquals(4.0F, model.predict((double)4.0F), 1.0E-4);
    }

    @Test
    public void throwsExceptionWhenArraysAreEmptyTest() {
        double[] x = new double[0];
        double[] y = new double[0];
        assertThrows(IllegalArgumentException.class, () -> model.fit(x, y));
    }
}
