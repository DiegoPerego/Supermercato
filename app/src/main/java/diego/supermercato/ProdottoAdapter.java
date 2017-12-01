package diego.supermercato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by utente3.academy on 01-Dec-17.
 */

public class ProdottoAdapter extends RecyclerView.Adapter<ProdottoAdapter.ViewProduct>{

    private List<Prodotti>prodotti;
    private Context context;

    public ProdottoAdapter(Context context, List<Prodotti>prodotti) {
        this.prodotti = prodotti;
        this.context = context;
    }

    @Override
    public ProdottoAdapter.ViewProduct onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewProduct pA = new ViewProduct(v, parent.getContext());
        return pA;
    }

    @Override
    public void onBindViewHolder(ProdottoAdapter.ViewProduct holder, int position) {

        Prodotti prod = prodotti.get(position);
        holder.prezzo.setText(""+prod.getPrezzo());
        holder.marca.setText(prod.getMarca());
        if(prod instanceof Carne){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.carne));
        }else if(prod instanceof Pesce){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.pesce));
        }else if(prod instanceof Latte){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.latte_bottiglia));
        }
    }

    @Override
    public int getItemCount() {
        return prodotti.size();
    }

    public static class ViewProduct extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView marca;
        public TextView prezzo;

        public ViewProduct(View v, Context context){
            super(v);

            imageView = v.findViewById(R.id.imageRow);
            marca = v.findViewById(R.id.tMarca);
            prezzo = v.findViewById(R.id.tPrezzo);
        }
    }
}
