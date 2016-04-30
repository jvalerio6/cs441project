/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samir
 */

package EisenhowerBox;

/** abstract class that Manager and TeamMember will inherit */
public abstract class User {
        /** variables name and password for a user */
	protected String name, password;
        /** variable ID represents the ID of a User */
        protected int ID;

        /** constructor with no arguments */
        public User() {}

        /** constructor with one string argument */
        public User(String name) {
        	this.name = name;
        }

        // Constructor with name and password
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
         // Constructor with name and password and ID
	public User(String name, String password, int ID) {
		this.name = name;
		this.password = password;
                this.ID = ID;
	}

	abstract boolean isWorkingOnProject(Project project);

}