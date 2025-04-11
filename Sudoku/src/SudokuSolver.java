import java.util.ArrayList;

public class SudokuSolver {
	private ArrayList<ArrayList<String>> board = new ArrayList<>();
	private int solutions = 0;
	private int count = 0;
	
	public void setBoard(ArrayList<ArrayList<String>> board) {
		this.board = board;
	}
	
	public boolean isValidMove(String num, int row, int col) {
		// Checks for the rows
		if(board.get(row).contains(num)) {
			return false;
		}
		
		// Checks for the columns
		for(int i = 0; i < board.size(); i++) {
			if(board.get(i).get(col).equals(num)) {
				return false;
			}
		}
		
		// Checks for 3x3
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;

		for (int i = startRow; i < startRow + 3; i++) {
		    for (int j = startCol; j < startCol + 3; j++) {
		        if (board.get(i).get(j).equals(num)) {
		            return false;
		        }
		    }
		}
		
		return true;
	}
	
	public int[] getNextEmptySpace() {
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if(board.get(i).get(j).equals(" ")) {
					return new int[] {i, j};
				}
			}
		}
		
		return null;
	}
	
	public void resetSolutions() {
		this.solutions = 0;
	}
	
	public void countSolutions() {
		int[] emptySpace = this.getNextEmptySpace();
		
		if(emptySpace == null) {
			this.solutions++;
			return;
		}
		
		int row = emptySpace[0];
		int col = emptySpace[1];
		
		for(int num = 1; num < 10; num++) {
			if(this.isValidMove(String.valueOf(num), row, col)) {
				board.get(row).set(col, String.valueOf(num));
				this.countSolutions();
				board.get(row).set(col, " ");
			}
		}
	}
	
	public boolean solveBoard() {
		count++;
		if(count >= 500) {
			return true;
		}
		
		int[] emptySpace = this.getNextEmptySpace();
		
		if(emptySpace == null) {
			return true;
		}
		
		int row = emptySpace[0];
		int col = emptySpace[1];
		
		for(int num = 1; num < 10; num++) {
			if(this.isValidMove(String.valueOf(num), row, col)) {
				board.get(row).set(col, String.valueOf(num));
				
				if(this.solveBoard()) {
					return true;
				}
				
				board.get(row).set(col, " ");
			}
		}
		
		return false;
	}
	
	public int getSolutions() {
		return this.solutions;
	}
	
	// Used for test
	public ArrayList<ArrayList<String>> getBoard() {
		return this.board;
	}
	
}
