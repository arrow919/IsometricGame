package Input;

import org.newdawn.slick.Input;

/**
 * This class is for holding the last time any boolean input took place. A
 * positive long number (the timestamp of the event) is stored if the input is
 * currently true, and a negative value is stored if it is false.
 * 
 * @author RevNate
 * 
 */
public class InputWrapper {
	public static long KEY_A, KEY_D, KEY_W, KEY_S;
	
	public static void keyEvent(boolean down, int key){
		long val = -1;
		if(down){
			val=System.currentTimeMillis();
		}
		if(down){
			switch(key){
			case Input.KEY_W:
				KEY_W=val;
			case Input.KEY_A:
				KEY_A=val;
			case Input.KEY_S:
				KEY_S=val;
			case Input.KEY_D:
				KEY_D=val;
			}
		}
	}
}
