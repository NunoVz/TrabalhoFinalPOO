import java.io.Serializable;

public class PromocaoTresQuatro extends Promocao implements Serializable {
    public PromocaoTresQuatro() {

    }

    public PromocaoTresQuatro(Produto produto,String tipo) {
        super(produto,tipo);
    }
}
