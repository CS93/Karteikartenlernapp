package de.fhdw.bfws114a.classManagement;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.lernKartei.R;

public class Data {

	private String mUser; //Eventuell eine Liste verwenden
	private Activity mActivity;
	private int[] mTimeOfClasses;
	private ArrayList<String> mTimeList;
	private DataInterface mDataInterface;
	
	
//	//Beim initialisieren mancher Objekte ist der Context notwendig. 
//	//Um auf diesen in der Methode setChoiceList zugreifen zu können wird er hier als Membervariable definiert	
	private Context mContext;
	
	public Data(Activity activity, String user){

		activity.setContentView(R.layout.activity_profile_management);
		mContext = activity;
		
		mActivity = activity;
		mDataInterface = new DataInterface(activity);
		mUser = user;	
		loadTimeOfClasses();
		loadTimeList();
		
	}
	
public DataInterface getDataInterface() {
	return mDataInterface;
}
	
	public Context getContext() {
		return mContext;
	}
	
	public Activity getActivity() {
		return mActivity;
	}
	
	public String getUser(){
		return mUser;
	}
	
	public int[] getTimeOfClasses(){
		return mTimeOfClasses;
	}
	
	public ArrayList<String> getTimeList(){
		return mTimeList;
	}
	
	public void setTimeOfClasses(int classNumber, int time){
		mTimeOfClasses[classNumber-1] = time;
	}
	
	private void loadTimeOfClasses(){
		mTimeOfClasses = mDataInterface.loadTimeToClasses(mUser);
	}
	
	private void loadTimeList(){
		mTimeList.add(mContext.getString(R.string.class_time_minute));
		mTimeList.add(mContext.getString(R.string.class_time_hour));
		mTimeList.add(mContext.getString(R.string.class_time_day));

	}
}
