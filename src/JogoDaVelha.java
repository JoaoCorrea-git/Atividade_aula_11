import java.util.Scanner;

class Jogador {
    String nome;
    int pontos;
    String simbolo;

    public Jogador(String nome, String simbolo) {
        this.nome = nome;
        this.pontos = 0;
        this.simbolo = simbolo;
    }

    public void adicionarPonto() {
        this.pontos++;
    }
}

public class JogoDaVelha {
    private String[][] tabuleiro;
    private Jogador[] jogadores; // Vetor heterogêneo de jogadores
    private int turnoAtual; // 0 para Jogador 1 (X), 1 para Jogador 2 (O)

    // Construtor para inicializar o jogo com os nomes dos jogadores
    public JogoDaVelha(String nome1, String nome2) {
        tabuleiro = new String[3][3];
        jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nome1, "X");
        jogadores[1] = new Jogador(nome2, "O");
        turnoAtual = 0;
        inicializarTabuleiro();
    }

    // Método para preencher o tabuleiro com espaços vazios
    public void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    // Método para exibir o tabuleiro formatado no console
    public void exibirTabuleiro() {
        System.out.println("\n    0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" [" + tabuleiro[i][j] + "] ");
            }
            System.out.println("\n");
        }
    }

    // Método com parâmetros para realizar a jogada validando regras
    public boolean fazerJogada(int linha, int coluna, String simbolo) {
        // Validação de limites da matriz
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            System.out.println("❌ Posição inválida! Escolha linhas e colunas entre 0 e 2.");
            return false;
        }

        // Validação de posição ocupada
        if (!tabuleiro[linha][coluna].equals(" ")) {
            System.out.println("❌ Esta posição já está ocupada! Tente novamente.");
            return false;
        }

        tabuleiro[linha][coluna] = simbolo;
        return true;
    }

    // Método para verificar vitória por linha, coluna ou diagonal
    public boolean verificarVitoria(String simbolo) {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)) ||
                    (tabuleiro[0][i].equals(simbolo) && tabuleiro[1][i].equals(simbolo) && tabuleiro[2][i].equals(simbolo))) {
                return true;
            }
        }

        // Verifica diagonais
        if ((tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)) ||
                (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo))) {
            return true;
        }

        return false;
    }

    // Método para verificar se o tabuleiro está cheio (Empate)
    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j].equals(" ")) {
                    return false; // Encontrou espaço vazio, jogo continua
                }
            }
        }
        return true;
    }

    // Método principal de controle de fluxo de uma partida
    public void iniciarPartida() {
        Scanner scanner = new Scanner(System.in);
        boolean jogoAtivo = true;
        inicializarTabuleiro();

        while (jogoAtivo) {
            exibirTabuleiro();
            Jogador jogadorAtual = jogadores[turnoAtual];
            System.out.println("Vez de " + jogadorAtual.nome + " (" + jogadorAtual.simbolo + ")");

            System.out.print("Digite a linha (0, 1 ou 2): ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna (0, 1 ou 2): ");
            int coluna = scanner.nextInt();

            // Tenta fazer a jogada
            if (fazerJogada(linha, coluna, jogadorAtual.simbolo)) {
                // Verifica se o jogador atual venceu
                if (verificarVitoria(jogadorAtual.simbolo)) {
                    exibirTabuleiro();
                    System.out.println("🎉 FIM DE JOGO! O vencedor é: " + jogadorAtual.nome + "!");
                    jogadorAtual.adicionarPonto(); // Atribui 1 ponto ao vencedor
                    jogoAtivo = false;
                } else if (verificarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("🤝 FIM DE JOGO! A partida terminou em empate!");
                    jogoAtivo = false;
                } else {
                    // Alterna o turno (0 vira 1, 1 vira 0)
                    turnoAtual = (turnoAtual + 1) % 2;
                }
            }
        }

        exibirPlacarFinal();
    }

    // Método para exibir o placar final e o estado do tabuleiro
    public void exibirPlacarFinal() {
        System.out.println("\n==================================");
        System.out.println("          PLACAR FINAL            ");
        System.out.println("==================================");
        for (Jogador j : jogadores) {
            System.out.println("Jogador: " + j.nome + " (" + j.simbolo + ") - Pontos: " + j.pontos);
        }
        System.out.println("==================================");
    }
}