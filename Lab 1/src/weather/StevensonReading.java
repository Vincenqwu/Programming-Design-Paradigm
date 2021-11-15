package weather;

import java.util.Arrays;

/**
 * WeatherReading represents as temperatures,
 * dew point, wind speed and total rain.
 */
public final class StevensonReading implements WeatherReading {
  private final double temperature;
  private final double dewPoint;
  private final double winSpeed;
  private final double totalRain;
  private double relativeHumidity;

  /**
   * Constructs a weather reading.
   *
   * @param temperature in Celsius
   * @param dewPoint    not greater than temp
   * @param windSpeed   non negative in miles per hours
   * @param totalRain   non negative received in last 24 hours in millimeters.
   */
  public StevensonReading(double temperature, double dewPoint, double windSpeed, double totalRain) {
    if (dewPoint > temperature) {
      throw new IllegalArgumentException("Dew point temperature "
              + "cannot be greater than the air temperature");
    }
    if (windSpeed < 0) {
      throw new IllegalArgumentException("Wind speed cannot be negative");
    }
    if (totalRain < 0) {
      throw new IllegalArgumentException(("Total rain cannot be negative"));
    }
    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.winSpeed = windSpeed;
    this.totalRain = totalRain;
    this.relativeHumidity = 100 - 5 * this.temperature + 5 * this.dewPoint;
  }

  @Override
  public int getTemperature() {
    return (int) Math.round(this.temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(this.dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(this.winSpeed);
  }

  @Override
  public int getTotalRain() {
    return (int) Math.round(this.totalRain);
  }

  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(this.relativeHumidity);
  }

  @Override
  public int getHeatIndex() {
    double c1 = -8.78469475556;
    double c2 = 1.61139411;
    double c3 = 2.33854883889;
    double c4 = -0.14611605;
    double c5 = -0.012308094;
    double c6 = -0.0164248277778;
    double c7 = 0.002211732;
    double c8 = 0.00072546;
    double c9 = -0.000003582;
    double t = this.temperature;
    double r = this.relativeHumidity;

    return (int) Math.round(c1 + c2 * t + c3 * r + c4 * t * r + c5 * t * t
            + c6 * r * r + c7 * t * t * r + c8 * t * r * r + c9 * t * t * r * r);
  }

  @Override
  public int getWindChill() {
    double v = this.winSpeed;
    double t = this.temperature * 1.8 + 32;
    return (int) Math.round(35.74 + 0.6215 * t - 35.75 * Math.pow(v, 0.16)
            + 0.4275 * t * Math.pow(v, 0.16));
  }

  @Override
  public String toString() {
    int t = this.getTemperature();
    int v = this.getWindSpeed();
    int d = this.getDewPoint();
    int rain = this.getTotalRain();
    return "Reading: T = " + t + ", D = " + d + ", v = " + v + ", rain = " + rain;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { // backward compatibility with default equals
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof WeatherReading)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    WeatherReading that = (WeatherReading) o;

    return this.getTemperature() == that.getTemperature() && this.getDewPoint()
            == that.getDewPoint() && this.getWindSpeed() == that.getWindSpeed()
            && this.getTotalRain() == that.getTotalRain();
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Integer[]{this.getTotalRain(),
            this.getDewPoint(), this.getWindSpeed(), this.getTotalRain()});
  }
}
