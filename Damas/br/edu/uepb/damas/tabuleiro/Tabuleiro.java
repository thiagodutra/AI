package br.edu.uepb.damas.tabuleiro;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private Type[][] board;
	public final int SIZE = 8;

	private int numPeaoBrancas;
	private int numPeaoPretas;
	private int numDamasBrancas;
	private int numDamasPretas;
	

	public enum Decisao {
		COMPLETO, MOVIMENTO_FALHO_PECA_INVALIDA, FALHA_DESTINO_INVALIDO, NOVO_MOVIMENTO, FIM_JOGO
	}

	private enum Type {
		VAZIO, BRANCA, PRETA, DAMA_BRANCA, DAMA_PRETA
	}

	public Tabuleiro() {
		setUpTabuleiro();
	}

	public Tabuleiro(Type[][] board) {
		numPeaoBrancas = 0;
		numPeaoPretas = 0;
		numDamasPretas = 0;
		numDamasBrancas = 0;

		this.board = board;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Type piece = getPeca(i, j);
				if (piece == Type.PRETA)
					numPeaoPretas++;
				else if (piece == Type.DAMA_PRETA)
					numDamasPretas++;
				else if (piece == Type.BRANCA)
					numPeaoBrancas++;
				else if (piece == Type.DAMA_BRANCA)
					numDamasBrancas++;
			}
		}
	}


	private void setUpTabuleiro() {
		numPeaoBrancas = 12;
		numPeaoPretas = 12;
		numDamasPretas = 0;
		numDamasBrancas = 0;
		board = new Type[SIZE][SIZE];
		for (int i = 0; i < board.length; i++) {
			int start = 0;
			if (i % 2 == 0)
				start = 1;

			Type tipoPeca = Type.VAZIO;
			if (i <= 2)
				tipoPeca = Type.BRANCA;
			else if (i >= 5)
				tipoPeca = Type.PRETA;

			for (int j = start; j < board[i].length; j += 2) {
				board[i][j] = tipoPeca;
			}
		}

		populateEmptyOnBoard();
	}

	// preenche a parte vazia do tabuleiro
	private void populateEmptyOnBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null)
					board[i][j] = Type.VAZIO;
			}
		}

	}

	public Type getPeca(int row, int col) {
		return board[row][col];
	}

	public Type getPeca(Point point) {
		return board[point.x][point.y];
	}

	public Type[][] getBoard() {
		return board;
	}

	public int getNumPecasBrancas() {
		return numDamasBrancas + numPeaoBrancas;
	}

	public int getNumPecasPretas() {
		return numDamasPretas + numPeaoPretas;
	}

	public int getNumDamasBrancas() {
		return numDamasBrancas;
	}

	public int getNumDamasPretas() {
		return numDamasPretas;
	}

	public int getNumPeaoBrancas() {
		return numPeaoBrancas;
	}

	public int getNumPeaoPretas() {
		return numPeaoPretas;
	}

	// Retorna verdadeiro se o movimento é válido
	public Decisao makeMove(Move move, Player.Side side) {
		if (move == null) {
			return Decisao.FIM_JOGO;
		}
		Point start = move.getStart();
		int startRow = start.x;
		int startCol = start.y;
		Point end = move.getEnd();
		int endRow = end.x;
		int endCol = end.y;

		// Só pode mover sua peca e não pode ser um espaço vazio
		if (!isSuaPeca(startRow, startCol, side) || getPeca(startRow, startCol) == Type.VAZIO)
			return Decisao.MOVIMENTO_FALHO_PECA_INVALIDA;

		List<Move> possibleMoves = getValidMoves(startRow, startCol, side);
		// System.out.println(possibleMoves);

		Type currentType = getPeca(startRow, startCol);

		if (possibleMoves.contains(move)) {
			boolean jumpMove = false;
			// if it contains move then it is either 1 move or 1 jump
			if (startRow + 1 == endRow || startRow - 1 == endRow) {
				board[startRow][startCol] = Type.VAZIO;
				board[endRow][endCol] = currentType;
			} else {
				jumpMove = true;
				board[startRow][startCol] = Type.VAZIO;
				board[endRow][endCol] = currentType;
				Point mid = findMidSquare(move);

				Type middle = getPeca(mid);
				if (middle == Type.PRETA)
					numPeaoPretas--;
				else if (middle == Type.DAMA_PRETA)
					numDamasPretas--;
				else if (middle == Type.BRANCA)
					numPeaoBrancas--;
				else if (middle == Type.DAMA_PRETA)
					numDamasBrancas--;
				board[mid.x][mid.y] = Type.VAZIO;
			}

			if (endRow == 0 && side == Player.Side.PRETAS) {
				board[endRow][endCol] = Type.DAMA_PRETA;
				numPeaoPretas--;
				numDamasPretas++;
			}

			else if (endRow == SIZE - 1 && side == Player.Side.BRANCAS) {
				board[endRow][endCol] = Type.DAMA_BRANCA;
				numPeaoBrancas--;
				numDamasBrancas++;
			}
			if (jumpMove) {
				List<Move> additional = getValidSkipMoves(endRow, endCol, side);
				if (additional.isEmpty())
					return Decisao.COMPLETO;
				return Decisao.NOVO_MOVIMENTO;
			}
			return Decisao.COMPLETO;
		} else
			return Decisao.FALHA_DESTINO_INVALIDO;
	}

	//PEga todos os movimentos possiveis
	public List<Move> getAllValidMoves(Player.Side side) {

		Type normal = side == Player.Side.PRETAS ? Type.PRETA : Type.BRANCA;
		Type king = side == Player.Side.PRETAS ? Type.DAMA_PRETA : Type.DAMA_BRANCA;

		List<Move> possibleMoves = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Type t = getPeca(i, j);
				if (t == normal || t == king)
					possibleMoves.addAll(getValidMoves(i, j, side));
			}
		}

		return possibleMoves;
	}

	private Point findMidSquare(Move move) {
		Point ret = new Point((move.getStart().x + move.getEnd().x) / 2, (move.getStart().y + move.getEnd().y) / 2);

		return ret;
	}
	
	//Pega os movimentos validos no tabuleiro
	public List<Move> getValidMoves(int row, int col, Player.Side side) {
		Type type = board[row][col];
		Point startPoint = new Point(row, col);
		if (type == Type.VAZIO)
			throw new IllegalArgumentException();

		List<Move> moves = new ArrayList<>();

		// 4 movimentos possiveis, 2 se nao for dama
		if (type == Type.BRANCA || type == Type.PRETA) {
			// 2 movimentos possiveis
			int rowChange = type == Type.BRANCA ? 1 : -1;

			int newRow = row + rowChange;
			if (newRow >= 0 || newRow < SIZE) {
				int newCol = col + 1;
				if (newCol < SIZE && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
				newCol = col - 1;
				if (newCol >= 0 && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
			}

		}
		// eh uma dama
		else {
			// Tem 4 movimentos possiveis

			int newRow = row + 1;
			if (newRow < SIZE) {
				int newCol = col + 1;
				if (newCol < SIZE && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
				newCol = col - 1;
				if (newCol >= 0 && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
			}
			newRow = row - 1;
			if (newRow >= 0) {
				int newCol = col + 1;
				if (newCol < SIZE && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
				newCol = col - 1;
				if (newCol >= 0 && getPeca(newRow, newCol) == Type.VAZIO)
					moves.add(new Move(startPoint, new Point(newRow, newCol)));
			}

		}

		moves.addAll(getValidSkipMoves(row, col, side));
		return moves;
	}
	
	//Pega os movimentos de 'comer' validos
	public List<Move> getValidSkipMoves(int row, int col, Player.Side side) {
		List<Move> move = new ArrayList<>();
		Point start = new Point(row, col);

		List<Point> possibilities = new ArrayList<>();

		if (side == Player.Side.BRANCAS && getPeca(row, col) == Type.BRANCA) {
			possibilities.add(new Point(row + 2, col + 2));
			possibilities.add(new Point(row + 2, col - 2));
		} else if (side == Player.Side.PRETAS && getPeca(row, col) == Type.PRETA) {
			possibilities.add(new Point(row - 2, col + 2));
			possibilities.add(new Point(row - 2, col - 2));
		} else if (getPeca(row, col) == Type.DAMA_PRETA || getPeca(row, col) == Type.DAMA_BRANCA) {
			possibilities.add(new Point(row + 2, col + 2));
			possibilities.add(new Point(row + 2, col - 2));
			possibilities.add(new Point(row - 2, col + 2));
			possibilities.add(new Point(row - 2, col - 2));
		}

		for (int i = 0; i < possibilities.size(); i++) {
			Point temp = possibilities.get(i);
			Move m = new Move(start, temp);
			if (temp.x < SIZE && temp.x >= 0 && temp.y < SIZE && temp.y >= 0 && getPeca(temp.x, temp.y) == Type.VAZIO
					&& isOponente(side, getPeca(findMidSquare(m)))) {
				move.add(m);
			}
		}

		
		return move;
	}

	// Retorna verdadeira se a peça é do oponente
	private boolean isOponente(Player.Side current, Type opponentPiece) {
		if (current == Player.Side.PRETAS && (opponentPiece == Type.BRANCA || opponentPiece == Type.DAMA_BRANCA))
			return true;
		if (current == Player.Side.BRANCAS && (opponentPiece == Type.PRETA || opponentPiece == Type.DAMA_PRETA))
			return true;
		return false;
	}

	private boolean isSuaPeca(int row, int col, Player.Side side) {
		Type pieceType = getPeca(row, col);
		if (side == Player.Side.PRETAS && pieceType != Type.PRETA && pieceType != Type.DAMA_PRETA)
			return false;
		else if (side == Player.Side.BRANCAS && pieceType != Type.BRANCA && pieceType != Type.DAMA_BRANCA)
			return false;
		return true;
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("  ");
		for (int i = 0; i < board.length; i++) {
			b.append(i + " ");
		}
		b.append("\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = -1; j < board[i].length; j++) {
				String a = "";
				if (j == -1)
					a = i + "";
				else if (board[i][j] == Type.BRANCA)
					a = "b";
				else if (board[i][j] == Type.PRETA)
					a = "p";
				else if (board[i][j] == Type.DAMA_BRANCA)
					a = "B";
				else if (board[i][j] == Type.DAMA_PRETA)
					a = "P";
				else
					a = "_";

				b.append(a);
				b.append(" ");
			}
			b.append("\n");
		}
		return b.toString();
	}

	public Tabuleiro clone() {
		Type[][] newTabuleiro = new Type[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				newTabuleiro[i][j] = board[i][j];
			}
		}
		Tabuleiro t = new Tabuleiro(newTabuleiro);
		return t;
	}

}
