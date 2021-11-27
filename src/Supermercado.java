import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado implements Serializable {
    private String nome;
    private ArrayList<Produto> produtos;
    private PromocaoPagueMenos PM;
    private PromocaoTresQuatro TQ;


    public Supermercado(String nome, ArrayList<Produto> produtos,PromocaoPagueMenos PM,PromocaoTresQuatro TQ) {
        this.nome=nome;
        this.produtos=produtos;
        this.PM=PM;
        this.TQ=TQ;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public String getNome() {
        return nome;
    }

    public PromocaoPagueMenos getPM() {
        return PM;
    }

    public PromocaoTresQuatro getTQ() {
        return TQ;
    }

    @Override
    public String toString() {
        return "Supermercado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
