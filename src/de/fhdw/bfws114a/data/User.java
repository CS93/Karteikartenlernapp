/**
 * @author Ricardo La Valle
 */
package de.fhdw.bfws114a.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	@Override
	public String toString() {
		return mName;
	}
	//private variables
	private int mId;
	private String mName;
	private int mClass1_duration;
	private int mClass2_duration;
	private int mClass3_duration;
	private int mClass4_duration;
	private int mClass5_duration;
	private int mClass6_duration;
	private String mLast_seen;
	
	// Empty constructor
	public User(){
		
	}
	
	// constructor
	public User(int id, String name, int class1_duration,
			int class2_duration, int class3_duration,
			int class4_duration, int class5_duration,
			int class6_duration, String last_seen) {
		this.mId = id;
		this.mName = name;
		this.mClass1_duration = class1_duration;
		this.mClass2_duration = class2_duration;
		this.mClass3_duration = class3_duration;
		this.mClass4_duration = class4_duration;
		this.mClass5_duration = class5_duration;
		this.mClass6_duration = class6_duration;
		this.mLast_seen = last_seen;
	}

	public User(int id, String name){
		this.mId = id;
		this.mName = name;
	}
	
	public User(String name){
		this.mName = name;
	}
	
	// Getters & Setters
	
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}
	public int getClass1_duration() {
		return mClass1_duration;
	}
	public int getClass2_duration() {
		return mClass2_duration;
	}
	public int getClass3_duration() {
		return mClass3_duration;
	}
	public int getClass4_duration() {
		return mClass4_duration;
	}
	public int getClass5_duration() {
		return mClass5_duration;
	}
	public int getClass6_duration() {
		return mClass6_duration;
	}
	
	public int[] getClass_durations(){
		int[] allDurations = new int[6];
		allDurations[0] = mClass1_duration;
		allDurations[1] = mClass1_duration;
		allDurations[2] = mClass1_duration;
		allDurations[3] = mClass1_duration;
		allDurations[4] = mClass1_duration;
		allDurations[5] = mClass1_duration;
		return allDurations;
	}
	
	public String getLast_seen() {
		return mLast_seen;
	}
	public void setLast_seen(String last_seen) {
		this.mLast_seen = last_seen;
	}
	public int getID() {
		return mId;
	}

}
