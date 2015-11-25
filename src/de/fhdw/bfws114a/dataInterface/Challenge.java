package de.fhdw.bfws114a.dataInterface;

import java.util.Date;

public class Challenge {
	private String mKartei;
	private int mAktuelleKlasse;
	private Date mZeitstempel;
	private String mFrage;
	private int mFrageTyp; //1 = Checkbox; 2 = Text; 3 = Eigenkontrolle
	private String[] mAntworten; //Fragetyp 1 = 6 Anworten; 2 = nur die korrekte Antwort; 3 = nur die Korrekte Antwort
	private int[] mKorrekteAnwortenFuerCheckbox; //Fragetyp 1 = indizes der korrekten Antworten; sonst = leer
	public Challenge(String kartei, int aktuelleKlasse, Date zeitstempel, String frage, int frageTyp,
			String[] antworten, int[] korrekteAnwortenFuerCheckbox) {
		super();
		this.mKartei = kartei;
		this.mAktuelleKlasse = aktuelleKlasse;
		this.mZeitstempel = zeitstempel;
		this.mFrage = frage;
		this.mFrageTyp = frageTyp;
		this.mAntworten = antworten;
		this.mKorrekteAnwortenFuerCheckbox = korrekteAnwortenFuerCheckbox;
	}
	
	
	public String getKartei() {
		return mKartei;
	}
	public void setKartei(String mKartei) {
		this.mKartei = mKartei;
	}
	public int getAktuelleKlasse() {
		return mAktuelleKlasse;
	}
	public void setAktuelleKlasse(int mAktuelleKlasse) {
		this.mAktuelleKlasse = mAktuelleKlasse;
	}
	public Date getZeitstempel() {
		return mZeitstempel;
	}
	public void setZeitstempel(Date mZeitstempel) {
		this.mZeitstempel = mZeitstempel;
	}
	public String getFrage() {
		return mFrage;
	}
	public void setFrage(String mFrage) {
		this.mFrage = mFrage;
	}
	public int getFrageTyp() {
		return mFrageTyp;
	}
	public void setFrageTyp(int mFrageTyp) {
		this.mFrageTyp = mFrageTyp;
	}
	public String[] getAntworten() {
		return mAntworten;
	}
	public void setAntworten(String[] mAntworten) {
		this.mAntworten = mAntworten;
	}
	public int[] getKorrekteAnwortenFuerCheckbox() {
		return mKorrekteAnwortenFuerCheckbox;
	}
	public void setKorrekteAnwortenFuerCheckbox(int[] mKorrekteAnwortenFuerCheckbox) {
		this.mKorrekteAnwortenFuerCheckbox = mKorrekteAnwortenFuerCheckbox;
	}
	
	
	}
