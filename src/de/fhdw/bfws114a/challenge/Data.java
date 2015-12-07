package de.fhdw.bfws114a.challenge;

import java.util.ArrayList;
import java.util.Date;

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
	private int mIndexOfCurrentChallenge;
	private int mNumberOfCorrectAnswers = 0;
	private int mNumberOfWrongAnswers = 0;
	private ArrayList<Challenge> mDueChallenges = new ArrayList<Challenge>();
	
	public Data(Bundle b, Activity activity, User user, String category){	
		mActivity = activity;
		mUser = user;
		mCategory = category;
		mDataInterface = new DataInterface(activity);
		if (b == null ){
			mIndexOfCurrentChallenge = 0;
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
		int testCounter = 0;
		for(int i = 0; i< alleChallenges.size(); i++){
			//calculat difference between timestamp of current challenge and system date
			
			difference = now.getTime() - alleChallenges.get(i).getZeitstempel().getTime();
			if(testCounter < 3){
			Log.d("Due Test", "Challenges: Question" +alleChallenges.get(i).getFrage() + "Mit Zeitstempel: " + alleChallenges.get(i).getZeitstempel() + "wird geprüft");
			Log.d("Due Test", "Difference: " + difference);
			Log.d("Due Test", "Current Class: " + alleChallenges.get(i).getAktuelleKlasse());
			testCounter++;
			}
			//test whether difference is larger than class time period (-> due). The Time period is returned in minutes and has to be multiplied with 60 and 1000 to compare it
			if(difference > (mDataInterface.getTimePeriod(alleChallenges.get(i).getAktuelleKlasse(), mUser)*60*1000)){
				if(testCounter < 3){
				Log.d("Due Test", "Challenge ist fällig weil: " + difference + "größer ist als :" + alleChallenges.get(i).getAktuelleKlasse());
				
				}
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
		mIndexOfCurrentChallenge = b.getInt(Constants.KEY_RESTORE_INDEX_OF_CURRENT_CHALLENGE);
	}
	
	public void saveDataInBundle(Bundle b){		
		//Use the Serializable Interface to put the due challenges in Bundle
		b.putSerializable(Constants.KEY_DUE_CHALLENGES_VALUE, mDueChallenges);	
		b.putInt(Constants.KEY_RESTORE_INDEX_OF_CURRENT_CHALLENGE, mIndexOfCurrentChallenge);
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
