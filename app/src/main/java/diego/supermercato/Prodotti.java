package diego.supermercato;

/**
 * Created by utente3.academy on 30-Nov-17.
 */

public class Prodotti {

    private String marca;
    private int prezzo;

    public Prodotti(String marca, int prezzo) {
        this.marca = marca;
        this.prezzo = prezzo;
    }

    public Prodotti() {
        this.marca = null;
        this.prezzo = 0;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }
}
