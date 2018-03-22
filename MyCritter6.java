/* CRITTERS MyCritter6.java
 * EE422C Assignment4 submission by:
 * Names: Keeshan Patel & Shawn Victor
 * EIDs : kpp484 & sfv225
 * Date : 3/20/2018
 */

package assignment4;

import assignment4.Critter.TestCritter;

/**
 * This Critter class: "MyCritter6" is a unique extension of the TestCritter
 * Unique Cases:
 *      -During a time step it does nothing
 *      -During a fight it will always evade and run in a random direction
 */

public class MyCritter6 extends TestCritter {

	/**
	 * This method will perform a time step for MyCritter6
	 * During this time step the critter does nothing
	 * Unique Case: Will do nothing
	 */
	@Override
	public void doTimeStep() {
	}

	/**
	 * This method will indicate whether the critter decides to fight another critter based on the toString of another Critter
	 * @param opponent is a string indicating what type of opponent the critter is going against
	 * @return boolean, if this critter wishes to fight the other critter it will return false, if not it will evade returning false
	 * Unique Case: It will always choose to evade and run in a random direction
	 */
	@Override
	public boolean fight(String opponent) {
		run(getRandomInt(8));
		return false;
	}

	/**
	 * This method returns the unique string ID of this critter
	 * @return the unique identifier string for MyCritter6
	 * Unique Case: The String Identifier for MyCritter6 is "5"
	 */
	@Override
	public String toString () {
		return "5";
	}
}
