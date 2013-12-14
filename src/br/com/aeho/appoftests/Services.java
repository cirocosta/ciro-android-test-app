package br.com.aeho.appoftests;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;

public class Services extends Activity implements View.OnClickListener
// ,Chronometer.OnChronometerTickListener
{

	Button bRodar, bRodarService;
	ProgressBar pBar, pBarService;
	ResponseReceiver mReceiver;
	// Chronometer cCronometro;

	long startTime = 0;

	private static final String RODANDO = "Rodando";
	private static final String RODAR = "Rodar";
	private static final String __CALLER__ = "__caller__";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.services);
		mReceiver = new ResponseReceiver();
		initialize();
		LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
				new IntentFilter(Constants.LOCAL_BROADCAST_TIMER));
		bRodar.setOnClickListener(this);
		bRodarService.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
		super.onDestroy();
	}

	private void initialize() {
		pBar = (ProgressBar) findViewById(R.id.services_pBar);
		bRodar = (Button) findViewById(R.id.services_bRodar);
		pBarService = (ProgressBar) findViewById(R.id.services_pBarService);
		bRodarService = (Button) findViewById(R.id.services_bRodarService);
		// cCronometro = (Chronometer) findViewById(R.id.services_cCronometro);
		// cCronometro.setOnChronometerTickListener(this);
	}

	private void inicializa_intent_service() {
		Intent myServiceIntent = new Intent(Services.this, MyService.class);
		startService(myServiceIntent);
	}

	private void inicializa_service() {
		Intent intent = new Intent(Services.this, NormalService.class);
		startService(intent);
	}

	public class ResponseReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int status = intent.getIntExtra("status", -1);
			int set_of_elements = intent.getIntExtra("set_of_elements", -1);
			set_status_of_elements(status, set_of_elements);
		}
	}

	public void set_status_of_elements(int status, int set_of_elements) {
		// status : running(1) or not(0)
		// set_of_elements : service(1) , intentservice(0)
		switch (set_of_elements) {
		case 0:
			if (status == 1) {
				bRodarService.setText(RODANDO);
				pBarService.setVisibility(View.VISIBLE);
			} else if (status == 0) {
				bRodarService.setText(RODAR);
				pBarService.setVisibility(View.GONE);
			} else {
				bRodarService.setText("erro");
				pBarService.setVisibility(View.GONE);
			}
			break;
		case 1:
			if (status == 1) {
				bRodar.setText(RODANDO);
				pBar.setVisibility(View.VISIBLE);
				startTime = SystemClock.elapsedRealtime();
				// cCronometro.setBase(startTime);
				// cCronometro.start();
			} else if (status == 0) {
				// cCronometro.stop();
				bRodar.setText(RODAR);
				pBar.setVisibility(View.GONE);
			} else {
				bRodar.setText("erro");
				pBar.setVisibility(View.GONE);
			}
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.services_bRodar:
			inicializa_intent_service();
			break;
		case R.id.services_bRodarService:
			inicializa_service();
			break;
		}
	}

	// @Override
	// public void onChronometerTick(Chronometer chronometer) {
	// switch (chronometer.getId()) {
	// case R.id.services_cCronometro:
	// Log.v("Services", cCronometro.getText().toString());
	// break;
	// }
	// }
}
