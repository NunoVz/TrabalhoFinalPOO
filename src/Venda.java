import java.util.ArrayList;

public class Venda {
    private ArrayList<Produto> CarrinhoDeCompras=new ArrayList<>();
    float preco_prod;
    float preco_transporte;

    public void add_produto(Produto P){
        CarrinhoDeCompras.add(P);
        preco_prod = preco_prod +(P.precoUnitario);
    }

    public ArrayList<Produto> getCarrinhoDeCompras() {
        return CarrinhoDeCompras;
    }

    public float getPreco_Prod() {
        return preco_prod;
    }
    public float getPreco_transporte(Cliente c){
        preco_transporte =20;
        if (c.isFrequente()){
            if(preco_transporte <40)
                preco_transporte =15;
            else
                preco_transporte =0;
        }
        for(Produto b:CarrinhoDeCompras){
            if (b.getType().equals("PDMOB")){
                if(((ProdutorMobilado) b).getPeso()>15)
                    preco_transporte = preco_transporte +15;
            }
        }
        return preco_transporte;
    }


    public float getTotal() {
        return (preco_prod + preco_transporte);
    }
}
