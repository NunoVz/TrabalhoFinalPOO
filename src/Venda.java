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
    public float addProduto(Produto p, int quantidade, float preco) {
        //Adciona o produto ao carrinho
        //Verifica se o produto ja existe no carrinho de compras
        int index = carrinhoDeCompras.indexOf(p);
        if (index != -1) {
            //Se não existir
            //Adciona o produto e indica a quantidade existente
            p.setQuantidade_carrinho(p.getQuantidade_carrinho() + quantidade);
            p.setStock(p.getStock() - quantidade);
        } else {
            //Se existir apenas aumenta a quantidade existente
            p.setQuantidade_carrinho(quantidade);
            p.setStock(p.getStock() - quantidade);
            carrinhoDeCompras.add(p);
        }

        //Adciona o preço sem desconto
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
        //Se esse produto existir
        if (index != -1) {
            //Repoem os produtos no stock
            stock += carrinhoDeCompras.get(index).getQuantidade_carrinho();
            p.setStock(stock);
            //Remove o produto do carrinho
            carrinhoDeCompras.remove(p);
            //Produto removido com sucesso
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
    public float getPrecoProd(Supermercado sup, float preco) {
        ArrayList<Promocao> PM = sup.getPromocao(sup.getPromocoes(), "PM");
        ArrayList<Promocao> TQ = sup.getPromocao(sup.getPromocoes(), "TQ");
        //Se existir promoção Pague Menos
        if (PM != null) {
            for (Promocao b : PM) {
                //Se o carrinho de compras conter um produto com desconto aplicavel
                if (carrinhoDeCompras.contains(b.getProduto())) {
                    int quantidade = b.getProduto().getQuantidade_carrinho();
                    int MaxDesc = quantidade;
                    preco -= b.getProduto().getPrecoUnitario() * quantidade;
                    //Desconto minimo
                    float prom = (float) 1.05;
                    //Maximo que a promoção pode descer é até 50% ou seja 10 produtos
                    if (MaxDesc > 10) {
                        MaxDesc = 11;
                    }
                    //Se a quantidade for maior de 10 os restantes produtos tem uma promoção de 50% automaticamente
                    int x = quantidade - MaxDesc;
                    if (x > 0) {
                        preco += b.getProduto().getPrecoUnitario() * 0.5 * x;
                    }

                    //Função que vai aumentando a promoção e aplicando aos produtos até este não existir
                    while (MaxDesc > 0) {
                        prom -= 0.05;
                        preco += b.getProduto().getPrecoUnitario() * prom;
                        MaxDesc -= 1;
                    }


                }
            }
        }
        //Se existir promoção leve tres pague quatro
        if (TQ != null) {
            for (Promocao b : TQ) {
                //E se conter o produto no carrinho de compras
                if (getCarrinhoDeCompras().contains(b.getProduto())) {
                    int quantidade = b.getProduto().getQuantidade_carrinho();
                    //Enquanto a quantidade for maior que 3 é removido o valor de um produto em 4
                    while (quantidade > 3) {
                        preco -= b.getProduto().precoUnitario;
                        quantidade -= 4;
                    }
                }
            }
        }
        //Arredontamentos de forma ao preço possuir duas casas decimais
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
        //Verifica se o cliente é frequente e aplica o respetico preço, se não for o preço base é 20
        if (c.isFrequente()) {
            if (preco_prod < 40)
                preco = 15;
            else
                preco = 0;
        }
        //Se existir um produto de mobilia o preco tem um custo acrescido de 15 euros se exceder o limite de 15Kg
        for (Produto b : carrinhoDeCompras) {
            if (b.getType().equals("PDMOB")) {
                if (((ProdutoMobilado) b).getPeso() > 15)
                    preco += 15;
            }
        }
        return preco;
    }

    private String getNomes() {
        //Listar produtos no historico de vendas do cliente
        String nomes = "";
        for (Produto p : carrinhoDeCompras) {
            nomes += "[" + p.getNome() + " * " + p.getQuantidade_carrinho() + "] ";
        }
        return nomes;
    }

    @Override
    public String toString() {
        return "Comprou: " + getNomes() + "\nPreço prod = " + preco_prod
                + "\nPreço do Transporte = " + preco_transporte + "\nTotal = " + getTotal() + "\n";
    }
}