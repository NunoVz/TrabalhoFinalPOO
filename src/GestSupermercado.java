import java.util.ArrayList;
import java.util.Scanner;

// TODO: 02/12/2021 - Hugo, 14:51
//  dar print da lista de produtos quando se seleciona um nao volta a listar os produtos
// Ta top
//xe
//não xe

public class GestSupermercado {
    public static ArrayList<Supermercado> supermercados;
    public static ArrayList<Cliente> clientes;

    public static void main(String[] args) {
        supermercados = new ArrayList<>();
        clientes = new ArrayList<>();

        //Ficheiros
        Ficheiro clientesTexto = new Ficheiro("Clientes.txt");

        System.out.println("Software Boot");
        //Função para a primeira inicialização do programa nunca correr em simultaneo com a função lerobjeto
        clientes = clientesTexto.lerClientes();
        //System.out.println(clientes);
        //clientesTexto.lerDados();
        clientesTexto.lerObjeto(clientes);


        System.out.println("Software up to date");

        Cliente cliente = null;
        while (cliente == null) {
            cliente = LoginRegister(clientes);
        }
        Supermercado sup = null;
        while (sup == null) {
            sup = escolherSupermercado();
        }

        escolherProdutos(sup, cliente);

        System.out.println("Software storing new data");
        Ficheiro.guardarDadosObj(clientes);
        System.out.println("Success\nProgram will be closing now!");
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

    private static void escolherProdutos(Supermercado sup, Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();
        int option = -1;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|       **Produtos**      |");
        for (int i = 0; i < sup.getProdutos().size(); i++) {
            System.out.println(i + "- " + sup.getProdutos().get(i).getNome());
        }
        System.out.println((sup.getProdutos().size()) + "- Retirar o ultimo elemento adicionado");
        System.out.println((sup.getProdutos().size() + 1) + "- Pagar");
        System.out.println((sup.getProdutos().size() + 2) + "- Exit");
        System.out.println("---------------------------");

        if(sup.getprom(sup.getPromocoes(),"TQ")!=null) {
            System.out.println("Produtos com a promoção leve 4 pague 3:");
            for (Promocao b : sup.getprom(sup.getPromocoes(), "TQ"))
                System.out.println(b.getProduto().getNome());
        }
        if(sup.getprom(sup.getPromocoes(),"PM")!=null) {
            System.out.println("Produtos com a promoção pague menos:");
            for (Promocao b : sup.getprom(sup.getPromocoes(), "PM"))
                System.out.println(b.getProduto().getNome());
        }



        //Checks for valid input
        if (sc.hasNextInt()) {
            //If option is 0 then it's the Exit
            while (option != sup.getProdutos().size() + 2) {
                System.out.println("Carrinho de compras: " + venda.getCarrinhoDeCompras());
                option = Integer.parseInt(sc.nextLine());
                if (option > sup.getProdutos().size() + 2) {
                    System.out.println("Please only input a valid number");
                    escolherProdutos(sup, cliente);
                } else if (sup.getProdutos().size() + 2 == option) {
                    System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                } else if (sup.getProdutos().size() + 1 == option) {
                    System.out.println("Prosseguindo para o pagamento");
                    System.out.println("Valor a pagar pelos produtos: " + venda.getPreco_Prod(sup));
                    System.out.println("Valor a pagar pelo Transporte: " + venda.getPreco_transporte(cliente));
                    System.out.println("Total: " + venda.getTotal());
                    cliente.add_venda(venda);
                } else if (sup.getProdutos().size() == option) {
                    System.out.println("Retirando o ultimo elemento");
                } else if (sup.getProdutos().get(option).stock != 0) {
                    System.out.print("Escolha a quantidade de " + sup.getProdutos().get(option).getNome() + " que deseja adicionar:");
                    Scanner sc2 = new Scanner(System.in);
                    while ((!sc2.hasNextInt())) {
                        sc2.next();
                        System.out.print("Escreva um numero!!");
                    }
                    int num = sc2.nextInt();
                    if (sup.getProdutos().get(option).stock > num) {
                        sup.getProdutos().get(option).stock -= num;
                        for (int i = 0; i < num; i++) {
                            venda.add_produto(sup.getProdutos().get(option));
                        }
                    } else
                        System.out.println("Não ha stock suficiente!");
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
