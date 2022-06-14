import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public static void main(String[] args) {
        Pedido Xsalada = new Lanche(1, "X-Salada", 0.8, 12.00);
        Pedido Xtudo = new Lanche(2, "X-Tudo", 1.2, 18.00);
        Pedido Xburguer = new Lanche(3, "X-Burguer", 0.6, 10.00);
        Pedido Xbacon = new Lanche(4, "X-Bacon", 1.0, 15.00);
        listaPedidos.add(Xsalada);
        listaPedidos.add(Xtudo);
        listaPedidos.add(Xburguer);
        listaPedidos.add(Xbacon);

        Pedido refrigerante = new Bebida(1, "Refrigerante", 5, 0.35);
        Pedido Refrigerante = new Bebida(2, "Refrigerante", 10, 0.6);
        Pedido suco = new Bebida(3, "Suco", 6, 0.35);
        Pedido Suco = new Bebida(4, "Suco", 12, 0.6);
        listaPedidos.add(refrigerante);
        listaPedidos.add(Refrigerante);
        listaPedidos.add(suco);
        listaPedidos.add(Suco);

        Pedido batatafrita = new Outro(1, "Batata Frita", 5, "Pequena");
        Pedido BATATAfrita = new Outro(2, "Batata Frita", 12.5, "Média");
        Pedido BATATAFRITA = new Outro(3, "Batata Frita", 20, "Grande");
        Pedido salada = new Outro(4, "Salada", 8, "Média");
        listaPedidos.add(batatafrita);
        listaPedidos.add(BATATAfrita);
        listaPedidos.add(BATATAFRITA);
        listaPedidos.add(salada);

        menu();
    }

    private static void menu() {
        System.out.println("----- MENU ----- \n1 - Listar o cardápio de hoje \n2 - Cadastrar novo pedido \n3 - Editar o preço de um pedido \n4 - Remover Pedido \n5 - Sair");
        int opcao = sc.nextInt();
        try {
            switch (opcao) {
                case 1 -> listar();
                case 2 -> cadastrar();
                case 3 -> editar();
                case 4 -> remover();
                case 5 -> {
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                }
                default -> throw new OpcaoInvalidaException();

            }
        } catch (RuntimeException idiota) {
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
        } finally {
            menu();
        }
    }

    private static void cadastrar() {
        System.out.println("Cadastrar:");
        int opcao = selecionaTipo("cadastrar");

        if (opcao == 0) {
            menu();
        } else if (opcao > 0 && opcao < 4) {
            listaPedidos.add(coletaDadosPedido(opcao));
            System.out.println("Cadastro completo!");
            cadastrar();
        } else {
            throw new OpcaoInvalidaException();
        }

    }

    private static int selecionaTipo(String acao) {
        System.out.println("Qual tipo de pedido vc quer " + acao + " ? \n1 - Lanche \n2 - Bebida \n3 - Outro \nDigite 0 para Voltar");
        return sc.nextInt();
    }

    private static Pedido coletaDadosPedido(int opcao) {

        int checar, codigo;
        System.out.println("Código: ");
        codigo = sc.nextInt();

        checar = valida(opcao, codigo);

        if (checar != -1) {
            throw new ProdutoExisteException();
        }

        System.out.println("Descrição do pedido:");
        String descricao = sc.next();

        System.out.println("Preço:");
        double preco = sc.nextDouble();

        if(preco < 0){
            throw  new ValorInvalidoException("Preço");
        }

        switch (opcao) {
            case 1 -> {
                System.out.println("Peso: ");
                double peso = sc.nextDouble();

                if(peso < 0){
                    throw  new ValorInvalidoException("Peso");
                }

                perguntaCerteza("cadastrar");
                return new Lanche(codigo, descricao, peso, preco);
            }
            case 2 -> {
                System.out.println("Volume (L): ");
                double volume = sc.nextDouble();

                if(volume < 0){
                    throw  new ValorInvalidoException("Volume");
                }

                perguntaCerteza("cadastrar");
                return new Bebida(codigo, descricao, preco, volume);
            }
            case 3 -> {
                System.out.println("Tamanho: ");
                String tamanho = sc.next();

                if(preco < 0){
                    throw  new ValorInvalidoException("Tamanho");
                }

                perguntaCerteza("cadastrar");
                return new Outro(codigo, descricao, preco, tamanho);
            }
        }
        return null;
    }

    private static void listar() {
        Pedido pedido = null;
        System.out.println("Listar: ");
        int opcao = selecionaTipo("listar");
        try {
            switch (opcao) {
                case 1 -> pedido = new Lanche();
                case 2 -> pedido = new Bebida();
                case 3 -> pedido = new Outro();
                case 0 -> menu();
                default -> {
                    throw new OpcaoInvalidaException();
                }
            }
        } catch (RuntimeException idiota) {
            System.out.println(idiota.getClass().getSimpleName() + ": " + idiota.getMessage() + "\n");
        } finally {
            pedido.listar();
            listar();
        }
    }

    private static void editar() {
        System.out.println("Editar:");
        int opcao = selecionaTipo("editar"), checar, codigo;

        if (opcao == 0) {
            menu();
        } else if (opcao > 3 || opcao < 1) {
            throw new OpcaoInvalidaException();
        } else {
            System.out.println("Código: ");
            codigo = sc.nextInt();

            checar = valida(opcao, codigo);

            if (checar == -1) {
                throw new ProdutoInvalidoException();
            }

            System.out.println("Qual o novo preço?");
            double preco = sc.nextDouble();

            if(preco < 0){
                throw  new ValorInvalidoException("Preço");
            }

            listaPedidos.get(checar).setPreco(preco);

            System.out.println("Edição completa!");
        }
        editar();
    }

    private static void remover() {
        System.out.println("Remover:");
        int opcao = selecionaTipo("remover"), checar, codigo;

        if (opcao == 0) {
            menu();
        } else if (opcao > 3 || opcao < 1) {
            throw new OpcaoInvalidaException();
        } else {

            System.out.println("Código: ");
            codigo = sc.nextInt();

            checar = valida(opcao, codigo);

            if (checar == -1) {
                throw new ProdutoInvalidoException();
            }

            perguntaCerteza("remover");

            listaPedidos.remove(checar);

            System.out.println("Remoção completa!");
        }
        remover();
    }

    private static int valida(int opcao, int codigo) {
        Pedido pedido = null;
        switch (opcao) {
            case 1 -> pedido = new Lanche();
            case 2 -> pedido = new Bebida();
            case 3 -> pedido = new Outro();
            case 0 -> menu();
            default -> {
                throw new OpcaoInvalidaException();
            }
        }

        return pedido.valida(codigo);
    }

    private static void perguntaCerteza(String acao) {
        System.out.println("Você quer mesmo " + acao + " este pedido? \n1 - Sim \n2 - Não");
        int opcao = sc.nextInt();

        if (opcao == 2) {
            System.out.println("Ação interrompida!");
            if (acao.equals("cadastrar")) {
                cadastrar();
            } else {
                remover();
            }
        } else if (opcao != 1) {
            throw new OpcaoInvalidaException();
        }
        perguntaCerteza(acao);
    }
}
