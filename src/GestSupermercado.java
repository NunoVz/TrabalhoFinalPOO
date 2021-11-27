import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class GestSupermercado {
    private static ArrayList<Supermercado> supermercados = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Cliente> clientes=new ArrayList<>();
        System.out.println("Software Boot");
        //Função para a primeira inicialização do programa nunca correr em simultaneo com a função lerobjeto
        //lerDados(clientes);
        lerobjeto(clientes);
        System.out.println("Software up to date");

        Cliente cliente=null;
        while(cliente==null) {
            cliente=MenuLoginRegister(clientes);}

        Supermercado sup=null;
        while(sup==null){
            sup= escolherSupermercado();}

        escolherProdutos(sup, cliente);

        MenuFinal(cliente);

        System.out.println("Software storing new data");
        guardarDados(clientes);
        System.out.println("Success\nProgram will be closing now!");

    }

    private static void lerDados(ArrayList<Cliente> clientes){
        File f= new File("Data\\Datasupermercados.txt");
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br= new BufferedReader(fr);

                String data;
                int mododeescrita=-1;
                String nome="";
                ArrayList<Produto> produtos = new ArrayList<>();
                while ((data= br.readLine())!= null) {
                    if (data.length()!=0) {

                        if (data.contains("*NOME*"))
                            mododeescrita = 0;

                        else if (data.contains("*PRODUTOS*"))
                            mododeescrita = 1;

                        else if (data.contains("*PUSH*")) {
                            supermercados.add(new Supermercado(nome, produtos));
                            produtos = new ArrayList<>();
                        } else {
                            switch (mododeescrita) {
                                case 0 -> nome = data;
                                case 1 -> {
                                    String[] array = data.split(" ");
                                    if (array[0].equals("PD"))
                                        produtos.add(new Produto(array[1], array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])));
                                    if (array[0].equals("PDALI"))
                                        produtos.add(new ProdutoAlimentar(array[1], array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7])));
                                    if (array[0].equals("PDLIMP"))
                                        produtos.add(new ProdutoLimpeza(array[1], array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])));
                                    if (array[0].equals("PDMOB"))
                                        produtos.add(new ProdutorMobilado(array[1], array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])));
                                }
                            }
                        }
                    }

                }
                br.close();
            } catch (FileNotFoundException ex){
                System.out.println("Erro a abrir o ficheiro de texto");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto");
            }


        } else {
            System.out.println("Ficheiro não existe");
        }

        f=new File("Data\\Clientes.txt");
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                String data;
                while ((data= br.readLine())!= null) {
                    String[] array=data.split(" ");
                    clientes.add(new Cliente(array[0], array[1], array[2], Integer.parseInt(array[3]), new Data(Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])), Boolean.parseBoolean(array[7]), new ArrayList<>()));
                }
                br.close();
            } catch (FileNotFoundException ex){
                System.out.println("Erro a abrir o ficheiro de texto");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto");
            }


        } else {
            System.out.println("Ficheiro não existe");
        }
    }

    private static void lerobjeto(ArrayList<Cliente> clientes){
        File f=new File("Data\\Datasupermercados.obj");
        try{
            FileInputStream fis= new FileInputStream(f);
            ObjectInputStream ois= new ObjectInputStream(fis);
            Supermercado b;
            while((b= (Supermercado) ois.readObject())!=null)
                supermercados.add(b);
            ois.close();
        }catch (FileNotFoundException ex){
            System.out.println("Erro a abrir o ficheiro");
        }catch (IOException ex){
            System.out.println("Erro a ler o ficheiro");
        }catch (ClassNotFoundException ex){
            System.out.println("Erro a converter o objeto");
        }

        f=new File("Data\\Clientes.obj");
        try{
            FileInputStream fis= new FileInputStream(f);
            ObjectInputStream ois= new ObjectInputStream(fis);
            Cliente b;

            while((b= (Cliente) ois.readObject())!=null){
                clientes.add(b);
            }
            ois.close();
        }catch (FileNotFoundException ex){
            System.out.println("Erro a abrir o ficheiro");
        }catch (IOException ex){
            System.out.println("Erro a ler o ficheiro");
        }catch (ClassNotFoundException ex){
            System.out.println("Erro a converter o objeto");
        }
    }

    private static Cliente MenuLoginRegister(ArrayList<Cliente> clientes) {
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
                MenuLoginRegister(clientes);
            }
            //Switch Case for the menu
            switch (option) {
                case 1 -> {
                    Cliente x=login(clientes);
                    if(x==null)
                        System.out.println("Este email não esta na nossa base de dados");
                    else {
                        System.out.println("Login Bem-sucedido!\nBem vindo " + x.getNome()+"! ");
                        return x;
                    }
                }
                case 2 -> {register(clientes);
                    System.out.println("Cliente registrado com sucesso!\nPedimos agora que efetue o login no respetivo supermercado!\n\n");
                    return null;}
            }
        } else System.out.println("Please type a valid option");return null;}

    public static void  register(ArrayList<Cliente> clientes){
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
        clientes.add(new Cliente(nome,morada,email,telefone,nascimento,frequente,new ArrayList<>()));
    }

    public static Cliente login(ArrayList<Cliente> clientes){
        Scanner sc= new Scanner(System.in);

        System.out.print("Introduza o seu email:");
        String email=sc.nextLine();
        for (Cliente b : clientes) {
            if(b.getEmail().equals(email))
                return b;
        }
        return null;
    }

    private static Supermercado escolherSupermercado() {
        Scanner sc = new Scanner(System.in);
        int option;

        System.out.println("---------------------------");
        System.out.println("|    **Supermercados**    |");
        for (int i = 0; i < supermercados.size(); i++) {
            System.out.println(i+"-"+supermercados.get(i).getNome());}
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

    private static void escolherProdutos(Supermercado sup, Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        Venda venda=new Venda();
        int option = -1;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|       **Produtos**      |");
        for (int i = 0; i < sup.getProdutos().size(); i++) {
            System.out.println(i+"- "+sup.getProdutos().get(i).getNome());}
        System.out.println((sup.getProdutos().size())+"- Retirar o ultimo elemento adcionado");
        System.out.println((sup.getProdutos().size()+1)+"- Pagar");
        System.out.println((sup.getProdutos().size()+2)+"- Exit");
        System.out.println("---------------------------");

        if (sc.hasNextInt()) {
            while (option != sup.getProdutos().size()+2) {
                System.out.println("Carrinho de compras: "+venda.getCarrinhoDeCompras());
                option = Integer.parseInt(sc.nextLine());
                if (option > sup.getProdutos().size()+2) {
                    System.out.println("Please only input a valid number");
                    escolherProdutos(sup,cliente);
                }
                else if(sup.getProdutos().size()+2==option){
                    System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                }
                else if(sup.getProdutos().size()+1==option){
                    System.out.println("Prosseguindo para o pagamento");
                    System.out.println("Valor a pagar pelos produtos: "+venda.getPreco_Prod());
                    System.out.println("Valor a pagar pelo Transporte: "+venda.getPreco_transporte(cliente));
                    System.out.println("Total: "+venda.getTotal());
                    cliente.add_venda(venda);
                    option=sup.getProdutos().size()+2;
                }
                else if(sup.getProdutos().size()==option){
                    System.out.println("Retirando o Ultimo elemento adcionado");
                    if(venda.getCarrinhoDeCompras().size()>0)
                        venda.removerProduto();
                    else
                        System.out.println("O seu carrinho de compras esta vazio!!");
                }

                else if (sup.getProdutos().get(option).stock!=0){
                    System.out.print("Escolha a quantidade de "+sup.getProdutos().get(option).getNome()+" que deseja adcionar:");
                    Scanner sc2 = new Scanner(System.in);

                    while ((!sc2.hasNextInt())) {sc2.next();System.out.print("Escreva um numero!!");}
                    int num = sc2.nextInt();
                    if (sup.getProdutos().get(option).stock>num) {
                        sup.getProdutos().get(option).stock=sup.getProdutos().get(option).stock-num;
                        for (int i = 0; i < num; i++) {venda.add_produto(sup.getProdutos().get(option));}
                    }
                    else
                        System.out.println("Não ha stock suficiente!");
                }
            }
        } else System.out.println("Please type a valid option");
    }

    private static void MenuFinal(Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|         **Fim**         |");
        System.out.println("1. Ver historico de compras");
        System.out.println("2. Exit");

        //Checks for valid input
        if (sc.hasNextInt()) {
            //If option is 0 then it's the Exit
            while (option != 2) {
                //Assigns the user input to the variable while (casting) the value to int
                option = Integer.parseInt(sc.nextLine());
                if (option > 2) {
                    System.out.println("Please only input a valid number");
                    MenuFinal(cliente);
                }
                System.out.println(cliente.getHistoricoVendas());
            }
        } else System.out.println("Please type a valid option");
    }

    private static void guardarDados(ArrayList<Cliente> clientes){
        File f= new File("Data\\Datasupermercados.obj");
        try{
            FileOutputStream fos= new FileOutputStream(f);
            ObjectOutputStream oos= new ObjectOutputStream(fos);

            for (Supermercado b:supermercados)
                oos.writeObject(b);

            oos.close();
        }catch (FileNotFoundException ex){
            System.out.println("Erro a criar ficheiro");
        }catch (IOException ex){
            System.out.println("Erro a escrever para o ficheiro2");
        }

        f= new File("Data\\Clientes.obj");
        try{
            FileOutputStream fos= new FileOutputStream(f);
            ObjectOutputStream oos= new ObjectOutputStream(fos);

            for (Cliente b:clientes)
                oos.writeObject(b);

            oos.close();
        }catch (FileNotFoundException ex){
            System.out.println("Erro a criar ficheiro");
        }catch (IOException ex){
            System.out.println("Erro a escrever para o ficheiro2");
        }



    }




}
