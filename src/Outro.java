public class Outro extends Pedido {

    private String tamanho;

    public Outro() {
    }

    public Outro(int codigo, String descricao, double preco, String tamanho) {
        super(codigo, descricao, preco);
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Outro:" +
                super.toString() +
                "\nTamanho: " + tamanho + "\n";
    }

    @Override
    public void listar() {
        for (Pedido outro : Main.listaPedidos) {
            if (outro instanceof Outro) {
                System.out.println(outro);
            }
        }
    }

    @Override
    public int valida(int codigo) {
        for (int i = 0; i < Main.listaPedidos.size(); i++) {
            if (Main.listaPedidos.get(i).getCodigo() == codigo && Main.listaPedidos.get(i) instanceof Outro) {
                return i;
            }
        }
        return -1;
    }
}
