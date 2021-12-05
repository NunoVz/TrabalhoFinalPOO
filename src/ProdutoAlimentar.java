import java.io.Serializable;

public class ProdutoAlimentar extends Produto implements Serializable {
    private int nCalorias100g;
    private int percentagemGordura;

    public ProdutoAlimentar() {

    }

    public ProdutoAlimentar(int identificador, String nome, float precoUnitario, int stock, int custoTransporte, int nCalorias100g, int percentagemGordura) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.nCalorias100g = nCalorias100g;
        this.percentagemGordura = percentagemGordura;
    }

    public int getnCalorias100g() {
        return nCalorias100g;
    }

    public void setnCalorias100g(int nCalorias100g) {
        this.nCalorias100g = nCalorias100g;
    }

    public int getPercentagemGordura() {
        return percentagemGordura;
    }

    public void setPercentagemGordura(int percentagemGordura) {
        this.percentagemGordura = percentagemGordura;
    }

    public String getType() {
        return "PDALI";
    }
}
