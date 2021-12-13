package view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import dungeons.DungeonsViewInfo;

/**
 * View interface that shows the map of the dungeons, control buttons, text field,
 * and property information.
 */
public class DungeonsViewImpl extends JFrame implements DungeonsView {

  private JMenuItem startGame;
  private JMenuItem quitGame;
  private JMenuItem restart;
  private JMenuItem instruction;
  private JPanel propertyPanel;
  private JTextArea textLabel;
  private JLabel[][] propertyList;
  private JPanel mapPanel;
  private JButton northButton;
  private JButton southButton;
  private JButton westButton;
  private JButton eastButton;
  private String textLine;

  /**
   * Constructor for the view class.
   */
  public DungeonsViewImpl() {
    super();
    //setSize(700, 850);
    setPreferredSize(new Dimension(650, 800));
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel rootPanel = new JPanel();
    BoxLayout rootLayout = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
    rootPanel.setLayout(rootLayout);

    // Map
    mapPanel = new JPanel();
    mapPanel.setLayout(new BorderLayout());
    mapPanel.setBackground(Color.BLACK);

    JScrollPane scrollPane = new JScrollPane(mapPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(650, 600));
    rootPanel.add(scrollPane, BorderLayout.NORTH);

    // Bottom Panel
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
    bottomPanel.setPreferredSize(new Dimension(650, 200));

    JPanel leftBottomPanel = new JPanel();
    leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.X_AXIS));
    leftBottomPanel.setPreferredSize(new Dimension(300, 200));

    // Add property panel and text panel to the left bottom panel
    setPropertyGrid();

    //textLabel = new JLabel();
    textLine = " -------------------------------------------------\n";
    textLabel = new JTextArea();
    textLabel.setOpaque(false);
    textLabel.setEditable(false);
    textLabel.setPreferredSize(new Dimension(250, 250));
    textLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    textLabel.setText(textLine + " Select <New Game> in the menu!");

    leftBottomPanel.add(propertyPanel);
    leftBottomPanel.add(textLabel);

    // Add move button to the right bottom panel
    northButton = new JButton("N");
    northButton.setActionCommand("Move North");
    northButton.setFont(new Font("Arial", Font.PLAIN, 15));
    northButton.setBounds(60, 30, 45, 45);
    southButton = new JButton("S");
    southButton.setActionCommand("Move South");
    southButton.setFont(new Font("Arial", Font.PLAIN, 15));
    southButton.setBounds(60, 110, 45, 45);
    westButton = new JButton("W");
    westButton.setActionCommand("Move West");
    westButton.setFont(new Font("Arial", Font.PLAIN, 15));
    westButton.setBounds(20, 70, 45, 45);
    eastButton = new JButton("E");
    eastButton.setActionCommand("Move East");
    eastButton.setFont(new Font("Arial", Font.PLAIN, 15));
    eastButton.setBounds(100, 70, 45, 45);

    JPanel rightBottomPanel = new JPanel();
    rightBottomPanel.setPreferredSize(new Dimension(180, 250));
    rightBottomPanel.setLayout(null);
    rightBottomPanel.add(northButton);
    rightBottomPanel.add(southButton);
    rightBottomPanel.add(westButton);
    rightBottomPanel.add(eastButton);

    bottomPanel.add(leftBottomPanel);
    bottomPanel.add(rightBottomPanel);

    rootPanel.add(bottomPanel, BorderLayout.SOUTH);
    add(rootPanel);

    // Menu
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    startGame = new JMenuItem("New Game");
    quitGame = new JMenuItem("Quit Game");
    quitGame.setActionCommand("Quit");
    restart = new JMenuItem("Restart");
    instruction = new JMenuItem(("Instruction"));
    menu.add(startGame);
    menu.add(quitGame);
    menu.add(restart);
    menu.add(instruction);
    menuBar.add(menu);

    pack();
    setJMenuBar(menuBar);
    setVisible(true);
  }

  private void setPropertyGrid() {
    InputStream diamondPath = getClass().getResourceAsStream("/resources/diamond.png");
    InputStream rubyPath = getClass().getResourceAsStream("/resources/ruby.png");
    InputStream emeraldPath = getClass().getResourceAsStream("/resources/emerald.png");
    InputStream arrowPath = getClass().getResourceAsStream("/resources/arrow-black.png");
    try {
      propertyPanel = new JPanel();
      propertyPanel.setLayout(new GridLayout(5, 3));
      propertyPanel.setPreferredSize(new Dimension(250, 250));
      propertyList = new JLabel[5][3];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 3; j++) {
          JLabel label = new JLabel();
          label.setBorder(BorderFactory.createLineBorder(Color.lightGray));
          label.setHorizontalAlignment(JLabel.CENTER);
          label.setText("");
          label.setFont(new Font("Arial", Font.PLAIN, 15));
          propertyPanel.add(label);
          propertyList[i][j] = label;
        }
      }
      propertyList[0][0].setText("Items");
      propertyList[0][1].setText("This Cave");
      propertyList[0][2].setText("Your Bag");
      propertyList[1][0].setIcon(new ImageIcon(ImageIO.read(diamondPath)));
      propertyList[2][0].setIcon(new ImageIcon(ImageIO.read(rubyPath)));
      propertyList[3][0].setIcon(new ImageIcon(ImageIO.read(emeraldPath)));
      propertyList[4][0].setIcon(new ImageIcon(ImageIO.read(arrowPath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void setMenuListeners(DungeonsController controller) {
    startGame.addActionListener(c -> showSettingDialog(controller));
    quitGame.addActionListener(c -> controller.quitGame());
    restart.addActionListener(c -> controller.restartGame());
    instruction.addActionListener(c -> new InstructionDialog(this));
  }

  @Override
  public void setActionListeners(ActionListener clicks, KeyListener keys) {
    addKeyListener(keys);
    northButton.addActionListener(clicks);
    southButton.addActionListener(clicks);
    westButton.addActionListener(clicks);
    eastButton.addActionListener(clicks);
  }

  @Override
  public void showSettingDialog(DungeonsController controller) {
    new SettingDialog(this, controller);
  }

  @Override
  public void showShootDialog(DungeonsController controller, String direction) {
    new ShootDialog(this, controller, direction);
  }

  @Override
  public void refreshMap(DungeonsViewInfo[][] viewMap) {
    int rows = viewMap.length;
    int columns = viewMap[0].length;

    InputStream otyughPath;
    InputStream playerPath;
    InputStream playerWinPath;
    InputStream playerDeadPath;
    InputStream weakSmellPath;
    InputStream strongSmellPath;
    mapPanel.removeAll();
    mapPanel.revalidate();
    mapPanel.repaint();

    mapPanel.setLayout(new GridLayout(rows, columns));
    try {
      for (int i = 0; i < (rows * columns); i++) {
        otyughPath = getClass().getResourceAsStream("/resources/otyugh.png");
        playerPath = getClass().getResourceAsStream("/resources/Mario_walk.png");
        playerWinPath = getClass().getResourceAsStream("/resources/Mario_win.png");
        playerDeadPath = getClass().getResourceAsStream("/resources/Mario_lose.png");
        weakSmellPath = getClass().getResourceAsStream("/resources/stench01.png");
        strongSmellPath = getClass().getResourceAsStream("/resources/stench02.png");
        int curCol = i;
        int curRow = 0;
        while (curCol - columns >= 0) {
          curCol -= columns;
          curRow += 1;
        }
        DungeonsViewInfo thisViewInfo = viewMap[curRow][curCol];
        boolean visited = thisViewInfo.getVisited();
        if (visited) {
          String graphType = thisViewInfo.getGraphType();
          String playerCondition = thisViewInfo.getPlayerCondition();
          boolean currLocation = thisViewInfo.getCurrLocation();
          boolean hasMonster = thisViewInfo.getHasMonster();
          int smellLevel = thisViewInfo.getSmellLevel();

          InputStream locationPath = getClass()
                  .getResourceAsStream("/resources/" + graphType + ".png");
          BufferedImage bufferLocationImage = ImageIO.read(locationPath);

          JButton button = new JButton();
          button.addActionListener(c -> {
            resetFocus();
          });

          // Set player image
          button.setBorder(BorderFactory.createEmptyBorder());
          if (currLocation) {
            JLabel playerLabel;
            if (playerCondition.equals("Win")) {
              playerLabel = new JLabel(new ImageIcon(ImageIO.read(playerWinPath)));
            } else if (playerCondition.equals("Dead")) {
              playerLabel = new JLabel(new ImageIcon(ImageIO.read(playerDeadPath)));
            } else {
              playerLabel = new JLabel(new ImageIcon(ImageIO.read(playerPath)));
            }
            playerLabel.setHorizontalAlignment(JLabel.CENTER);
            button.add(playerLabel);
          }

          // If smells, add stench image
          if (smellLevel > 0) {
            JLabel smellLabel = new JLabel();
            if (smellLevel == 1) {
              BufferedImage bufferWeak = ImageIO.read(weakSmellPath);
              Image weak = bufferWeak.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
              smellLabel.setIcon(new ImageIcon(weak));
            } else {
              BufferedImage bufferWeak = ImageIO.read(strongSmellPath);
              Image weak = bufferWeak.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
              smellLabel.setIcon(new ImageIcon(weak));
            }
            button.add(smellLabel);
          }

          // If has monster, add monster image
          if (hasMonster) {
            JLabel monsterLabel = new JLabel(new ImageIcon(ImageIO.read(otyughPath)));
            monsterLabel.setHorizontalAlignment(JLabel.CENTER);
            button.add(monsterLabel);
          }

          // Add cave image to the grid
          Image locationImage = bufferLocationImage
                  .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
          JLabel locationLabel = new JLabel();
          locationLabel.setIcon(new ImageIcon(locationImage));
          button.add(locationLabel);

          mapPanel.add(button);
        } else {
          // Not visited, add a blank label to the grid
          JLabel label = new JLabel();
          mapPanel.add(label);
        }
        mapPanel.revalidate();
        mapPanel.repaint();
      }
      setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void refreshLeftPanel(Map<String, int[]> propertyInfo) {
    propertyList[1][1].setText(String.valueOf(propertyInfo.get("Diamond")[0]));
    propertyList[1][2].setText(String.valueOf(propertyInfo.get("Diamond")[1]));
    propertyList[2][1].setText(String.valueOf(propertyInfo.get("Ruby")[0]));
    propertyList[2][2].setText(String.valueOf(propertyInfo.get("Ruby")[1]));
    propertyList[3][1].setText(String.valueOf(propertyInfo.get("Emerald")[0]));
    propertyList[3][2].setText(String.valueOf(propertyInfo.get("Emerald")[1]));
    propertyList[4][1].setText(String.valueOf(propertyInfo.get("Arrow")[0]));
    propertyList[4][2].setText(String.valueOf(propertyInfo.get("Arrow")[1]));
  }

  @Override
  public void refreshTextPanel(String text) {
    textLabel.setText(textLine + text);
  }


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}
