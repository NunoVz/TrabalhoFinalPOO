import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class GestSupermercado implements Serializable {
    public static ArrayList<Supermercado> supermercados;
    public static ArrayList<Cliente> clientes;

    public static void main(String[] args) {
        supermercados = new ArrayList<>();
        clientes = new ArrayList<>();

        //Ficheiros
        Ficheiro clientesTexto = new Ficheiro("Clientes.txt");
        Ficheiro dataSupermercadosTexto = new Ficheiro("Datasupermercados.txt");

        System.out.println("Software Boot");

        //Função para a primeira inicialização do programa nunca correr em simultaneo com a função lerobjeto

        clientes = clientesTexto.lerClientes();

        dataSupermercadosTexto.lerDados();

        //Ler objetos
        //clientes = clientesTexto.lerObjetoCliente();
        //supermercados = dataSupermercadosTexto.lerObjetoSupermercado();

        System.out.println("Software up to date");

        Cliente cliente = clientes.get(0);
        Supermercado sup = supermercados.get(0);

        /*Cliente cliente = null;
        while (cliente == null) {
            cliente = LoginRegister(clientes);
        }
        Supermercado sup = null;
        while (sup == null) {
            sup = escolherSupermercado();
        }*/

        escolherProdutos(sup, cliente);

        System.out.println("Software storing new data");
        Ficheiro.guardarDadosObj(clientes, supermercados);
        System.out.println("Success\nProgram will be closing now!");
    }

    private static Cliente LoginRegister(ArrayList<Cliente> clientes) {
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

    private static Supermercado escolherSupermercado() {
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

    private static void menuProdutos(Venda venda) {
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

    private static void escolherProdutos(Supermercado sup, Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();
        int option = -1;

        menuProdutos(venda);

        //Checks for valid input
        if (sc.hasNextInt()) {
            while (option != 5) {
                option = sc.nextInt();

                if (option > 5 || option < 0) {
                    System.out.println("Please only input a valid number");
                    menuProdutos(venda);

                } else {
                    switch (option) {
                        case 5 -> System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                        case 4 -> {
                            System.out.println("Prosseguindo para o pagamento");
                            System.out.println("Valor a pagar pelos produtos: " + venda.getPreco_Prod(sup));
                            System.out.println("Valor a pagar pelo Transporte: " + venda.getPreco_transporte(cliente));
                            System.out.println("Total: " + venda.getTotal());
                            cliente.add_venda(venda);
                            venda = new Venda();
                            menuProdutos(venda);
                        }
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
                            }

                            menuProdutos(venda);
                        }
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
                                if (p.getStock() >= quantidade)
                                    venda.add_produto(p, quantidade);
                                else
                                    System.out.println("Não existe stock suficiente!");
                            }
                            menuProdutos(venda);
                        }
                        case 1 -> {
                            if (sup.getprom(sup.getPromocoes(), "TQ") != null) {
                                System.out.println("Produtos com a promoção leve 4 pague 3:");
                                for (Promocao b : sup.getprom(sup.getPromocoes(), "TQ"))
                                    System.out.println(b.getProduto().getNome());
                            }
                            if (sup.getprom(sup.getPromocoes(), "PM") != null) {
                                System.out.println("Produtos com a promoção pague menos:");
                                for (Promocao b : sup.getprom(sup.getPromocoes(), "PM"))
                                    System.out.println(b.getProduto().getNome());
                            }
                            menuProdutos(venda);
                        }
                        case 0 -> {
                            for (Produto b : sup.getProdutos())
                                System.out.println(b.getIdentificador() + "-" + b.getNome());
                            menuProdutos(venda);
                        }
                    }
                }
            }
        } else System.out.println("Please type a valid option");
    }

    private static void register(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduza o seu Nome:");
        String nome = sc.nextLine();
        System.out.print("Introduza a sua Morada:");
        String morada = sc.nextLine();
        System.out.print("Introduza o seu Email:");
        String email = sc.nextLine();
        System.out.println("Introduza a data de nascimento:");
        String nascimento = sc.nextLine();
        Data dtNascimento;
        while (!(dtNascimento = Ficheiro.getDateFromString(nascimento)).isDateValid()) {
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

    private static Cliente login(ArrayList<Cliente> clientes) {
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
