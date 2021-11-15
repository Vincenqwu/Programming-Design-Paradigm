package battle;

/**
 * Driver function for running the battle class. It will also
 * print out battle information and do the rematch.
 */
public class Driver {

  /**
   * Main function for running the battle class. It will also
   * print out battle information and do the rematch.
   * @param s No use.
   */
  public static void main(String[] s) {
    int roundLimites = 30;
    System.out.println("Battle game Started....." +
            "\nTwo players will come to the arena with their basic abilities. "
            + " Players then equip themselves with gears and weapon and take turns "
            + "to attack one another until one of the players is dead. "
            + "If after 30 rounds and nobody wins, the battle is a draw."
            + " Player with the higher charisma will start first. "
            + " After the first battle, a rematch is available.");
    Battle battle = new RunBattle();

    System.out.println("\nPlayers come into the battle arena.....\n");
    System.out.println(battle.setPlayer1());
    System.out.println(battle.setPlayer2());

    System.out.println("\nPlayers start to equip gears and weapons.....\n");
    System.out.println(battle.equipPlayer1());
    System.out.println("\n" + battle.equipPlayer2());

    System.out.println("\nDecide who go first based on higher charisma.....\n");
    battle.whoFirst();

    System.out.println("Start Battle.....");

    System.out.println("\n" + battle.getResult());
    while (!battle.isOver()) {
      if (roundLimites > 0) {
        System.out.println("\nNext Player: " + battle.nextPlayer());
        System.out.println(battle.doAttack());
        roundLimites -= 1;
      } else {
        break;
      }
    }
    System.out.println("\n" + battle.getResult());


    System.out.println("\nRematch.....\n");
    System.out.println(battle.reMatch());
    System.out.println("\nDecide who go first based on higher charisma.....\n");
    battle.whoFirst();

    System.out.println("Start Battle.....");
    roundLimites = 30;
    while (!battle.isOver()) {
      if (roundLimites > 0) {
        System.out.println("\nNext Player: " + battle.nextPlayer());
        System.out.println(battle.doAttack());
        roundLimites -= 1;
      } else {
        break;
      }
    }
    System.out.println("\n" + battle.getResult());
  }
}
