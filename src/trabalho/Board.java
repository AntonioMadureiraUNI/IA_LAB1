package trabalho;

import java.util.Arrays;
import java.util.List;

import trabalho.BestFirst.State;

public class Board implements Ilayout, Cloneable {
	private static final int dim = 3;
	private int board[][];
	private int hashCode = -1;

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
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (board[i][j] == 0)
					result.append(" ");
				else
					result.append(board[i][j]);
				if (j == dim - 1)
					result.append(System.lineSeparator());
				// result.append("\n");
			}
		}
		return result.toString();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Board n = (Board) o;
		return Arrays.deepEquals(board, n.board) && hashCode == n.hashCode;
	}

	public int hashCode() {
		if (this.hashCode != -1)
			return hashCode;
		this.hashCode = Arrays.deepHashCode(board);
		return this.hashCode;
	}	
	

	// Interface
	@Override
	public List<Ilayout> children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoal(Ilayout l) {
		Board lBoard = (Board) l;
		if (lBoard == null)
			return false;
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				if (this.board[i][j] != lBoard.board[i][j])
					return false;
		return true;
	}

	@Override
	public double getG() {
		return 1;
	}
}
