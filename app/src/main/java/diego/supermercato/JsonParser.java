package diego.supermercato;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by utente3.academy on 01-Dec-17.
 */

public class JsonParser {

    public static String position(String json){
        int num = 1;
        String numeri = "";
        String getKey = null;
        JSONObject usercom= null;
        try {
            usercom = new JSONObject(json);
            Iterator iterator = usercom.keys();
            while (iterator.hasNext()){
                num++;
                iterator.next();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(num<10){
            numeri = "0"+ num;
        }else {
            numeri=""+num;
        }
        return numeri;
    }

    public static List<Prodotti> jsonParser(String json){
        List<Prodotti> prodotti = new ArrayList<>();
        String getKey = null;

        JSONObject usercom = null;

        try {
            usercom = new JSONObject(json);
            Iterator iterator = usercom.keys();

            while (iterator.hasNext()){
                getKey = (String) iterator.next();
                JSONObject prod = usercom.getJSONObject(getKey);
                Iterator iteratorC = prod.keys();

                while (iteratorC.hasNext()){
                    String key = (String)iteratorC.next();
                    JSONObject num = prod.getJSONObject(key);
                    Iterator iteratorN = num.keys();

                    if(getKey.equalsIgnoreCase("carne")) {

                        Carne carne = new Carne();
                        while (iteratorN.hasNext()) {
                            String keys = (String) iteratorN.next();
                            String s = num.getString(keys);

                            if (keys.equalsIgnoreCase("Marca")) {
                                carne.setMarca(s);
                            }
                            if (keys.equalsIgnoreCase("Prezzo")) {
                                carne.setPrezzo(Integer.parseInt(s));
                            }
                        }
                        prodotti.add(carne);
                    }

                    if(getKey.equalsIgnoreCase("latte")) {

                        Latte latte = new Latte();
                        while (iteratorN.hasNext()) {
                            String keys = (String) iteratorN.next();
                            String s = num.getString(keys);

                            if (keys.equalsIgnoreCase("Marca")) {
                                latte.setMarca(s);
                            }
                            if (keys.equalsIgnoreCase("Prezzo")) {
                                latte.setPrezzo(Integer.parseInt(s));
                            }
                        }
                        prodotti.add(latte);
                    }

                    if(getKey.equalsIgnoreCase("pesce")) {

                        Pesce pesce = new Pesce();
                        while (iteratorN.hasNext()) {
                            String keys = (String) iteratorN.next();
                            String s = num.getString(keys);

                            if (keys.equalsIgnoreCase("Marca")) {
                                pesce.setMarca(s);
                            }
                            if (keys.equalsIgnoreCase("Prezzo")) {
                                pesce.setPrezzo(Integer.parseInt(s));
                            }
                        }
                        prodotti.add(pesce);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return prodotti;
    }
}
