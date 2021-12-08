import java.io.Serializable;
import java.util.ArrayList;

/**
 * O tipo Venda.
 */
public class Venda implements Serializable {
    private ArrayList<Produto> carrinhoDeCompras = new ArrayList<>();
    /**
     * O Preco de produto.
     */
    public float preco_prod;
    /**
     * O Preco de transporte.
     */
    public float preco_transporte;

    /**
     * Cria uma nova Venda.
     */
    public Venda() {
    }

    /**
     * Gets carrinho de compras.
     *
     * @return o carrinho de compras
     */
    public ArrayList<Produto> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    /**
     * Sets carrinho de compras.
     *
     * @param carrinhoDeCompras o carrinho de compras
     */
    public void setCarrinhoDeCompras(ArrayList<Produto> carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    /**
     * Gets preco prod.
     *
     * @return o preco prod
     */
    public float getPreco_prod() {
        return preco_prod;
    }

    /**
     * Sets preco produto.
     *
     * @param preco_prod o preco produto
     */
    public void setPreco_prod(float preco_prod) {
        this.preco_prod = preco_prod;
    }

    /**
     * Gets preco de transporte.
     *
     * @return o preco de transporte
     */
    public float getPreco_transporte() {
        return preco_transporte;
    }

    /**
     * Sets preco de transporte.
     *
     * @param preco_transporte o preco de transporte
     */
    public void setPreco_transporte(float preco_transporte) {
        this.preco_transporte = preco_transporte;
    }

    /**
     * Gets total.
     *
     * @return o total
     */
    public float getTotal() {
        return (preco_prod + preco_transporte);
    }

    /**
     * Adicionar produto float.
     *
     * @param p          o p
     * @param quantidade o quantidade
     * @param preco      o preco
     * @return um float
     */
    public float add_produto(Produto p, int quantidade, float preco) {
        int index = carrinhoDeCompras.indexOf(p);
        if (index != -1) {
            p.setQuantidade_carrinho(p.getQuantidade_carrinho() + quantidade);
            p.setStock(p.getStock() - quantidade);
        } else {
            p.setQuantidade_carrinho(quantidade);
            p.setStock(p.getStock() - quantidade);
            carrinhoDeCompras.add(p);
        }

        preco += (p.precoUnitario) * quantidade;
        return preco;
    }

    /**
     * Remover produto boolean.
     *
     * @param p o produto
     * @return um boolean
     */
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

    /**
     * Gets preco produto.
     *
     * @param sup   o supermercado
     * @param preco o preco
     * @return o preco produto
     */
    public float getPreco_Prod(Supermercado sup,float preco) {
        ArrayList<Promocao> PM = sup.getPromocao(sup.getPromocoes(), "PM");
        ArrayList<Promocao> TQ = sup.getPromocao(sup.getPromocoes(), "TQ");

        if (PM != null) {
            for (Promocao b : PM) {
                if (carrinhoDeCompras.contains(b.getProduto())) {
                    int quantidade = b.getProduto().getQuantidade_carrinho();
                    float MaxDesc;
                    for (Produto x : carrinhoDeCompras) {
                        if (x == b.getProduto())
                            quantidade += 1;
                    }
                    MaxDesc = quantidade;
                    preco -= quantidade * b.getProduto().precoUnitario;
                    if (quantidade > 10)
                        MaxDesc = 10;
                    MaxDesc = (100 - (5 * MaxDesc)) / 100;
                    preco += (quantidade * b.getProduto().precoUnitario) * MaxDesc;
                }
            }
        }
        if (TQ != null) {
            for (Promocao b : TQ) {
                if (getCarrinhoDeCompras().contains(b.getProduto())) {
                    int quantidade = b.getProduto().getQuantidade_carrinho();
                    while (quantidade > 3) {
                        preco -= b.getProduto().precoUnitario;
                        quantidade -= 4;
                    }
                }
            }
        }

        preco = (float) (Math.round(preco * 100.0) / 100.0);
        return preco;
    }

    /**
     * Gets preco de transporte.
     *
     * @param c o cliente
     * @return o preco de transporte
     */
    public float getPreco_transporte(Cliente c) {
        float preco = 20;
        if (c.isFrequente()) {
            if (preco_prod < 40)
                preco = 15;
            else
                preco = 0;
        }
        for (Produto b : carrinhoDeCompras) {
            if (b.getType().equals("PDMOB")) {
                if (((ProdutoMobilado) b).getPeso() > 15)
                    preco += 15;
            }
        }
        return preco;
    }

    @Override
    public String toString() {
        return "Comprou: " + carrinhoDeCompras + "\nPreço prod = " + preco_prod
                + "\nPreço do Transporte = " + preco_transporte + "\nTotal = " + getTotal() + "\n";
    }
}