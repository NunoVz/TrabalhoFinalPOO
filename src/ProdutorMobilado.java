public class ProdutorMobilado extends Produto {
    private int peso;
    //dimensao composta por altura/largura/profundidad

    private String dimensao;
    //Tive que fazer isto para o codigo correr not sure se ta bem
    public ProdutorMobilado(String identificador, String nome, float precoUnitario, int stock, int custoTransporte,int peso) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.peso=peso;
    }

    public int getPeso() {
        return peso;
    }


}
