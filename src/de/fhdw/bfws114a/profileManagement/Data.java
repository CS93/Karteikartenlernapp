package de.fhdw.bfws114a.profileManagement;

import java.util.ArrayList;
import android.app.Activity;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {

	private ArrayList<String> mUserList; //Eventuell eine Liste verwenden
	private Activity mActivity;
	
	public Data(Activity activity){	
		mActivity = activity;			
			ladeUser();
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
	
}

