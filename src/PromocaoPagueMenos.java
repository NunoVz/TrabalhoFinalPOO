import java.io.Serializable;

public class PromocaoPagueMenos extends Promocao implements Serializable {
    public PromocaoPagueMenos() {

    }

    public PromocaoPagueMenos(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        super(produto, tipo, dataInicio, dataFim);
    }
}