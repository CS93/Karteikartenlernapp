package de.fhdw.bfws114a.userMenu;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;

public class Init extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Die folgende Zeile enthält den aktuellen User als String
		getIntent().getStringExtra(Constants.KEY_PAR_CURRENT_USER_VALUE);
		//setContent auf UserMenu-Layout 
		// ...
	}



}
