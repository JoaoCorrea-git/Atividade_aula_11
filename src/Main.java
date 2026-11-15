public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== BEM-VINDO AO JOGO DA VELHA ===");
    System.out.print("Informe o nome do Jogador 1 (X): ");
    String nome1 = scanner.nextLine();
    System.out.print("Informe o nome do Jogador 2 (O): ");
    String nome2 = scanner.nextLine();

    JogoDaVelha jogo = new JogoDaVelha(nome1, nome2);

    char continuar = 's';
    while (continuar == 's' || continuar == 'S') {
        jogo.iniciarPartida();
        System.out.print("\nDeseja jogar outra partida? (s/n): ");
        continuar = scanner.next().charAt(0);
    }

    System.out.println("\nObrigado por jogar! Até a próxima.");
    scanner.close();
}