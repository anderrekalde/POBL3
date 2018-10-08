package packClasses;

import java.util.ArrayList;

public class Hand {
	
	ArrayList<Finger> list;
	float posX;
	float posY;
	float posZ;
	
	public Hand(float x, float y, float z){
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}
	
	public void addFinger(Finger e){
		list.add(e);
	}

	public ArrayList<Finger> getList() {
		return list;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public float getPosZ() {
		return posZ;
	}
	
	public void showHand() {
		for (Finger finger : list){
			System.out.println("Nuevo dedo: ");
			for (Bone bone : finger.getList()){
				System.out.print("X: " + bone.getPosX()+"\t");
				
			}
		}
	}

}
