public class ProdutoAlimentar extends Produto {
    private int nCalorias100g;
    private int percentagemGordura;//

    //Tive que fazer isto para o codigo correr not sure se ta bem
    public ProdutoAlimentar(String identificador, String nome, float precoUnitario, int stock, int custoTransporte, int nCalorias100g, int percentagemGordura) {
        super(identificador, nome, precoUnitario, stock, custoTransporte);
        this.nCalorias100g = nCalorias100g;
        this.percentagemGordura = percentagemGordura;
    }

    public String getType() {
        return "PDALI";
    }
}
