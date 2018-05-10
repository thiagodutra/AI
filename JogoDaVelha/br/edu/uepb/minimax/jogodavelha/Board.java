package br.edu.uepb.minimax.jogodavelha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
	private List<Cell> emptyCells;
	private Scanner scanner;
	private Player[][] board;
	private List<Cell> rootValues;

	public Board() {
		initBoard();
	}

	private void initBoard() {
		this.rootValues = new ArrayList<>();
		this.scanner = new Scanner(System.in);
		this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}

	// Início Regras e verficações do jogo.
	public boolean stillPlaying() {
		if (isWin(Player.COMPUTER))
			return false;
		if (isWin(Player.USER))
			return false;
		if (getEmptyCells().isEmpty())
			return false;
		return true;
	}
	//Verifica quantos espaços vazios ainda existem no tabuleiro
	//e as adiciona numa lista, juntamente com suas coordenadas.
	public List<Cell> getEmptyCells() {
		emptyCells = new ArrayList<>();
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				if (board[i][j] == Player.EMPTY) {
					emptyCells.add(new Cell(i, j));
				}
			}
		}
		return emptyCells;
	}
	//Faz o movimento do jogador atual
		public void makeMove(Cell cell, Player player) {
			this.board[cell.getX()][cell.getY()] = player;
		}
	//Verifica fim do jogo
	boolean isWin(Player player) {
		if (checkDiagonals(player) || checkRows(player) || checkCols(player)) {
			return true;
		}
		return false;
	}
	//Checa vitória nas diagonais principal e secundária
	private boolean checkDiagonals(Player player) {
		return ((board[0][0] == player && board[1][1] == player && board[2][2] == player)
				|| (board[0][2] == player && board[1][1] == player && board[2][0] == player));

	}
	//Checa a vitória nas linhas
	private boolean checkRows(Player player) {
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
				return true;
		}
		return false;
	}
	//Checa a vitória nas colunas
	private boolean checkCols(Player player) {
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
				return true;
		}
		return false;
	}
	// FIM Regras e verficações do jogo.

	// Inicio IA do jogo
	//Calcula qual o melhor movimento
	public Cell calculateBestMove() {
		int max = Integer.MIN_VALUE;
		int best = Integer.MIN_VALUE;

		for (int i = 0; i < rootValues.size(); i++) {
			if (max < rootValues.get(i).getMinimaxHeuristicsValue()) {
				max = rootValues.get(i).getMinimaxHeuristicsValue();
				best = i;
			}
		}
		return rootValues.get(best);
	}
	//Retorna o menor valor dentre os nós
	public int returnMin(List<Integer> list) {
		int min = Integer.MAX_VALUE;
		int index = Integer.MAX_VALUE;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < min) {
				min = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}
	//Retorna o maior valor dentre os nós
	public int returnMax(List<Integer> list) {
		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}
	//Chama o algoritmo de minimax
	public void callMiniMax(int depth, Player player) {
		rootValues.clear();
		minimax(depth, player);
	}
	//Algoritmi MINIMAX
	private int minimax(int depth, Player player) {
		//Se o computador esta ganhando, retorne +1
		if (isWin(Player.COMPUTER)) {
			return +1;
		}
		//Se o usuário esta ganhando, retorne -1
		if (isWin(Player.USER)) {
			return -1;
		}
		//Cria uma lista com as celulas que ainda estão EMPTY (Jogáveis)
		List<Cell> availableCells = getEmptyCells();
		
		//Se for empate, retorne 0
		if (availableCells.isEmpty()) {
			return 0;
		} 
		//Cria uma lista com as pontuações
		List<Integer> scores = new ArrayList<>();
		for (int i = 0; i < availableCells.size(); i++) {
			
			Cell point = availableCells.get(i);
			//Sempre na vez do COMPUTADOR, será maximizado
			if (player == Player.COMPUTER) {
				//Constrói a árvore do jogo (GAMETREE)
				makeMove(point, Player.COMPUTER);
				int currentScore = minimax(depth+1, Player.USER);
				//Adiciona as pontuações a lista.
				scores.add(currentScore);
				//Retorna para a raiz a maior pontuação
				if (depth == 0) {
					point.setMinimaxHeuristicsValue(currentScore);
					rootValues.add(point);
				}
				//Simula as jogadas do usuário
			} else if (player == Player.USER) {
				makeMove (point, Player.USER);
				scores.add(minimax(depth+1, Player.COMPUTER));
			}
			board[point.getX()][point.getY()] = Player.EMPTY;
		}
		
		//Se o jogador for o COMPUTADOR, retorne o valor máximo
		if (player == Player.COMPUTER) {
			return returnMax(scores);
		}
		//Se não, retorno o valor minimo
		return returnMin(scores);
	}

	// Fim AI do jogo

	// Metodos do Jogo
	public void setUpBoard() {
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				board[i][j] = Player.EMPTY;
			}
		}
	}
	//Computa a entrada do usuário
	public void userInput() {
		System.out.println("Movimento do usuário: ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x, y);
		makeMove(cell, Player.USER);
	}
	//Mostra o tabuleiro atual
	public void showBoard() {
		System.out.println();

		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	// FIM metodos do Jogo


	//Getters and Setters
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Player[][] getBoard() {
		return board;
	}

	public void setBoard(Player[][] board) {
		this.board = board;
	}

	public List<Cell> getRootValues() {
		return rootValues;
	}

	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}

	public void setEmptyCells(List<Cell> emptyCells) {
		this.emptyCells = emptyCells;
	}
	
}
