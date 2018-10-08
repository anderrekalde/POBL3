package packClasses;

import java.util.ArrayList;

public class Finger {
	private ArrayList<Bone> list;
	
	public Finger(){
		list = new ArrayList<Bone>();
	}
	
	public void addElement(Bone e){
		list.add(e);
	}
	
	public ArrayList<Bone> getBones(){
		return list;
	}
	
	public ArrayList<Bone> getList(){
		return list;
	}
}
