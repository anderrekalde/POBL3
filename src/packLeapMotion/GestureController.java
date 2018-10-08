package packLeapMotion;

import java.awt.Color;



import com.leapmotion.leap.Arm;
import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Matrix;
import com.leapmotion.leap.Vector;

import packInterface.MainFrame;

public class GestureController extends Listener {
	private Vector indexPos;
	private Vector thumbPos;
	private Vector middlePos;
	private Vector pinkyPos;
	private Vector ringPos;
	private Vector handPos;
	private Frame frame;
	private MainFrame window;
	private float cont = 0.0f;

	public GestureController(MainFrame window) {
		this.window = window;
		
	}

	public void onConnect(Controller controller) {
		
		window.conexion(true);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	public void onDisconnect (Controller controller){
		window.conexion(false);
	}


	public void onFrame(Controller controller) {
		frame = controller.frame();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Finger f : frame.fingers()) {
			for (Finger t : frame.fingers()) {
				for (Finger p : frame.fingers()) {
					for (Finger m : frame.fingers()) {
						for (Finger r : frame.fingers()) {
							if (f.type() == Finger.Type.TYPE_INDEX) {
								if (t.type() == Finger.Type.TYPE_THUMB) {
									if (p.type() == Finger.Type.TYPE_PINKY) {
										if (m.type() == Finger.Type.TYPE_MIDDLE) {
											if (r.type() == Finger.Type.TYPE_RING) {
												
													
															
													   
												
												
												
												
												
												indexPos = f.stabilizedTipPosition();
												thumbPos = t.stabilizedTipPosition();
												middlePos = m.stabilizedTipPosition();
												pinkyPos = p.stabilizedTipPosition();
												ringPos = r.stabilizedTipPosition();
												handPos = frame.hands().frontmost().palmPosition();
												if (hasHand(frame)) {
													
													for (Gesture g : frame.gestures()) {
														switch (g.type()) {
														case TYPE_CIRCLE:
															System.out.println("Jimmy da vueltas");
															if (f.isExtended() && !m.isExtended())
																window.toggleLights();	
															
															break;
														case TYPE_SWIPE:
															System.out.println("Jimmy te saluda");
															if (f.isExtended() && m.isExtended() && p.isExtended() && r.isExtended())
																window.toggleMusic();	
															
															if (f.isExtended() && !m.isExtended() && !p.isExtended() && !r.isExtended())
																window.togglePersiana();
															break;
														case TYPE_SCREEN_TAP:
															System.out.println("Jimmy te señala");
																
															break;
														case TYPE_KEY_TAP:
															System.out.println("Jimmy baja el dedo");
															/*if (f.isExtended() && m.isExtended() && p.isExtended() && r.isExtended())
																window.togglePersiana();*/
															
														/*	if (f.isExtended() && !m.isExtended() && !p.isExtended() && !r.isExtended())
																window.togglePersiana();
															*/
															break;
														}
														if (g.type() == Type.TYPE_CIRCLE 
																|| g.type() == Type.TYPE_SWIPE
																|| g.type() == Type.TYPE_SCREEN_TAP
																|| g.type() == Type.TYPE_KEY_TAP) {
															try {
																Thread.sleep(2000);
															} catch (InterruptedException e) {
																// TODO
																// Auto-generated
																// catch block
																e.printStackTrace();
															}
														}
													}
												} else {
													try {
														Thread.sleep(2000);
													} catch (InterruptedException e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
													}
													if (!hasHand(frame))
														System.out.println("Lanas no trabaja");
												}

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean hasHand(Frame frame) {
		return (frame.hands().count() > 0) ? true : false;
	}
	
	/*public packClasses.Hand getPosition(Frame frame) {
		Hand hand = frame.hands().frontmost();
		Arm arm = hand.arm();
		Matrix basisHand = hand.basis();
		packClasses.Hand hand1 = new packClasses.Hand(basisHand.getXBasis().getX(), basisHand.getYBasis().getY(), basisHand.getZBasis().getZ());
		for (Finger finger : hand.fingers()) {
			packClasses.Finger finger1 = new packClasses.Finger();
		    for(Bone.Type boneType : Bone.Type.values()) {
		        Bone bone = finger.bone(boneType);
		        Matrix basis = bone.basis();
		        packClasses.Bone bone1 = new packClasses.Bone(basis.getXBasis().getX(),basis.getYBasis().getY(),basis.getZBasis().getZ(),false);
		        finger1.addElement(bone1);
		        Vector xBasis = basis.getXBasis();
		        Vector yBasis = basis.getYBasis();
		        Vector zBasis = basis.getZBasis();
		        Vector origin = basis.getOrigin();
		    }
		    hand1.addFinger(finger1);
		}
		return hand1;
	}*/
	
	/*private void drawElement(Frame frame){
		Hand hand = frame.hands().frontmost();
		Arm arm = hand.arm();
		Matrix basisHand = hand.basis();
		for (Finger finger : hand.fingers()) {
		    for(Bone.Type boneType : Bone.Type.values()) {
		        Bone bone = finger.bone(boneType);
		        Matrix basis = bone.basis();
		        packClasses.Bone bone1 = new packClasses.Bone(basis.getXBasis().getX(),basis.getYBasis().getY(),basis.getZBasis().getZ(),false);
		        Vector xBasis = basis.getXBasis();
		        Vector yBasis = basis.getYBasis();
		        Vector zBasis = basis.getZBasis();
		        Vector origin = basis.getOrigin();
		        (new Thread(){
		        	public void run() {
		        		universe.group = new BranchGroup();

		        		universe.tg = new TransformGroup();

		           	//group = new BranchGroup(); 	 
		        		universe.sphere = new Sphere(0.3f);
		        		universe.transform = new Transform3D();
		        		universe.vector1 = new Vector3f(0.5f, cont, 0f);
		        		universe.transform.setTranslation(universe.vector1);
		        		universe.tg.setTransform(universe.transform);
		        		universe.tg.addChild(universe.sphere);
		        		universe.group.addChild(universe.tg);
		          	 
		        		universe.lightColor = new Color3f(Color.WHITE); // color
		        		universe.bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		        		universe.lightDirection = new Vector3f(4.0f, -7.0f, -12.0f);
		        		universe.light = new DirectionalLight(universe.lightColor, universe.lightDirection);
		        		universe.light.setInfluencingBounds(universe.bounds);
		        		universe.group.addChild(universe.light);
		          	 universe.universe.getViewingPlatform().setNominalViewingTransform();
		          	 universe.universe.addBranchGraph(universe.group);
		        	};
		        }).start();
		        
		        cont = cont+0.01f;
		        if(cont==1f){
		        	cont = -1f;
		        }
		        //universe.drawObject(-0.9f, -0.0f, .5f, 0.3f);
		        //universe.drawObject(0.9f, -0.0f, .5f, 0.3f);

		        //universe.drawObject((float)xBasis.getX(), (float)yBasis.getY(), (float)zBasis.getZ(), 5f);
		    }
		}
	}
	
	*/
}
