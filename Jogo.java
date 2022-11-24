package JogoDaVelha;

import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String marcador = "X";
        int[] placar = new int[2];
        int linha, coluna, partidas;
        String[][] jogada = new String[3][3];
        boolean valida, vitoria;


        System.out.print("Digite o n�mero de partidas: ");
        partidas = scan.nextInt();

//        Fiz a verifica��o do loop das partidas somando o placar que � um vetor com 2 posi��es.
//        Cada partida terminaria com algu�m ganhando,
//        ent�o a soma do placar do jogador 1 e 2 ser� igual ao n�mero de partidas jogadas
//        at� que o n�mero de partidas seja igual ao que o usu�rio pediu

        while (placar[0] + placar[1] != partidas) {
            System.out.println("\nNova Rodada!");
            iniciaTabuleiro(jogada);
            imprimeTabuleiro(jogada);

//            Sempre alternar jogadores para o Jogador 2 ter a chance de iniciar,
//            inclusive numa nova rodada

            do {
                do {
                    System.out.printf("Jogador %s, sua vez", marcador);
                    System.out.print("\nDigite a linha: ");
                    linha = scan.nextInt();
                    System.out.print("Digite a coluna: ");
                    coluna = scan.nextInt();

                    valida = verificaJogada(jogada, marcador, linha, coluna);

//          Se a fun��o verificaJogada n�o retornar um valor true
//          voltamos ao loop para que o jogador repita as estradas

                } while (!valida);

                System.out.println();
                imprimeTabuleiro(jogada);

//          A fun��o verificaVitoria vai retornar um valor que ser� usada no final do do-while
//          para terminar a partida se algu�m j� ganhou

                vitoria = verificaVitoria(jogada, marcador);

                if (vitoria){
                    if (marcador.equals("X")) {
                        placar[0]++;
                    } else { placar[1]++;}
                    System.out.printf("O Jogador %s ganhou essa rodada!", marcador);
                    System.out.println("\n      Placar");
                    System.out.printf("Jogador 1: %d ", placar[0]);
                    System.out.printf("\nJogador 2: %d ", placar[1]);
                }
                if (marcador.equals("X")){
                    marcador = "O";
                } else {
                    marcador = "X";}

            } while (!vitoria);

            System.out.println();
        }

//      Aqui mostramos quem ganhou ao final do n�mero de partidas selecionado pelo usu�rio

        if (placar[0] > placar[1]) {
            System.out.println("Jogador 1 venceu o jogo!!");
        } else if (placar[0] < placar[1]) {
            System.out.println("Jogador 2 venceu o jogo!!");
        } else {
            System.out.println("Empate!!");
        }
    }

    public static void iniciaTabuleiro(String[][] jogada) {
//      Preenche o tabuleiro (matriz) com valores de String em branco
//      para que a verifica��o seja no verificaJogada

        String branco = " ";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogada[i][j] = branco;
            }
        }
    }

    public static void imprimeTabuleiro(String[][] jogada) {
        System.out.println("   c0   c1  c2");
        System.out.printf("l0  %s | %s | %s \n", jogada[0][0], jogada[0][1], jogada[0][2]);
        System.out.println("    _________");
        System.out.printf("l1  %s | %s | %s \n", jogada[1][0], jogada[1][1], jogada[1][2]);
        System.out.println("    _________");
        System.out.printf("l2  %s | %s | %s \n", jogada[2][0], jogada[2][1], jogada[2][2]);
        System.out.println();
    }

    public static boolean verificaVitoria(String[][] jogada, String marcador) {
        boolean vitoria = false;

//      Vit�ria por linha
        if (jogada[0][0].equals(marcador) && jogada[0][1].equals(marcador) && jogada[0][2].equals(marcador)) {
            vitoria = true;
        } else if (jogada[1][0].equals(marcador) && jogada[1][1].equals(marcador) && jogada[1][2].equals(marcador)) {
            vitoria = true;
        } else if (jogada[2][0].equals(marcador) && jogada[2][1].equals(marcador) && jogada[2][2].equals(marcador)) {
            vitoria = true;
        }
//      Vit�ria por coluna
        if (jogada[0][0].equals(marcador) && jogada[1][0].equals(marcador) && jogada[2][0].equals(marcador)) {
            vitoria = true;
        } else if (jogada[0][1].equals(marcador) && jogada[1][1].equals(marcador) && jogada[2][1].equals(marcador)) {
            vitoria = true;
        } else if (jogada[0][2].equals(marcador) && jogada[1][2].equals(marcador) && jogada[2][2].equals(marcador)) {
            vitoria = true;
        }
//      V�toria por diagonal
        if (jogada[0][0].equals(marcador) && jogada[1][1].equals(marcador) && jogada[2][2].equals(marcador)) {
            vitoria = true;
        } else if (jogada[0][2].equals(marcador) && jogada[1][1].equals(marcador) && jogada[2][0].equals(marcador)) {
            vitoria = true;
        }
        return vitoria;
    }

    public static boolean verificaJogada(String[][] jogada, String marcador, int linha, int coluna) {
//      Fun��o que verifica se a jogada � v�lida.
//      Ou seja, se o valor digitado est� no tabuleiro(matriz) ou se j� n�o foi ocupada.

        String branco = " ";
        boolean validada = false;
        try {
            if (jogada[linha][coluna].equals(branco)) {
                jogada[linha][coluna] = marcador;
                validada = true;
            } else {
                System.out.println("Jogada Inv�lida. Espa�o j� ocupado");
            }
        }
            catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Jogada Inv�lida. Escolha entre as linhas e colunas 0, 1 ou 2");
        }
      return validada;
    }
}