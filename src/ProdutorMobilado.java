import java.io.Serializable;

public class ProdutorMobilado extends Produto implements Serializable {
    private int peso;
    //dimensao composta por altura/largura/profundidade
    private String dimensao;

    public ProdutorMobilado() {

    }

    public ProdutorMobilado(int identificador, String nome, float precoUnitario, int stock, int custoTransporte, int peso, String dimensao) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public int getPeso() {
        return peso;
    }

    public String getType() {
        return "PDMOB";
    }

}
