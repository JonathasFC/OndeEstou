package pooa20171.iff.br.ondeestou.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import pooa20171.iff.br.ondeestou.R;
import pooa20171.iff.br.ondeestou.util.PermissionUtils;

public class UltimaLocalizacaoActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };


    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private TextView tvLatitude;
    private TextView tvLongitude;
    private TextView tvAltitude;
    private TextView tvVelocidade;
    private TextView tvProvedor;
    private TextView tvPresicao;
    private TextView tvHora;

    private TextView tvRua;
    private TextView tvCidade;
    private TextView tvEstado;
    private TextView tvPais;
    private TextView tvCompleto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultima_localizacao);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvAltitude = (TextView) findViewById(R.id.tvAltitude);
        tvPresicao = (TextView) findViewById(R.id.tvPrecisao);
        tvVelocidade = (TextView) findViewById(R.id.tvVelocidade);
        tvProvedor = (TextView) findViewById(R.id.tvProvedor);
        tvHora = (TextView) findViewById(R.id.tvHora);

        tvCidade = (TextView) findViewById(R.id.tvCidade);
        tvEstado = (TextView) findViewById(R.id.tvEstado);
        tvPais = (TextView) findViewById(R.id.tvPais);
        tvRua = (TextView) findViewById(R.id.tvRua);
        tvCompleto = (TextView) findViewById(R.id.tvCompleto);

        callConnection();
        PermissionUtils.validate(this, 0, permissoes);

        googleApiClient.connect();
    }

    private synchronized void callConnection() {
        Log.i("LOG", "callConnection()");
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

    }

    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else
            googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
