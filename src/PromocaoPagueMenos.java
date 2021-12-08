import java.io.Serializable;

/**
 * O tipo Promocao pague menos.
 */
public class PromocaoPagueMenos extends Promocao implements Serializable {
    /**
     * Criar uma nova Promocao pague menos.
     */
    public PromocaoPagueMenos() {

    }

    /**
     * Criar uma nova Promocao pague menos.
     *
     * @param produto    o produto
     * @param tipo       o tipo
     * @param dataInicio a data de inicio de promo
     * @param dataFim    a data de fim de promo
     */
    public PromocaoPagueMenos(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        super(produto, tipo, dataInicio, dataFim);
    }
}