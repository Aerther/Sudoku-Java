import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Sudoku {
	private ArrayList<ArrayList<String>> originalBoard = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> lockedPositions = new ArrayList<>();
	private ArrayList<ArrayList<String>> filledBoard = new ArrayList<>();
	private ArrayList<ArrayList<String>> board = new ArrayList<>();

	private String selectedNumber = "1";

	public Sudoku() {
		this.initializeBoard();
		this.initializeLockedPositions();
	}

	public void restartBoard() {
		this.board = cloneBoard(this.originalBoard);
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

		// Check for the columns
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
	
	public void seeSolution() {
		board = cloneBoard(filledBoard);
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

	public boolean isLocked(int row, int col) {
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

	private ArrayList<ArrayList<String>> cloneBoard(ArrayList<ArrayList<String>> originalBoard) {
		ArrayList<ArrayList<String>> boardClone = new ArrayList<>();

	    for (ArrayList<String> originalRow : originalBoard) {
	        ArrayList<String> clonedRow = new ArrayList<>(originalRow);

	        boardClone.add(clonedRow);
	    }

		return boardClone;
	}

	private void initializeBoard() {
		SudokuMaker sm = new SudokuMaker();
		originalBoard = sm.makeSudoku();
		board = cloneBoard(this.originalBoard);
		filledBoard = sm.getFilledBoard();
	}
	
	// Used for test
	public ArrayList<ArrayList<String>> getBoard() {
		return this.cloneBoard(this.originalBoard);
	}
}