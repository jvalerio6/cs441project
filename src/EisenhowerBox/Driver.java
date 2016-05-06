package EisenhowerBox;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import EisenhowerBox.ui.GuiTester;


public class Driver {
	/**
	 * Enter initialization instances here befor the Gui starts
	 */
	public static void init() {
		DbManager.getInstance();
		GuiTester.test();
		//Dbdriver.test();
		
	}
	
	public static void main(String args[]) {
		init();
    }

	
}