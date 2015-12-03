package de.fhdw.bfws114a.classManagement;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;
import de.fhdw.bfws114a.lernKartei.R;

public class Data {

	private User mUser; //Eventuell eine Liste verwenden
	private Activity mActivity;
	private int[] mTimeOfClasses;
	private ArrayList<String> mTimeList;
	private DataInterface mDataInterface;
	
	
//	//Beim initialisieren mancher Objekte ist der Context notwendig. 
//	//Um auf diesen in der Methode setChoiceList zugreifen zu k�nnen wird er hier als Membervariable definiert	
	private Context mContext;
	
	public Data(Activity activity, User user){
 
		mContext = activity;
		
		mActivity = activity;
		mDataInterface = new DataInterface(activity);
		mUser = user;
		if (mTimeOfClasses == null) {
			loadTimeOfClasses(); 
		}
		mTimeList = new ArrayList<String>();
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
	
	public User getUser(){
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
	
	public void setTimeOfClasses(int[] timeOfClasses){
		mTimeOfClasses = timeOfClasses;
	}
	
	private void loadTimeOfClasses(){
		//load the times to the classes in connection to one User
		mTimeOfClasses = mDataInterface.getClassDurations(mUser);
	}
	
	public void loadDefaultTimeToClasses(){
		//load the default times to the classes, is not dependent on a User
		//is used for the DefaultButton
		mTimeOfClasses = mDataInterface.loadDefaultTimeToClasses();
	}
	
	private void loadTimeList(){
		mTimeList.add(mContext.getString(R.string.class_time_minute));
		mTimeList.add(mContext.getString(R.string.class_time_hour));
		mTimeList.add(mContext.getString(R.string.class_time_day));

	}
}
