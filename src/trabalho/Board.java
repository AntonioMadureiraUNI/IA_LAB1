package trabalho;

import java.util.List;

public class Board implements Ilayout, Cloneable {
	private static final int dim = 3;
	private int board[][];

	public Board() {
		board = new int[dim][dim];
	}

	public Board(String str) throws IllegalStateException {
		if (str.length() != dim * dim)
			throw new IllegalStateException("Invalid arg in Board constructor");
		board = new int[dim][dim];
		int si = 0;
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				board[i][j] = Character.getNumericValue(str.charAt(si++));

	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				if(board[i][j] == 0)
					result.append(" ");
				else
					result.append(board[i][j]);					
				if(j == dim-1 )
					result.append(System.lineSeparator());
					//result.append("\n");					
			}
		}
		return result.toString();	
		// TO BE COMPLETED
	}

	public boolean equals(Object o) {
		return false;
		// TO BE COMPLETED
	}

	public int hashCode() {
		return 0;
		// TO BE COMPLETED
	}
	//... TO BE COMPLETED
	
	//Interface
	@Override
	public List<Ilayout> children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoal(Ilayout l) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getG() {
		// TODO Auto-generated method stub
		return 0;
	}
}
