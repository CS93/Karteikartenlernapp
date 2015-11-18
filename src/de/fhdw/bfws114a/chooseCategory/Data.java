package de.fhdw.bfws114a.chooseCategory;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;

public class Data {
	
	private String mUser;
	private Activity mActivity;
	private ArrayList<String> mKarteien;
	//Hinweis: Man braucht auch die dazugehörige Statistik
	
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
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		//Außerdem braucht man auch die dazugehörige Statistik
		
		//Test
		mKarteien = new ArrayList<String>();
		mKarteien.add("Biologie");
		mKarteien.add("Chemie");
		mKarteien.add("Physik");
		mKarteien.add("Informatik");
		mKarteien.add("Sport");
		mKarteien.add("Technik");
		mKarteien.add("Geschichte");
		mKarteien.add("Erdkunde");
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
