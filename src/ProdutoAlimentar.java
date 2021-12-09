import java.io.Serializable;

/**
 * O tipo Produto alimentar.
 */
public class ProdutoAlimentar extends Produto implements Serializable {
    private int nCalorias100g;
    private int percentagemGordura;

    /**
     * Cria um novo Produto alimentar.
     */
    public ProdutoAlimentar() {

    }

    /**
     * Cria um novo Produto alimentar.
     *
     * @param identificador      o identificador
     * @param nome               o nome
     * @param precoUnitario      o preco unitario
     * @param stock              o stock
     * @param custoTransporte    o custo de transporte
     * @param nCalorias100g      o numero de calorias por 100 g
     * @param percentagemGordura a percentagem de gordura
     */
    public ProdutoAlimentar(int identificador, String nome, float precoUnitario, int stock, int custoTransporte, int nCalorias100g, int percentagemGordura) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.nCalorias100g = nCalorias100g;
        this.percentagemGordura = percentagemGordura;
    }

    /**
     * Gets calorias por 100 g.
     *
     * @return as calorias por 100 g
     */
    public int getnCalorias100g() {
        return nCalorias100g;
    }

    /**
     * Sets calorias por 100 g.
     *
     * @param nCalorias100g o n calorias por 100 g
     */
    public void setnCalorias100g(int nCalorias100g) {
        this.nCalorias100g = nCalorias100g;
    }

    /**
     * Gets percentagem de gordura.
     *
     * @return a percentagem de gordura
     */
    public int getPercentagemGordura() {
        return percentagemGordura;
    }

    /**
     * Sets percentagem de gordura.
     *
     * @param percentagemGordura o percentagem de gordura
     */
    public void setPercentagemGordura(int percentagemGordura) {
        this.percentagemGordura = percentagemGordura;
    }

    public String getType() {
        return "PDALI";
    }

    @Override
    public String toString() {
        return " - nº Calorias/100g: " + nCalorias100g + " - Percentagem de Gordura: " + percentagemGordura + "%";
    }
}
