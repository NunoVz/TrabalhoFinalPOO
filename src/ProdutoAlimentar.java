public class ProdutoAlimentar extends Produto {
    private int nCalorias100g;
    private int percentagemGordura;//

    //Tive que fazer isto para o codigo correr not sure se ta bem
    public ProdutoAlimentar(String identificador, String nome, float precoUnitario, int stock, int custoTransporte) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
    }
}
