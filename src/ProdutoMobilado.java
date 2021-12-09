import java.io.Serializable;

/**
 * O tipo Produto mobilado.
 */
public class ProdutoMobilado extends Produto implements Serializable {
    private int peso;
    //dimensao composta por altura/largura/profundidade
    private String dimensao;

    /**
     * Cria um novo Produtor mobilado.
     */
    public ProdutoMobilado() {

    }

    /**
     * Cria um novo Produtor de mobilado.
     *
     * @param identificador   o identificador
     * @param nome            o nome
     * @param precoUnitario   o preco unitario
     * @param stock           o stock
     * @param custoTransporte o custo de transporte
     * @param peso            o peso
     * @param dimensao        a dimensao
     */
    public ProdutoMobilado(int identificador, String nome, float precoUnitario, int stock, int custoTransporte, int peso, String dimensao) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    /**
     * Sets peso.
     *
     * @param peso o peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Gets dimensao.
     *
     * @return a dimensao
     */
    public String getDimensao() {
        return dimensao;
    }

    /**
     * Sets dimensao.
     *
     * @param dimensao a dimensao
     */
    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    /**
     * Gets peso.
     *
     * @return o peso
     */
    public int getPeso() {
        return peso;
    }

    public String getType() {
        return "PDMOB";
    }

    /* * @param custoTransporte o custo de transporte
     * @param peso            o peso
     * @param dimensao        a dimensao*/

    @Override
    public String toString() {
        return " - Custo de transporte: " + custoTransporte + " - Peso: " + peso + "Dimensao: " + dimensao;
    }
}
