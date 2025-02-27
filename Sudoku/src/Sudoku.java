import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Sudoku {
	private ArrayList<ArrayList<ArrayList<String>>> boards = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> lockedPositions = new ArrayList<>();
	private ArrayList<ArrayList<String>> board = new ArrayList<>();
	private int indexBoard = -1;

	private String selectedNumber = "1";

	public Sudoku() {
		this.initializeBoards();
		this.initializeBoard();
		this.initializeLockedPositions();
	}

	public void restartBoard() {
		this.board = cloneBoard(indexBoard);
	}

	public void newGame() {
		this.initializeBoard();
		this.initializeLockedPositions();
	}

	public boolean checkWin() {
		if(!this.isComplete()) return false;

		// Array to check
		ArrayList<String> check = new ArrayList<>();

		// Check for the rows
		for (int row = 0; row < board.size(); row++) {
			ArrayList<String> list = board.get(row);
			Set<String> set = new HashSet<String>(board.get(row));

			if(list.size() != set.size()) return false;
		}

		// Check for the collumns
		for (int col = 0; col < board.size(); col++) {
			check.clear();
			for (int row = 0; row < board.get(col).size(); row++) {
				check.add(board.get(row).get(col));
			}

			Set<String> set = new HashSet<String>(check);

			if(check.size() != set.size()) return false;
		}

		// Check for the grids
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				check.clear();
				for (int rowBoard = row*3; rowBoard < row*3+3; rowBoard++) {
					for (int colBoard = col*3; colBoard < col*3+3; colBoard++) {
						check.add(board.get(rowBoard).get(colBoard));
					}
				}

				Set<String> set = new HashSet<String>(check);

				if(check.size() != set.size()) return false;
			}

		}

		return true;

	}

	private boolean isComplete() {
		for (int row = 0; row < board.size(); row++) {
			for (int col = 0; col < board.get(row).size(); col++) {
				if(board.get(row).get(col).equals(" ")) {
					return false;
				}
			}
		}

		return true;
	}

	public void makeMove(int row, int col) {
		if(!isLocked(row, col)) {
			board.get(row).set(col, this.selectedNumber);
		}
	}

	private boolean isLocked(int row, int col) {
		for (int i = 0; i < lockedPositions.size(); i++) {
			for (int j = 0; j < lockedPositions.get(i).size(); j++) {
				if(row == lockedPositions.get(i).get(0) && col == lockedPositions.get(i).get(1)) {
					return true;
				}
			}
		}

		return false;
	}

	public String getBoardPosition(int row, int col) {
		return board.get(row).get(col);
	}

	public void setSelectedNumber(String number) {
		this.selectedNumber = number;
	}

	private void initializeLockedPositions() {
		lockedPositions.clear();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if(!board.get(i).get(j).equals(" ")) {
					lockedPositions.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}
	}

	private void initializeBoards() {
		ArrayList<ArrayList<String>> board1 = new ArrayList<>();
		board1.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "8", " ", "3", " ", "7", "6")));
		board1.add(new ArrayList<>(Arrays.asList(" ", "7", " ", " ", "9", "2", " ", "1", "4")));
		board1.add(new ArrayList<>(Arrays.asList(" ", " ", "8", "1", "7", " ", "3", "9", " ")));
		board1.add(new ArrayList<>(Arrays.asList(" ", " ", "6", " ", "5", " ", " ", "8", "1")));
		board1.add(new ArrayList<>(Arrays.asList("9", "1", " ", "2", " ", "7", " ", " ", "3")));
		board1.add(new ArrayList<>(Arrays.asList(" ", " ", "7", "6", " ", " ", " ", " ", " ")));
		board1.add(new ArrayList<>(Arrays.asList("7", "3", " ", " ", " ", " ", " ", " ", "8")));
		board1.add(new ArrayList<>(Arrays.asList("4", "9", " ", "7", "6", " ", " ", "3", "2")));
		board1.add(new ArrayList<>(Arrays.asList("8", "6", " ", "3", " ", "4", " ", " ", " ")));
		boards.add(board1);

		ArrayList<ArrayList<String>> board2 = new ArrayList<>();
		board2.add(new ArrayList<>(Arrays.asList("1", " ", "2", " ", "4", " ", " ", " ", "5")));
		board2.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "5", " ", "1", "7", " ", " ")));
		board2.add(new ArrayList<>(Arrays.asList("6", " ", " ", "7", "8", " ", " ", "2", "9")));
		board2.add(new ArrayList<>(Arrays.asList("5", " ", " ", " ", "3", " ", "9", "8", "1")));
		board2.add(new ArrayList<>(Arrays.asList(" ", "8", "3", "1", " ", "6", " ", "5", " ")));
		board2.add(new ArrayList<>(Arrays.asList("7", " ", "1", "8", " ", "4", "2", " ", " ")));
		board2.add(new ArrayList<>(Arrays.asList("3", "2", " ", "4", "1", "5", " ", "7", "8")));
		board2.add(new ArrayList<>(Arrays.asList(" ", " ", "6", " ", " ", " ", "3", "1", "4")));
		board2.add(new ArrayList<>(Arrays.asList("4", " ", "7", "3", " ", " ", " ", "9", " ")));
		boards.add(board2);
	}

	private ArrayList<ArrayList<String>> cloneBoard(int index) {
		ArrayList<ArrayList<String>> boardClone = new ArrayList<>();
	    ArrayList<ArrayList<String>> originalBoard = boards.get(index);

	    for (ArrayList<String> originalRow : originalBoard) {
	        ArrayList<String> clonedRow = new ArrayList<>(originalRow);

	        boardClone.add(clonedRow);
	    }

		return boardClone;
	}

	private void initializeBoard() {
		Random random = new Random();

		int temp = indexBoard;

		while(temp == indexBoard) {
			indexBoard = random.nextInt(boards.size());
		}

		board = cloneBoard(indexBoard);		
	}
}