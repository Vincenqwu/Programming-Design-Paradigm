package battle;

/**
 * The battle Interface.
 * Set up two players with four skills, equips gears and weapon,
 * and get attack order, and make attack and choice of rematch.
 * Also gives description of each player when they get in the arena and make attack.
 */
public interface Battle {
  /**
   * Set player 1 with four abilities.
   *
   * @return Description
   */
  String setPlayer1();

  /**
   * Set player 1 with four abilities.
   * Only for testing.
   *
   * @param randomLst dice values
   * @return Description
   */
  String setPlayer1(int... randomLst);

  /**
   * Set player 2 with four abilities.
   *
   * @return Description
   */
  String setPlayer2();

  /**
   * Equip player 1 with gears and weapons.
   *
   * @return Description
   */
  String equipPlayer1();

  /**
   * Equip player 2 with gears and weapons.
   *
   * @return Description
   */
  String equipPlayer2();

  /**
   * Decide who go first based on higher charisma.
   */
  void whoFirst();

  /**
   * Get the next player.
   *
   * @return Player order
   */
  int nextPlayer();

  /**
   * Let player attack each other.
   *
   * @return Attack result
   */
  String doAttack();

  /**
   * Check if one player is dead.
   *
   * @return Boolean
   */
  boolean isOver();

  /**
   * Return the winner.
   *
   * @return String
   */
  String getResult();

  /**
   * Get the current result of the battle.
   *
   * @return Description
   */
  String getWinner();

  /**
   * Start over and reset both players.
   */
  String reMatch();
}
