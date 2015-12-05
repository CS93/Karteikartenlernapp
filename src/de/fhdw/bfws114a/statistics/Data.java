package de.fhdw.bfws114a.statistics;

import android.app.Activity;
import android.os.Bundle;

public class Data {
	
	private Activity mActivity;
	private int mIndexOfCurrentChallenge;
	private int mNumberOfDueChallenges;
	private int mNumberOfCorrectAnswers;
	private int mNumberOfWrongAnswers;
	
	public Data(Bundle savedInstanceState, Activity activity, int indexCurrentChallenge, int numberOfDueChallenges,
			int numberOfCorrectAnswers, int numberOfWrongAnswers) {
		mActivity = activity;
		mIndexOfCurrentChallenge = indexCurrentChallenge;
		mNumberOfDueChallenges = numberOfDueChallenges;
		mNumberOfCorrectAnswers = numberOfCorrectAnswers;
		mNumberOfWrongAnswers = numberOfWrongAnswers;
	}

	public Activity getActivity() {
		return mActivity;
	}	
	
	public int getIndexOfCurrentChallenge() {
		return mIndexOfCurrentChallenge;
	}

	public int getNumberOfDueChallenges() {
		return mNumberOfDueChallenges;
	}

	public int getNumberOfCorrectAnswers() {
		return mNumberOfCorrectAnswers;
	}

	public int getNumberOfWrongAnswers() {
		return mNumberOfWrongAnswers;
	}
	
}
