package de.fhdw.bfws114a.data;

public class File {
	
	private int mId;
	private String mName;

	@Override
	public String toString() {
		return "Karteikarte [ID=" + mId + ", Bezeichnung=" + mName + "]";
	}

	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}
}
