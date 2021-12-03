import java.io.Serializable;

public class ProdutoLimpeza extends Produto implements Serializable {
    //Grau de Toxicidade varia de 0 a 10//
    private int grauToxicidade;

    //Tive que fazer isto para o codigo correr not sure se ta bem
    public ProdutoLimpeza(String identificador, String nome, float precoUnitario, int stock, int custoTransporte, int grauToxicidade) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.grauToxicidade = grauToxicidade;
    }

    public String getType() {
        return "PDLIMP";
    }
}
