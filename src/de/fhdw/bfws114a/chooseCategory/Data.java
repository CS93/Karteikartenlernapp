package de.fhdw.bfws114a.chooseCategory;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.StatisticsObject;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private String mUser;
	private Activity mActivity;
	private DataInterface mDataInterface;
	private ArrayList<String> mKarteien;
	private ArrayList<StatisticsObject> mStatistik;
	//Hinweis: Man braucht auch die dazugeh�rige Statistik
	
	public Data(Bundle b, Activity activity, String user){	
		mActivity = activity;
		mUser = user;
		mDataInterface = new DataInterface(activity);
		if (b == null ){
			//Erstes Aufrufen dieser Activity			
			ladeKarteien();
			ladeStatistiken();
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
		mKarteien = mDataInterface.loadCategories();	
		
	}

	private void ladeStatistiken(){
		//Karteien aus xml in mKarteien laden (es muss sichergestellt werden, dass die Anzahl der Karteien = 8 ist (siehe ApplicationLogic --> applyToData()
		mStatistik = mDataInterface.loadStatistics(mKarteien);	
		
	}
	
	public String getUser() {
		return mUser;
	}

	public ArrayList<String> getKarteien() {
		return mKarteien;
	}

		
	public ArrayList<StatisticsObject> getStatistik() {
		return mStatistik;
	}

	private void restoreDataFromBundle(Bundle b) {
		mKarteien = b.getStringArrayList(Constants.KEY_KARTEIEN_VALUE);
		
	}
	
	public void saveDataInBundle(Bundle b){
		b.putStringArrayList(Constants.KEY_KARTEIEN_VALUE, mKarteien);
	}
	
}
