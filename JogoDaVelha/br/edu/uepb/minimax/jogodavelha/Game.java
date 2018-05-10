package br.edu.uepb.minimax.jogodavelha;

import java.util.Random;

public class Game {
	
	private Board board;
	private Random random;

	public Game() {
		initGame();
		showBoard();
		firstMove();
		playGame();
		checkStatus();
	}

	
	//Game Loop
	private void playGame() {
		//Enquanto não houver vencedor
		while (this.board.stillPlaying()) {
			//Faz jogada do usuário
			System.out.println("Jogada do usuário");
			Cell userCell = new Cell(board.getScanner().nextInt(), (board.getScanner().nextInt()));
			
			this.board.makeMove(userCell, Player.USER);
			showBoard();
			//Se houver vencedor, pare!
			if (!this.board.stillPlaying()) {
				break;
			}
			//Calcula a melhor jogada (constroi a gametree e calcula o máximo)
			this.board.callMiniMax(0, Player.COMPUTER);
			//Imprime os valores dos nós
			for (Cell cell : this.board.getRootValues()) {
				System.out.println("Coordenada da celula: "+cell+" valor minimax: "+ cell.getMinimaxHeuristicsValue());
			}
			//Faz a melhor jogada para o computador
			this.board.makeMove(board.calculateBestMove(), Player.COMPUTER);
			showBoard();
		}
		
	}



	private void firstMove() {
		System.out.println("Quem começa? 1 - COMPUTADOR ; 2 - USUARIO");
		int sophiesChoice = this.board.getScanner().nextInt();
		
		if (sophiesChoice == 1) {
			Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
		    this.board.makeMove(cell, Player.COMPUTER);
		    showBoard();
		}
		
	}

	private void showBoard() {
		this.board.showBoard();
		
	}

	private void checkStatus() {
		if (board.isWin(Player.COMPUTER)) {
			System.out.println("COMPUTADOR GANHOU!!");
		} else if( board.isWin(Player.USER)) {
			System.out.println(("USUARIO GANHOUUU!!"));
		} else {
			System.out.println("EMPATARAMMM!!!");
		}
		
	}

	private void initGame() {
		this.board = new Board();
		this.board.setUpBoard();
		this.random = new Random();
		
	}
	
	
}
