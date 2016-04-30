package com.tommyputranto.tes2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.tommyputranto.tes2.API.Api;
import com.tommyputranto.tes2.model.Model;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapActivity extends AppCompatActivity {
    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions tes;
    private TextView nama2;
    private ImageView fotoView;
    private TextView alamat;
    private TextView deskripsi;
    private ListView fasilita;
    String nama;
    private ArrayAdapter<String> mDataAdapter;
    String API = "http://dev.gits.co.id/apicafe/public";
    String lokasiLongitude;
    String lokasiLatitude;
    Double mLokasiLongitude = Double.valueOf(0);
    ;
    Double mLokasiLatitude = Double.valueOf(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        nama2 = (TextView) findViewById(R.id.textview_nama2);
        fotoView = (ImageView) findViewById(R.id.image_gambar2);
        alamat = (TextView) findViewById(R.id.textview_alamat2);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        fasilita = (ListView) findViewById(R.id.list_fasilitas);
        String hasil = "";
        String foto = "";
        nama = "";
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("tes")) {
            hasil = getIntent().getStringExtra("tes");
            nama = getIntent().getStringExtra("nama");

        }

        mDataAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.row_list,
                R.id.row_list_id,
                new ArrayList<String>()
        );
        fasilita.setAdapter(mDataAdapter);


        foto = getIntent().getStringExtra("foto");
        Picasso.with(this)
                .load(foto)
                .noFade()
                .into(fotoView);
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API).build();
        Model model = restAdapter.create(Model.class);
        model.getFeed(hasil, new Callback<Api>() {
            @Override
            public void success(Api api, Response response) {
                nama2.setText((api.getDATA().getNama()));
                alamat.setText(api.getDATA().getAlamat());
                deskripsi.setText(api.getDATA().getDeskripsi());
                mDataAdapter.clear();
                for (int i = 0; i < api.getDATA().getFasilitas().size(); i++) {


                    lokasiLongitude = api.getDATA().getLocation().getLongitude().get(0);
                    lokasiLatitude = api.getDATA().getLocation().getLatitude2().get(0);
                    mDataAdapter.add(String.valueOf(api.getDATA().getFasilitas().get(i).getNama()));
                    nama = api.getDATA().getNama();

                    mLokasiLatitude = new Double(Double.valueOf(lokasiLatitude));
                    mLokasiLongitude = new Double(Double.valueOf(lokasiLongitude));


                    getTotalHeightofListView(fasilita);
                    tes = new MarkerOptions()
                            .position(new LatLng(mLokasiLatitude, mLokasiLongitude))
                            .title(nama);

                    MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mapReady = true;
                            m_map = googleMap;
                            LatLng newYork = new LatLng(mLokasiLatitude, mLokasiLongitude);
                            CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
                            m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
                            m_map.addMarker(tes);
                        }
                    });


                }


            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("error", String.valueOf(error));

            }
        });


    }


    public static void getTotalHeightofListView(ListView listView) {

        ListAdapter mAdapter = listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}
