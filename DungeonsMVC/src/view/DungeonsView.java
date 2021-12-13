package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Map;

import dungeons.DungeonsViewInfo;

/**
 * View interface that shows the map of the dungeons, control buttons, text field,
 * and property information.
 */
public interface DungeonsView {
  /**
   * Set the listener for the menu buttons.
   *
   * @param controller controller
   */
  void setMenuListeners(DungeonsController controller);

  /**
   * Set the listener for buttons and keyboard.
   *
   * @param clicks controller
   * @param keys controller
   */
  void setActionListeners(ActionListener clicks, KeyListener keys);

  /**
   * Open the window for game setting.
   *
   * @param controller controller
   */
  void showSettingDialog(DungeonsController controller);

  /**
   * Open the window for arrow setting.
   *
   * @param controller controller
   * @param direction controller
   */
  void showShootDialog(DungeonsController controller, String direction);

  /**
   * Update the dungeon map on top of the frame based on the information from the viewMap.
   *
   * @param viewMap contains the information of each piece of the map.
   */
  void refreshMap(DungeonsViewInfo[][] viewMap);

  /**
   * Update the property grid.
   *
   * @param propertyInfo Hashmap about the property information
   */
  void refreshLeftPanel(Map<String, int[]> propertyInfo);

  /**
   * Update the text in the bottom middle textarea.
   *
   * @param text description
   */
  void refreshTextPanel(String text);

  /**
   * Reset keyboard focuse.
   */
  void resetFocus();
}
