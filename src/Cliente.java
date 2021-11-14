public class Cliente {
    private String nome;
    private String morada;
    private String email;
    private int telefone;
    private Data nascimento;
    private boolean frequente;
    //
    public Cliente() {}

    public Cliente(String nome, String morada, String email, int telefone, Data nascimento, boolean frequente) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.frequente = frequente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Data getNascimento() {
        return nascimento;
    }

    public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isFrequente() {
        return frequente;
    }

    public void setFrequente(boolean frequente) {
        this.frequente = frequente;
    }
}
