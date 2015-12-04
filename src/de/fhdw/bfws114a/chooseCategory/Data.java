package de.fhdw.bfws114a.chooseCategory;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.Statistics;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private User mUser;
	private Activity mActivity;
	private DataInterface mDataInterface;
	private ArrayList<String> mKarteien;
	private ArrayList<Statistics> mStatistik;
	//Hinweis: Man braucht auch die dazugeh�rige Statistik
	
	public Data(Bundle b, Activity activity, User user){	
		mActivity = activity;
		mUser = user;
		mDataInterface = new DataInterface(activity);
		if (b == null ){
			//Erstes Aufrufen dieser Activity			
			loadCategories();
			loadStatistics();
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void loadCategories(){
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		mKarteien = mDataInterface.loadCategories();	
		
	}

	private void loadStatistics(){
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		mStatistik = mDataInterface.loadStatistics(mKarteien);	
		
	}
	
	public User getUser() {
		return mUser;
	}

	public ArrayList<String> getKarteien() {
		return mKarteien;
	}

		
	public ArrayList<Statistics> getStatistik() {
		return mStatistik;
	}

	private void restoreDataFromBundle(Bundle b) {
		//The Serializable is used to put the User-Object in Bundle
		mKarteien = (ArrayList<String>) b.getStringArrayList(Constants.KEY_KARTEIEN_VALUE);
		mStatistik = (ArrayList<Statistics>) b.getSerializable(Constants.KEY_STATISTICS_VALUE);
	}
	
	public void saveDataInBundle(Bundle b){
		//The Serializable is used to put the User-Object in Bundle
		b.putStringArrayList(Constants.KEY_KARTEIEN_VALUE, mKarteien);
		b.putSerializable(Constants.KEY_STATISTICS_VALUE, mStatistik);
	}
	
}
