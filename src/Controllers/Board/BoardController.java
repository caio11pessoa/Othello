package Controllers.Board;

import Models.Celula;
import Models.Player;
import Models.Tabuleiro;
import Models.Turno;

public class BoardController {
    public Player player;
    public Tabuleiro tabuleiro = new Tabuleiro();
    public Turno turno = Turno.BRANCAS;

    public BoardController(Player player) {
        this.player = player;
        tabuleiro.preencherTabuleiro();
        tabuleiro.preencherNaoClicavel();
        tabuleiro.addPossibiblePlayFrom(Turno.BRANCAS);
    }

    public void clickCell(int i, int j) {
        tabuleiro.tabCelulas[i][j] = turno == Turno.BRANCAS ? Celula.BRANCA : Celula.PRETA;
        tabuleiro.sanduichar(i, j, turno);
        turno = turno == Turno.BRANCAS ? Turno.PRETAS : Turno.BRANCAS;
        tabuleiro.addPossibiblePlayFrom(turno);
    }
}
