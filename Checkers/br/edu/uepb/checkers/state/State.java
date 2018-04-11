package br.edu.uepb.checkers.state;

import java.util.Arrays;

import br.edu.uepb.checkers.enums.Players;
import br.edu.uepb.eightpuzzle.vertex.Vertex;

public class State implements Cloneable{
	private Players[][] board = new Players[8][8];
	private State predecessor;
	private static Players currentPlayer;

	public State() {
		this.board = board;
		this.predecessor = predecessor;
	}
	
	public static State moveRed(State state) throws Exception {
		if (getCurrentPlayer() != currentPlayer) {
			throw new Exception ("Não é a vez deste jogador");
		}
		return state;
	}
	
	public static State moveBlack(State state) {
		
		return state;
	}
	private boolean validMove() {
		return false;
	}
		
	public void setUpGame() {
         // Coloca as peças no tabuleiro para inicio do jogo
         // Note que as peças apenas são encontradas em quadrados
         // that satisfy  row % 2 == col % 2.  At the start of the game,
         // all such squares in the first three rows contain black squares
         // and all such squares in the last three rows contain red squares.
     for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
           if ( row % 2 == col % 2 ) {
              if (row < 3)
                 board[row][col] = Players.WHI;
              else if (row > 4)
                 board[row][col] = Players.RED;
              else
                 board[row][col] = Players.EMP;
           }
           else {
              board[row][col] = Players.EMP;
           }
        }
     }
  }  // end setUpGame()
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String board1="";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				board1 += board[i][j] + " ";
			}
			board1 += "\n";
		}
		
		return board1;
	}


	public static Players getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Players currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public State getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(State predecessor) {
		this.predecessor = predecessor;
	}


}
