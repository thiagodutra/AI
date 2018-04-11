package br.edu.uepb.checkers.app;

import br.edu.uepb.checkers.gui.Board;
import br.edu.uepb.checkers.state.State;

public class App {

	public static void main(String[] args) {
		State s = new State();
		s.setUpGame();
		System.out.println(s);
		

	}

}
