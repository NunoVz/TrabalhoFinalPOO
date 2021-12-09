import java.io.*;
import java.util.ArrayList;

/**
 * O tipo Ficheiro.
 */
public class Ficheiro {
    /**
     * O Nome.
     */
    public String nome;

    /**
     * Cria um novo Ficheiro.
     */
    public Ficheiro() {
    }

    /**
     * Cria um novo Ficheiro.
     *
     * @param nome o nome
     */
    public Ficheiro(String nome) {
        this.nome = nome;
    }

    /**
     * Ler clientes array list.
     *
     * @return o array list
     */
    public ArrayList<Cliente> lerClientes() {
        //Le os dados relativos aos clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        File f = new File("Data\\Clientes.txt");
        try {
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() != 0) {
                    String[] array = line.split("\\|");
                    //new Cliente(nome, morada, email, telefone, Data nascimento, bool frequente)
                    //Criação do cliente e respetica adição com os dados apresentados no ficheiro de texto
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

    /**
     * Ler dados.
     *
     * @param g o gestor
     */
    public void lerDadosTexto(GestSupermercado g) {
        //Le os dados relativos ao Supermercado
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
                     /*Verifica o modo de escrita:
                             1-Nome
                             2-Produtos
                             3-Promoções
                             4-Guardar Classe no array
                             */
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
                                //Atribui o nome do supermercado
                                case 0 -> nome = linha;
                                //Adciona produtos ao supermercado
                                case 1 -> {
                                    String[] array = linha.split(" ");
                                    switch (array[0]) {
                                        case "PD" -> produtos.add(new Produto(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])));
                                        case "PDALI" -> produtos.add(new ProdutoAlimentar(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7])));
                                        case "PDLIMP" -> produtos.add(new ProdutoLimpeza(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])));
                                        case "PDMOB" -> produtos.add(new ProdutoMobilado(Integer.parseInt(array[1]), array[2], Float.parseFloat(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), array[7]));
                                    }
                                }
                                //Adciona as promoções
                                case 2 -> {
                                    String[] array = linha.split(" ");
                                    //Se for Pague menos executa este bloco de codigo e verifica se as datas estao corretas
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
                                    //Se for pague 3 leve quatro executa este bloco de codigo e verifica se as datas estao corretas
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

    /**
     * Ler objeto supermercado array list.
     *
     * @return a array list
     */
    public ArrayList<Supermercado> lerObjetoSupermercado() {
        ArrayList<Supermercado> supermercados = null;
        File dataSupermercadosObj = new File("Data\\Datasupermercados.obj");
        try {
            FileInputStream fis = new FileInputStream(dataSupermercadosObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //Obtenção da arraylist que contem os supermercados
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

    /**
     * Ler objeto cliente array list.
     *
     * @return a array list
     */
    public ArrayList<Cliente> lerObjetoCliente() {
        ArrayList<Cliente> clientes = null;
        File clientesObj = new File("Data\\Clientes.obj");
        try {
            FileInputStream fis = new FileInputStream(clientesObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //Obtenção da arraylist que contem os clientes
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

    /**
     * Guardar dados obj.
     *
     * @param clientes      os clientes
     * @param supermercados os supermercados
     */
    public void guardarDadosObj(ArrayList<Cliente> clientes, ArrayList<Supermercado> supermercados) {
        File f = new File("Data\\Datasupermercados.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //Guarda o array dos supermercados
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
            //Guarda o array dos clientes
            oos.writeObject(clientes);

            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro a criar ficheiro");
        } catch (IOException e) {
            System.out.println("Erro a escrever para o ficheiro2");
        }
    }

    /**
     * Gets date from string.
     *
     * @param date the date
     * @return the date from string
     */
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
