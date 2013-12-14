package br.com.aeho.appoftests;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UniversalImageLoader extends Activity {

	Context context;
	ListView listaImagens;
	ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.univimageloader);
		context = getApplicationContext();
		initialize();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build());

		ImagensAdapter adapter = new ImagensAdapter(
				UniversalImageLoader.this,
				R.layout.univimgloader_row,
				new String[] {
						"http://www.bibliomed.com.br/bancoimagens/imagens/kinking_carotida_interna___visao_per_operatoria.jpg",
						"http://www.p69.com.br/wp-content/uploads/2013/04/imagens-lindas-3.jpg?0bce15",
						"http://meupetideal.com.br/wp-content/uploads/2013/05/tucano.jpg",
						"http://guiaavare.com/img/upload/images/gato.jpg",
						"http://www.infoescola.com/wp-content/uploads/2009/08/full-11-3112da76de.jpg",
						"http://www.infoescola.com/wp-content/uploads/2009/08/full-11-02f767b76e.jpg"

				});
		listaImagens.setAdapter(adapter);
	}

	private void initialize() {
		listaImagens = (ListView) findViewById(R.id.univimageloader_listaImagens);
	}

	private class ImagensAdapter extends ArrayAdapter<String> {

		final Context context;
		final int layoutResourceId;
		final String[] data;

		public ImagensAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
			this.context = context;
			this.layoutResourceId = resource;
			this.data = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ImagensHolder holder = null;

			if (row == null) {
				LayoutInflater inflater = ((Activity) context)
						.getLayoutInflater();
				row = inflater.inflate(layoutResourceId, parent, false);

				holder = new ImagensHolder();
				holder.iv = (ImageView) row
						.findViewById(R.id.univimgloader_row_iv);
				row.setTag(holder);
			} else {
				holder = (ImagensHolder) row.getTag();
			}
			String url = data[position];
			imageLoader.displayImage(url, holder.iv);
			return row;
		}

	}

	private class ImagensHolder {
		ImageView iv;
	}
}
