package de.fhdw.bfws114a.profileManagement;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import de.fhdw.bfws114a.data.User;
//import de.fhdw.bfws114asc.counter1.R;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {

//	TextView @+id/t_adduser_profile_management
//	EditText @+id/et_adduser_profile_management
//	Button @+id/b_adduser_profile_management
//	View @+id/v_separator_profile_management
//	TextView @+id/t_deluser_profile_management
//	Button @+id/b_deluser_profile_management
//	Spinner @+id/s_deluser_profile_management
	
	private Button mAddUserButton, mDelUserButton;
	private EditText mAddUser;
	private Spinner mChoiceList;
	//Beim initialisieren mancher Objekte ist der Context notwendig. 
	//Um auf diesen in der Methode setChoiceList zugreifen zu k�nnen wird er hier als Membervariable definiert	
	private Context mContext;

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_profile_management);
		mContext = act;
		mAddUserButton = (Button) act.findViewById(R.id.b_adduser_profile_management);
		mAddUser = (EditText) act.findViewById(R.id.et_adduser_profile_management);	
		mDelUserButton = (Button) act.findViewById(R.id.b_deluser_profile_management);		
		mChoiceList = (Spinner) act.findViewById(R.id.s_deluser_profile_management);
	}

	public Button getAddUserButton() {
		return mAddUserButton;
	}

	public Button getDelUserButton() {
		return mDelUserButton;
	}

	public EditText getAddUser() {
		return mAddUser;
	}
	
	public Spinner getChoiceList() {
		return mChoiceList;
	}
	
	public void setChoiceList(ArrayList<User> userList) {	
		ArrayList<User> choiseList = new ArrayList<User>();
		 for (User u : userList) {
	        	choiseList.add(u);
		}
		
		ArrayAdapter<User> spinnerAdapter = new ArrayAdapter<User>(mContext, R.layout.spinner_item, choiseList);
		mChoiceList.setAdapter(spinnerAdapter);
		
	}	
	
}

