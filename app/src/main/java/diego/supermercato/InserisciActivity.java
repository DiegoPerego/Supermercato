package diego.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class InserisciActivity extends AppCompatActivity implements TaskDelegate{

    private Spinner spinner;
    private Button inserisci;
    private ImageView image;
    private EditText marca;
    private EditText prezzo;
    private ProgressDialog dialog;
    private Prodotti prodotto;
    private Intent intent;
    private String num;
    private String tipo;
    private DatabaseReference databaseReference;
    private String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci);

        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.iItem);
        inserisci = findViewById(R.id.bInserisci);
        marca = findViewById(R.id.eMarca);
        prezzo = findViewById(R.id.ePrezzo);

        final TaskDelegate delegate= this;

        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserisciItem();
                prodotti(delegate);
                databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://supermercato-5a96b.firebaseio.com/Prodotti/"+ tipo);
                numero = "";
                InternalStorage.writeObject(getApplicationContext(), "Nuovo", prodotto);

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void inserisciItem(){
        if (marca.getText().toString().equals("")|| prezzo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Inserisci tutti i dati" ,Toast.LENGTH_SHORT).show();
        }else{
            Object obj = InternalStorage.readObject(getApplicationContext(), "Prodotto");
            if(spinnerItem().equals("Carne")){
                tipo = "Carne";
                Carne carne = new Carne();
                image.setImageDrawable(getResources().getDrawable(R.drawable.carne));
                carne.setMarca(marca.getText().toString());
                carne.setPrezzo(Integer.parseInt(""+prezzo.getText()));
                prodotto=carne;
            }

            if(spinnerItem().equals("Pesce")){
                tipo  = "Pesce";
                Pesce pesce = new Pesce();
                image.setImageDrawable(getResources().getDrawable(R.drawable.pesce));
                pesce.setMarca(marca.getText().toString());
                pesce.setPrezzo(Integer.parseInt(""+prezzo.getText()));
                prodotto=pesce;
            }

            if(spinnerItem().equals("Latte")){
                tipo = "Latte";
                Latte latte = new Latte();
                image.setImageDrawable(getResources().getDrawable(R.drawable.latte_bottiglia));
                latte.setMarca(marca.getText().toString());
                latte.setPrezzo(Integer.parseInt(""+prezzo.getText()));
                prodotto=latte;
            }
        }
    }

    public void prodotti(final TaskDelegate delegate){
        dialog= new ProgressDialog(InserisciActivity.this);
        dialog.setMessage("Inserimento prodotto");
        dialog.show();

        FirebaseRest.get("Prodotti/"+ tipo, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                num = JsonParser.position(s);
                databaseReference.child(num).setValue(prodotto);
                delegate.taskCompleto("Inserimento completato");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompleto("Errore...Riprova");
            }
        });
    }

    private String spinnerItem(){
        String item = (String) spinner.getSelectedItem();
        return item;
    }

    @Override
    public void taskCompleto(String s) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
