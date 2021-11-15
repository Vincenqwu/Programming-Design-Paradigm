package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import weather.WeatherReading;
import weather.StevensonReading;

import org.junit.Before;
import org.junit.Test;


/**
 * Class for testing the WeatherReading.
 */
public class WeatherReadingTest {

  private WeatherReading weather;

  @Before
  public void setUp() {
    weather = wreading(20, 15, 50, 500);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * WeatherReading object.
   *
   * @param temperature in Celsius
   * @param dewPoint    not greater than temp
   * @param windSpeed   non negative in miles per hours
   * @param totalRain   non negative received in last 24 hours in millimeters.
   * @return a new instance of a WeatherReading object
   */
  protected WeatherReading wreading(int temperature, int dewPoint, int windSpeed, int totalRain) {
    return new StevensonReading(temperature, dewPoint, windSpeed, totalRain);
  }

  @Test
  public void testGetTemperature() {
    assertEquals(20, weather.getTemperature());
    assertNotEquals(0, weather.getTemperature());
  }

  @Test
  public void testGetDewPoint() {
    assertEquals(15, weather.getDewPoint());
    assertNotEquals(10, weather.getDewPoint());
  }

  @Test
  public void testGetWindSpeed() {
    assertEquals(50, weather.getWindSpeed());
    assertNotEquals(20, weather.getWindSpeed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDewPoint() {
    wreading(10, 20, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWindSpeed() {
    wreading(30, 20, -10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidTotalRain() {
    wreading(30, 20, 10, -20);
  }

  @Test
  public void testGetRelativeHumidity() {
    assertEquals(75, weather.getRelativeHumidity());
  }

  @Test
  public void testGetHeatIndex() {
    assertEquals(22, weather.getHeatIndex());
  }

  @Test
  public void testGetWindChill() {
    assertEquals(-3, weather.getWindChill());
  }

  @Test
  public void testEquals() {
    assertTrue(weather.equals(weather));
    assertFalse(wreading(20, 10, 45, 200).equals(wreading(22, 10, 45, 200)));
    assertFalse(wreading(20, 10, 45, 200).equals(wreading(20, 11, 45, 200)));
    assertFalse(wreading(20, 10, 45, 200).equals(wreading(20, 10, 40, 200)));
    assertFalse(wreading(20, 10, 45, 200).equals(wreading(20, 10, 45, 100)));
  }

  @Test
  public void testHashCode() {
    assertEquals(wreading(20, 10, 45, 200).hashCode(), wreading(20, 10, 45, 200).hashCode());
  }
}