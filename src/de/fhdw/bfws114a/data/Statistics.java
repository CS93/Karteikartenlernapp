package de.fhdw.bfws114a.data;

import java.io.Serializable;

public class Statistics implements Serializable {
	private String mKartei;
	private int mFaelligeChallenges;
	private int mGesamteChallenges;
	
	public Statistics(String kartei, int faelligeChallenges, int gesamteChallenges){
		this.mKartei = kartei;
		this.mFaelligeChallenges = faelligeChallenges;
		this.mGesamteChallenges = gesamteChallenges;
	}

	public String getKartei() {
		return mKartei;
	}

	public void setKartei(String mKartei) {
		this.mKartei = mKartei;
	}

	public int getFaelligeChallenges() {
		return mFaelligeChallenges;
	}

	public void setFaelligeChallenges(int mFaelligeChallenges) {
		this.mFaelligeChallenges = mFaelligeChallenges;
	}

	public int getGesamteChallenges() {
		return mGesamteChallenges;
	}

	public void setGesamteChallenges(int mGesamteChallenges) {
		this.mGesamteChallenges = mGesamteChallenges;
	}
	
}
