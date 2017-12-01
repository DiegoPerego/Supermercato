package diego.supermercato;

/**
 * Created by utente3.academy on 30-Nov-17.
 */

public class Carne extends Prodotti{
    public Carne(String marca, int prezzo) {
        super(marca, prezzo);
    }

    public Carne() {
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
