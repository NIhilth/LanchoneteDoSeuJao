public class Bebida extends Pedido {

    private double volume;

    public Bebida() {
    }

    public Bebida(int codigo, String descricao, double preco, double volume) {
        super(codigo, descricao, preco);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Bebida: " +
                super.toString() +
                "\nVolume: " + volume + "\n";
    }

    @Override
    public void listar() {
        for (Pedido bebida : Main.listaPedidos) {
            if(bebida instanceof Bebida) {
                System.out.println(bebida);
            }
        }
    }

    @Override
    public int valida(int codigo) {
        for (int i = 0; i < Main.listaPedidos.size(); i++) {
            if (Main.listaPedidos.get(i).getCodigo() == codigo && Main.listaPedidos.get(i) instanceof Bebida) {
                return i;
            }
        }
        return -1;
    }
}
