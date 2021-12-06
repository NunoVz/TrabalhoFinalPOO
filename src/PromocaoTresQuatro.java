import java.io.Serializable;

public class PromocaoTresQuatro extends Promocao implements Serializable {
    public PromocaoTresQuatro() {

    }

    public PromocaoTresQuatro(Produto produto, String tipo, Data dataInicio, Data dataFim) {
        super(produto, tipo, dataInicio, dataFim);
    }
}
