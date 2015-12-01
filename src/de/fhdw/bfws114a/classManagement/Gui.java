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

	public EditText getOneClass() {
		return mOneClass;
	}
	
	public EditText getTwoClass() {
		return mTwoClass;
	}
	
	public EditText getThreeClass() {
		return mThreeClass;
	}
	
	public EditText getFourClass() {
		return mFourClass;
	}
	
	public EditText getFiveClass() {
		return mFiveClass;
	}
	
	public EditText getSixClass() {
		return mSixClass;
	}
	
	public void setClass(int[] classes) {
		mOneClass.setText(Integer.toString(classes[0]));
		mTwoClass.setText(Integer.toString(classes[1]));
		mThreeClass.setText(Integer.toString(classes[2]));
		mFourClass.setText(Integer.toString(classes[3]));
		mFiveClass.setText(Integer.toString(classes[4]));
		mSixClass.setText(Integer.toString(classes[5]));
	}

	public Spinner getOneClassSpinner() {
		return mOneClassSpinner;
	}
	
	public Spinner getTwoClassSpinner() {
		return mTwoClassSpinner;
	}
	
	public Spinner getThreeClassSpinner() {
		return mThreeClassSpinner;
	}
	
	public Spinner getFourClassSpinner() {
		return mFourClassSpinner;
	}
	
	public Spinner getFiveClassSpinner() {
		return mFiveClassSpinner;
	}
	
	public Spinner getSixClassSpinner() {
		return mSixClassSpinner;
	}	
	
	public void setClassSpinner(ArrayList<String> classSpinner, int[] position) {		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mData.getContext(), android.R.layout.simple_spinner_item, classSpinner);
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
