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
        this.myAC = new AirConditioner();
    }

    @Test
    public void turnOnAC_acTurnedOnTest() {
        this.myAC.turnOn();

        assert this.myAC.isOn();

    }

    @Test
    public void turnOffAC_acTurnedOffTest() {
        this.myAC.turnOn();
        this.myAC.turnOff();
        assertFalse(this.myAC.isOn());
    }

    @Test
    public void increaseTemperatureAt16_TemperatureIncreasedTo17Test() {
        this.myAC.turnOn();
        this.myAC.increaseTemperature();
        assertEquals(17, this.myAC.getTemperature());
    }

    @Test
    public void decreaseTemperatureAt17_TemperatureDecreasedTo16Test() {
        this.myAC.turnOn();
        this.myAC.increaseTemperature();
        this.myAC.decreaseTemperature();
        assertEquals(16, this.myAC.getTemperature());
    }

    @Test
    public void increaseTemperatureBeyond30_TemperatureRemain30Test() {
        this.myAC.turnOn();

        for(int count = 16; count <= 30; ++count) {
            this.myAC.increaseTemperature();
        }

        assertEquals(30, this.myAC.getTemperature());
    }

    @Test
    public void decreaseTemperatureBelow16_TemperatureRemain16Test() {
        this.myAC.turnOn();
        this.myAC.decreaseTemperature();
        assertEquals(16, this.myAC.getTemperature());
    }
}
