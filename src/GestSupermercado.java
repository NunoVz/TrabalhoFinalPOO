import java.awt.print.Printable;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class GestSupermercado {
    private static ArrayList<Supermercado> supermercados = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Software Boot");
        lerdados();
        System.out.println("Software up to date");

        Supermercado sup=null;
        while(sup==null){
            sup= EscolherSupermercado();}

        Cliente cliente=null;
        while(cliente==null) {
            cliente=LoginRegister(sup);}

        EscolherProdutos(sup, cliente);
    }

    private static void lerdados(){
        try {
            File myObj = new File("Data\\Datasupermercados.txt");
            Scanner myReader = new Scanner(myObj);
            int mododeescrita=-1;
            String nome="";
            ArrayList<Cliente> clientes = new ArrayList<>();
            ArrayList<Produto> produtos = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.length()!=0) {

                    if (data.contains("*NOME*"))
                        mododeescrita = 0;

                    else if (data.contains("*PRODUTOS*"))
                        mododeescrita = 1;

                    else if (data.contains("*CLIENTES*"))
                        mododeescrita = 2;

                    else if (data.contains("*PUSH*")) {
                        supermercados.add(new Supermercado(nome, clientes, produtos));
                        clientes = new ArrayList<>();
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
                            case 2 -> {
                                String[] array = data.split(" ");
                                clientes.add(new Cliente(array[0], array[1], array[2], Integer.parseInt(array[3]), new Data(Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])), Boolean.parseBoolean(array[7])));
                            }
                        }
                    }
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private static Supermercado EscolherSupermercado() {
        Scanner sc = new Scanner(System.in);
        int option;

        System.out.println("---------------------------");
        System.out.println("|      Supermercados      |");
        for (int i = 0; i < supermercados.size(); i++) {
            System.out.println(i+"-"+supermercados.get(i).getNome());}
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        if (sc.hasNextInt()) {
                option = Integer.parseInt(sc.nextLine());
                if (option > supermercados.size()) {
                    System.out.println("Please only input a valid number");
                    EscolherSupermercado();
                }
                return supermercados.get(option);

        } else System.out.println("Please type a valid option");
        return null;

    }

    private static Cliente LoginRegister(Supermercado sup) {
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
                LoginRegister(sup);
            }
            //Switch Case for the menu
            switch (option) {
                case 1 -> {
                    Cliente x=sup.login();
                    if(x==null)
                        System.out.println("Este email não esta na nossa base de dados");
                    else {
                        System.out.println("Login Bem-sucedido!\nBem vindo " + x.getNome()+"! \nEsta a efetuar compras no "+sup.getNome());
                        return x;
                    }
                }
                case 2 -> {sup.register();
                    System.out.println("Cliente registrado com sucesso!\nPedimos agora que efetue o login no respetivo supermercado!\n\n");
                    return null;}
                }
    } else System.out.println("Please type a valid option");return null;}

    private static void EscolherProdutos(Supermercado sup,Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Produto> carrinhodecompras = new ArrayList<>();
        int option = -1;

        //Prints the various options
        System.out.println("---------------------------");
        System.out.println("|       **Produtos**      |");
        for (int i = 0; i < sup.getProdutos().size(); i++) {
            System.out.println(i+"- "+sup.getProdutos().get(i).getNome());}
        System.out.println((sup.getProdutos().size()+1)+"- Retirar o ultimo elemento adcionado");
        System.out.println((sup.getProdutos().size()+2)+"- Pagar");
        System.out.println((sup.getProdutos().size()+3)+"- Exit");
        System.out.println("---------------------------");

        //Checks for valid input
        if (sc.hasNextInt()) {
            //If option is 0 then it's the Exit
            while (option != sup.getProdutos().size()+2) {
                System.out.println("Carrinho de compras: "+carrinhodecompras);
                option = Integer.parseInt(sc.nextLine());
                if (option > sup.getProdutos().size()+3) {
                    System.out.println("Please only input a valid number");
                    EscolherProdutos(sup,cliente);
                }
                else if(sup.getProdutos().size()+3==option){
                    System.out.println("Obrigado pela sua visita! :)\nVolte sempre!");
                    System.exit(0);
                }
                else if(sup.getProdutos().size()+2==option){
                    System.out.println("Prosseguindo para o pagamento");
                }

                else if (sup.getProdutos().get(option).stock!=0){
                    System.out.print("Escolha a quantidade de "+sup.getProdutos().get(option).getNome()+" que deseja adcionar:");
                    Scanner sc2 = new Scanner(System.in);

                    while ((!sc2.hasNextInt())) {sc2.next();System.out.print("Escreva um numero!!");}
                    int num = sc2.nextInt();
                    if (sup.getProdutos().get(option).stock>num) {
                        sup.getProdutos().get(option).stock=sup.getProdutos().get(option).stock-num;
                        for (int i = 0; i < num; i++) {carrinhodecompras.add(sup.getProdutos().get(option));}
                    }
                    else
                        System.out.println("Não ha stock suficiente!");
                }
            }
        } else System.out.println("Please type a valid option");
    }
}
