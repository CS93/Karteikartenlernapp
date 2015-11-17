package de.fhdw.bfws114a.userMenu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;

public class Data {
	
	private String mUser; //Eventuell eine Liste verwenden
	private Activity mActivity;
	private ArrayList<String> mKarteien;
	
	public Data(Bundle b, Activity activity, String user){
		Intent intent;		
		mActivity = activity;
		mUser = user;
		
		if (b == null ){
			//Erstes Aufrufen dieser Activity
			
			ladeKarteien();
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

	private void ladeKarteien(){
		//Karteien aus xml in mKarteien laden
		
		//Test
		mKarteien = new ArrayList<String>();
		mKarteien.add("Biologie");
		
	}

	
	public String getUser() {
		return mUser;
	}

	public ArrayList<String> getKarteien() {
		return mKarteien;
	}

	private void restoreDataFromBundle(Bundle b) {
		mKarteien = b.getStringArrayList(Constants.KEY_KARTEIEN_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){
		b.putStringArrayList(Constants.KEY_KARTEIEN_VALUE, mKarteien);
	}
	
}
