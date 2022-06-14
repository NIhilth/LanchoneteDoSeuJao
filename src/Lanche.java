public class Lanche extends Pedido {

    private double peso;

    public Lanche(){
    }

    public Lanche(int codigo, String descricao, double peso, double preco) {
        super(codigo, descricao, preco);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Lanche:" +
                super.toString() +
                "\nPeso: " + peso + "\n";
    }

    @Override
    public void listar() {
        for (Pedido lanche : Main.listaPedidos) {
            if(lanche instanceof Lanche) {
                System.out.println(lanche);
            }
        }
    }

    @Override
    public int valida(int codigo) {
        for (int i = 0; i < Main.listaPedidos.size(); i++) {
            if (Main.listaPedidos.get(i).getCodigo() == codigo && Main.listaPedidos.get(i) instanceof Lanche) {
                return i;
            }
        }
        return -1;
    }
}
