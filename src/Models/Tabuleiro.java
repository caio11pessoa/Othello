package Models;

import java.util.*;

public class Tabuleiro {
    public Celula[][] tabCelulas = new Celula[8][8];

    public void preencherTabuleiro() {
        tabCelulas[3][3] = Celula.BRANCA;
        tabCelulas[4][4] = Celula.BRANCA;
        tabCelulas[4][3] = Celula.PRETA;
        tabCelulas[3][4] = Celula.PRETA;
    }

    public void addPossibiblePlayFrom(Turno turno) {
        clean();
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
                            int correDireitaIndex = j;
                            while (correDireitaIndex < 7) {
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

    public void clean() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabCelulas[i][j] == Celula.CLICAVEL) {
                    tabCelulas[i][j] = Celula.VAZIO;
                }
            }
        }
    }

    public void sanduichar(int i, int j, Turno turno) {
        switch (turno) {
            case BRANCAS:
                // Baixo i-1
                int v = i - 1;
                List<int[]> save = new ArrayList<>();
                save.add(new int[] { v, j });
                if (v >= 0 && tabCelulas[v][j] == Celula.PRETA) {
                    while (v > 0) {
                        v -= 1;
                        if (tabCelulas[v][j] == Celula.PRETA) {
                            save.add(new int[] { v, j });
                        }
                        if (tabCelulas[v][j] == Celula.BRANCA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.BRANCA;
                            }
                        }
                    }
                }

                // Cima
                v = i + 1;
                if (v <= 7 && tabCelulas[v][j] == Celula.PRETA) {
                    save = new ArrayList<>();
                    save.add(new int[] { v, j });
                    while (v < 7) {
                        v += 1;
                        if (tabCelulas[v][j] == Celula.PRETA) {
                            save.add(new int[] { v, j });
                        }
                        if (tabCelulas[v][j] == Celula.BRANCA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.BRANCA;
                            }
                        }
                    }
                }

                // direita
                int k = j - 1;
                if (k >= 0 && tabCelulas[i][k] == Celula.PRETA) {
                    save = new ArrayList<>();
                    save.add(new int[] { i, k });
                    while (k > 0) {
                        k -= 1;
                        if (tabCelulas[i][k] == Celula.PRETA) {
                            save.add(new int[] { i, k });
                        }
                        if (tabCelulas[i][k] == Celula.BRANCA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.BRANCA;
                            }
                            save = new ArrayList<>();
                        }
                    }
                }

                // Esquerda
                k = j + 1;
                if (k <= 7 && tabCelulas[i][k] == Celula.PRETA) {
                    save = new ArrayList<>();
                    save.add(new int[] { i, k });
                    while (k < 7) {
                        k += 1;
                        if (tabCelulas[i][k] == Celula.PRETA) {
                            save.add(new int[] { i, k });
                        }
                        if (tabCelulas[i][k] == Celula.BRANCA) {
                            // makeWhite
                            // tabCelulas[i][k-1] = Celula.BRANCA;
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.BRANCA;
                            }
                            save = new ArrayList<>();
                            break;
                        }
                    }
                }

                break;
            case PRETAS:
                
                v = i - 1;
                save = new ArrayList<>();
                save.add(new int[] { v, j });
                if (v >= 0 && tabCelulas[v][j] == Celula.BRANCA) {
                    while (v > 0) {
                        v -= 1;
                        if (tabCelulas[v][j] == Celula.BRANCA) {
                            save.add(new int[] { v, j });
                        }
                        if (tabCelulas[v][j] == Celula.PRETA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.PRETA;
                            }
                        }
                    }
                }

                // Cima
                v = i + 1;
                if (v <= 7 && tabCelulas[v][j] == Celula.BRANCA) {
                    save = new ArrayList<>();
                    save.add(new int[] { v, j });
                    while (v < 7) {
                        v += 1;
                        if (tabCelulas[v][j] == Celula.BRANCA) {
                            save.add(new int[] { v, j });
                        }
                        if (tabCelulas[v][j] == Celula.PRETA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.PRETA;
                            }
                        }
                    }
                }

                // direita
                k = j - 1;
                if (k >= 0 && tabCelulas[i][k] == Celula.BRANCA) {
                    save = new ArrayList<>();
                    save.add(new int[] { i, k });
                    while (k > 0) {
                        k -= 1;
                        if (tabCelulas[i][k] == Celula.BRANCA) {
                            save.add(new int[] { i, k });
                        }
                        if (tabCelulas[i][k] == Celula.PRETA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.PRETA;
                            }
                            save = new ArrayList<>();
                        }
                    }
                }

                // Esquerda
                k = j + 1;
                if (k <= 7 && tabCelulas[i][k] == Celula.BRANCA) {
                    save = new ArrayList<>();
                    save.add(new int[] { i, k });
                    while (k < 7) {
                        k += 1;
                        if (tabCelulas[i][k] == Celula.BRANCA) {
                            save.add(new int[] { i, k });
                        }
                        if (tabCelulas[i][k] == Celula.PRETA) {
                            for (int[] indice : save) {
                                tabCelulas[indice[0]][indice[1]] = Celula.PRETA;
                            }
                            save = new ArrayList<>();
                            break;
                        }
                    }
                }

                break;

            default:
                break;
        }

    }

}
