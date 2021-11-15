package transmission;

import java.util.Objects;

/**
 * Transmission represents as speed and gear,
 * and state.
 */
public final class AutomaticTransmission implements Transmission {

  private int speed = 0;
  private int gear = 0;
  private String state;

  private int[] thresholds = new int[5];

  /**
   * Constructs a automatic transmission with
   * speed thresholds as input.
   *
   * @param t1 threshold 1,
   * @param t2 threshold 2,
   * @param t3 threshold 3,
   * @param t4 threshold 4,
   * @param t5 threshold 5.
   */
  public AutomaticTransmission(int t1, int t2, int t3, int t4, int t5) {
    thresholds[0] = t1;
    thresholds[1] = t2;
    thresholds[2] = t3;
    thresholds[3] = t4;
    thresholds[4] = t5;
    if (t1 <= 0 || t2 <= 0 || t3 <= 0 || t4 <= 0 || t5 <= 0) {
      throw new IllegalArgumentException(
              "The speed threshold must be a positive number!");
    }

    for (int i = 1; i < thresholds.length; i++) {
      if (thresholds[i] <= thresholds[i - 1]) {
        throw new IllegalArgumentException(
                "The threshold value should be increasing from left to right!");
      }
    }

    this.state = this.toString();
  }

  @Override
  public void increaseSpeed() {
    this.speed += 1;
    for (int i = thresholds.length - 2; i >= 0; i--) {
      if (this.speed >= thresholds[i] && this.speed < thresholds[i + 1]) {
        this.gear = i + 2;
        break;
      }
    }
    if (this.speed < thresholds[0]) {
      this.gear = 1;
    } else if (this.speed >= thresholds[4]) {
      this.gear = 6;
    }
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    this.speed -= 1;
    if (this.speed < 0) {
      throw new IllegalStateException("The speed cannot be lower than 0");
    }
    else if (this.speed == 0) {
      this.gear = 0;
    }
    else {
      for (int i = thresholds.length - 2; i >= 0; i--) {
        if (this.speed >= thresholds[i] && this.speed < thresholds[i + 1]) {
          this.gear = i + 2;
          break;
        }
      }
      if (this.speed < thresholds[0]) {
        this.gear = 1;
      }
      if (this.speed >= thresholds[4]) {
        this.gear = 6;
      }
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getGear() {
    return this.gear;
  }

  @Override
  public String toString() {
    this.state = "Transmission (speed = "
            + this.getSpeed() + ", gear = " + this.getGear() + ")";
    return this.state;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null ) {
      return false;
    }

    if (obj.getClass() != this.getClass()) {
      return false;
    }

    final Transmission other = (Transmission) obj;

    return Double.compare(speed, other.getSpeed()) == 0
            && Double.compare(gear, other.getGear()) == 0;

  }

  @Override
  public int hashCode() {
    return Objects.hash(speed, gear);
  }
}

