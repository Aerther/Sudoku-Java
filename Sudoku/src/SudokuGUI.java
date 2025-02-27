import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SudokuGUI extends JFrame implements ActionListener {
	// ArrayList to store the board buttons
	private ArrayList<JPanel> panels = new ArrayList<>();

	// ArrayList to store the number buttons
	private ArrayList<JButton> numButtons = new ArrayList<>();

	// Sudoku Class
	Sudoku sudoku = new Sudoku();

	public SudokuGUI() {
		super();
		setTitle("Sudoku");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Constants.BACKGROUND_COLOR);

		createComponents();
	}

	public void createComponents() {
		// Restart Button
		JButton restartButton = new JButton("Restart");
		restartButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		restartButton.setBackground(Constants.BUTTON_BACKGROUND);
		restartButton.setForeground(Constants.BUTTON_FOREGROUND);
		restartButton.addActionListener(this);
		restartButton.setActionCommand("restart");
		restartButton.setBounds(Constants.WINDOW_WIDTH/3-Constants.OTHERS_BUTTON_WIDTH/2, 40-Constants.OTHER_BUTTON_HEIGHT/2, Constants.OTHERS_BUTTON_WIDTH, Constants.OTHER_BUTTON_HEIGHT);
		getContentPane().add(restartButton);

		// New Game
		JButton newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newGameButton.setBackground(Constants.BUTTON_BACKGROUND);
		newGameButton.setForeground(Constants.BUTTON_FOREGROUND);
		newGameButton.addActionListener(this);
		newGameButton.setActionCommand("new_game");
		newGameButton.setBounds(2*Constants.WINDOW_WIDTH/3-Constants.OTHERS_BUTTON_WIDTH/2, 40-Constants.OTHER_BUTTON_HEIGHT/2, Constants.OTHERS_BUTTON_WIDTH, Constants.OTHER_BUTTON_HEIGHT);
		getContentPane().add(newGameButton);

		// Add the numbers buttons
		String[] numButtonsString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", " "};
		for (int i = 1; i <= numButtonsString.length; i++) {
			JButton button = new JButton(numButtonsString[i-1]);
			button.setFont(new Font("Tahoma", Font.PLAIN, 25));
			button.setBackground(Constants.BUTTON_BACKGROUND);
			button.setForeground(Constants.BUTTON_FOREGROUND);
			button.addActionListener(this);
			button.setActionCommand(numButtonsString[i-1]);
			button.setBounds((i)*Constants.WINDOW_WIDTH/11-Constants.BUTTON_WIDTH/2, Constants.WINDOW_HEIGHT-130, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
			getContentPane().add(button);

			numButtons.add(button);
		}

		// Create the panels
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JPanel panel = new JPanel();
				panel.setLayout(null);
				panel.setBounds(Constants.INICIAL_X + (Constants.PANEL_WIDTH+Constants.PANEL_X_SEPARATION)*col,
						Constants.INICIAL_Y + (Constants.PANEL_HEIGHT+Constants.PANEL_Y_SEPARATION)*row,
						Constants.PANEL_WIDTH,
						Constants.PANEL_HEIGHT);

				getContentPane().add(panel);

				// Add Buttons to the Panel
				for (int rowButton = 0; rowButton < 3; rowButton++) {
					for (int colButton = 0; colButton < 3; colButton++) {
						JButton button = new JButton(" ");
						button.setFont(new Font("Tahoma", Font.PLAIN, 25));
						button.setBackground(Constants.BUTTON_BACKGROUND);
						button.setForeground(Constants.BUTTON_FOREGROUND);
						button.setActionCommand((row * 3 + rowButton) + Constants.SEPARATOR + (col * 3 + colButton));
						button.addActionListener(this);
						button.setBounds(Constants.BUTTON_WIDTH*colButton, Constants.BUTTON_HEIGHT*rowButton, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
						panel.add(button);
					}
				}

				panels.add(panel);
			}
		}

		updateNumButtons(numButtons.get(0));
		updateBoard();
	}

	// Update the board
	private void updateBoard() {
		for (int panelIndex = 0; panelIndex < panels.size(); panelIndex++) {
			JPanel panel = panels.get(panelIndex);

			for (int buttonIndex = 0; buttonIndex < panel.getComponentCount(); buttonIndex++) {
				JButton button = (JButton) panel.getComponent(buttonIndex);

				int row = Integer.parseInt(button.getActionCommand().split(Constants.SEPARATOR)[0]);
				int col = Integer.parseInt(button.getActionCommand().split(Constants.SEPARATOR)[1]);

				button.setText(sudoku.getBoardPosition(row, col));
			}
		}
	}

	// Update the number buttons
	private void updateNumButtons(JButton selectedButton) {
		for (int i = 0; i < numButtons.size(); i++) {
			JButton button = numButtons.get(i);
			button.setBackground(Constants.NON_SELECTED_BUTTON_BACKGROUND);
			button.setForeground(Constants.NON_SELECTED_BUTTON_FOREGROUND);

			if(button.getActionCommand().equals(selectedButton.getActionCommand())) {
				button.setBackground(Constants.SELECTED_BUTTON_BACKGROUND);
				button.setForeground(Constants.SELECTED_BUTTON_FOREGROUND);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("restart")) {
			sudoku.restartBoard();
			updateBoard();
		} else if(e.getActionCommand().equals("new_game")) {
			sudoku.newGame();
			updateBoard();
		}
		else if(!e.getActionCommand().contains(Constants.SEPARATOR)) {
			updateNumButtons((JButton) e.getSource());
			sudoku.setSelectedNumber(e.getActionCommand());
		} else {
			int row = Integer.parseInt(e.getActionCommand().split(Constants.SEPARATOR)[0]);
			int col = Integer.parseInt(e.getActionCommand().split(Constants.SEPARATOR)[1]);

			sudoku.makeMove(row, col);
			updateBoard();

			if(sudoku.checkWin()) {
				JOptionPane.showMessageDialog(null, "You did it, congratulations", "You WON", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}