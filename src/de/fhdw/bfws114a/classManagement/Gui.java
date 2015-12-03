package de.fhdw.bfws114a.classManagement;

import java.util.ArrayList;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import de.fhdw.bfws114a.lernKartei.R;
import de.fhdw.bfws114a.classManagement.Data;

public class Gui {
	
	private Button mDefaultClassButton, mSaveClassButton;
	private EditText mOneClass, mTwoClass, mThreeClass, mFourClass, mFiveClass, mSixClass;
	private Spinner  mOneClassSpinner, mTwoClassSpinner, mThreeClassSpinner, 
					 mFourClassSpinner, mFiveClassSpinner, mSixClassSpinner;
	private Data mData;

	public Gui(Activity act, Data data) {
		act.setContentView(R.layout.activity_settings_class_management);
		mData = data;
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
	
	public int[] getClassEditText() {
		int[] Classes = new int[6];
		Classes[0] = Integer.parseInt(mOneClass.getText().toString());
		Classes[1] = Integer.parseInt(mTwoClass.getText().toString());
		Classes[2] = Integer.parseInt(mThreeClass.getText().toString());
		Classes[3] = Integer.parseInt(mFourClass.getText().toString());
		Classes[4] = Integer.parseInt(mFiveClass.getText().toString());
		Classes[5] = Integer.parseInt(mSixClass.getText().toString());
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
		
	public void setClassSpinner(ArrayList<String> classSpinner, int[] position) {	
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mData.getContext(), android.R.layout.simple_spinner_item, classSpinner);
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
	
}
