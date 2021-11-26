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

        Supermercado Sup= EscolherSupermercado();
        Cliente cliente=null;
        while(cliente==null) {
            cliente=LoginRegister(Sup);
        }
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
                if(data.contains("*NOME*")){
                    mododeescrita=0;
                }
                else if(data.contains("*PRODUTOS*")) {
                    mododeescrita = 1;
                }
                else if(data.contains("*CLIENTES*")){
                    mododeescrita=2;
                }
                else if(data.contains("*PUSH*")) {
                    supermercados.add(new Supermercado(nome, clientes, produtos));
                    clientes = new ArrayList<>();
                    produtos = new ArrayList<>();
                }
                else {
                    switch (mododeescrita) {
                        case 0 -> nome = data;
                        case 1 -> {
                            String[] array = data.split(" ");
                            produtos.add(new Produto(array[0], array[1], Float.parseFloat(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
                        }
                        case 2 -> {
                            String[] array = data.split(" ");
                            clientes.add(new Cliente(array[0], array[1], array[2], Integer.parseInt(array[3]), new Data(Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])), Boolean.parseBoolean(array[7])));
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
        int option = -1;

        System.out.println("---------------------------");
        System.out.println("|      Supermercados      |");
        for (int i = 0; i < supermercados.size(); i++) {
            System.out.println(i+"-"+supermercados.get(i).getNome());}
        System.out.println("---------------------------");
        System.out.print("Introduza um numero:");

        if (sc.hasNextInt()) {
            while (option != 0) {
                option = Integer.parseInt(sc.nextLine());
                if (option > supermercados.size()) {
                    System.out.println("Please only input a valid number");
                    EscolherSupermercado();
                }
                option=0;
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
            //If option is 0 then it's the Exit
            System.out.print("Introduza um numero:");
            //Assigns the user input to the variable while (casting) the value to int
            option = Integer.parseInt(sc.nextLine());
            if (option > 7) {
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
                        System.out.println("Login Bem-sucedido!\nBem vindo " + x.getNome()+"! ");
                        return x;
                    }
                }
                case 2 -> {sup.register();
                    System.out.println("Cliente registrado com sucesso!\nPedimos agora que efetue o login no respetivo supermercado!\n\n");
                    return null;}
                }
    } else System.out.println("Please type a valid option");return null;}

}
