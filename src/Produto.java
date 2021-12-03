import java.io.Serializable;

public class Produto implements Serializable {
    protected int identificador;
    protected String nome;
    protected float precoUnitario;
    protected int stock;
    protected int custoTransporte;
    protected int quantidade_carrinho;

    //
    public Produto(int identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.stock = stock;
        this.custoTransporte = custoTransporte;
    }


    public int getCustoTransporte() {
        return custoTransporte;
    }

    public void setCustoTransporte(int custoTransporte) {
        this.custoTransporte = custoTransporte;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return "PD";
    }

    public int getQuantidade_carrinho() {
        return quantidade_carrinho;
    }

    public void setQuantidade_carrinho(int quantidade_carrinho) {
        this.quantidade_carrinho = quantidade_carrinho;
    }

    public String guardarData() {
        return getType() + " " + identificador
                + " " + nome + " " + precoUnitario + " " + stock + " " + custoTransporte;
    }

    @Override
    public String toString() {
        return nome;
    }
}
