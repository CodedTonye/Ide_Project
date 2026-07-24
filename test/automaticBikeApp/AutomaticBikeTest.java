package automaticBikeApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticBikeTest {
    private AutomaticBike bike;

    public AutomaticBikeTest() {
    }

    @BeforeEach
    public void setUp() {
        bike = new AutomaticBike();
    }

    @Test
    public void givenBike_whenTurnedOn_thenBikeIsOnTest() {
        bike.turnOn();
        assertTrue(bike.isOn());
    }

    @Test
    public void givenBikeIsOn_whenTurnedOff_thenBikeIsOffTest() {
        bike.turnOn();
        bike.turnOff();
        assertFalse(bike.isOn());
    }

    @Test
    public void givenBikeOnGear1AtSpeed15_whenAccelerate_thenSpeedIs16Test() {
        bike = new AutomaticBike(15);
        bike.accelerate();
        assertEquals(16, bike.getSpeed());
        assertEquals(1, bike.getGear());
    }

    @Test
    public void givenBikeOnGear2AtSpeed24_whenAccelerate_thenSpeedIs26Test() {
        bike = new AutomaticBike(24);
        bike.accelerate();
        assertEquals(26, bike.getSpeed());
        assertEquals(2, bike.getGear());
    }

    @Test
    public void givenBikeOnGear3AtSpeed35_whenAccelerate_thenSpeedIs38Test() {
        bike = new AutomaticBike(35);
        bike.accelerate();
        assertEquals(38, bike.getSpeed());
        assertEquals(3, bike.getGear());
    }

    @Test
    public void givenBikeOnGear4AtSpeed44_whenAccelerate_thenSpeedIs48Test() {
        bike = new AutomaticBike(44);
        bike.accelerate();
        assertEquals(48, bike.getSpeed());
        assertEquals(4, bike.getGear());
    }

    @Test
    public void givenBikeOnGear1AtSpeed15_whenDecelerate_thenSpeedIs14Test() {
        bike = new AutomaticBike(15);
        bike.decelerate();
        assertEquals(14, bike.getSpeed());
        assertEquals(1, bike.getGear());
    }

    @Test
    public void givenBikeOnGear2AtSpeed24_whenDecelerate_thenSpeedIs22Test() {
        bike = new AutomaticBike(24);
        bike.decelerate();
        assertEquals(22, bike.getSpeed());
        assertEquals(2, bike.getGear());
    }

    @Test
    public void givenBikeOnGear3AtSpeed35_whenDecelerate_thenSpeedIs32Test() {
        bike = new AutomaticBike(35);
        bike.decelerate();
        assertEquals(32, bike.getSpeed());
        assertEquals(3, bike.getGear());
    }

    @Test
    public void givenBikeOnGear4AtSpeed44_whenDecelerate_thenSpeedIs40Test() {
        bike = new AutomaticBike(44);
        bike.decelerate();
        assertEquals(40, bike.getSpeed());
        assertEquals(3, bike.getGear());
    }

    @Test
    public void givenBikeOnGear1AtSpeed20_whenAccelerate_thenGearShiftsTo2Test() {
        bike = new AutomaticBike(20);
        bike.accelerate();
        assertEquals(21, bike.getSpeed());
        assertEquals(2, bike.getGear());
    }

    @Test
    public void givenBikeOnGear2AtSpeed29_whenAccelerate_thenGearShiftsTo3Test() {
        bike = new AutomaticBike(29);
        bike.accelerate();
        assertEquals(31, bike.getSpeed());
        assertEquals(3, bike.getGear());
    }

    @Test
    public void givenBikeOnGear3AtSpeed38_whenAccelerate_thenGearShiftsTo4Test() {
        bike = new AutomaticBike(38);
        bike.accelerate();
        assertEquals(41, bike.getSpeed());
        assertEquals(4, bike.getGear());
    }

    @Test
    public void givenBikeOnGear2AtSpeed22_whenDecelerate_thenGearShiftsTo1Test() {
        bike = new AutomaticBike(22);
        bike.decelerate();
        assertEquals(20, bike.getSpeed());
        assertEquals(1, bike.getGear());
    }

    @Test
    public void givenBikeOnGear3AtSpeed33_whenDecelerate_thenGearShiftsTo2Test() {
        bike = new AutomaticBike(33);
        bike.decelerate();
        assertEquals(30, bike.getSpeed());
        assertEquals(2, bike.getGear());
    }

    @Test
    public void givenBikeOnGear4AtSpeed44_whenDecelerate_thenGearShiftsTo3Test() {
        bike = new AutomaticBike(44);
        bike.decelerate();
        assertEquals(40, bike.getSpeed());
        assertEquals(3, bike.getGear());
    }

    @Test
    public void givenBikeOnGear1AtSpeed0_whenDecelerate_thenSpeedRemainsAt0Test() {
        bike.turnOn();
        bike.decelerate();
        assertEquals(0, bike.getSpeed());
    }

    @Test
    public void givenBikeIsOff_whenAccelerate_thenSpeedDoesNotChangeTest() {
        bike.accelerate();
        assertEquals(0, bike.getSpeed());
    }

    @Test
    public void givenBikeIsOff_whenDecelerate_thenSpeedDoesNotChangeTest() {
        bike.decelerate();
        assertEquals(0, bike.getSpeed());
    }

    @Test
    public void givenNewBike_thenDefaultStateIsOffSpeedZeroGearOneTest() {
        assertFalse(bike.isOn());
        assertEquals(0, bike.getSpeed());
        assertEquals(1, bike.getGear());
    }
}
