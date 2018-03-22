/* CRITTERS MyCritter7.java
 * EE422C Assignment4 submission by:
 * Names: Keeshan Patel & Shawn Victor
 * EIDs : kpp484 & sfv225
 * Date : 3/20/2018
 */

package assignment4;

import assignment4.Critter.TestCritter;

/**
 * This Critter class: "MyCritter7" is a unique extension of the TestCritter
 * Unique Cases:
 *      -Will do nothing during a time step
 *      -Will always attempt to kill during a fight
 */

public class MyCritter7 extends TestCritter {

	/**
	 * This method will perform a time step for MyCritter7
	 * Unique Case: Will do nothing
	 */
	@Override
	public void doTimeStep() {
	}

	/**
	 * This method will indicate whether the critter decides to fight another critter based on the toString of another Critter
	 * @param opponent is a string indicating what type of opponent the critter is going against
	 * @return boolean, if this critter wishes to fight the other critter it will return false, if not it will evade returning false
	 * Unique Case: This critter will always chose to try to kill its opponent
	 */
	@Override
	public boolean fight(String opponent) {

		return true;
	}

	/**
	 * This method returns the unique string ID of this critter
	 * @return the unique identifier string for MyCritter7
	 * Unique Case: The String Identifier for MyCritter7 is "7"
	 */
	@Override
	public String toString () {
		return "7";
	}
}
