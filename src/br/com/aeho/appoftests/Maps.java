package br.com.aeho.appoftests;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class Maps extends FragmentActivity {

	private GoogleMap mMap;
	ImageLoader imageLoader;
	static final String iconUrl = "http://gymup.com.br/media/acadciro/img/profile.png";
	static Bitmap icon;
	Marker academiaMarker;

	int CONTADOR_ICON = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapas);
		initialize();
		prepare_image_loader();
		set_up_maps(mMap);
		imageLoader.loadImage(iconUrl, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {

			}

			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {

			}

			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				icon = loadedImage;
				change_contador_icon();
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {

			}
		});
	}

	private void change_contador_icon() {
		CONTADOR_ICON += 1;
		if (CONTADOR_ICON == 2) {
			changeMarkerIcon(academiaMarker, icon);
		}
	}

	private void changeMarkerIcon(Marker marker, Bitmap icon) {
		marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
	}

	private void set_up_maps(GoogleMap mMap) {
		if (mMap != null) {
			LatLng academiaPosicao = new LatLng(-33.796923, 150.922433);
			academiaMarker = mMap.addMarker(new MarkerOptions()
					.position(academiaPosicao).title("AcadCIro")
					.snippet("academia maneirona pra treinar"));
			academiaMarker.showInfoWindow();
			change_contador_icon();
		}
	}

	private void prepare_image_loader() {
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build());
	}

	private void initialize() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}
	}

}
