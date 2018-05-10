package br.edu.uepb.damas.tabuleiro;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.uepb.damas.tabuleiro.Tabuleiro.Decisao;

public class Minimax extends Player implements AI {

	private Point pontoPulo;
	private int profundidade;
	private long totalTimeElapsed;
	private double numMovimentosChamados;
	private int poda = 0;

	public Minimax(String name, Side s) {
		super(name, s);
	}

	public Minimax(Side s, int profundidade) {
		super("MinimaxAI", s);
		this.profundidade = profundidade;
		this.totalTimeElapsed = 0;
	}

	//Faz movimento
	public Decisao makeMove(Tabuleiro Tabuleiro) {
		numMovimentosChamados++;
		long startTime = System.nanoTime();
		Move m = minimaxStart(Tabuleiro, profundidade, getSide(), true);
		totalTimeElapsed += System.nanoTime() - startTime;
		Tabuleiro.Decisao decision = Tabuleiro.makeMove(m, getSide());
		if (decision == Decisao.NOVO_MOVIMENTO) {
			pontoPulo = m.getEnd();
		}
		return decision;
	}

	public String getAverageTimePerMove() {
		return totalTimeElapsed / numMovimentosChamados * Math.pow(10, -6) + " milisegundos";
	}

	
	//Inicio Minimax
	private Move minimaxStart(Tabuleiro Tabuleiro, int profundidade, Side side, boolean maximizingPlayer) {
		double alpha = Double.NEGATIVE_INFINITY;
		double beta = Double.POSITIVE_INFINITY;

		List<Move> possibleMoves;
		if (pontoPulo == null)
			possibleMoves = Tabuleiro.getAllValidMoves(side);
		else {
			possibleMoves = Tabuleiro.getValidSkipMoves(pontoPulo.x, pontoPulo.y, side);
			pontoPulo = null;
		}
		

		List<Double> heuristica = new ArrayList<>();
		if (possibleMoves.isEmpty())
			return null;
		Tabuleiro tempTabuleiro = null;
		for (int i = 0; i < possibleMoves.size(); i++) {
			tempTabuleiro = Tabuleiro.clone();
			tempTabuleiro.makeMove(possibleMoves.get(i), side);
			heuristica.add(minimax(tempTabuleiro, profundidade - 1, flipSide(side), !maximizingPlayer, alpha, beta));
		}
		

		double maxheuristica = Double.NEGATIVE_INFINITY;

		Random rand = new Random();
		for (int i = heuristica.size() - 1; i >= 0; i--) {
			if (heuristica.get(i) >= maxheuristica) {
				maxheuristica = heuristica.get(i);
			}
		}
		
		for (int i = 0; i < heuristica.size(); i++) {
			if (heuristica.get(i) < maxheuristica) {
				heuristica.remove(i);
				possibleMoves.remove(i);
				i--;
			}
		}
		
		return possibleMoves.get(rand.nextInt(possibleMoves.size()));
	}

	private double minimax(Tabuleiro Tabuleiro, int profundidade, Side side, boolean maximizingPlayer, double alpha,
			double beta) {
		if (profundidade == 0) {
			return getHeuristic(Tabuleiro);
		}
		List<Move> possibleMoves = Tabuleiro.getAllValidMoves(side);

		double initial = 0;
		Tabuleiro tempTabuleiro = null;
		//Maximizando
		if (maximizingPlayer) {
			initial = Double.NEGATIVE_INFINITY;
			for (int i = 0; i < possibleMoves.size(); i++) {
				tempTabuleiro = Tabuleiro.clone();
				tempTabuleiro.makeMove(possibleMoves.get(i), side);

				double result = minimax(tempTabuleiro, profundidade - 1, flipSide(side), !maximizingPlayer, alpha,
						beta);
				
				//Poda
				initial = Math.max(result, initial);
				alpha = Math.max(alpha, initial);

				if (alpha >= beta)
					break;
			}
		}
		// minimizing
		else {
			initial = Double.POSITIVE_INFINITY;
			for (int i = 0; i < possibleMoves.size(); i++) {
				tempTabuleiro = Tabuleiro.clone();
				tempTabuleiro.makeMove(possibleMoves.get(i), side);

				double result = minimax(tempTabuleiro, profundidade - 1, flipSide(side), !maximizingPlayer, alpha,
						beta);
				
				//Poda
				initial = Math.min(result, initial);
				alpha = Math.min(alpha, initial);

				if (alpha >= beta)
					break;
			}
		}

		return initial;
	}

	private double getHeuristic(Tabuleiro t) {
		// heuristica simples
		// if(getSide() == Side.BRANCAS)
		// return b.getNumWhitePieces() - b.getNumBlackPieces();
		// return b.getNumBlackPieces() - b.getNumWhitePieces();

		//Mais elaborada
		double pesoDama = 1.2;
		double result = 0;
		if (getSide() == Side.BRANCAS)
			result = t.getNumDamasBrancas() * pesoDama + t.getNumPeaoBrancas()
					- t.getNumDamasPretas() * pesoDama - t.getNumPeaoPretas();
		else
			result = t.getNumDamasPretas() * pesoDama + t.getNumPeaoPretas()
					- t.getNumDamasBrancas() * pesoDama - t.getNumPeaoBrancas();
		return result;

	}

	private Side flipSide(Side side) {
		if (side == Side.PRETAS)
			return Side.BRANCAS;
		return Side.PRETAS;
	}
}
