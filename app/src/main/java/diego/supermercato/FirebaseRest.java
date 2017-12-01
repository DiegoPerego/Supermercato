package diego.supermercato;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by utente3.academy on 30-Nov-17.
 */

public class FirebaseRest {

    private static final String baseUrl = "https://supermercato-5a96b.firebaseio.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(getTotalUrl(url), params, asyncHttpResponseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(getTotalUrl(url), params, asyncHttpResponseHandler);
    }

    private static String getTotalUrl(String relativeUrl){
        return baseUrl+ relativeUrl + ".json";
    }
}
