import java.awt.Color;

public class Constants {
	// Separator
	public static final String SEPARATOR = ";";
	// Window constants
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;

	// Color constants
	public static final Color BACKGROUND_COLOR = Color.decode("#ffa2b5");
	public static final Color BUTTON_BACKGROUND = Color.decode("#fbf1f9");
	public static final Color BUTTON_FOREGROUND = Color.decode("#000000");
	public static final Color SELECTED_BUTTON_BACKGROUND = BUTTON_BACKGROUND;
	public static final Color SELECTED_BUTTON_FOREGROUND = Color.decode("#000000");
	public static final Color NON_SELECTED_BUTTON_BACKGROUND = BUTTON_BACKGROUND;
	public static final Color NON_SELECTED_BUTTON_FOREGROUND = Color.decode("#808080");

	// JPanel constants
	public static final int PANEL_WIDTH = 150;
	public static final int PANEL_HEIGHT = 150;
	public static final int PANEL_X_SEPARATION = 5;
	public static final int PANEL_Y_SEPARATION = 5;
	public static final int PANEL_Y_INCREMENT = 40;
	public static final int INICIAL_X = WINDOW_WIDTH/2-PANEL_WIDTH/2-PANEL_WIDTH-PANEL_X_SEPARATION;
	public static final int INICIAL_Y = WINDOW_HEIGHT/2-PANEL_HEIGHT/2-PANEL_HEIGHT-PANEL_Y_SEPARATION-PANEL_Y_INCREMENT;

	// JButton constants
	public static final int BUTTON_WIDTH = PANEL_WIDTH/3;
	public static final int BUTTON_HEIGHT = PANEL_HEIGHT/3;
	public static final int OTHERS_BUTTON_WIDTH = 130;
	public static final int OTHER_BUTTON_HEIGHT = 30;
}