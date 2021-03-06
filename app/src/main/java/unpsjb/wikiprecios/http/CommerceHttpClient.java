package unpsjb.wikiprecios.http;

import android.content.Context;
import android.location.Location;

import unpsjb.wikiprecios.config.Routes;
import unpsjb.wikiprecios.controller.SessionManager;
import unpsjb.wikiprecios.service.LocationService;
import unpsjb.wikiprecios.view.Coordinator;

/**
 * Esta clase se encarga de realizar las consultas, que busca los comercios pertenecientes a un usuario.
 */
public class CommerceHttpClient extends HttpClient implements HttpResponseHandler {

    public CommerceHttpClient(Coordinator coordinator, Context context) {
        super(coordinator,context);
    }

    /**
     * Se ocupa de realizar una consulta, con la ubicacion actual para recibir los comercios mas
     * cercanos al usuario
     */
    public void sendRequest() {
        HttpHandler http = new HttpHandler(base_url + Routes.URL_NEARBY_COMMERCES,HttpHandler.GET_REQUEST);
        Location location = LocationService.getInstance(context).getLocation();

        http.addParams("latitud", String.valueOf(location.getLatitude()));
        http.addParams("longitud", String.valueOf(location.getLongitude()));
        http.addParams("usuario", SessionManager.getInstance(context).getUserLoged());
        http.setListener(this);
        http.sendRequest();
        coordinator.showDialog("Obteniendo lista de comercios...");
    }

    @Override
    public void onSuccess(Object commerces) {
        coordinator.hideDialog();
        coordinator.viewCommerces(commerces);
    }
}
