/**
 * @author Ricardo La Valle
 */
package de.fhdw.bfws114a.data;

public class Card {
	private int mId;
	private int mFile;
	private int mType;
	private String mQuestion;
	private String mAnswer1;
	private String mAnswer2;
	private String mAnswer3;
	private String mAnswer4;
	private String mAnswer5;
	private String mAnswer6;
	private String mSolution;
	
	public Card() {
		super();
	}
	
	public int getId() {
		return mId;
	}


	public void setId(int id) {
		this.mId = id;
	}


	public int getFile() {
		return mFile;
	}


	public void setFile(int file) {
		this.mFile = file;
	}


	public String getQuestion() {
		return mQuestion;
	}
	public void setQuestion(String question) {
		this.mQuestion = question;
	}
	public int getType() {
		return mType;
	}
	public void setType(int type) {
		this.mType = type;
	}
	public String getAnswer1() {
		return mAnswer1;
	}
	public void setAnswer1(String answer1) {
		this.mAnswer1 = answer1;
	}
	public String getAnswer2() {
		return mAnswer2;
	}
	public void setAnswer2(String answer2) {
		this.mAnswer2 = answer2;
	}
	public String getAnswer3() {
		return mAnswer3;
	}
	public void setAnswer3(String answer3) {
		this.mAnswer3 = answer3;
	}
	public String getAnswer4() {
		return mAnswer4;
	}
	public void setAnswer4(String answer4) {
		this.mAnswer4 = answer4;
	}
	public String getAnswer5() {
		return mAnswer5;
	}
	public void setAnswer5(String answer5) {
		this.mAnswer5 = answer5;
	}
	public String getAnswer6() {
		return mAnswer6;
	}
	public void setAnswer6(String answer6) {
		this.mAnswer6 = answer6;
	}
	public String getSolution() {
		return mSolution;
	}
	public void setSolution(String solution) {
		this.mSolution = solution;
	}

	@Override
	public String toString() {
		return "Card [id=" + mId + ", file=" + mFile + ", type=" + mType + "]";
	}
	
	
}
