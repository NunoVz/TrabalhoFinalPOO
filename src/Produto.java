import java.io.Serializable;

/**
 * O tipo Produto.
 */
public class Produto implements Serializable {
    /**
     * O Identificador.
     */
    protected int identificador;
    /**
     * O Nome.
     */
    protected String nome;
    /**
     * O Preco unitario.
     */
    protected float precoUnitario;
    /**
     * O Stock.
     */
    protected int stock;
    /**
     * O Custo de transporte.
     */
    protected int custoTransporte;
    /**
     * A Quantidade que consta no carrinho.
     */
    protected int quantidade_carrinho;

    /**
     * Cria um novo Produto.
     */
    public Produto() {

    }

    /**
     * Cria um novo Produto.
     *
     * @param identificador   o identificador
     * @param nome            o nome
     * @param precoUnitario   o preco unitario
     * @param stock           o stock
     * @param custoTransporte o custo de transporte
     */
    public Produto(int identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.stock = stock;
        this.custoTransporte = custoTransporte;
    }

    /**
     * Gets custo de transporte.
     *
     * @return custo de transporte
     */
    public int getCustoTransporte() {
        return custoTransporte;
    }

    /**
     * Sets custo de transporte.
     *
     * @param custoTransporte custo de transporte
     */
    public void setCustoTransporte(int custoTransporte) {
        this.custoTransporte = custoTransporte;
    }

    /**
     * Gets identificador.
     *
     * @return o identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Sets identificador.
     *
     * @param identificador o identificador
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Gets nome.
     *
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome o nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets preco unitario.
     *
     * @return o preco unitario
     */
    public float getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Sets preco unitario.
     *
     * @param precoUnitario o preco unitario
     */
    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Gets stock.
     *
     * @return o stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock o stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets tipo.
     *
     * @return o tipo
     */
    public String getType() {
        return "PD";
    }

    /**
     * Gets quantidade carrinho.
     *
     * @return a quantidade que consta no carrinho
     */
    public int getQuantidade_carrinho() {
        return quantidade_carrinho;
    }

    /**
     * Sets quantidade que consta no carrinho.
     *
     * @param quantidade_carrinho aquantidade que consta no carrinho
     */
    public void setQuantidade_carrinho(int quantidade_carrinho) {
        this.quantidade_carrinho = quantidade_carrinho;
    }

    /**
     * Guardar data string.
     *
     * @return a string
     */
    public String guardarData() {
        return getType() + " " + identificador
                + " " + nome + " " + precoUnitario + " " + stock + " " + custoTransporte;
    }

    @Override
    public String toString() {
        return nome;
    }
}
