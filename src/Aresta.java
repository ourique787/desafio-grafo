public class Aresta {
    private String origem;
    private String destino;
    private double distancia;
    private double custo;

    public Aresta(String origem, String destino, double distancia, double custo) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.custo = custo;
    }

    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public double getDistancia() { return distancia; }
    public double getCusto() { return custo; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Aresta)) return false;
        Aresta outra = (Aresta) obj;
        return origem.equals(outra.origem) && destino.equals(outra.destino);
    }

    @Override
    public int hashCode() {
        return origem.hashCode() + destino.hashCode();
    }
}