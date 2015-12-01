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
	private DataInterface mDataInterface;
	
	public Data(Bundle b, Activity activity){
		Intent intent;		
		mActivity = activity;	
		mDataInterface = new DataInterface(activity);
		ladeUser(); //User m�ssen immer geladen werden (da dieser Konstruktor auch nach dem Aktualisieren der Profile im Profilemanagement aufgerufen wird 
		
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void ladeUser(){
		//User aus xml laden und in user (String Array) hineinschreiben
		mUserList = mDataInterface.loadUser();	
		
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
