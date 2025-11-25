import java.util.Scanner;

public class SistemaCargoFast {
    private Grafo grafo;
    private BuscadorRotas buscador;
    private Scanner scanner;

    public SistemaCargoFast() {
        grafo = new Grafo();
        buscador = new BuscadorRotas(grafo);
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Sistema de Otimização de Rotas CargoFast ===\n");

        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarEstrada(); break;
                case 2: bloquearEstrada(); break;
                case 3: liberarEstrada(); break;
                case 4: calcularRota(); break;
                case 5: carregarExemplo(); break;
                case 6: continuar = false; break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Adicionar estrada");
        System.out.println("2. Bloquear estrada");
        System.out.println("3. Liberar estrada");
        System.out.println("4. Calcular rota");
        System.out.println("5. Carregar exemplo do desafio");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void adicionarEstrada() {
        System.out.print("Cidade origem: ");
        String origem = scanner.nextLine();
        System.out.print("Cidade destino: ");
        String destino = scanner.nextLine();
        System.out.print("Distância (km): ");
        double distancia = scanner.nextDouble();
        System.out.print("Custo (R$): ");
        double custo = scanner.nextDouble();
        scanner.nextLine();

        grafo.adicionarEstrada(origem, destino, distancia, custo);
        System.out.println("Estrada adicionada com sucesso!");
    }

    private void bloquearEstrada() {
        System.out.print("Cidade origem: ");
        String origem = scanner.nextLine();
        System.out.print("Cidade destino: ");
        String destino = scanner.nextLine();

        grafo.bloquearEstrada(origem, destino);
        System.out.println("Estrada bloqueada!");
    }

    private void liberarEstrada() {
        System.out.print("Cidade origem: ");
        String origem = scanner.nextLine();
        System.out.print("Cidade destino: ");
        String destino = scanner.nextLine();

        grafo.liberarEstrada(origem, destino);
        System.out.println("Estrada liberada!");
    }

    private void calcularRota() {
        System.out.print("Cidade de partida: ");
        String origem = scanner.nextLine();
        System.out.print("Cidade de destino: ");
        String destino = scanner.nextLine();
        System.out.print("Custo máximo (R$, 0 para sem limite): ");
        double custoMaximo = scanner.nextDouble();
        scanner.nextLine();

        if (custoMaximo > 0) {
            Rota rota = buscador.encontrarRotaMenorCusto(origem, destino, custoMaximo);

            if (rota != null) {
                System.out.println("\nA rota de menor custo dentro do orçamento é: " + rota.getCaminhoFormatado());
                System.out.printf("Custo Total: R$ %.2f\n", rota.getCustoTotal());
                System.out.printf("Distância Total: %.2f km\n", rota.getDistanciaTotal());
            } else {
                Rota alternativa = buscador.encontrarRotaMenorDistancia(origem, destino);

                if (alternativa != null) {
                    System.out.println("\nNenhuma rota encontrada dentro do limite de custo de R$ " + custoMaximo);
                    System.out.println("A rota alternativa mais curta é: " + alternativa.getCaminhoFormatado());
                    System.out.printf("Custo Total: R$ %.2f\n", alternativa.getCustoTotal());
                    System.out.printf("Distância Total: %.2f km\n", alternativa.getDistanciaTotal());
                } else {
                    System.out.println("\nNão há caminho disponível entre " + origem + " e " + destino + " devido aos bloqueios.");
                }
            }
        } else {
            Rota rota = buscador.encontrarRotaMenorDistancia(origem, destino);

            if (rota != null) {
                System.out.println("\nA rota de menor distância é: " + rota.getCaminhoFormatado());
                System.out.printf("Custo Total: R$ %.2f\n", rota.getCustoTotal());
                System.out.printf("Distância Total: %.2f km\n", rota.getDistanciaTotal());
            } else {
                System.out.println("\nNão há caminho disponível entre " + origem + " e " + destino + ".");
            }
        }
    }

    private void carregarExemplo() {
        grafo.adicionarEstrada("A", "B", 10, 5);
        grafo.adicionarEstrada("B", "C", 20, 15);
        grafo.adicionarEstrada("C", "E", 25, 10);
        grafo.adicionarEstrada("B", "D", 30, 20);
        grafo.adicionarEstrada("A", "E", 50, 40);
        grafo.bloquearEstrada("B", "C");

        System.out.println("Exemplo carregado com sucesso!");
        System.out.println("Cidades: A, B, C, D, E");
        System.out.println("Estrada B-C está bloqueada");
        System.out.println("Teste com: Origem=A, Destino=E, Custo Máximo=30");
    }

    public static void main(String[] args) {
        SistemaCargoFast sistema = new SistemaCargoFast();
        sistema.iniciar();
    }
}