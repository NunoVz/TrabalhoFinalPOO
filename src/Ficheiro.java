import java.io.*;
import java.util.ArrayList;

public class Ficheiro {
    private String nome;

    public Ficheiro(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cliente> lerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        File f = new File("Data\\Clientes.txt");
        try {
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() != 0) {
                    String[] array = line.split("\\|");
                    clientes.add(new Cliente(array[0], array[1], array[2], Integer.parseInt(array[3]), getDateFromString(array[4]), Boolean.parseBoolean(array[5])));
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro nao existe.");
        } catch (IOException e) {
            System.out.println("Erro.");
        }
        return clientes;
    }

    public void lerDados() {
        File f = new File("Data\\Datasupermercados.txt");
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String linha;
                int modoEscrita = -1;
                String nome = "";
                ArrayList<Produto> produtos = new ArrayList<>();
                ArrayList<Promocao> promocoes = new ArrayList<>();

                while ((linha = br.readLine()) != null) {
                    if (linha.length() != 0) {

                        if (linha.contains("*NOME*"))
                            modoEscrita = 0;

                        else if (linha.contains("*PRODUTOS*"))
                            modoEscrita = 1;

                        else if (linha.contains("*PROMOCAO*"))
                            modoEscrita = 2;

                        else if (linha.contains("*PUSH*")) {
                            GestSupermercado.supermercados.add(new Supermercado(nome, produtos, promocoes));

                            produtos = new ArrayList<>();
                            promocoes = new ArrayList<>();
                        } else {
                            switch (modoEscrita) {
                                case 0 -> nome = linha;
                                case 1 -> {
                                    String[] array = linha.split(" ");
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
                                    String[] array = linha.split(" ");
                                    if (array[0].equals("PM")) {
                                        String Produto = array[1];
                                        for (Produto b : produtos) {
                                            if (b.getNome().equals(Produto)) {
                                                promocoes.add(new PromocaoPagueMenos(b,array[0]));
                                            }
                                        }

                                    } else if (array[0].equals("TQ")) {
                                        String Produto = array[1];
                                        for (Produto b : produtos) {
                                            if (b.getNome().equals(Produto)) {
                                                promocoes.add(new PromocaoTresQuatro(b,array[0]));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir o ficheiro de texto");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto");
            }


        } else {
            System.out.println("Ficheiro não existe");
        }
    }


    public void lerObjeto(ArrayList<Cliente> clientes) {
        File dataSupermercadosObj = new File("Data\\Datasupermercados.ser");
        try {
            FileInputStream fis = new FileInputStream(dataSupermercadosObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GestSupermercado.supermercados = (ArrayList<Supermercado>) ois.readObject();
//            Supermercado b = null;
//            while ((b = (Supermercado) ois.readObject()) != null)
//                GestSupermercado.supermercados.add(b);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a abrir o ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a ler o objeto Supermercados");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro a converter o objeto");
        }

        File clientesObj = new File("Data\\Clientes.ser");
        try {
            FileInputStream fis = new FileInputStream(clientesObj);
            ObjectInputStream ois = new ObjectInputStream(fis);

            clientes = (ArrayList<Cliente>) ois.readObject();
//            Cliente cliente;
//            while ((cliente = (Cliente) ois.readObject()) != null)
//                clientes.add(cliente);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a abrir o ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a ler o ficheiro objeto clientes ");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro a converter o objeto");
        }
    }

    public static void guardarDadosObj(ArrayList<Cliente> clientes) {
        File f = new File("Data\\Datasupermercados.ser");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(GestSupermercado.supermercados);
//            for (Supermercado b : GestSupermercado.supermercados)
//                oos.writeObject(b);

            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro");
        }

        f = new File("Data\\Clientes.ser");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(clientes);
//            for (Cliente b : clientes)
//                oos.writeObject(b);

            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a criar ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a escrever para o ficheiro2");
        }
    }

    public static Data getDateFromString(String date) {
        //splits it by '/'
        String[] d = date.split("/");

        //Assigns day month and year the correct values
        int day = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int year = Integer.parseInt(d[2]);
        //A new date object is created
        return new Data(day, month, year);
    }
}
