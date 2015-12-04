package de.fhdw.bfws114a.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button mLoginButton, mProfileManagementButton;
	private ImageButton mInfoButton;
	private Spinner mChoiceList;	
	private Context mContext; //To instantiate the Choicelist (Spinner) the context has to be accessable

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_login);
		mContext = act;
		mLoginButton = (Button) act.findViewById(R.id.login);
		mProfileManagementButton = (Button) act.findViewById(R.id.profile_management_start);		
		mChoiceList = (Spinner) act.findViewById(R.id.choose_profile);
		mInfoButton = (ImageButton) act.findViewById(R.id.ib_information_button_login);
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
	
	public ImageButton getInfoButton(){
		return mInfoButton;
	}
	
	public void setChoiceList(ArrayList<User> choiceList) {		
		ArrayAdapter<User> spinnerAdapter = new ArrayAdapter<User>(mContext, R.layout.spinner_item, choiceList);
		mChoiceList.setAdapter(spinnerAdapter);
		
	}
	
	//Whether no user has been chosen, the App shows a Toast (Message) to inform the User
	public void showToast(Activity act){
		 Toast toast = Toast.makeText(act, act.getString(R.string.login_failed), Toast.LENGTH_LONG);
		 LinearLayout toastLayout = (LinearLayout) toast.getView();
		 TextView toastTV = (TextView) toastLayout.getChildAt(0);
		 toastTV.setTextSize(30);
		 toastTV.setTextColor(Color.RED);
		 toast.show();	 
	}
	
}
