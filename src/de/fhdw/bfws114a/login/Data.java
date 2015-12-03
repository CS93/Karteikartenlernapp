package de.fhdw.bfws114a.login;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private ArrayList<User> mUserList; //Eventuell eine Liste verwenden
	private Activity mActivity;
	private DataInterface mDataInterface;
	
	public Data(Bundle b, Activity activity){	
		mActivity = activity;	
		mDataInterface = new DataInterface(activity);
		
		mDataInterface.importXMLtoDB();
		
		if(b == null){
			//Activity wurde das erste Mal gestartet
			ladeUser(); 
		} else {
			//Beispielsweise bei einem Wechsel zwischen Portrait- und Landscape-Modus
			restoreDataFromBundle(b);
		}
		 
		
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	protected void ladeUser(){
		//User aus Datenschnittstelle laden und in String ArrayList hineinspeichern
		mUserList = mDataInterface.loadUsers();	
		
	}
	
	public ArrayList<User> getUser(){
		return mUserList;
	}
	private void restoreDataFromBundle(Bundle b) {
		//Man kann keine User-Objekte ins bundle packen
//		mUserList = b.getStringArrayList(Constants.KEY_USER_VALUE);		
	}
	
	public void saveDataInBundle(Bundle b){
		//Man kann keine User-Objekte ins bundle packen
//		b.putStringArrayList(Constants.KEY_USER_VALUE, mUserList);
	}
	
}
