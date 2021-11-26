public class Produto {
    protected String identificador;
    protected String nome;
    protected float precoUnitario;
    protected int stock;
    protected int custoTransporte;

//
    public Produto(String identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
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

    @Override
    public String toString() {
        return nome;
    }
}
