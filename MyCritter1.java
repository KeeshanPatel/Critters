/* CRITTERS MyCritter1.java
 * EE422C Assignment4 submission by:
 * Names: Keeshan Patel & Shawn Victor
 * EIDs : kpp484 & sfv225
 * Date : 3/20/2018
 */

package assignment4;

import java.util.*;

/**
 * This Critter class: "MyCritter1" is a unique extension of the TestCritter
 * Unique Cases:
 *      -During a time step it will always walk diagonally to the right
 *      -During a fight if its energy is greater than 10 it will fight, if not it will evade
 */

public class MyCritter1 extends Critter.TestCritter {

	/**
	 * This method will perform a time step for MyCritter1
	 * Unique Case: During this time step the critter will only walk diagonally to the left
	 */
	@Override
	public void doTimeStep() {
		walk(0);
	}

	/**
	 * This method will indicate whether the critter decides to fight another critter based on the toString of another Critter
	 * @param opponent is a string indicating what type of opponent the critter is going against
	 * @return boolean, if this critter wishes to fight the other critter it will return false, if not it will evade returning false
	 * Unique Case: If the energy of the critter is greater it will fight, otherwise it will evade
	 */
	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}

	/**
	 * This method returns the unique string ID of this critter
	 * @return the unique identifier string for MyCritter1
	 * Unique Case: The String Identifier for MyCritter1 is "1"
	 */
	public String toString() {
		return "1";
	}

	/**
	 * This is just used for testing working ability of this Critter for debugging
	 * @param l is a list of all the critters
	 */
	public void test (List<Critter> l) {
		
	}
}
