import java.io.Serializable;

public class Promocao implements Serializable {
    private Produto produto;
    private String tipo;
    private Data dataInicio;
    private Data dataFim;

    public Promocao() {

    }

    public Promocao(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        this.produto = produto;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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