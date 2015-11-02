package de.fhdw.bfws114a.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.he.data.Constants;

public class Data {
	
	private int mCounterValue;
	private Activity mActivity;
	
	public Data(Bundle b, Activity activity){
		Intent intent;
		
		mActivity = activity;
		if ( b == null ){
			mCounterValue=0;
			intent = activity.getIntent();
			intent.getIntExtra(Constants.KEY_PAR_COUNTER_VALUE, mCounterValue);
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void restoreDataFromBundle(Bundle b) {
		mCounterValue = b.getInt(Constants.KEY_COUNTER_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){
		b.putInt(Constants.KEY_COUNTER_VALUE, mCounterValue);
	}

	public void incrementCounterValue () {
		mCounterValue++;
	}
	
	public int getCounterValue(){
		return mCounterValue;
	}
	
	public void setCounterValue(int counterValue){
		mCounterValue = counterValue;
	}
	
}
