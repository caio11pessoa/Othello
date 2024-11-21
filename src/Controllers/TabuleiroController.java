package Controllers;

import Models.Tabuleiro;
import Models.Turno;

public class TabuleiroController {
    public Tabuleiro tabuleiro = new Tabuleiro();
    {
        tabuleiro.preencherTabuleiro();
        tabuleiro.preencherNaoClicavel();
        tabuleiro.addPossibiblePlayFrom(Turno.PRETAS);
    }

    public void startGame() {

    }
}
