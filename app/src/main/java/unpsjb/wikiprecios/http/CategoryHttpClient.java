package unpsjb.wikiprecios.http;

import android.content.Context;

import unpsjb.wikiprecios.config.Routes;
import unpsjb.wikiprecios.view.Coordinator;

/**
 * Created by emanuel on 05/10/17.
 */
public class CategoryHttpClient extends HttpClient implements HttpResponseHandler {

    public CategoryHttpClient(Coordinator coordinator, Context context) {
        super(coordinator,context);
    }

    /**
     * Envio de la peticion para obtener la lista de categorias.
     */
    public void sendRequest() {
        HttpHandler http = new HttpHandler(base_url + Routes.URL_GET_RUBRO, HttpHandler.GET_REQUEST);
        http.setListener(this);
        http.sendRequest();
    }

    @Override
    public void onSuccess(Object categories) {
        coordinator.viewCategories(categories);
    }
}