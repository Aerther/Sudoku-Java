import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuMaker {
	private ArrayList<ArrayList<String>> emptyBoard = new ArrayList<>();
	private ArrayList<ArrayList<String>> filledBoard = new ArrayList<>();
	
	public SudokuMaker() {
		this.fillEmptyBoard();
	}
	
	public ArrayList<ArrayList<String>> getFilledBoard() {
		return this.filledBoard;
	}
	
	public ArrayList<ArrayList<String>> makeSudoku() {
		SudokuSolver ss = new SudokuSolver();
		
		this.addNumbers(3);
		
		ss.setBoard(this.cloneBoard(emptyBoard));
		ss.solveBoard();
		filledBoard = this.cloneBoard(ss.getBoard());
		
		for (int i = 0; i < ss.getBoard().size(); i++) {
			if(ss.getBoard().get(i).contains(" ")) {
				this.fillEmptyBoard();
				return makeSudoku();
			}
		}
		
		this.emptyBoard = ss.getBoard();
		this.removeNumbers(50);
		
		return this.emptyBoard;
	}
	
	public void removeNumbers(int count) {
		Random r = new Random();
		SudokuSolver ss = new SudokuSolver();
		
		ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
		
		for (int i = 0; i < emptyBoard.size(); i++) {
			for (int j = 0; j < emptyBoard.get(i).size(); j++) {
				positions.add(new ArrayList<>(List.of(i, j)));
			}
		}
		
		for(int c = 0; c != count;) {
			int random = r.nextInt(positions.size());
			
			int row1 = positions.get(random).get(0);
			int col1 = positions.get(random).get(1);
			
			String number = this.emptyBoard.get(row1).get(col1);
			this.emptyBoard.get(row1).set(col1, " ");
			
			ss.setBoard(emptyBoard);
			ss.countSolutions();

			if(ss.getSolutions() == 1) {
				positions.remove(random);
				c++;
			} else {
				this.emptyBoard.get(row1).set(col1, number);
			}
			
			ss.resetSolutions();
		}
	}
	
	public void addNumbers(int count) {
		Random r = new Random();
		
		for(int i = 0; i < 3; i++) {
			int number1 = r.nextInt(9)+1;
			int col1 = r.nextInt(9);
			this.emptyBoard.get(0).set(col1, String.valueOf(number1));
		}

		for (int c = 0; c != count;) {
			int number = r.nextInt(9)+1;
			int row = r.nextInt(9);
			int col = r.nextInt(9);
			
			if(this.isValidMove(String.valueOf(number), row, col)) {
				this.emptyBoard.get(row).set(col, String.valueOf(number));
				c++;
			}
		}
	}
	
	public boolean isValidMove(String num, int row, int col) {
		// Checks for the rows
		if(emptyBoard.get(row).contains(num)) {
			return false;
		}
				
		// Checks for the columns
		for(int i = 0; i < emptyBoard.size(); i++) {
			if(emptyBoard.get(i).get(col).equals(num)) {
				return false;
			}
		}
				
		// Checks for 3x3
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (emptyBoard.get(i).get(j).equals(num)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void fillEmptyBoard() {
		this.emptyBoard.clear();
		
		for (int i = 0; i < 9; i++) {
			ArrayList<String> row = new ArrayList<>();
			for (int j = 0; j < 9; j++) {
				row.add(" ");
			}
			emptyBoard.add(row);
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
	
	// Used for test
	public ArrayList<ArrayList<String>> getEmptyBoard() {
		return this.emptyBoard;
	}
}
