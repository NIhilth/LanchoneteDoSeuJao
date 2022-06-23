import java.util.ArrayList;
public abstract class Opcao {
    private static ArrayList<Opcao> cardapio = new ArrayList<>();
    private int quantidade;
    private String descricao;
    private double preco;
    public Opcao(String descricao, double preco) {
        this.setQuantidade(0);
        this.descricao = descricao;
        this.preco = preco;
    }
    private static void reordenar() {
        ArrayList<Opcao> novoCardapio = new ArrayList<>();
        for (Opcao opcao: cardapio) {
            if (opcao instanceof Lanche) {
                novoCardapio.add(opcao);
            }
        }
        for (Opcao opcao: cardapio) {
            if (opcao instanceof Porcao) {
                novoCardapio.add(opcao);
            }
        }
        for (Opcao opcao: cardapio) {
            if (opcao instanceof Bebida) {
                novoCardapio.add(opcao);
            }
        }
        cardapio = novoCardapio;
    }

    public int getCodigo() {
        return cardapio.indexOf(this)+1;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static void inserir(Opcao opcao) {
        cardapio.add(opcao);
        reordenar();
    }

    public static void remover(Opcao opcao) {
        cardapio.remove(opcao);
        reordenar();
    }

    public static void editar(Opcao opcao, double preco){
        opcao.setPreco(preco);
    }

    public static Opcao procurarOpcao(int codigo) {
        for (Opcao opcao : cardapio) {
            if (opcao.getCodigo() == codigo) {
                return opcao;
            }
        }
        throw new OpcaoInvalidaException();
    }

    public static String mostrarOpcoes() {
        String dadosOpcoes = """
                    > > > > > > > > CARD�PIO < < < < < < < <
                    TIPO   COD DESCRI��O           PRE�O
                    """;
        for (Opcao opcao : cardapio) {
            dadosOpcoes += opcao.toString() + "\n";
        }
        return dadosOpcoes;
    }

    @Override
    public abstract String toString();
    public abstract String toStringPedido();

}