package de.fhdw.bfws114a.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private ArrayList<String> mUserList; //Eventuell eine Liste verwenden
	private Activity mActivity;
	
	public Data(Bundle b, Activity activity){
		Intent intent;		
		mActivity = activity;
		if (b == null ){
			//Erstes Aufrufen dieser Activity			
			ladeUser();
//			Dieser Teil scheint die Counter-Value an die Activity zu binden
//			intent = activity.getIntent();
//			intent.getIntExtra(Constants.KEY_PAR_COUNTER_VALUE, mCounterValue);
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void ladeUser(){
		//User aus xml laden und in user (String Array) hineinschreiben
		mUserList = DataInterface.loadUser();	
		
	}
	
	public ArrayList<String> getUser(){
		return mUserList;
	}
	private void restoreDataFromBundle(Bundle b) {
		mUserList = b.getStringArrayList(Constants.KEY_USER_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){
		b.putStringArrayList(Constants.KEY_USER_VALUE, mUserList);
	}
	
}
