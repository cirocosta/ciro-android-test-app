package br.com.aeho.appoftests;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityForResult extends Activity implements View.OnClickListener {

	private Button bGetContact, bTextit;

	private static final String TAG = "ActivityForResult";
	private static final int PICK_CONTACT_REQUEST = 1;
	private static final int TEXTIT_REQUEST = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_result);
		initialize();
		bGetContact.setOnClickListener(this);
		bTextit.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PICK_CONTACT_REQUEST:
			if (resultCode == RESULT_OK) {
				// result Intent returned by Android's Contacts or People app
				// provides a content Uri that identifies the contact the user
				// selected.
				String contact_name_and_number = get_contact_name_and_number(data
						.getData());
				if (contact_name_and_number != null) {
					Toast.makeText(ActivityForResult.this,
							contact_name_and_number, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(ActivityForResult.this,
							"Erro ao obter nome e número", Toast.LENGTH_LONG)
							.show();

				}
			} else {
				Toast.makeText(ActivityForResult.this,
						"Não foram escolhidos contatos", Toast.LENGTH_LONG)
						.show();
			}
			break;
		case TEXTIT_REQUEST:
			if (resultCode == RESULT_OK) {
				Toast.makeText(ActivityForResult.this,
						"OK! " + data.getStringExtra("text"),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(ActivityForResult.this, "CANCELED!",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	private String get_contact_name_and_number(Uri contactUri) {
		try {
			String[] projection = { Phone.DISPLAY_NAME, Phone.NUMBER };
			// ps: It should be async
			Cursor cursor = getContentResolver().query(contactUri, projection,
					null, null, null);
			cursor.moveToFirst();
			int[] columns = { cursor.getColumnIndex(Phone.DISPLAY_NAME),
					cursor.getColumnIndex(Phone.NUMBER) };
			String result = cursor.getString(columns[0]) + ", "
					+ cursor.getString(columns[1]);
			return result;
		} catch (Exception e) {
			Log.e(TAG,
					"Error getting contact name and number. " + e.getMessage());
			return null;
		}
	}

	private void initialize() {
		bTextit = (Button) findViewById(R.id.actv_for_result_bTextit);
		bGetContact = (Button) findViewById(R.id.actv_for_result_bGetContact);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actv_for_result_bGetContact:
			Intent pickContactIntent = new Intent(Intent.ACTION_PICK,
					Uri.parse("content://contacts"));
			pickContactIntent.setType(Phone.CONTENT_TYPE);
			startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
			break;
		case R.id.actv_for_result_bTextit:
			Intent textItIntent = new Intent(Intent.ACTION_VIEW);
			textItIntent.setClass(ActivityForResult.this,
					ActivityForResultTextit.class);
			startActivityForResult(textItIntent, TEXTIT_REQUEST);
			return;
		}
	}

}
