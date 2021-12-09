import java.io.Serializable;
import java.util.ArrayList;

/**
 * O tipo Cliente.
 */
public class Cliente implements Serializable {
    private String nome;
    private String morada;
    private String email;
    private int telefone;
    private Data nascimento;
    private boolean frequente;
    private ArrayList<Venda> historicoVendas = new ArrayList<>();

    /**
     * Cria um novo Cliente.
     */
    public Cliente() {

    }

    /**
     * Cria um novo Cliente.
     *
     * @param nome       o nome
     * @param morada     o morada
     * @param email      o email
     * @param telefone   o telefone
     * @param nascimento o nascimento
     * @param frequente  é frequente
     */
    public Cliente(String nome, String morada, String email, int telefone, Data nascimento, boolean frequente) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.frequente = frequente;
    }

    /**
     * Gets historico vendas.
     *
     * @return o historico vendas
     */
    public ArrayList<Venda> getHistoricoVendas() {
        return historicoVendas;
    }

    /**
     * Sets historico vendas.
     *
     * @param historicoVendas o historico vendas
     */
    public void setHistoricoVendas(ArrayList<Venda> historicoVendas) {
        this.historicoVendas = historicoVendas;
    }

    /**
     * Gets nome.
     *
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome o nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets morada.
     *
     * @return a morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Sets morada.
     *
     * @param morada a morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Gets email.
     *
     * @return o email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email o email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets telefone.
     *
     * @return o telefone
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * Sets telefone.
     *
     * @param telefone o telefone
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * Gets nascimento.
     *
     * @return o nascimento
     */
    public Data getNascimento() {
        return nascimento;
    }

    /**
     * Sets nascimento.
     *
     * @param nascimento o nascimento
     */
    public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * É frequente boolean.
     *
     * @return o boolean
     */
    public boolean isFrequente() {
        return frequente;
    }

    /**
     * Sets frequente.
     *
     * @param frequente frequente
     */
    public void setFrequente(boolean frequente) {
        this.frequente = frequente;
    }

    /**
     * Adiciona venda.
     *
     * @param venda a venda
     */
    public void add_venda(Venda venda) {
        historicoVendas.add(venda);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
