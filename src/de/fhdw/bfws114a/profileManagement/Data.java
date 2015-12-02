package de.fhdw.bfws114a.profileManagement;

import java.util.ArrayList;
import android.app.Activity;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.data.User;


public class Data {

	private ArrayList<User> mUserList; //Eventuell eine Liste verwenden
	private Activity mActivity;
	private DataInterface mDataInterface;
	
	
	public Data(Activity activity){	
		mActivity = activity;
		mDataInterface = new DataInterface(activity);
		ladeUser();
	}
	
	public DataInterface getDataInterface() {
		return mDataInterface;
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void ladeUser(){
		//load all User from db and save it in user (String Array)
		mUserList = mDataInterface.loadUsers2();	
		
	}
	
	public ArrayList<User> getUser(){
		return mUserList;
	}
	
}

