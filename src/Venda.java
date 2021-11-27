import java.io.Serializable;
import java.util.ArrayList;

public class Venda implements Serializable {
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
    public void removerProduto(){
        CarrinhoDeCompras.remove(CarrinhoDeCompras.size()-1);
    }
    public float getPreco_Prod(Supermercado sup) {
        if(CarrinhoDeCompras.contains(sup.getPM().getProduto())) {
            System.out.println("Tem alho");
            int quantidade = 0;
            float MaxDesc;
            for (Produto b : CarrinhoDeCompras) {
                if (b == sup.getPM().getProduto())
                    quantidade+=1;
            }
            MaxDesc=quantidade;
            preco_prod-=quantidade*sup.getPM().getProduto().precoUnitario;
            if(quantidade>10)
                MaxDesc=10;
            MaxDesc=(100-(5*MaxDesc))/100;
            preco_prod+=quantidade*(sup.getPM().getProduto().precoUnitario*MaxDesc);
        }

        if(getCarrinhoDeCompras().contains(sup.getTQ().getProduto())){
            int quantidade = 0;
            for (Produto b : CarrinhoDeCompras) {
                if (b == sup.getTQ().getProduto())
                    quantidade+=1;
            }
            while(quantidade>3){
                preco_prod-=sup.getTQ().getProduto().precoUnitario;
                quantidade-=4;
            }
        }
        preco_prod = (float) (Math.round(preco_prod * 100.0) / 100.0);
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
            if (b.getClass()==ProdutorMobilado.class){
                if(((ProdutorMobilado) b).getPeso()>15)
                    preco_transporte = preco_transporte +15;
            }
        }
        return preco_transporte;
    }

    public void limparCarro(){
        for(Produto b:CarrinhoDeCompras)
            b.stock+=1;
    }
    public float getTotal() {
        return (preco_prod + preco_transporte);
    }

    @Override
    public String toString() {
        return "\nComprou: "+ CarrinhoDeCompras+"\nPreço prod = "+preco_prod
                +"\nPreço do Transporte = "+preco_transporte+"\nTotal = "+getTotal()+"\n-------";
    }
}
