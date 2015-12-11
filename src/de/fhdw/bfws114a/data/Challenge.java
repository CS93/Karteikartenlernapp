package de.fhdw.bfws114a.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Challenge implements Serializable {
	private String mCategory;
	private int mFileID;
	private int mCardID;
	private int mCurrentClass;
	private Date mTimeStamp;
	private String mFrage;
	private int mQuestionType; //1 = Checkbox; 2 = Text; 3 = Self Review
	private String[] mAnswers; //QuestionType 1 -> 6 Anworten; 2 -> only correct answer; 3 -> also the correct answer
	private boolean[] mCorrectAnswersForCheckbox; //QuestionType 1 -> indexes with right answers from the 6 mAnswers; QuestionType 2,3 = empty
	
	
	public Challenge(String category, int cardID, int fileID, int currentClass, Date timestamp, String question, int questionType,
			String[] answers, boolean[] correctAnswersForCheckbox) {
		super();
		this.mCategory = category;
		this.mFileID = fileID;
		this.mCardID = cardID;
		this.mCurrentClass = currentClass;
		this.mTimeStamp = timestamp;
		this.mFrage = question;
		this.mQuestionType = questionType;
		this.mAnswers = answers;
		this.mCorrectAnswersForCheckbox = correctAnswersForCheckbox;
	}
	
	
	public String getCategory() {
		return mCategory;
	}
	public void setCategory(String category) {
		this.mCategory = category;
	}
	public int getFileID() {
		return mFileID;
	}


	public void setFileID(int fileID) {
		mFileID = fileID;
	}


	public int getCardID() {
		return mCardID;
	}


	public void setCardID(int cardID) {
		mCardID = cardID;
	}


	public int getCurrentClass() {
		return mCurrentClass;
	}
	public void setCurrentClass(int currentClass) {
		this.mCurrentClass = currentClass;
	}
	public Date getTimestamp() {
		return mTimeStamp;
	}
	public void setTimestamp(Date timestamp) {
		this.mTimeStamp = timestamp;
	}
	public String getQuestion() {
		return mFrage;
	}
	public void setQuestion(String question) {
		this.mFrage = question;
	}
	public int getQuestionType() {
		return mQuestionType;
	}
	public void setQuestionType(int questionType) {
		this.mQuestionType = questionType;
	}
	public String[] getAnswers() {
		return mAnswers;
	}
	public String getAnswer(int index) {
		return mAnswers[index];
	}
	public void setAnswers(String[] answers) {
		this.mAnswers = answers;
	}
	public boolean[] getCorrectAnswersForCheckbox() {
		return mCorrectAnswersForCheckbox;
	}
	public void setCorrectAnswersForCheckbox(boolean[] correctAnswersForCheckbox) {
		this.mCorrectAnswersForCheckbox = correctAnswersForCheckbox;
	}
	
	
	}
