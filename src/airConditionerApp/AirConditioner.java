package airConditionerApp;

public class AirConditioner {
    private boolean isOn;
    private int temperature = 16;

    public AirConditioner() {
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void increaseTemperature() {
        if (this.temperature < 30) {
            ++this.temperature;
        }

    }

    public void decreaseTemperature() {
        if (this.temperature > 16) {
            --this.temperature;
        }

    }

    public int getTemperature() {
        return this.temperature;
    }
}
