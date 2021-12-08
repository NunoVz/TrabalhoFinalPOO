import java.io.Serializable;

/**
 * O tipo Promocao tres quatro.
 */
public class PromocaoTresQuatro extends Promocao implements Serializable {
    /**
     * Criar uma nova Promocao tres quatro.
     */
    public PromocaoTresQuatro() {

    }

    /**
     * Cria uma nova Promocao tres quatro.
     *
     * @param produto    o produto
     * @param tipo       o tipo
     * @param dataInicio o data de inicio de promo
     * @param dataFim    o data de fim de promo
     */
    public PromocaoTresQuatro(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        super(produto, tipo, dataInicio, dataFim);
    }
}
