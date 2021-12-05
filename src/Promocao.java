import java.io.Serializable;

public class Promocao implements Serializable {
    private Produto produto;
    private String tipo;

    public Promocao() {

    }

    public Promocao(Produto produto, String tipo) {
        this.produto = produto;
        this.tipo = tipo;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Produto getProduto() {
        return produto;
    }
}