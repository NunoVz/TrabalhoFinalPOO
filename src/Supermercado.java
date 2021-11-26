import java.util.ArrayList;
import java.util.Scanner;

public class Supermercado {
    private String nome;
    private ArrayList<Cliente> clientes;
    private ArrayList<Produto> produtos;


    public Supermercado(String nome, ArrayList<Cliente> clientes, ArrayList<Produto> produtos) {
        this.nome=nome;
        this.clientes=clientes;
        this.produtos=produtos;
    }

    public void add_produto(String identificador, String nome, float precoUnitario, int stock, int custoTransporte){
        produtos.add(new Produto(identificador,nome,precoUnitario,stock,custoTransporte));
    }
    public void  register(){
        Scanner sc= new Scanner(System.in);

        System.out.print("Introduza o seu Nome:");
        String nome=sc.nextLine();
        System.out.print("Introduza a sua Morada:");
        String morada=sc.nextLine();
        System.out.print("Introduza o seu Email:");
        String email=sc.nextLine();
        Data nascimento=new Data(-1,-1,-1);
        //Compor Data
        /*while(Dtnascimento.isDateValid(Dtnascimento)){
        }*/
        System.out.print("Introduza o seu nº de telefone:");
        while ((!sc.hasNextInt())) {sc.next();
            System.out.print("Escreva o seu nº de telefone:");
        }
        int telefone = sc.nextInt();
        int freq=(int)(Math.random()*10)%2;
        boolean frequente= freq == 1;
        clientes.add(new Cliente(nome,morada,email,telefone,nascimento,frequente));
    }
    public Cliente login(){
        Scanner sc= new Scanner(System.in);

        System.out.print("Introduza o seu email:");
        String email=sc.nextLine();
        for (Cliente b : clientes) {
            if(b.getEmail().equals(email))
                return b;
        }
        return null;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public String getNome() {
        return nome;
    }


}
