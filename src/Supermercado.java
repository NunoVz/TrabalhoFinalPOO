import java.io.Serializable;
import java.util.ArrayList;

/**
 * O tipo Supermercado.
 */
public class Supermercado implements Serializable {
    private String nome;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocoes;

    /**
     * Cria um novo Supermercado.
     */
    public Supermercado() {

    }

    /**
     * Cria um novo Supermercado.
     *
     * @param nome      o nome
     * @param produtos  os produtos
     * @param promocoes as promocoes
     */
    public Supermercado(String nome, ArrayList<Produto> produtos, ArrayList<Promocao> promocoes) {
        this.nome = nome;
        this.produtos = produtos;
        this.promocoes = promocoes;
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
     * Sets produtos.
     *
     * @param produtos os produtos
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Gets promocoes.
     *
     * @return as promocoes
     */
    public ArrayList<Promocao> getPromocoes() {
        return promocoes;
    }

    /**
     * Sets promocoes.
     *
     * @param promocoes as promocoes
     */
    public void setPromocoes(ArrayList<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    /**
     * Gets produtos.
     *
     * @return as produtos
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Gets nome.
     *
     * @return as nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Add produto.
     *
     * @param identificador   o identificador
     * @param nome            o nome
     * @param precoUnitario   o preco unitario
     * @param stock           o stock
     * @param custoTransporte o custo de transporte
     */
    public void add_produto(int identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        //Adcionar um produto ao supermercado
        produtos.add(new Produto(identificador, nome, precoUnitario, stock, custoTransporte));
    }

    /**
     * Get promocao array list.
     *
     * @param promos as promos
     * @param tipo   o tipo
     * @return a array list
     */
    public ArrayList<Promocao> getPromocao(ArrayList<Promocao> promos, String tipo){
        //Função para retornar as promoções conforme o tipo requesitado
        ArrayList<Promocao> promoTipo=new ArrayList<>();
        for(Promocao b:promos){
            if (b.getTipo().equals(tipo)){
                promoTipo.add(b);
            }
        }
        if((promoTipo.size())!=0){
            return promoTipo;

        }
        //Se retornar null significa que não existe promoções
        return null;
    }

    @Override
    public String toString() {
        return "Supermercado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
