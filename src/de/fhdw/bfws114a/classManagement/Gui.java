package de.fhdw.bfws114a.classManagement;

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
import de.fhdw.bfws114a.lernKartei.R;

public class Gui {
	
	private Button mDefaultClassButton, mSaveClassButton;
	private EditText mOneClass, mTwoClass, mThreeClass, mFourClass, mFiveClass, mSixClass;
	private Spinner  mOneClassSpinner, mTwoClassSpinner, mThreeClassSpinner, 
					 mFourClassSpinner, mFiveClassSpinner, mSixClassSpinner;

	public Gui(Activity act) {
		act.setContentView(R.layout.activity_settings_class_management);
		mDefaultClassButton 	= (Button) act.findViewById(R.id.b_default_class_management);
		mSaveClassButton 		= (Button) act.findViewById(R.id.b_save_class_management);	
		mOneClass 		= (EditText) act.findViewById(R.id.et_class_one_class_management);
		mTwoClass 		= (EditText) act.findViewById(R.id.et_class_two_class_management);
		mThreeClass 	= (EditText) act.findViewById(R.id.et_class_three_class_management);
		mFourClass		= (EditText) act.findViewById(R.id.et_class_four_class_management);
		mFiveClass 		= (EditText) act.findViewById(R.id.et_class_five_class_management);
		mSixClass 		= (EditText) act.findViewById(R.id.et_class_six_class_management);
		mOneClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_one_class_management);
		mTwoClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_two_class_management);
		mThreeClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_three_class_management);
		mFourClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_four_class_management);
		mFiveClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_five_class_management);
		mSixClassSpinner 	= (Spinner) act.findViewById(R.id.s_class_six_class_management);
	}

	public Button getDefaultClassButton() {
		return mDefaultClassButton;
	}

	public Button getSaveClassButton() {
		return mSaveClassButton;
	}
	
	public boolean checkClassEditText(){
		if (mOneClass.getText().toString().isEmpty() == true ||
			mTwoClass.getText().toString().isEmpty() == true ||
			mThreeClass.getText().toString().isEmpty() == true ||
			mFourClass.getText().toString().isEmpty() == true ||
			mFiveClass.getText().toString().isEmpty() == true ||
			mSixClass.getText().toString().isEmpty() == true){
			return false;
		}
		return true;
	}
	
	public int[] getClassEditText() {
		int[] Classes = new int[6];
		try{
		Classes[0] = Integer.parseInt(mOneClass.getText().toString());
		Classes[1] = Integer.parseInt(mTwoClass.getText().toString());
		Classes[2] = Integer.parseInt(mThreeClass.getText().toString());
		Classes[3] = Integer.parseInt(mFourClass.getText().toString());
		Classes[4] = Integer.parseInt(mFiveClass.getText().toString());
		Classes[5] = Integer.parseInt(mSixClass.getText().toString());
		}
	   catch (NumberFormatException e) {
		    return null;
	   }
		return Classes;
	}
	
	public int[] getClassSpinner() {
		int[] ClassSpinner = new int[6];
		ClassSpinner[0] = mOneClassSpinner.getSelectedItemPosition();
		ClassSpinner[1] = mTwoClassSpinner.getSelectedItemPosition();
		ClassSpinner[2] = mThreeClassSpinner.getSelectedItemPosition();
		ClassSpinner[3] = mFourClassSpinner.getSelectedItemPosition();
		ClassSpinner[4] = mFiveClassSpinner.getSelectedItemPosition();
		ClassSpinner[5] = mSixClassSpinner.getSelectedItemPosition();
		return ClassSpinner;
	}
	
	public void setClass(int[] classes) {
		mOneClass.setText(Integer.toString(classes[0]));
		mTwoClass.setText(Integer.toString(classes[1]));
		mThreeClass.setText(Integer.toString(classes[2]));
		mFourClass.setText(Integer.toString(classes[3]));
		mFiveClass.setText(Integer.toString(classes[4]));
		mSixClass.setText(Integer.toString(classes[5]));
	}
		
	public void setClassSpinner(Context context, ArrayList<String> classSpinner, int[] position) {	
		//generate Adapter, setAdapter, setSelection
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, classSpinner);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		setAdapter(spinnerAdapter);
		setSelection(position);
	}	
	
	private void setAdapter(ArrayAdapter<String> spinnerAdapter) {
		mOneClassSpinner.setAdapter(spinnerAdapter);
		mTwoClassSpinner.setAdapter(spinnerAdapter);
		mThreeClassSpinner.setAdapter(spinnerAdapter);
		mFourClassSpinner.setAdapter(spinnerAdapter);
		mFiveClassSpinner.setAdapter(spinnerAdapter);
		mSixClassSpinner.setAdapter(spinnerAdapter);
	}
	
	private void setSelection(int[] position) {
		mOneClassSpinner.setSelection(position[0]);
		mTwoClassSpinner.setSelection(position[1]);
		mThreeClassSpinner.setSelection(position[2]);
		mFourClassSpinner.setSelection(position[3]);
		mFiveClassSpinner.setSelection(position[4]);
		mSixClassSpinner.setSelection(position[5]);
	}
	
	public void showToast(Context context, String messageText){
		//create Toast, LinearLayout and TextView, set TextSize and TextColor 
		//of the TextView and Show the Toast
		 Toast toast = Toast.makeText(context, messageText, Toast.LENGTH_SHORT);
		 LinearLayout toastLayout = (LinearLayout) toast.getView();
		 TextView toastTV = (TextView) toastLayout.getChildAt(0);
		 toastTV.setTextSize(30);
		 toastTV.setTextColor(Color.RED);
		 toast.show();
	}
}
