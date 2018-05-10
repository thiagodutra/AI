package br.edu.uepb.minimax.jogodavelha;

public enum Player {
	
	COMPUTER("X"), USER("O"), EMPTY("-");
	
	private Player(String player) {
		this.player = player;
	}

	private final String player;
	
	@Override
	public String toString() {
		return this.player;
	}
	
}
