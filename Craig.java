/* CRITTERS Craig.java
 * EE422C Assignment4 submission by:
 * Names: Keeshan Patel & Shawn Victor
 * EIDs : kpp484 & sfv225
 * Date : 3/20/2018
 */

package assignment4;

/**
 * This Critter class: "Craig" is a unique extension of the Critter
 * Unique Cases:
 *      -Has 24 Genes
 *      -During a time step it will walk randomly in a single direction for the rest of its life
 *      -During a time step if it has more than 150 energy it will create a new Craig with the same genes except two indexes with -/+ 1 value difference
 *      -During a fight it will always try to fight
 */

public class Craig extends Critter {

	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;

	/**
	 * This method returns the unique string ID of this critter
	 * @return the unique identifier string for Craig
	 * Unique Case: The String Identifier for Craig is "C"
	 */
	@Override
	public String toString() { return "C"; }

	/**
	 * Constructor for Craig
	 * Sets each of the indexes of its gene array to the value 3
	 * Sets its direction value to a random direction
	 */
	public Craig() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	/**
	 * This method will indicate whether the critter decides to fight another critter based on the toString of another Critter
	 * @param not_used is a string indicating what type of opponent the critter is going against
	 * @return boolean, if this critter wishes to fight the other critter it will return false, if not it will evade returning false
	 * Unique Case:
	 *      -Will always try to fight
	 */
	public boolean fight(String not_used) { return true; }

	/**
	 * This method will perform a time step for Craig
	 * Unique Case:
	 *      -Will randomly walk in a direction pre-defined
	 *      -If its energy is greater than 150 it will create a new Craig child with the same gene array except two indexes will vary by -/+ 1 number
	 *      -At the end of the time step it will will select a new direction based on its genes
	 */
	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		
		if (getEnergy() > 150) {
			Craig child = new Craig();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}

	/**
	 * This method will output to the console the statistics of the genes array of each craig
	 * @param craigs this is a List containing all the craigs in the world
	 */
	public static void runStats(java.util.List<Critter> craigs) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : craigs) {
			Craig c = (Craig) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + craigs.size() + " total Craigs    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * craigs.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * craigs.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * craigs.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * craigs.size()) + "% left   ");
		System.out.println();
	}
}
