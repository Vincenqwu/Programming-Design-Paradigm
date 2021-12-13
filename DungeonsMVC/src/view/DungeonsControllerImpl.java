package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import dungeons.Dungeons;
import dungeons.DungeonsImpl;
import dungeons.DungeonsViewInfo;

/**
 * Controller interface that connects the model and view.
 * Receive action from the view and control the model, and update the view.
 */
public class DungeonsControllerImpl implements DungeonsController, KeyListener, ActionListener {
  Dungeons model;
  DungeonsView view;
  boolean shootPressed;
  boolean firstGame;

  /**
   * Constructor for the controller that takes in a model and view.
   *
   * @param m Dungeons model
   * @param v Dungeons view
   */
  public DungeonsControllerImpl(Dungeons m, DungeonsView v) {
    this.model = m;
    this.view = v;
    this.view.setMenuListeners(this);
    shootPressed = false;
    firstGame = true;
  }

  @Override
  public void getSettingFromView(int row, int column, String wrapping, int interConnectivity,
                                 int treArrowPercentage, int monsterPercentage) {
    int intWrapping;
    if (wrapping.equals("Yes")) {
      intWrapping = 1;
    } else {
      intWrapping = 0;
    }
    try {
      model = new DungeonsImpl(row, column, intWrapping, interConnectivity, treArrowPercentage,
              monsterPercentage);
      model.generateDungeons();
      model.setPlayer();
      if (firstGame) {
        view.setActionListeners(this, this);
      }
      view.resetFocus();
      getActionResult("");
      getViewMap();
      getPropertyInfo();
      firstGame = false;

    } catch (IllegalArgumentException iae) {
      String text = " Please set the dungeon again!";
      view.refreshTextPanel(text);
      view.showSettingDialog(this);
    }
  }

  private void getViewMap() {
    DungeonsViewInfo[][] viewMap = model.getMapViewInformation();
    view.refreshMap(viewMap);
  }

  private void getPropertyInfo() {
    Map<String, int[]> propertyMap = model.getPropertyInfo();
    view.refreshLeftPanel(propertyMap);
  }

  private void getActionResult(String description) {
    String result = " <（￣︶￣）>";
    if (description.contains("next")) {
      result = " You are eaten by the Otyugh!\n"
              + " Better luck next time!\n"
              + "（┬_┬)";
    } else if (description.contains("but")) {
      result = " You just escaped from a Otyugh!\n"
              + " ╮（￣▽￣）╭";
    } else if (description.contains("darkness")) {
      result = " You shoot an arrow in the darkness\n"
              + "（︶^︶)";
    } else if (description.contains("wall")) {
      result = " You shoot an arrow to the wall\n"
              + "（︶^︶)";
    } else if (description.contains("great")) {
      result = " You hear a great howl\n"
              + " []~(￣▽￣)~*";
    } else if (description.contains("death")) {
      result = " You hear a death howl\n"
              + " []~(￣▽￣)~*";
    } else if (model.playerWin()) {
      result = " Congratulations, you successfully\n"
              + " get to the destination!\n"
              + " (^O^)/";
    }
    view.refreshTextPanel(result);
  }

  @Override
  public void getShootArrowSetting(String direction, int distance) {
    try {
      String shootResult = model.playerShootAnArrow(direction, distance);
      getActionResult(shootResult);
      getViewMap();
      getPropertyInfo();
      view.resetFocus();
    } catch (IllegalArgumentException | IllegalStateException iae) {
      view.refreshTextPanel(" You are out of arrow");
    }
    shootPressed = false;
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }


  @Override
  public void restartGame() {
    try {
      model.resetGame();
      view.resetFocus();
      view.refreshTextPanel(" Game restarted!");
      getViewMap();
      getPropertyInfo();
    } catch (IndexOutOfBoundsException e) {
      view.refreshTextPanel(" Cannot restart without \n starting the game first!");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!model.isOver()) {
      try {
        String moveResult = "";
        switch (e.getActionCommand()) {
          case "Move North":
            moveResult = model.playerMove("NORTH");
            break;
          case "Move South":
            moveResult = model.playerMove("SOUTH");
            break;
          case "Move West":
            moveResult = model.playerMove("WEST");
            break;
          case "Move East":
            moveResult = model.playerMove("EAST");
            break;
          default:
            throw new IllegalStateException("Error: unknown button");
        }
        view.resetFocus();
        getActionResult(moveResult);
        getPropertyInfo();
        getViewMap();
      } catch (IllegalArgumentException iae) {
        // skip
      }
    }

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // does nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (!model.isOver()) {
      try {
        String moveResult = "";
        switch (keyCode) {
          case KeyEvent.VK_UP:
            if (shootPressed) {
              // Shoot North
              view.showShootDialog(this, "NORTH");
            } else {
              moveResult = model.playerMove("NORTH");
            }
            break;
          case KeyEvent.VK_DOWN:
            if (shootPressed) {
              // Shoot South
              view.showShootDialog(this, "SOUTH");
            } else {
              moveResult = model.playerMove("SOUTH");
            }
            break;
          case KeyEvent.VK_LEFT:
            if (shootPressed) {
              // Shoot West
              view.showShootDialog(this, "WEST");
            } else {
              moveResult = model.playerMove("WEST");
            }

            break;
          case KeyEvent.VK_RIGHT:
            if (shootPressed) {
              // Shoot East
              view.showShootDialog(this, "EAST");
            } else {
              moveResult = model.playerMove("EAST");
            }
            break;
          case KeyEvent.VK_S:
            shootPressed = true;
            break;
          default:
            break;
        }
        getActionResult(moveResult);
        getPropertyInfo();
        getViewMap();
      } catch (IllegalArgumentException iae) {
        // Skip
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_S) {
      shootPressed = false;
    }
    if (!model.isOver()) {
      if (e.getKeyCode() == KeyEvent.VK_T) {
        model.playerPickUpTreasures();
      }
      else if (e.getKeyCode() == KeyEvent.VK_A) {
        model.playerPickUpArrows();
      }
      getPropertyInfo();
    }

  }
}
