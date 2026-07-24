package airConditionerApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AirConditionerTest {
    private AirConditioner myAC;

    public AirConditionerTest() {
    }

    @BeforeEach
    public void setUp() {
        myAC = new AirConditioner();
    }

    @Test
    public void turnOnAC_acTurnedOnTest() {
        myAC.turnOn();

        assert myAC.isOn();

    }

    @Test
    public void turnOffAC_acTurnedOffTest() {
        myAC.turnOn();
        myAC.turnOff();
        assertFalse(myAC.isOn());
    }

    @Test
    public void increaseTemperatureAt16_TemperatureIncreasedTo17Test() {
        myAC.turnOn();
        myAC.increaseTemperature();
        assertEquals(17, myAC.getTemperature());
    }

    @Test
    public void decreaseTemperatureAt17_TemperatureDecreasedTo16Test() {
        myAC.turnOn();
        myAC.increaseTemperature();
        myAC.decreaseTemperature();
        assertEquals(16, myAC.getTemperature());
    }

    @Test
    public void increaseTemperatureBeyond30_TemperatureRemain30Test() {
        myAC.turnOn();

        for(int count = 16; count <= 30; ++count) {
            myAC.increaseTemperature();
        }

        assertEquals(30, myAC.getTemperature());
    }

    @Test
    public void decreaseTemperatureBelow16_TemperatureRemain16Test() {
        myAC.turnOn();
        myAC.decreaseTemperature();
        assertEquals(16, myAC.getTemperature());
    }
}
