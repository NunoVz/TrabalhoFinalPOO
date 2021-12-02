import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado implements Serializable {
    private String nome;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocoes;


    public Supermercado(String nome, ArrayList<Produto> produtos, ArrayList<Promocao> promocoes) {
        this.nome = nome;
        this.produtos = produtos;
        this.promocoes = promocoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(ArrayList<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public String getNome() {
        return nome;
    }

    public void add_produto(String identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        produtos.add(new Produto(identificador, nome, precoUnitario, stock, custoTransporte));
    }

    @Override
    public String toString() {
        return "Supermercado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
