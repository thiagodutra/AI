package br.edu.uepb.damas.tabuleiro;

import java.util.Scanner;

import br.edu.uepb.damas.tabuleiro.Tabuleiro.Decisao;

public class Main {

	public static double total = 1;
	public static boolean jogadasMultiplas = false;

	public static void main(String[] args) throws InterruptedException {

		jogadasMultiplas = total > 1;
		Player one = new Player("Player 1", Player.Side.PRETAS);

		Minimax two = new Minimax(Player.Side.BRANCAS, 6);

		// Jogador one, começar se for verdadeiro;
		boolean turn = true;

		// System.out.println(Tabuleiro.toString());

		Scanner sc = new Scanner(System.in);

		int blackWin = 0;
		int whiteWin = 0;

		for (int t = 0; t < total; t++) {
			Tabuleiro Tabuleiro = new Tabuleiro();
			Player current = one;
			if (!turn)
				current = two;
			int c = 0;
			println(Tabuleiro.toString());
			while (c < 1000) {
				c++;
				print(current.toString() + " eh sua vez: ");

				Tabuleiro.Decisao decision = null;
				if (current instanceof AI) {
					decision = ((AI) current).makeMove(Tabuleiro);
					println();
				} else {
					String text = sc.nextLine();
					if (text.equals("Tabuleiro")) {
						println(Tabuleiro.toString());
					}
					if (text.equals("rand")) {
						decision = current.makeRandomMove(Tabuleiro);
					} else {
						String[] split = text.split(" ");
						Move m;
						if (split.length == 1) {
							m = new Move(Integer.parseInt(text.charAt(0) + ""), Integer.parseInt(text.charAt(1) + ""),
									Integer.parseInt(text.charAt(2) + ""), Integer.parseInt(text.charAt(3) + ""));
						} else {
							int[] s = new int[split.length];
							for (int i = 0; i < split.length; i++) {
								s[i] = Integer.parseInt(split[i]);
							}
							m = new Move(s[0], s[1], s[2], s[3]);

						}
						decision = current.makeMove(m, Tabuleiro);
					}

				}

				if (decision == Decisao.FALHA_DESTINO_INVALIDO
						|| decision == Decisao.MOVIMENTO_FALHO_PECA_INVALIDA) {
					println("Movimento Falhou");
					// don't update anything
				} else if (decision == Decisao.COMPLETO) {
					println(Tabuleiro.toString());
					if (Tabuleiro.getNumPecasPretas() == 0) {
						println("Brancas ganham com " + Tabuleiro.getNumPecasBrancas() + " sobrando");
						whiteWin++;
						break;
					}
					if (Tabuleiro.getNumPecasBrancas() == 0) {
						println("Pretas ganham com " + Tabuleiro.getNumPecasPretas() + " sobrando");
						blackWin++;
						break;
					}
					if (turn)
						current = two;
					else
						current = one;
					turn = !turn;

				} else if (decision == Decisao.NOVO_MOVIMENTO) {
					println("Novo Movimento");
				} else if (decision == Decisao.FIM_JOGO) {
					// current player cannot move
					if (current.getSide()==Player.Side.PRETAS) {
						println("Brangas ganharam!");
						whiteWin++;

					} else {
						println("pretas ganharam");
						blackWin++;
					}
					break;
				}

			}
			System.out.println("Jogo terminado depois de: " + c + " jogadas");
			if (one instanceof Minimax)
				System.out.println("Tempo médio por movimento: " + ((Minimax) one).getAverageTimePerMove());
		}
		System.out.println("Pretas Ganharam " + blackWin / total * 100 + "%" + ", Brancas ganharam "
				+ whiteWin / total * 100 + "%");

	}

	public static void println(String s) {
		if (!jogadasMultiplas)
			System.out.println(s);
	}

	public static void print(String s) {
		if (!jogadasMultiplas)
			System.out.print(s);
	}

	public static void println() {
		if (!jogadasMultiplas)
			System.out.println();
	}
}
