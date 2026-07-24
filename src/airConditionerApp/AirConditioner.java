package airConditionerApp;

public class AirConditioner {
    private boolean isOn;
    private int temperature = 16;

    public AirConditioner() {
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void increaseTemperature() {
        if (temperature < 30) {
            ++temperature;
        }

    }

    public void decreaseTemperature() {
        if (temperature > 16) {
            --temperature;
        }

    }

    public int getTemperature() {
        return temperature;
    }
}
