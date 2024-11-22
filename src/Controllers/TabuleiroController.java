package Controllers;

import Models.Celula;
import Models.Tabuleiro;
import Models.Turno;

public class TabuleiroController {
    public Tabuleiro tabuleiro = new Tabuleiro();
    {
        tabuleiro.preencherTabuleiro();
        tabuleiro.preencherNaoClicavel();
        tabuleiro.addPossibiblePlayFrom(Turno.BRANCAS);
    }
    public Turno turno = Turno.BRANCAS;

    public void startGame() {

    }
    public void escolherCelula(int i, int j){
        tabuleiro.tabCelulas[i][j] = turno == Turno.BRANCAS ? Celula.BRANCA : Celula.PRETA;
        tabuleiro.sanduichar(i, j, turno);
        turno = turno == Turno.BRANCAS ? Turno.PRETAS : Turno.BRANCAS;
        tabuleiro.addPossibiblePlayFrom(turno);
    }
}
