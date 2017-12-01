package diego.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ProdottiActivity extends AppCompatActivity implements TaskDelegate{

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Prodotti> prodotti;
    private ProgressDialog dialog;
    private ProdottoAdapter adapter;
    private FloatingActionButton inserisci;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotti);
        TaskDelegate delegate = this;
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        restProdotti(delegate);

        inserisci = findViewById(R.id.fab);
        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), InserisciActivity.class);
                InternalStorage.writeObject(getApplicationContext(), "Prodotto", prodotti);
                startActivity(intent);
            }
        });

        Object obj = InternalStorage.readObject(getApplicationContext(), "Nuovo");
        if(obj != null){
            prodotti.add((Prodotti) obj);
            adapter.notifyDataSetChanged();
        }

    }

    public void restProdotti (final TaskDelegate delegate){
        dialog= new ProgressDialog(ProdottiActivity.this);
        dialog.setMessage("Caricamento prodotti");
        dialog.show();

        prodotti = new ArrayList<>();

        FirebaseRest.get("Prodotti", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                prodotti= JsonParser.jsonParser(s);
                adapter = new ProdottoAdapter(getApplicationContext(), prodotti);
                recyclerView.setAdapter(adapter);
                delegate.taskCompleto("Caricamento Completato");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompleto("Errore...Riprova");
            }
        });

    }

    public void taskCompleto(String s){
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
