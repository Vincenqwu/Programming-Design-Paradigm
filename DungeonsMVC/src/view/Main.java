package view;

import dungeons.Dungeons;
import dungeons.DungeonsImpl;

/**
 * Driver class for the MVC model of the dungeons game.
 */
public class Main {
  /**
   * Main class for the MVC model.
   *
   * @param args no use
   */
  public static void main(String[] args) {

    Dungeons model = new DungeonsImpl();
    DungeonsView view = new DungeonsViewImpl();
    new DungeonsControllerImpl(model, view);

  }
}
