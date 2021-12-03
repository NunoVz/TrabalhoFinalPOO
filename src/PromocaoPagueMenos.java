import java.io.Serializable;

public class PromocaoPagueMenos extends Promocao implements Serializable {

    public PromocaoPagueMenos(Produto produto,String tipo) {
        super(produto,tipo);
    }
}
