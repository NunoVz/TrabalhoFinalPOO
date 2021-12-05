import java.io.Serializable;
import java.util.ArrayList;

public class Venda implements Serializable {
    private ArrayList<Produto> carrinhoDeCompras = new ArrayList<>();
    float preco_prod;
    float preco_transporte;

    public Venda() {
    }

    public ArrayList<Produto> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void setCarrinhoDeCompras(ArrayList<Produto> carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public float getPreco_prod() {
        return preco_prod;
    }

    public void setPreco_prod(float preco_prod) {
        this.preco_prod = preco_prod;
    }

    public float getPreco_transporte() {
        return preco_transporte;
    }

    public void setPreco_transporte(float preco_transporte) {
        this.preco_transporte = preco_transporte;
    }

    private int getProdutoIndex(Produto produto) {
        int index = -1;
        for (int i = 0; i < carrinhoDeCompras.size(); i++) {
            if (produto.getIdentificador() == carrinhoDeCompras.get(i).getIdentificador())
                index = i;
        }
        return index;
    }

    public float getTotal() {
        return (preco_prod + preco_transporte);
    }

    public boolean add_produto(Produto p, int quantidade) {
        boolean added = false;
        int index = carrinhoDeCompras.indexOf(p);
        if (index != -1) {
            p.setQuantidade_carrinho(p.getQuantidade_carrinho() + quantidade);
            p.setStock(p.getStock() - quantidade);
        } else {
            p.setQuantidade_carrinho(quantidade);
            p.setStock(p.getStock() - quantidade);
            carrinhoDeCompras.add(p);
        }

        preco_prod += (p.precoUnitario) * quantidade;
        return added;
    }

    public boolean removerProduto(Produto p) {
        boolean removed = false;
        int stock = p.getStock();
        int index = carrinhoDeCompras.indexOf(p);
        if (index != -1) {
            stock += carrinhoDeCompras.get(index).getQuantidade_carrinho();
            p.setStock(stock);
            carrinhoDeCompras.remove(p);
            removed = true;
        }
        return removed;
    }

    public float getPreco_Prod(Supermercado sup) {
        ArrayList<Promocao> PM = sup.getprom(sup.getPromocoes(), "PM");
        ArrayList<Promocao> TQ = sup.getprom(sup.getPromocoes(), "TQ");
        for (Promocao b : PM) {
            if (carrinhoDeCompras.contains(b.getProduto())) {
                int quantidade = b.getProduto().getQuantidade_carrinho();
                float MaxDesc;
                for (Produto x : carrinhoDeCompras) {
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
        for (Promocao b : TQ) {

            if (getCarrinhoDeCompras().contains(b.getProduto())) {
                int quantidade = b.getProduto().getQuantidade_carrinho();
                while (quantidade > 3) {
                    preco_prod -= b.getProduto().precoUnitario;
                    quantidade -= 4;
                }
            }
        }
        preco_prod = (float) (Math.round(preco_prod * 100.0) / 100.0);
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
        for (Produto b : carrinhoDeCompras) {
            if (b.getType().equals("PDMOB")) {
                if (((ProdutorMobilado) b).getPeso() > 15)
                    preco_transporte = preco_transporte + 15;
            }
        }
        return preco_transporte;
    }

    @Override
    public String toString() {
        return "Comprou: " + carrinhoDeCompras + "\nPreço prod = " + preco_prod
                + "\nPreço do Transporte = " + preco_transporte + "\nTotal = " + getTotal() + "\n";
    }
}