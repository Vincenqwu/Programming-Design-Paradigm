package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import transmission.Transmission;
import transmission.AutomaticTransmission;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the Transmission.
 */
public class TransmissionTest {

  private Transmission vehicle;

  @Before
  public void setUp() {
    vehicle = vTransmission(10, 20, 30, 40, 50);
  }


  protected Transmission vTransmission(int t1, int t2, int t3, int t4, int t5) {
    return new AutomaticTransmission(t1, t2, t3, t4, t5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholds1() {
    vTransmission(-1, 40, 50, 60, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholds2() {
    vTransmission(20, 18, 21, 40, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThresholds3() {
    vTransmission(210, 90, 50, 20, 1);
  }


  @Test(expected = IllegalStateException.class)
  public void testDecreasingSpeed() {
    vehicle.decreaseSpeed();
  }

  @Test
  public void testIncreaseSpeed() {
    for (int i = 0; i < 20; i++) {
      vehicle.increaseSpeed();
    }
    assertEquals("Increase speed from 0 to 20", 20, vehicle.getSpeed());
  }

  @Test
  public void testDecreaseSpeed2() {
    for (int i = 0; i < 20; i++) {
      vehicle.increaseSpeed();
    }
    for (int i = 0; i < 10; i++) {
      vehicle.decreaseSpeed();
    }
    assertEquals("Decrease speed from 20 to 10", 10, vehicle.getSpeed());
  }

  @Test
  public void testGetGear() {
    for (int i = 0; i < 20; i++) {
      vehicle.increaseSpeed();
    }
    for (int i = 0; i < 10; i++) {
      vehicle.decreaseSpeed();
    }
    System.out.println(vehicle.getSpeed());
    System.out.println(vehicle.getGear());
    assertEquals("The current speed is 10, gear should be 2", 2, vehicle.getGear());
  }

  @Test
  public void testGetGear1() {
    for (int i = 0; i < 3; i++) {
      vehicle.increaseSpeed();
    }
    System.out.println(vehicle.getSpeed());
    System.out.println(vehicle.getGear());
    assertEquals("The current speed is 3, gear should be 1", 1, vehicle.getGear());
  }

  @Test
  public void testGetGear2() {
    for (int i = 0; i < 36; i++) {
      vehicle.increaseSpeed();
    }
    System.out.println(vehicle.getSpeed());
    System.out.println(vehicle.getGear());
    assertEquals("The current speed is 36, gear should be 4", 4, vehicle.getGear());
  }

  @Test
  public void testGetGear3() {
    for (int i = 0; i < 50; i++) {
      vehicle.increaseSpeed();
    }
    System.out.println(vehicle.getSpeed());
    System.out.println(vehicle.getGear());
    assertEquals("The current speed is 50, gear should be 6", 6, vehicle.getGear());
  }

  @Test
  public void testEquals() {
    Transmission other = new AutomaticTransmission(10, 20, 30, 40, 50);
    assertTrue(vehicle.equals(other));
  }
}