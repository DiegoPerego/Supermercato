package diego.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegistratiActivity extends AppCompatActivity implements TaskDelegate{

    private EditText username;
    private Button salvaUser;
    private Intent i;
    private String user;
    private DatabaseReference database;
    private ProgressDialog dialog;
    private String numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);

        username = findViewById(R.id.eUsername);
        salvaUser = findViewById(R.id.bSalvaUtente);
        i= new Intent(getApplicationContext(), MainActivity.class);
        final TaskDelegate delegate = this;


        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://supermercato-5a96b.firebaseio.com/Users");
        numero = "";

        salvaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                if (user.equals("")){
                    Toast.makeText(getApplicationContext(), "Inserisci il tuo Username", Toast.LENGTH_SHORT).show();
                }else {
                    restNumero(delegate);
                    InternalStorage.writeObject(getApplicationContext(), "User", user);
                    startActivity(i);
                }
            }
        });
    }

    public void restNumero(final TaskDelegate delegate){
        dialog= new ProgressDialog(RegistratiActivity.this);
        dialog.setMessage("Registrazione in corsco");
        dialog.show();

        FirebaseRest.get("Users", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                numero = JsonParser.position(s);
                database.child(numero).setValue(user);
                delegate.taskCompleto("Registrazione Completata");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompleto("Errore...Riprova");
            }
        });
    }

    @Override
    public void taskCompleto(String s) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
