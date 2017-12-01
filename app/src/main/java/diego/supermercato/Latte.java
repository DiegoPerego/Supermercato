package diego.supermercato;

/**
 * Created by utente3.academy on 30-Nov-17.
 */

public class Latte extends Prodotti{
    public Latte(String marca, int prezzo) {
        super(marca, prezzo);
    }

    public Latte() {
        super();
    }

    @Override
    public String getMarca() {
        return super.getMarca();
    }

    @Override
    public void setMarca(String marca) {
        super.setMarca(marca);
    }

    @Override
    public int getPrezzo() {
        return super.getPrezzo();
    }

    @Override
    public void setPrezzo(int prezzo) {
        super.setPrezzo(prezzo);
    }
}
