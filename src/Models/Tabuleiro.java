package Models;

public class Tabuleiro {
    public Celula[][] tabCelulas = new Celula[8][8];

    public void preencherTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 3 && i == 3 || j == 4 && i == 4) {
                    tabCelulas[i][j] = Celula.BRANCA;
                } else if (j == 4 && i == 3 || j == 3 && i == 4) {
                    tabCelulas[i][j] = Celula.PRETA;
                }
            }
        }
    }

    public void addPossibiblePlayFrom(Turno turno) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Celula propriaCelula;
                Celula rivalCelula;
                switch (turno) {
                    case PRETAS:
                        propriaCelula = Celula.PRETA;
                        rivalCelula = Celula.BRANCA;
                        break;
                    case BRANCAS:
                        propriaCelula = Celula.BRANCA;
                        rivalCelula = Celula.PRETA;
                        break;

                    default:
                        propriaCelula = Celula.VAZIO;
                        rivalCelula = Celula.VAZIO;
                        break;
                }

                if (tabCelulas[i][j] == rivalCelula) {
                    int k = i;
                    int z = j;

                    // Verificação 1 (Baixo)
                    while (k > 0) {
                        k -= 1;
                        Celula verifyCelula = tabCelulas[k][j];
                        if (verifyCelula == null || verifyCelula == Celula.VAZIO) {
                            break;
                        } else if (verifyCelula == propriaCelula) {
                            // Corre à direita
                            int correDireitaIndex = i;
                            while (correDireitaIndex < 7) {
                                correDireitaIndex += 1;
                                Celula verifyCorreDireitaCelula = tabCelulas[correDireitaIndex][j];
                                if (verifyCorreDireitaCelula == rivalCelula) {
                                    continue;
                                } else if (verifyCorreDireitaCelula == propriaCelula) {
                                    break;
                                } else {
                                    tabCelulas[correDireitaIndex][j] = Celula.CLICAVEL;
                                    break;
                                }
                            }
                        } else if (verifyCelula == rivalCelula) {
                            continue;
                        }
                    }

                    // Verificação 1 (Cima)
                    while (k < 7) {
                        k += 1;
                        Celula verifyCelula = tabCelulas[k][j];
                        if (verifyCelula == null || verifyCelula == Celula.VAZIO) {
                            break;
                        } else if (verifyCelula == propriaCelula) {
                            // Corre à direita
                            int correDireitaIndex = i;
                            while (correDireitaIndex > 0) {
                                correDireitaIndex -= 1;
                                Celula verifyCorreDireitaCelula = tabCelulas[correDireitaIndex][j];
                                if (verifyCorreDireitaCelula == rivalCelula) {
                                    continue;
                                } else if (verifyCorreDireitaCelula == propriaCelula) {
                                    break;
                                } else {
                                    tabCelulas[correDireitaIndex][j] = Celula.CLICAVEL;
                                    break;
                                }
                            }
                        } else if (verifyCelula == rivalCelula) {
                            continue;
                        }
                    }

                    // Verificação 3 (Esquerda)
                    while (z < 7) {
                        z += 1;
                        Celula verifyCelula = tabCelulas[i][z];
                        if (verifyCelula == null || verifyCelula == Celula.VAZIO) {
                            break;
                        } else if (verifyCelula == propriaCelula) {
                            // Corre à direita
                            int correDireitaIndex = j;
                            while (correDireitaIndex > 0) {
                                correDireitaIndex -= 1;
                                Celula verifyCorreDireitaCelula = tabCelulas[i][correDireitaIndex];
                                if (verifyCorreDireitaCelula == rivalCelula) {
                                    continue;
                                } else if (verifyCorreDireitaCelula == propriaCelula) {
                                    break;
                                } else {
                                    tabCelulas[i][correDireitaIndex] = Celula.CLICAVEL;
                                    break;
                                }
                            }
                        } else if (verifyCelula == rivalCelula) {
                            continue;
                        }
                    }

                    // Verificação 4 (Direita)
                    while (z > 0) {
                        z -= 1;
                        Celula verifyCelula = tabCelulas[i][z];
                        if (verifyCelula == null || verifyCelula == Celula.VAZIO) {
                            break;
                        } else if (verifyCelula == propriaCelula) {
                            // Corre à direita
                            int correDireitaIndex = j;
                            while (correDireitaIndex > 0) {
                                correDireitaIndex += 1;
                                Celula verifyCorreDireitaCelula = tabCelulas[i][correDireitaIndex];
                                if (verifyCorreDireitaCelula == rivalCelula) {
                                    continue;
                                } else if (verifyCorreDireitaCelula == propriaCelula) {
                                    break;
                                } else {
                                    tabCelulas[i][correDireitaIndex] = Celula.CLICAVEL;
                                    break;
                                }
                            }
                        } else if (verifyCelula == rivalCelula) {
                            continue;
                        }
                    }
                }
            }
        }
    }

    public void preencherNaoClicavel() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabCelulas[i][j] == null) {
                    tabCelulas[i][j] = Celula.VAZIO;
                }
            }
        }
    }

}
