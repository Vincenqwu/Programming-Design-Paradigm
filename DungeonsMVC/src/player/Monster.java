package player;

/**
 * This class represents the monster with two bloods;
 * When the monster is hit by an arrow, it lose one blood;
 * After two arrow attacks, the monster is dead.
 */
public class Monster {
  private int life;

  /**
   * Constructor for the monster class that initialize the blood as 2.
   */
  public Monster() {
    life = 2;
  }

  /**
   * Return the current blood of the monster.
   *
   * @return life value
   */
  public int getLife() {
    return life;
  }

  /**
   * When the monster is hit by an arrow, if not dead, drop 1 blood.
   */
  public void getHit() {
    if (!isDead()) {
      life -= 1;
    }
  }

  /**
   * Check whether the monster is dead.
   *
   * @return boolean
   */
  public boolean isDead() {
    return life == 0;
  }
}
