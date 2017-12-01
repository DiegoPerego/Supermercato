package diego.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button registra;
    private Button prodotto;
    private TextView nome;
    private Intent register;
    private Intent product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registra = findViewById(R.id.bRegistrati);
        prodotto = findViewById(R.id.bMostraProd);
        nome = findViewById(R.id.tOspite);

        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register = new Intent(getApplicationContext(), RegistratiActivity.class);
                startActivity(register);
            }
        });

        prodotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product = new Intent(getApplicationContext(), ProdottiActivity.class);
                startActivity(product);

            }
        });

        Object obj = InternalStorage.readObject(getApplicationContext(), "User");

        if (obj!= null) {
            String s = obj.toString();
            nome.setText("" + s);
        }
    }
}