package de.fhdw.bfws114a.profileManagement;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button mAddUserButton, mDelUserButton;
	private EditText mAddUser;
	private Spinner mChoiceList;
	private Context mContext;

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_profile_management);
		mContext = act;
		mAddUserButton 	= (Button) 		act.findViewById(R.id.b_adduser_profile_management);
		mAddUser 		= (EditText)	act.findViewById(R.id.et_adduser_profile_management);	
		mDelUserButton 	= (Button) 		act.findViewById(R.id.b_deluser_profile_management);		
		mChoiceList 	= (Spinner) 	act.findViewById(R.id.s_deluser_profile_management);
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
		ArrayAdapter<User> spinnerAdapter = new ArrayAdapter<User>(mContext, R.layout.spinner_item, userList);
		mChoiceList.setAdapter(spinnerAdapter);
	}	
	
	public void showToast(int message){
		 Toast toast = Toast.makeText(mContext, mContext.getString(message), Toast.LENGTH_SHORT);
		 LinearLayout toastLayout = (LinearLayout) toast.getView();
		 TextView toastTV = (TextView) toastLayout.getChildAt(0);
		 toastTV.setTextSize(30);
		 toastTV.setTextColor(Color.RED);
		 toast.show();
	}
	
}

