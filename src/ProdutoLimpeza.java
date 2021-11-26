public class ProdutoLimpeza extends Produto {
    //Grau de Toxicidade varia de 0 a 10//
    private int grauToxicidade;

    //Tive que fazer isto para o codigo correr not sure se ta bem
    public ProdutoLimpeza(String identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
    }
}
