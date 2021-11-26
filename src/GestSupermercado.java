import java.util.ArrayList;
import java.util.Scanner;


public class GestSupermercado {
    private static ArrayList<Supermercado> supermercados = new ArrayList<>();

    public static void main(String[] args) {
        supermercados.add(new Supermercado("SuperDaBaixa"));
        supermercados.add(new Supermercado("SuperDaBaguete"));
        supermercados.add(new Supermercado("SuperDoMoelas"));
        Supermercado Sup= EscolherSupermercado();
        Cliente cliente=null;
        while(cliente==null) {
            cliente=LoginRegister(Sup);
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
