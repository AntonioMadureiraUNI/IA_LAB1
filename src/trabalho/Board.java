package trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private Board swaprows(int[][] a, int i, int j, int k) {
		int temp = a[i][k];
		a[i][k] = a[j][k];
		a[j][k] = temp;
		
		Board t = copyBoard(a);
		return t;
	}

	private Board swapcolumns(int[][] a, int i, int j, int k) {
		int temp = a[k][i];
		a[k][i] = a[k][j];
		a[k][j] = temp;

		Board t = copyBoard(a);
		return t;
	}

	private Board copyBoard(int[][] a) {
		Board t = new Board();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				t.board[i][j] = a[i][j];
			}
		}
		return t;
	}
	
	private int[][] copyBoard2(int[][] a) {
		int[][] t = new int[a.length][a.length];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				t[i][j] = a[i][j];
			}
		}
		return t;
	}

	// Interface
	@Override
	public List<Ilayout> children() {
		List<Ilayout> o = new ArrayList<Ilayout>();
		int[] position = new int[2];
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				if (this.board[i][j] == 0) {
					position[0] = i;
					position[1] = j;
				}

		int[][] a = copyBoard2(this.board);
		if (position[0] - 1 >= 0) {
			Board x = swaprows(a, position[0], position[0] - 1, position[1]);
			o.add(x);
		}
		
		a = copyBoard2(this.board);
		if (position[1] - 1 >= 0) {
			Board x1 = swapcolumns(a, position[1], position[1] - 1, position[0]);
			o.add(x1);
		}
		
		a = copyBoard2(this.board);
		if (position[1] + 1 <= 2) {
			Board x2 = swapcolumns(a, position[1], position[1] + 1, position[0]);
			o.add(x2);
		}
		
		a = copyBoard2(this.board);
		if (position[0] + 1 <= 2) {
			Board x3 = swaprows(a, position[0], position[0] + 1, position[1]);
			o.add(x3);
		}

		return o;
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
