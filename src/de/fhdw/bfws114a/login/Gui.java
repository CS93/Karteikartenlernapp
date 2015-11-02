package de.fhdw.bfws114a.login;

import java.util.ArrayList;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import de.fhdw.bfws114asc.counter1.R;

public class Gui {
	
	private Button mLoginButton, mProfileManagementButton;
	private Spinner mChoiceList;

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_login);
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
	
	public void setChoiceList(ArrayList<String> choiceList) {
//		choiceList.add("");
//		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choiceList);
//		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		mChoiceList.setAdapter(spinnerAdapter);
	}

	
}
