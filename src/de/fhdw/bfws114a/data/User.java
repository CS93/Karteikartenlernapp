package de.fhdw.bfws114a.data;

public class User {
	
	@Override
	public String toString() {
		return name;
	}
	//private variables
	private int id;
	private String name;
	private int class1_duration;
	private int class2_duration;
	private int class3_duration;
	private int class4_duration;
	private int class5_duration;
	private int class6_duration;
	private String last_seen;
	
	// Empty constructor
	public User(){
		
	}
	
	// constructor
	public User(int id, String name, int class1_duration,
			int class2_duration, int class3_duration,
			int class4_duration, int class5_duration,
			int class6_duration, String last_seen) {
		this.id = id;
		this.name = name;
		this.class1_duration = class1_duration;
		this.class2_duration = class2_duration;
		this.class3_duration = class3_duration;
		this.class4_duration = class4_duration;
		this.class5_duration = class5_duration;
		this.class6_duration = class6_duration;
		this.last_seen = last_seen;
	}

	public User(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public User(String name){
		this.name = name;
	}
	
	// Getters & Setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClass1_duration() {
		return class1_duration;
	}
	public int getClass2_duration() {
		return class2_duration;
	}
	public int getClass3_duration() {
		return class3_duration;
	}
	public int getClass4_duration() {
		return class4_duration;
	}
	public int getClass5_duration() {
		return class5_duration;
	}
	public int getClass6_duration() {
		return class6_duration;
	}
	public String getLast_seen() {
		return last_seen;
	}
	public void setLast_seen(String last_seen) {
		this.last_seen = last_seen;
	}
	public int getID() {
		return id;
	}

}
