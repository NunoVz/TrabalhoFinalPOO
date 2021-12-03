import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Venda implements Serializable {
    private ArrayList<Produto> CarrinhoDeCompras = new ArrayList<>();
    float preco_prod;
    float preco_transporte;

    public void add_produto(Produto p,int quantidade) {

        int index=CarrinhoDeCompras.indexOf(p);
        if(index!=-1){
            for(Produto b:CarrinhoDeCompras)
                if(b==p)
                    b.setQuantidade_carrinho(CarrinhoDeCompras.get(index).getQuantidade_carrinho()+quantidade);
        }
        else{
            p.setQuantidade_carrinho(quantidade);
            CarrinhoDeCompras.add(p);
        }

        preco_prod += (p.precoUnitario)*quantidade;
    }

    public ArrayList<Produto> getCarrinhoDeCompras() {
        return CarrinhoDeCompras;
    }

    public void removerProduto() {
        CarrinhoDeCompras.remove(CarrinhoDeCompras.size() - 1);
    }

    public float getPreco_Prod(Supermercado sup) {
        ArrayList<Promocao> PM= sup.getprom(sup.getPromocoes(),"PM");
        ArrayList<Promocao> TQ=sup.getprom(sup.getPromocoes(),"TQ");
        for(Promocao b: PM) {
            if (CarrinhoDeCompras.contains(b.getProduto())) {
                int quantidade = b.getProduto().getQuantidade_carrinho();
                float MaxDesc;
                for (Produto x : CarrinhoDeCompras) {
                    if (x == b.getProduto())
                        quantidade += 1;
                }
                MaxDesc = quantidade;
                preco_prod -= quantidade * b.getProduto().precoUnitario;
                if (quantidade > 10)
                    MaxDesc = 10;
                MaxDesc = (100 - (5 * MaxDesc)) / 100;
                preco_prod += (quantidade * b.getProduto().precoUnitario) * MaxDesc;
            }
        }
        for(Promocao b:TQ) {

            if (getCarrinhoDeCompras().contains(b.getProduto())) {
                int quantidade = b.getProduto().getQuantidade_carrinho();
                while (quantidade > 3) {
                    preco_prod -= b.getProduto().precoUnitario;
                    quantidade -= 4;
                }
            }
        }
        preco_prod=(float)(Math.round(preco_prod * 100.0) / 100.0);
        return preco_prod;
    }

    public float getPreco_transporte(Cliente c) {
        preco_transporte = 20;
        if (c.isFrequente()) {
            if (preco_prod < 40)
                preco_transporte = 15;
            else
                preco_transporte = 0;
        }
        for (Produto b : CarrinhoDeCompras) {
            if (b.getType().equals("PDMOB")) {
                if (((ProdutorMobilado) b).getPeso() > 15)
                    preco_transporte = preco_transporte + 15;
            }
        }
        return preco_transporte;
    }


    public float getTotal() {
        return (preco_prod + preco_transporte);
    }

    @Override
    public String toString() {
        return "Comprou: " + CarrinhoDeCompras + "\nPreço prod = " + preco_prod
                + "\nPreço do Transporte = " + preco_transporte + "\nTotal = " + getTotal() + "\n";
    }
}
