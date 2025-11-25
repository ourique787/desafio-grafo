// BuscadorRotas.java
import java.util.*;

public class BuscadorRotas {
    private Grafo grafo;

    public BuscadorRotas(Grafo grafo) {
        this.grafo = grafo;
    }

    public Rota encontrarRotaMenorCusto(String origem, String destino, double custoMaximo) {
        PriorityQueue<Estado> fila = new PriorityQueue<>(Comparator.comparingDouble(e -> e.custoAcumulado));
        Map<String, Double> menorCusto = new HashMap<>();

        fila.offer(new Estado(origem, new ArrayList<>(Arrays.asList(origem)), 0, 0));

        Rota melhorRota = null;

        while (!fila.isEmpty()) {
            Estado atual = fila.poll();

            if (atual.cidade.equals(destino)) {
                if (atual.custoAcumulado <= custoMaximo) {
                    return new Rota(atual.caminho, atual.custoAcumulado, atual.distanciaAcumulada);
                }
                continue;
            }

            if (menorCusto.containsKey(atual.cidade) && menorCusto.get(atual.cidade) < atual.custoAcumulado) {
                continue;
            }
            menorCusto.put(atual.cidade, atual.custoAcumulado);

            for (Aresta aresta : grafo.getVizinhos(atual.cidade)) {
                double novoCusto = atual.custoAcumulado + aresta.getCusto();
                double novaDistancia = atual.distanciaAcumulada + aresta.getDistancia();

                if (novoCusto <= custoMaximo && !atual.caminho.contains(aresta.getDestino())) {
                    List<String> novoCaminho = new ArrayList<>(atual.caminho);
                    novoCaminho.add(aresta.getDestino());
                    fila.offer(new Estado(aresta.getDestino(), novoCaminho, novoCusto, novaDistancia));
                }
            }
        }

        return null;
    }

    public Rota encontrarRotaMenorDistancia(String origem, String destino) {
        PriorityQueue<Estado> fila = new PriorityQueue<>(Comparator.comparingDouble(e -> e.distanciaAcumulada));
        Map<String, Double> menorDistancia = new HashMap<>();

        fila.offer(new Estado(origem, new ArrayList<>(Arrays.asList(origem)), 0, 0));

        while (!fila.isEmpty()) {
            Estado atual = fila.poll();

            if (atual.cidade.equals(destino)) {
                return new Rota(atual.caminho, atual.custoAcumulado, atual.distanciaAcumulada);
            }

            if (menorDistancia.containsKey(atual.cidade) && menorDistancia.get(atual.cidade) < atual.distanciaAcumulada) {
                continue;
            }
            menorDistancia.put(atual.cidade, atual.distanciaAcumulada);

            for (Aresta aresta : grafo.getVizinhos(atual.cidade)) {
                if (!atual.caminho.contains(aresta.getDestino())) {
                    List<String> novoCaminho = new ArrayList<>(atual.caminho);
                    novoCaminho.add(aresta.getDestino());
                    fila.offer(new Estado(aresta.getDestino(), novoCaminho,
                            atual.custoAcumulado + aresta.getCusto(),
                            atual.distanciaAcumulada + aresta.getDistancia()));
                }
            }
        }

        return null;
    }

    private static class Estado {
        String cidade;
        List<String> caminho;
        double custoAcumulado;
        double distanciaAcumulada;

        Estado(String cidade, List<String> caminho, double custo, double distancia) {
            this.cidade = cidade;
            this.caminho = caminho;
            this.custoAcumulado = custo;
            this.distanciaAcumulada = distancia;
        }
    }
}
