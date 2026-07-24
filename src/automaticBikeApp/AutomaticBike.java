package automaticBikeApp;

public class AutomaticBike {
    private boolean isOn;
    private int speed;
    private int gear;

    public AutomaticBike() {
        isOn = false;
        speed = 0;
        gear = 1;
    }

    AutomaticBike(int initialSpeed) {
        isOn = true;
        speed = initialSpeed;
        gear = computeGear(initialSpeed);
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

    public int getSpeed() {
        return speed;
    }

    public int getGear() {
        return gear;
    }

    public void accelerate() {
        if (isOn) {
            speed += gear;
            updateGear();
        }
    }

    public void decelerate() {
        if (isOn) {
            speed -= gear;
            if (speed < 0) {
                speed = 0;
            }

            updateGear();
        }
    }

    private void updateGear() {
        gear = computeGear(speed);
    }

    private int computeGear(int gearSpeed) {
        if (gearSpeed <= 20) {
            return 1;
        } else if (gearSpeed <= 30) {
            return 2;
        } else {
            return gearSpeed <= 40 ? 3 : 4;
        }
    }
}
