package de.fhdw.bfws114a.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Statistics implements Serializable {
	private String mCategory;
	private int mDueChallenges;
	private int mOverallChallenges;
	
	public Statistics(String category, int dueChallenges, int overallChallenges){
		this.mCategory = category;
		this.mDueChallenges = dueChallenges;
		this.mOverallChallenges = overallChallenges;
	}

	public String getCategory() {
		return mCategory;
	}

	public void setCategory(String mKartei) {
		this.mCategory = mKartei;
	}

	public int getDueChallenges() {
		return mDueChallenges;
	}

	public void setDueChallenges(int dueChallenges) {
		this.mDueChallenges = dueChallenges;
	}

	public int getOverAllChallengers() {
		return mOverallChallenges;
	}

	public void setOverallChallenges(int overallChallenges) {
		this.mOverallChallenges = overallChallenges;
	}
	
}
