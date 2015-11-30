package de.fhdw.bfws114a.dataInterface;

public class User {
	
	//private variables
	private int id;
	private String name;
	private String class1_duration;
	private String class2_duration;
	private String class3_duration;
	private String class4_duration;
	private String class5_duration;
	private String class6_duration;
	private String last_seen;
	
	// Empty constructor
	
	
	public User(){
		
	}
	
	
	// constructor
	
	
	public User(int id, String name, String class1_duration,
			String class2_duration, String class3_duration,
			String class4_duration, String class5_duration,
			String class6_duration, String last_seen) {
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
	public String getClass1_duration() {
		return class1_duration;
	}
	public void setClass1_duration(String class1_duration) {
		this.class1_duration = class1_duration;
	}
	public String getClass2_duration() {
		return class2_duration;
	}
	public void setClass2_duration(String class2_duration) {
		this.class2_duration = class2_duration;
	}
	public String getClass3_duration() {
		return class3_duration;
	}
	public void setClass3_duration(String class3_duration) {
		this.class3_duration = class3_duration;
	}
	public String getClass4_duration() {
		return class4_duration;
	}
	public void setClass4_duration(String class4_duration) {
		this.class4_duration = class4_duration;
	}
	public String getClass5_duration() {
		return class5_duration;
	}
	public void setClass5_duration(String class5_duration) {
		this.class5_duration = class5_duration;
	}
	public String getClass6_duration() {
		return class6_duration;
	}
	public void setClass6_duration(String class6_duration) {
		this.class6_duration = class6_duration;
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
