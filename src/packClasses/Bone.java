package packClasses;

import com.leapmotion.leap.Vector;

public class Bone {
	float posX;
	float posY;
	float posZ;
	
	boolean isHand;
	
	public Bone(float x, float y, float z, boolean isHand){
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.isHand = isHand;
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
}
