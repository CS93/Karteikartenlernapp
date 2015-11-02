package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import de.fhdw.bfws114asc.counter1.R;

public class Init extends Activity implements OnClickListener {

	private Button mLoginButton, mProfileManagementButton;
	private ListView mChoiceList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mLoginButton = (Button) findViewById(R.id.increment);
		mProfileManagementButton = (Button) findViewById(R.id.decrement);		
		mChoiceList = (ListView) findViewById(R.id.choice);
		mLoginButton.setOnClickListener(this);
		mProfileManagementButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == mLoginButton){
			//Weiterleitung zur Karteiauswahl (UserMenu) + Übergabe des ausgewählten Benutzers --> mChoiceList
		} else {
			//Weiterleitung zum ProfileManagement
		}		
		Log.d("", "Folgender Button wurde geklickt:" + v);
	}
	

}
