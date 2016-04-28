package EisenhowerBox;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Scanner;

import EisenhowerBox.ui.AddTaskTester;
import EisenhowerBox.ui.GuiTester;


public class Driver {

	public static void main(String[] args) throws IOException  
	{
				
		GuiTester gTest = new GuiTester();
		//AddTaskTester att = new AddTaskTester();
		int x;
		Scanner in = new Scanner(System.in);
		x = in.nextInt();
		/*
		 * GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 
		String[] fnt = ge.getAvailableFontFamilyNames();

		for (String f : fnt){
		            System.out.println(f);
		}*/
	}

}