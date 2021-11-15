package weather;

/**
 * WeatherReading: Each instance represents a single
 * reading of a weather station in a Stevenson Station.
 */
public interface WeatherReading {

  /**
   * Get the temperature of this reading.
   * 
   * @return the temperature
   */
  public int getTemperature();

  /**
   * Get the dew point for this reading.
   * 
   * @return the dew point
   */
  public int getDewPoint();

  /**
   * Get the wind speed for this reading.
   * 
   * @return the wind speed
   */
  public int getWindSpeed();

  /**
   * Get the total rain of this reading (in mm).
   * 
   * @return the total rain
   */
  public int getTotalRain();

  /**
   * Get the relative humidity of this weather reading.
   * 
   * @return the relative humidity
   */
  public int getRelativeHumidity();

  /**
   * Get the heat index for this weather reading.
   * 
   * @return the heat index
   */
  public int getHeatIndex();

  /**
   * Get the wind chill.
   * 
   * @return the wind chill
   */
  public int getWindChill();

}
