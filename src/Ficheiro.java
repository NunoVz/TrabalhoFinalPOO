import java.io.*;
import java.util.ArrayList;

public class Ficheiro {
    public String nome;

    public Ficheiro(){}

    public Ficheiro(String nome) {
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

    public void lerDados(GestSupermercado g) {
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
                            g.getSupermercados().add(new Supermercado(nome, produtos, promocoes));
                            produtos = new ArrayList<>();
                            promocoes = new ArrayList<>();
                        } else {
                            switch (modoEscrita) {
                                case 0 -> nome = linha;
                                case 1 -> {
                                    String[] array = linha.split(" ");
                                    if (array[0].equals("PD"))
                                        produtos.add(new Produto(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])));
                                    if (array[0].equals("PDALI"))
                                        produtos.add(new ProdutoAlimentar(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7])));
                                    if (array[0].equals("PDLIMP"))
                                        produtos.add(new ProdutoLimpeza(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])));
                                    if (array[0].equals("PDMOB"))
                                        produtos.add(new ProdutorMobilado(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), array[7]));
                                }
                                case 2 -> {
                                    String[] array = linha.split(" ");
                                    if (array[0].equals("PM")) {
                                        String produto = array[1];
                                        for (Produto b : produtos) {
                                            if (b.getNome().equals(produto)) {
                                                Data inicio = getDateFromString(array[2]);
                                                Data fim = getDateFromString(array[3]);
                                                if (inicio.isDateValid() && fim.isDateValid() && inicio.isBigger(fim, inicio))
                                                    promocoes.add(new PromocaoPagueMenos(b, array[0], getDateFromString(array[2]), getDateFromString(array[3])));
                                                else System.out.println("Data inválida, a promoçao nao foi adicionada");
                                            }
                                        }
                                    } else if (array[0].equals("TQ")) {
                                        String Produto = array[1];
                                        for (Produto b : produtos) {
                                            if (b.getNome().equals(Produto)) {
                                                Data inicio = getDateFromString(array[2]);
                                                Data fim = getDateFromString(array[3]);
                                                if (inicio.isDateValid() && fim.isDateValid() && inicio.isBigger(fim, inicio))
                                                    promocoes.add(new PromocaoTresQuatro(b, array[0], getDateFromString(array[2]), getDateFromString(array[3])));
                                                else System.out.println("Data inválida, a promoçao nao foi adicionada");
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
        } else System.out.println("Ficheiro não existe");
    }

    public ArrayList<Supermercado> lerObjetoSupermercado() {
        ArrayList<Supermercado> supermercados = null;
        File dataSupermercadosObj = new File("Data\\Datasupermercados.ser");
        try {
            FileInputStream fis = new FileInputStream(dataSupermercadosObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            supermercados = (ArrayList<Supermercado>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a abrir o ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a ler o objeto Supermercados");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro a converter o objeto");
        }
        return supermercados;
    }

    public ArrayList<Cliente> lerObjetoCliente() {
        ArrayList<Cliente> clientes = null;
        File clientesObj = new File("Data\\Clientes.ser");
        try {
            FileInputStream fis = new FileInputStream(clientesObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            clientes = (ArrayList<Cliente>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a abrir o ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a ler o ficheiro objeto clientes ");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro a converter o objeto");
        }
        return clientes;
    }

    public void guardarDadosObj(ArrayList<Cliente> clientes, ArrayList<Supermercado> supermercados) {
        File f = new File("Data\\Datasupermercados.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(supermercados);

            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a criar ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a escrever para o ficheiro");
        }

        f = new File("Data\\Clientes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(clientes);

            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a criar ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a escrever para o ficheiro2");
        }
    }

    public Data getDateFromString(String date) {
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
