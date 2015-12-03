package de.fhdw.bfws114a.data;

public class File {
	
	private int id;
	private String name;

	@Override
	public String toString() {
		return "Karteikarte [ID=" + id + ", Bezeichnung=" + name + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
