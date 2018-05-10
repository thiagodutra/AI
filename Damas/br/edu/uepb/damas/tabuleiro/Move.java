package br.edu.uepb.damas.tabuleiro;

import java.awt.Point;

public class Move {

	private Point start;
    private Point end;

    public Move(int startRow, int startCol, int endRow, int endCol)
    {
        start = new Point(startRow, startCol);
        end = new Point(endRow, endCol);
    }
    //Transforma a entrada em pontos, ex: 0 5 1 6
    //onde 0 5 é onde esta a peça e 1 6 para onde a peça deve ir (se possível)
    public Move(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Point getStart()
    {
        return start;
    }

    public Point getEnd()
    {
        return end;
    }

    public String toString()
    {
        return "Inicio: " + start.x + ", " + start.y + " Fim: " + end.x + ", " + end.y;
    }

    public boolean equals(Object m)
    {
        if(!(m instanceof Move))
            return false;
        Move x = (Move) m;
        if(this.getStart().equals(x.getStart()) && this.getEnd().equals(x.getEnd()))
            return true;

        return false;
    }

}
