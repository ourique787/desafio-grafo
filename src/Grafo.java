import java.util.*;

public class Grafo {
    private Map<String, List<Aresta>> adjacencias;
    private Set<Aresta> arestasBloqueadas;

    public Grafo() {
        adjacencias = new HashMap<>();
        arestasBloqueadas = new HashSet<>();
    }

    public void adicionarCidade(String cidade) {
        adjacencias.putIfAbsent(cidade, new ArrayList<>());
    }

    public void adicionarEstrada(String origem, String destino, double distancia, double custo) {
        adicionarCidade(origem);
        adicionarCidade(destino);
        adjacencias.get(origem).add(new Aresta(origem, destino, distancia, custo));
    }

    public void bloquearEstrada(String origem, String destino) {
        arestasBloqueadas.add(new Aresta(origem, destino, 0, 0));
    }

    public void liberarEstrada(String origem, String destino) {
        arestasBloqueadas.remove(new Aresta(origem, destino, 0, 0));
    }

    public List<Aresta> getVizinhos(String cidade) {
        List<Aresta> vizinhos = new ArrayList<>();
        if (adjacencias.containsKey(cidade)) {
            for (Aresta aresta : adjacencias.get(cidade)) {
                if (!arestasBloqueadas.contains(aresta)) {
                    vizinhos.add(aresta);
                }
            }
        }
        return vizinhos;
    }

    public Set<String> getCidades() {
        return adjacencias.keySet();
    }
}