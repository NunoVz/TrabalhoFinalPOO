import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/*
 TODO: 06/12/2021
    tirar o calculo de atributos da classe, da classe venda (preco_prod)
    cena de abertura de ficheiros obj apenas se existirem
*/

public class GestSupermercado implements Serializable {
    public ArrayList<Supermercado> supermercados;
    public ArrayList<Cliente> clientes;
    public Data hoje;

    public GestSupermercado() {
        supermercados = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public ArrayList<Supermercado> getSupermercados() {
        return supermercados;
    }

    public void setSupermercados(ArrayList<Supermercado> supermercados) {
        this.supermercados = supermercados;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setHoje(Data hoje) {
        this.hoje = hoje;
    }

    public static void main(String[] args) {
        GestSupermercado g = new GestSupermercado();

        //Ficheiros
        Ficheiro clientesTexto = new Ficheiro("Clientes.txt");
        Ficheiro dataSupermercadosTexto = new Ficheiro("Datasupermercados.txt");

        System.out.println("Software Boot");

        //Função para a primeira inicialização do programa nunca correr em simultaneo com a função lerobjeto

        g.clientes = clientesTexto.lerClientes();

        dataSupermercadosTexto.lerDados(g);

        /*
        Ler objetos
        clientes = clientesTexto.lerObjetoCliente();
        supermercados = dataSupermercadosTexto.lerObjetoSupermercado();
        */

        System.out.println("Software up to date");

        g.hoje = g.getHoje();
        Cliente cliente = g.clientes.get(0);
        Supermercado sup = g.supermercados.get(0);

        /*Cliente cliente = null;
        while (cliente == null) {
            cliente = LoginRegister(clientes);
        }
        Supermercado sup = null;
        while (sup == null) {
            sup = escolherSupermercado();
        }*/

        g.escolherProdutos(g, sup, cliente);

        System.out.println("Software storing new data");
        clientesTexto.guardarDadosObj(g.clientes, g.supermercados);
        System.out.println("Success\nProgram will be closing now!");
    }

    private Data getHoje() {
        Ficheiro f = new Ficheiro();
        System.out.print("Bom dia,\nQue dia é hoje?");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        Data data;
        while (!(data = f.getDateFromString(in)).isDateValid()) {
            System.out.print("A data que inseriu não é válida.\nIntroduza uma data válida.");
        }
        return data;
    }

    private Cliente LoginRegister(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        int option;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|        **Menu**         |");
        System.out.println("|1-Login                  |");
        System.out.println("|2-Register               |");
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        //Checks for valid input
        if (sc.hasNextInt()) {
            //Assigns the user input to the variable while (casting) the value to int
            option = Integer.parseInt(sc.nextLine());
            if (option > 2) {
                System.out.println("Please only input a valid number");
                LoginRegister(clientes);
            }
            //Switch Case for the menu
            switch (option) {
                case 1 -> {
                    Cliente x = login(clientes);
                    if (x == null)
                        System.out.println("Este email não esta na nossa base de dados");
                    else {
                        System.out.println("Login Bem-sucedido!\nBem vindo " + x.getNome() + "! ");
                        return x;
                    }
                }
                case 2 -> {
                    register(clientes);
                    System.out.println("Cliente registrado com sucesso!\nPedimos agora que efetue o login no respetivo supermercado!\n\n");
                    return null;
                }
            }
        } else System.out.println("Please type a valid option");
        return null;
    }

    private Supermercado escolherSupermercado() {
        Scanner sc = new Scanner(System.in);
        int option;

        System.out.println("---------------------------");
        System.out.println("|      Supermercados      |");
        for (int i = 0; i < supermercados.size(); i++) {
            System.out.println(i + "-" + supermercados.get(i).getNome());
        }
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        if (sc.hasNextInt()) {
            option = Integer.parseInt(sc.nextLine());
            if (option > supermercados.size()) {
                System.out.println("Please only input a valid number");
                escolherSupermercado();
            }
            return supermercados.get(option);

        } else System.out.println("Please type a valid option");
        return null;

    }

    private void menuProdutos(Venda venda) {
        System.out.println("---------------------------");
        System.out.println("|       **Produtos**      |");
        System.out.println("0- Listar os produtos");
        System.out.println("1- Listar as promoções");
        System.out.println("2- Adicionar um produto");
        System.out.println("3- Remover elemento do carrinho");
        System.out.println("4- Pagar");
        System.out.println("5- Exit");
        if (venda.getCarrinhoDeCompras().size() != 0) {
            System.out.print("Carrinho de compras=[");
            int i = 0;
            for (Produto b : venda.getCarrinhoDeCompras()) {
                i += 1;
                System.out.print(b.nome + " * " + b.quantidade_carrinho);
                if (i != venda.getCarrinhoDeCompras().size())
                    System.out.print(", ");
                else
                    System.out.print("]\n");
            }
        }
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");


    }

    private void escolherProdutos(GestSupermercado g, Supermercado sup, Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();
        int option = -1;

        g.menuProdutos(venda);

        //Checks for valid input
        if (sc.hasNextInt()) {
            while (option != 5) {
                option = sc.nextInt();

                if (option > 5 || option < 0) {
                    System.out.println("Please only input a valid number");
                    g.menuProdutos(venda);

                } else {
                    switch (option) {
                        // 5-Sair
                        case 5 -> System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                        // 4-Pagar
                        case 4 -> {
                            System.out.println("Prosseguindo para o pagamento");
                            System.out.println("Valor a pagar pelos produtos: " + venda.getPreco_Prod(sup));
                            System.out.println("Valor a pagar pelo Transporte: " + venda.getPreco_transporte(cliente));
                            System.out.println("Total: " + venda.getTotal());
                            cliente.add_venda(venda);
                            venda = new Venda();
                            g.menuProdutos(venda);
                        }
                        //3- Remover elemento do carrinho
                        case 3 -> {
                            Produto produto = null;
                            while (produto == null) {
                                System.out.print("Indique o ID do produto que deseja eliminar: ");
                                int ID = sc.nextInt();
                                for (Produto p : sup.getProdutos()) {
                                    if (p.getIdentificador() == ID) {
                                        produto = p;
                                    }
                                }
                                if (produto == null) {
                                    System.out.println("O ID que inseriu não corresponde a nenhum produto\nTente outra vez!");
                                }
                            }
                            if (venda.removerProduto(produto)) {
                                System.out.println(produto.getNome() + " removido com sucesso!");
                            } else System.out.println("Erro ao remover produto");

                            g.menuProdutos(venda);
                        }
                        // 2-Adicionar produto
                        case 2 -> {
                            Produto p = null;
                            while (p == null) {
                                System.out.print("Indique o ID do produto que deseja adicionar: ");
                                int ID = sc.nextInt();
                                for (Produto b : sup.getProdutos()) {
                                    if (b.getIdentificador() == ID) {
                                        p = b;
                                    }
                                }
                                if (p == null)
                                    System.out.println("O ID que inseriu não corresponde a nenhum produto\nTente outra vez!");
                            }

                            int quantidade = 0;
                            while (quantidade <= 0) {
                                System.out.print("Escolha a quantidade de " + p.getNome() + " que deseja adicionar:");
                                quantidade = sc.nextInt();
                                if (p.getStock() >= quantidade) {
                                    if (venda.add_produto(p, quantidade))
                                        System.out.println("Produto adicionado com sucesso!");
                                    else System.out.println("Erro ao adicionar o produto.");

                                }
                                else
                                    System.out.println("Não existe stock suficiente!");
                            }
                            g.menuProdutos(venda);
                        }
                        //Listar promocoes
                        case 1 -> {
                            if (sup.getPromocao(sup.getPromocoes(), "TQ") != null) {
                                System.out.println("Produtos com a promoção leve 4 pague 3:");
                                for (Promocao b : sup.getPromocao(sup.getPromocoes(), "TQ"))
                                    System.out.println(b.getProduto().getNome());
                            }
                            if (sup.getPromocao(sup.getPromocoes(), "PM") != null) {
                                System.out.println("Produtos com a promoção pague menos:");
                                for (Promocao b : sup.getPromocao(sup.getPromocoes(), "PM"))
                                    System.out.println(b.getProduto().getNome());
                            }
                            g.menuProdutos(venda);
                        }
                        //Listar produtos
                        case 0 -> {
                            for (Produto b : sup.getProdutos())
                                System.out.println("ID: " + b.getIdentificador() + " - " + b.getNome() + " - Preco: " + b.getPrecoUnitario() + " - Stock: " + b.getStock());
                            g.menuProdutos(venda);
                        }
                    }
                }
            }
        } else System.out.println("Please type a valid option");
    }

    private void register(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        Ficheiro f = new Ficheiro();

        System.out.print("Introduza o seu Nome:");
        String nome = sc.nextLine();
        System.out.print("Introduza a sua Morada:");
        String morada = sc.nextLine();
        System.out.print("Introduza o seu Email:");
        String email = sc.nextLine();
        System.out.println("Introduza a data de nascimento:");
        String nascimento = sc.nextLine();
        Data dtNascimento;
        while (!(dtNascimento = f.getDateFromString(nascimento)).isDateValid()) {
            System.out.println("Data de nascimento inválida.");
            System.out.println("Introduza a data de nascimento:");
            nascimento = sc.nextLine();
        }
        System.out.print("Introduza o seu nº de telefone:");
        while ((!sc.hasNextInt())) {
            sc.next();
            System.out.print("Escreva o seu nº de telefone:");
        }
        int telefone = sc.nextInt();
        int freq = (int) (Math.random() * 10) % 2;
        boolean frequente = freq == 1;
        clientes.add(new Cliente(nome, morada, email, telefone, dtNascimento, frequente));
    }

    private Cliente login(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduza o seu email:");
        String email = sc.nextLine();
        for (Cliente b : clientes) {
            if (b.getEmail().equals(email))
                return b;
        }
        return null;
    }
}
