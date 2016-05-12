package EisenhowerBox;

import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.SwingUtilities;

import EisenhowerBox.ui.Gui;
import EisenhowerBox.ui.GuiTester;


public class Driver {
	/**
	 * Enter initialization instances here befor the Gui starts
	 */
	public static void init() {		
		DbManager db = DbManager.getInstance();
		Gui.getInstance();
	
	}
	
	public static void updateGUI(final Status status) {
		   if (!SwingUtilities.isEventDispatchThread()) {
		     SwingUtilities.invokeLater(new Runnable() {
		       @Override
		       public void run() {
		          updateGUI(status);
		       }
		     });
		   }
		   Gui.getInstance().refresh();
		}
	
	public static void main(String args[]) {
		init();
		new Runnable() {
		       @Override
		       public void run() {
		    	   while(true){
		    		   try {
		    			   Thread.sleep(1000);
		    			   updateGUI(Status.OK);
		    		   }catch(Exception exc) {
			    	       System.out.println("Thread interrupted.");
			    	   }
		    	   }
		       }
		};		
	}

	
}