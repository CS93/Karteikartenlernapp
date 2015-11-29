package de.fhdw.bfws114a.data;

public class StatisticsObject {
	private String mKartei;
	private String mFaelligeChallenges;
	private String mGesamteChallenges;
	
	public StatisticsObject(String kartei, String faelligeChallenges, String gesamteChallenges){
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

	public String getFaelligeChallenges() {
		return mFaelligeChallenges;
	}

	public void setFaelligeChallenges(String mFaelligeChallenges) {
		this.mFaelligeChallenges = mFaelligeChallenges;
	}

	public String getGesamteChallenges() {
		return mGesamteChallenges;
	}

	public void setGesamteChallenges(String mGesamteChallenges) {
		this.mGesamteChallenges = mGesamteChallenges;
	}
	
}
