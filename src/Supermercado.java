import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado implements Serializable {
    private String nome;
    private ArrayList<Produto> produtos;


    public Supermercado(String nome, ArrayList<Produto> produtos) {
        this.nome = nome;
        this.produtos = produtos;
    }

    public void add_produto(String identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        produtos.add(new Produto(identificador, nome, precoUnitario, stock, custoTransporte));
    }


    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Supermercado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
