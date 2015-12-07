package de.fhdw.bfws114a.challenge;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import de.fhdw.bfws114a.data.Challenge;
import de.fhdw.bfws114a.data.Constants;
import de.fhdw.bfws114a.data.User;
import de.fhdw.bfws114a.dataInterface.DataInterface;

public class Data {
	
	private User mUser;
	private String mCategory;
	private Activity mActivity;
	private DataInterface mDataInterface;
	private int mIndexOfCurrentChallenge = 0;
	private int mNumberOfCorrectAnswers = 0;
	private int mNumberOfWrongAnswers = 0;
	private ArrayList<Challenge> mDueChallenges = new ArrayList<Challenge>();
	
	public Data(Bundle b, Activity activity, User user, String category){	
		mActivity = activity;
		mUser = user;
		mCategory = category;
		mDataInterface = new DataInterface(activity);
		if (b == null ){
			//first time activity is displayed on screen		
			ladeFaelligeChallenges();
		}
		else {
			restoreDataFromBundle(b);
		}
	}
	
	public Activity getActivity() {
		return mActivity;
	}

	private void ladeFaelligeChallenges(){
		ArrayList<Challenge> alleChallenges = mDataInterface.loadChallenges(mCategory, mUser);
		java.util.Date now = new java.util.Date();
		long difference;
		for(int i = 0; i< alleChallenges.size(); i++){
			//calculat difference between timestamp of current challenge and system date
			Log.d("Debug FileID", ""+alleChallenges.get(i).getFileID());
			Log.d("Debug card ID", ""+alleChallenges.get(i).getCardID());
			
			difference = now.getTime() - alleChallenges.get(i).getZeitstempel().getTime();
			
			//test whether difference is larger than class time period (-> due). The Time period is returned in minutes and has to be multiplied with 60 and 1000 to compare it
			if(difference > (mDataInterface.getTimePeriod(alleChallenges.get(i).getAktuelleKlasse(), mUser)*60*1000)){
				mDueChallenges.add(alleChallenges.get(i));
			}
		}		
	}
	
	public User getUser() {
		return mUser;
	}	
	
	public ArrayList<Challenge> getFaelligeChallenges() {
		return mDueChallenges;
	}	

	public int getIndexOfCurrentChallenge() {
		return mIndexOfCurrentChallenge;
	}

	public int getNumberOfCorrectAnswers() {
		return mNumberOfCorrectAnswers;
	}

	public int getNumberOfWrongAnswers() {
		return mNumberOfWrongAnswers;
	}

	private void restoreDataFromBundle(Bundle b) {
		//load the due challenges from Bundle through casting the Serializable value
		mDueChallenges = (ArrayList<Challenge>) b.getSerializable(Constants.KEY_DUE_CHALLENGES_VALUE);		
	}
	
	public void saveDataInBundle(Bundle b){		
		//Use the Serializable Interface to put the due challenges in Bundle
		b.putSerializable(Constants.KEY_DUE_CHALLENGES_VALUE, mDueChallenges);		
	}

	public void increaseIndexOfCurrentChallenge() {
		mIndexOfCurrentChallenge++;
		
	}

	public void increaseNumberOfCorrectAnswers() {
		mNumberOfCorrectAnswers++;		
	}

	public void decreaseNumberOfCorrectAnswers() {
		mNumberOfWrongAnswers++;		
	}
	
}
