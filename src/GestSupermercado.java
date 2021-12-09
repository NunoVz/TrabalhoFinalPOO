import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe do tipo Gest supermercado.
 */
public class GestSupermercado {
    /**
     * Os Supermercados.
     */
    public ArrayList<Supermercado> supermercados;
    /**
     * Os Clientes.
     */
    public ArrayList<Cliente> clientes;
    /**
     * Data de Hoje.
     */
    public Data hoje;

    /**
     * Cria um Gestor de supermercados.
     */
    public GestSupermercado() {
        supermercados = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    /**
     * Gets hoje.
     *
     * @return hoje
     */
    public Data getHoje() {
        return hoje;
    }

    /**
     * Gets supermercados.
     *
     * @return os supermercados
     */
    public ArrayList<Supermercado> getSupermercados() {
        return supermercados;
    }

    /**
     * Sets supermercados.
     *
     * @param supermercados os supermercados
     */
    public void setSupermercados(ArrayList<Supermercado> supermercados) {
        this.supermercados = supermercados;
    }

    /**
     * Gets clientes.
     *
     * @return os clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Sets clientes.
     *
     * @param clientes os clientes
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Sets hoje.
     *
     * @param hoje hoje
     */
    public void setHoje(Data hoje) {
        this.hoje = hoje;
    }

    /**
     * O inicio do programa
     *
     * @param args os argumentos de entrada
     */
    public static void main(String[] args) {
        GestSupermercado g = new GestSupermercado();
        boolean exit = false;
        String[] ficheiros = {"Data\\Clientes.txt", "Data\\Datasupermercados.txt", "Data\\Clientes.obj", "Data\\Datasupermercados.obj"};

        //Ficheiros
        Ficheiro clientesTexto = new Ficheiro(ficheiros[0]);
        Ficheiro dataSupermercadosTexto = new Ficheiro(ficheiros[1]);

        System.out.println("Software Boot");

        //Condicao que garante que na primeira incicalizacao(quando nao existem ficheiros objetos)
        if (!(new File(ficheiros[2])).exists()) {
            //Ler Texto
            g.clientes = clientesTexto.lerClientes();
            dataSupermercadosTexto.lerDadosTexto(g);
        } else {
            //Ler objetos
            g.clientes = clientesTexto.lerObjetoCliente();
            g.supermercados = dataSupermercadosTexto.lerObjetoSupermercado();
        }

        System.out.println("Software up to date");

        //Função que pergunta ao user o dia de hoje
        g.hoje = g.hoje();

        //Condição e função que garante que o user faz login com uma conta existente na base de dados
        Cliente cliente = null;
        while (cliente == null) {
            cliente = g.loginRegister(g.clientes);
        }


        while (!exit) {
            //Condição e função que garantem que o user escolhe um supermercado valido
            Supermercado sup = null;
            while (sup == null) {
                sup = g.escolherSupermercado();
            }
            //Menu Principal
            g.escolherProdutos(g, sup, cliente);

            //Menu Final
            exit = g.exit(cliente);

            System.out.println("Software storing new data");
            clientesTexto.guardarDadosObj(g.clientes, g.supermercados);
            System.out.println("Success");
        }
    }

    private Data hoje() {
        Ficheiro f = new Ficheiro();
        System.out.print("Bom dia,\nQue dia é hoje?(dd/mm/aaaa): ");
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        Data data;
        //Verifica se a data inserida é valida
        while (!(data = f.getDateFromString(in)).isDateValid()) {
            System.out.print("A data que inseriu não é válida.\nIntroduza uma data válida.");
            hoje();
        }
        return data;
    }

    private Cliente loginRegister(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        int option;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|        **Menu**         |");
        System.out.println("|1-Login                  |");
        System.out.println("|2-Register               |");
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        if (sc.hasNextInt()) {
            option = Integer.parseInt(sc.nextLine());
            if (option > 2) {
                System.out.println("Please only input a valid number");
                loginRegister(clientes);
            }
            switch (option) {
                case 1 -> {
                    //Login do cliente e devolve um cliente, se este valor for null significa que não encontrou nenhum cliente
                    Cliente x = login(clientes);
                    if (x == null)
                        System.out.println("Este email não esta na nossa base de dados");
                    else {
                        System.out.println("Login Bem-sucedido!\nBem vindo " + x.getNome() + "! ");
                        return x;
                    }
                }
                case 2 -> {
                    //Register do cliente
                    register(clientes);
                    System.out.println("Cliente registrado com sucesso!\nPedimos agora que efetue o login no respetivo supermercado!\n\n");
                    //Devolve-mos null para o cliente fazer login como medida de segurança
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
        //Percorre os supermercados existentes e lista
        for (int i = 0; i < supermercados.size(); i++) {
            System.out.println(i + "-" + supermercados.get(i).getNome());
        }
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        //Escolha do supermercado
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
        //Menu dos produtos lista as opções e o carrinho de compras caso este tenha algo contido
        System.out.println("------------------------------------");
        System.out.println("|           **Produtos**           |");
        System.out.println("| 0- Listar os produtos            |");
        System.out.println("| 1- Listar as promoções           |");
        System.out.println("| 2- Adicionar um produto          |");
        System.out.println("| 3- Remover elemento do carrinho  |");
        System.out.println("| 4- Pagar                         |");
        System.out.println("| 5- Exit                          |");
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
        System.out.println("------------------------------------");
        System.out.print("Introduza um numero:");


    }

    private void escolherProdutos(GestSupermercado g, Supermercado sup, Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();
        int option = -1;
        float preco_prod = 0;
        float preco_trans;

        g.menuProdutos(venda);

        //Verifica se o input é valido
        if (sc.hasNextInt()) {
            //Se a opção for diferente de 5 o menu não fecha
            while (option != 5) {
                option = sc.nextInt();

                //Se o usario não introduzir o valor correto das funções listadas é pedido um valor novamente
                if (option > 5 || option < 0) {
                    System.out.println("Introduza um número válido!");
                    g.menuProdutos(venda);

                } else {
                    switch (option) {
                        // 5-Sair do menu
                        case 5 -> System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                        // 4-Pagar
                        case 4 -> {
                            System.out.println("Prosseguindo para o pagamento");
                            //Adquire o valor dos produtos com desconto
                            preco_prod = venda.getPrecoProd(sup, preco_prod);
                            System.out.println("Valor a pagar pelos produtos: " + preco_prod);
                            //Adquire o valor do transporte
                            preco_trans = venda.getPreco_transporte(cliente);
                            System.out.println("Valor a pagar pelo Transporte: " + preco_trans);
                            //Guarda os preços na classe venda
                            venda.setPreco_prod(preco_prod);
                            venda.setPreco_transporte(preco_trans);
                            System.out.println("Total: " + venda.getTotal());
                            //Adciona a venda efetuada ao historico do cliente
                            cliente.add_venda(venda);
                            //Começa uma nova venda caso o usuario queira continuar as suas compras
                            venda = new Venda();
                            //Opção 5 para sair do programa
                            option = 5;

                        }
                        //3- Remover elemento do carrinho
                        case 3 -> {
                            if (venda.getCarrinhoDeCompras().size() != 0) {
                                Produto produto = null;
                                while (produto == null) {
                                    //Pergunta ao user o ID do produto para remover
                                    System.out.print("Indique o ID do produto que deseja eliminar: ");
                                    int ID = sc.nextInt();
                                    //Percorre os produtos para identificar qual produto deve remover
                                    for (Produto p : sup.getProdutos()) {
                                        if (p.getIdentificador() == ID) {
                                            produto = p;
                                        }
                                    }
                                    //Se o produto não existir significa que o user inseriu incorretamente o ID
                                    if (produto == null) {
                                        System.out.println("O ID que inseriu não corresponde a nenhum produto\nTente outra vez!");
                                    }
                                }

                                //Função que devolve true se o produto for removido com sucesso
                                if (venda.removerProduto(produto)) {
                                    System.out.println(produto.getNome() + " removido com sucesso!");
                                } else System.out.println("Erro ao remover produto");
                            } else {
                                System.out.println("O carrinho encontra-se vazio");
                            }
                            //Apresenta o menu outra vez ao user
                            g.menuProdutos(venda);
                        }
                        // 2-Adicionar produto
                        case 2 -> {
                            Produto p = null;
                            while (p == null) {
                                System.out.print("Indique o ID do produto que deseja adicionar: ");
                                int ID = sc.nextInt();
                                //Percorre os produtos existentes no supermercado para ver se este existe
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
                                System.out.print("Escolha a quantidade de " + p.getNome() + " que deseja adicionar: ");
                                quantidade = sc.nextInt();
                                //Apos a obtenção da quantidade que o usuario quer adcionar prossegue para adcionar ao carrinho
                                if (quantidade > 0) {
                                    //Se existir quantidade suficiente em stock adciona os produtos
                                    if (p.getStock() >= quantidade) {
                                        preco_prod += venda.addProduto(p, quantidade, preco_prod);
                                    } else
                                        System.out.println("Não existe stock suficiente!");
                                } else System.out.println("Indique uma quantidade válida!");

                            }
                            //Apresenta o menu ao user outra vez
                            g.menuProdutos(venda);
                        }
                        //Listar promocoes
                        case 1 -> {
                            ArrayList<Promocao> promosTQ = sup.getPromocao(sup.getPromocoes(), "TQ");
                            //Se existir promoçõs leve tres pague quatro lista as promoções
                            if (promosTQ != null) {
                                System.out.println("\nProdutos com a promoção leve 4 pague 3:");
                                //lista as promoções se estiverem dentro do prazo e a duração das mesmas
                                for (Promocao b : promosTQ) {
                                    int index = promosTQ.indexOf(b);
                                    if (getHoje().isBigger(getHoje(), promosTQ.get(index).getDataInicio()) && getHoje().isBigger(promosTQ.get(index).getDataFim(), getHoje()))
                                        System.out.println(b.getProduto().getNome() + ", Válido de " + promosTQ.get(index).getDataInicio() + " a " + promosTQ.get(index).getDataFim());
                                }
                            }

                            ArrayList<Promocao> promosPM = sup.getPromocao(sup.getPromocoes(), "PM");
                            //Se existir promoções pague menos lista as promoções
                            if (promosPM != null) {
                                System.out.println("\nProdutos com a promoção pague menos:");
                                //lista as promoções se estiverem dentro do prazo e a duração das mesmas
                                for (Promocao b : promosPM) {
                                    int index = promosPM.indexOf(b);
                                    if (getHoje().isBigger(getHoje(), promosPM.get(index).getDataInicio()) && getHoje().isBigger(promosPM.get(index).getDataFim(), getHoje()))
                                        System.out.println(b.getProduto().getNome() + ", Válido de " + promosPM.get(index).getDataInicio() + " a " + promosPM.get(index).getDataFim());
                                }
                            }
                            //Apresenta o menu ao user outra vez
                            g.menuProdutos(venda);
                        }
                        //Listar produtos
                        case 0 -> {
                            //Percorre os produtos do supermercado e lista
                            for (Produto b : sup.getProdutos())
                                System.out.println("ID: " + b.getIdentificador() + " - " + b.getNome() + " - Preco: " + b.getPrecoUnitario() + "€ - Stock: " + b.getStock() + " unidades" + b.toString());
                            //Apresenta o menu ao user outra vez
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

        //Pede ao user todos os dados necessarios para o register
        System.out.print("Introduza o seu Nome:");
        String nome = sc.nextLine();
        System.out.print("Introduza a sua Morada:");
        String morada = sc.nextLine();
        System.out.print("Introduza o seu Email:");
        String email = sc.nextLine();
        System.out.println("Introduza a data de nascimento:");
        String nascimento = sc.nextLine();
        Data dtNascimento;
        //Verifica se a data de nascimento é valida
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

        //adcionar o cliente a base de dados
        clientes.add(new Cliente(nome, morada, email, telefone, dtNascimento, false));
    }

    private Cliente login(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduza o seu email:");
        String email = sc.nextLine();
        //Percorre a base de dados e verifica que o email existe se não existir retorna null
        for (Cliente b : clientes) {
            if (b.getEmail().equals(email))
                return b;
        }
        return null;
    }

    private boolean exit(Cliente x) {
        Scanner sc = new Scanner(System.in);
        int option;
        boolean exit = false;

        System.out.println("---------------------------");
        System.out.println("|Obrigado pela sua compra |");
        System.out.println("|1-Fazer uma nova compra  |");
        System.out.println("|2-Historico de compras   |");
        System.out.println("|3-Exit                   |");
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        if (sc.hasNextInt()) {
            option = Integer.parseInt(sc.nextLine());
            if (option > 3) {
                System.out.println("Please only input a valid number");
                exit(x);
            }
            //Se o user quiser fazer uma nova compra basta deixar a variavel exit como foi declarada

            //Se o user quiser listar as compras efetuadas
            if (option == 2)
                for (Venda b : x.getHistoricoVendas()) {
                    System.out.println(b);
                }

            //Exit da aplicação
            else if (option == 3) {
                exit = true;
            }

        } else System.out.println("Please type a valid option");
        return exit;
    }

}
