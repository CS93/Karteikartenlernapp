package de.fhdw.bfws114a.classManagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;

public class Init extends Activity {

	private Data mData;
	private Gui mGui;
	private ApplicationLogic mApplicationLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("DEBUG","Activity gestartet"); 
		//nur zum Testen
//		setContentView(R.layout.activity_settings_class_management);
		//Der zweite Parameter den aktuellen User als String
		initData((User) getIntent().getSerializableExtra(Constants.KEY_PAR_CURRENT_USER_VALUE));
		initGui();
		initApplicationLogic();
		initEventToListenerMapping();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}	
	
	private void initData(User user) {
		mData = new Data(this,  user);
		
	}
	
	private void initGui() {
		mGui = new Gui(this, mData);
		
	}

	private void initApplicationLogic() {
		mApplicationLogic = new ApplicationLogic(mData, mGui);
	}

	private void initEventToListenerMapping() {
		new EventToListenerMapping(mGui, mApplicationLogic);
		
	}
}

