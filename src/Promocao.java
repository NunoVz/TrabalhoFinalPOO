import java.io.Serializable;

/**
 * O tipo Promocao.
 */
public class Promocao implements Serializable {
    private Produto produto;
    private String tipo;
    private Data dataInicio;
    private Data dataFim;

    /**
     * Cria uma nova Promocao.
     */
    public Promocao() {

    }

    /**
     * Cria uma nova Promocao.
     *
     * @param produto    o produto
     * @param tipo       o tipo
     * @param dataInicio o data de inicio de promo
     * @param dataFim    o data de fim de promo
     */
    public Promocao(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        this.produto = produto;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * Gets data de inicio.
     *
     * @return a data de inicio
     */
    public Data getDataInicio() {
        return dataInicio;
    }

    /**
     * Sets data inicio.
     *
     * @param dataInicio a data de inicio
     */
    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Gets data fim.
     *
     * @return a data de fim
     */
    public Data getDataFim() {
        return dataFim;
    }

    /**
     * Sets data fim.
     *
     * @param dataFim a data de fim
     */
    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Sets produto.
     *
     * @param produto o produto
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Sets tipo.
     *
     * @param tipo o tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets tipo.
     *
     * @return o tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Gets produto.
     *
     * @return o produto
     */
    public Produto getProduto() {
        return produto;
    }
}