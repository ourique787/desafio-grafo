import java.util.*;

public class Rota {
    private List<String> caminho;
    private double custoTotal;
    private double distanciaTotal;

    public Rota(List<String> caminho, double custoTotal, double distanciaTotal) {
        this.caminho = new ArrayList<>(caminho);
        this.custoTotal = custoTotal;
        this.distanciaTotal = distanciaTotal;
    }

    public List<String> getCaminho() { return caminho; }
    public double getCustoTotal() { return custoTotal; }
    public double getDistanciaTotal() { return distanciaTotal; }

    public String getCaminhoFormatado() {
        return String.join(" -> ", caminho);
    }
}