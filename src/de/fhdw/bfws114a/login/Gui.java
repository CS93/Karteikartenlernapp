package de.fhdw.bfws114a.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button mLoginButton, mProfileManagementButton;
	private Spinner mChoiceList;
	//Beim initialisieren mancher Objekte ist der Context notwendig. 
	//Um auf diesen in der Methode setChoiceList zugreifen zu können wird er hier als Membervariable definiert	
	private Context mContext;

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_login);
		mContext = act;
		mLoginButton = (Button) act.findViewById(R.id.login);
		mProfileManagementButton = (Button) act.findViewById(R.id.profile_management_start);		
		mChoiceList = (Spinner) act.findViewById(R.id.choose_profile);
	}

	public Button getLoginButton() {
		return mLoginButton;
	}

	public Button getProfileManagementButton() {
		return mProfileManagementButton;
	}

	public Spinner getChoiceList() {
		return mChoiceList;
	}
	
	public void setChoiceList(ArrayList<User> choiceList) {		
		ArrayAdapter<User> spinnerAdapter = new ArrayAdapter<User>(mContext, R.layout.spinner_item, choiceList);
		mChoiceList.setAdapter(spinnerAdapter);
		
	}	
}
