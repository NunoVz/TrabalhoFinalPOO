import java.io.Serializable;

/**
 * O tipo Produto de limpeza.
 */
public class ProdutoLimpeza extends Produto implements Serializable {
    //Grau de Toxicidade varia de 0 a 10//
    private int grauToxicidade;

    /**
     * Cria um novo Produto de limpeza.
     *
     * @param identificador   o identificador
     * @param nome            o nome
     * @param precoUnitario   o preco unitario
     * @param stock           o stock
     * @param custoTransporte o custo de transporte
     * @param grauToxicidade  o grau de toxicidade
     */
    public ProdutoLimpeza(int identificador, String nome, float precoUnitario, int stock, int custoTransporte, int grauToxicidade) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.grauToxicidade = grauToxicidade;
    }

    /**
     * Gets grau de toxicidade.
     *
     * @return o grau de toxicidade
     */
    public int getGrauToxicidade() {
        return grauToxicidade;
    }

    /**
     * Sets grau de toxicidade.
     *
     * @param grauToxicidade o grau de toxicidade
     */
    public void setGrauToxicidade(int grauToxicidade) {
        this.grauToxicidade = grauToxicidade;
    }

    public String getType() {
        return "PDLIMP";
    }
}
