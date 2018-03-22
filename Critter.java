/* CRITTERS Critter.java
 * EE422C Assignment4 submission by:
 * Names: Keeshan Patel & Shawn Victor
 * EIDs : kpp484 & sfv225
 * Date : 3/20/2018
 */

package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This is an abstract class that makes up all the variables and methods for a Critter object
 */
public abstract class Critter
{
	private static String myPackage = Critter.class.getPackage().toString().split(" ")[1];
	private static List<Critter> population = new ArrayList();
	private static List<Critter> babies = new ArrayList();
	private static Random rand = new Random();
	private int energy = 0;
	private int x_coord;
	private int y_coord;

	/**
	 * This is the constructor for creating a critter
	 * Performs no action
	 */
	public Critter() {
	}

	/**
	 * This method will return an random integer value that is betwee 0 and the max parameter
	 * @param max is an integer valuet that is the upper bounds of what the random number can be
	 * @return a random integer value between 0 and the max paramter
	 */
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	/**
	 * This methood is responsible for setting the seed for the random number generator for consistent testing
	 * @param new_seed is a long value that will be the seed for the random number generator
	 */
	public static void setSeed(long new_seed) {
		rand = new Random(new_seed);
	}

	/**
	 * This method returns the unique string ID of this critter
	 * @return the unique identifier string for this Critter
	 */
	public String toString() {
		return "";
	}

	/**
	 * This method will return the integer energy of the critter
	 * @return integer value representing the current energy of the critter
	 */
	protected int getEnergy() {
		return this.energy;
	}

	/**
	 * Will change the coodinate values x and y based on the given direction integer
	 * @param direction is a integer value between 0 and 7 that will change the direction of movement
	 * This method will handle the movements so that the Critter does not go out of bounds
	 * This method will also reduce the energy of the Critter based on the walking energy cost
	 */
	protected final void walk(int direction) {
		this.energy -= Params.walk_energy_cost;
		switch (direction) {
			case 0:
				this.x_coord = (x_coord+1) % Params.world_width;
				break;
			case 1:
				this.x_coord = (x_coord+1) % Params.world_width;
				this.y_coord = (y_coord-1) % Params.world_height;
				if(y_coord<0) {
					y_coord += Params.world_height;
				}
				break;
			case 2:
				this.y_coord = (y_coord-1) % Params.world_height;
				if(y_coord<0) {
					y_coord += Params.world_height;
				}
				break;
			case 3:
				this.y_coord = (y_coord-1) % Params.world_height;
				if(y_coord<0) {
					y_coord += Params.world_height;
				}
				this.x_coord = (x_coord-1) % Params.world_width;
				if(x_coord<0) {
					x_coord += Params.world_width;
				}
				break;
			case 4:
				this.x_coord = (x_coord-1) % Params.world_width;
				if(x_coord<0) {
					x_coord += Params.world_width;
				}
				break;
			case 5:
				this.x_coord = (x_coord-1) % Params.world_width;
				if(x_coord<0) {
					x_coord += Params.world_width;
				}
				this.y_coord = (y_coord+1) % Params.world_height;
				break;
			case 6:
				this.y_coord = (y_coord+1) % Params.world_height;
				this.y_coord = (y_coord+1) % Params.world_height;
				break;
			case 7:
				this.x_coord = (x_coord+1) % Params.world_width;
				this.y_coord = (y_coord+1) % Params.world_height;
				break;
		}
	}

	/**
	 * This method will perform the run method twice in the given direction
	 * @param direction is a integer value between 0 and 7 that will change the direction of movement
	 * This method will also decrease the energy of the critter  by two walk energy costs
	 */
	protected final void run(int direction) {
		this.energy += (2*Params.walk_energy_cost);
		walk(direction);
		walk(direction);
		this.energy -= Params.run_energy_cost;
	}

	/**
	 * This method will check if the critter has enough energy to reproduce, and then based on the given direction
	 * 	it will give the new offspring 1/2 of the energy of the parent, and based on the given direction it will
	 * 	place the child adjacent to the parent even considering the bounds of the matrix
	 * @param offspring is the Critter object this is the child of the parent Critter
	 * @param direction is the direction that the child needs to placed adjacent from the parent
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(getEnergy() < Params.min_reproduce_energy)
		{
			return;
		}

		offspring.energy = getEnergy() / 2;
		energy = energy/2;

		switch(direction)
		{
			case 0:
				offspring.x_coord = (x_coord+1) % Params.world_width;
				break;
			case 1:
				offspring.x_coord = (x_coord+1) % Params.world_width;
				offspring.y_coord = (y_coord-1) % Params.world_height;
				if(y_coord < 0) {
					offspring.y_coord += Params.world_height;
				}
				break;
			case 2:
				offspring.y_coord = (y_coord-1) % Params.world_height;
				if(y_coord<0) {
					offspring.y_coord += Params.world_height;
				}
				break;
			case 3:
				offspring.y_coord = (y_coord-1) % Params.world_height;
				offspring.x_coord = (x_coord-1) % Params.world_width;
				if(y_coord<0){
					offspring.y_coord += Params.world_height;
				}
				if(x_coord<0) {
					offspring.x_coord += Params.world_width;
				}
				break;
			case 4:
				offspring.x_coord = (x_coord-1) % Params.world_width;
				if(x_coord<0) {
					offspring.x_coord += Params.world_width;
				}
				break;
			case 5:
				offspring.x_coord = (x_coord-1) % Params.world_width;
				offspring.y_coord = (y_coord+1) % Params.world_height;
				if(x_coord<0) {
					offspring.x_coord += Params.world_width;
				}
				break;
			case 6:
				offspring.y_coord = (y_coord+1) % Params.world_height;
				break;
			case 7:
				offspring.x_coord = (x_coord+1) % Params.world_width;
				offspring.y_coord = (y_coord+1) % Params.world_height;
				break;
		}
		babies.add(offspring);
		//System.out.println("Critter born");
	}

	/**
	 * This method is a template that will perform a time step which is series of actions taken by the Critter
	 */
	public abstract void doTimeStep();

	/**
	 * This method is a template that will determine whether or not the Critter will chose to fight or not
	 * @return boolean, if this critter wishes to fight the other critter it will return false, if not it will evade returning false
	 */
	public abstract boolean fight(String var1);

	/**
	 * This method will Create a Critter of a specific instance based on the string parameter
	 * @param critter_class_name is a string that is the name of the Critter type we want to create
	 * @throws InvalidCritterException is an exception if the Critter with this name does not exist
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try
		{
			critter_class_name = "assignment4." +critter_class_name;

			Class c = Class.forName(critter_class_name);
			Critter newCritter = (Critter) c.newInstance();
			newCritter.energy = Params.start_energy;
			newCritter.x_coord = getRandomInt(Params.world_width);
			newCritter.y_coord = getRandomInt(Params.world_height);
			population.add(newCritter);
		}
		catch(Exception c){
			throw new InvalidCritterException(c.toString());
		}

	}

	/**
	 * This methood will return all the instances of a specific Critter type in the population List of all the Objects in the world
	 * @param critter_class_name is a string that is the name of the Critter type we want to create
	 * @return is a list of Critters that are of the same type that was specified by the parameter
	 * @throws InvalidCritterException is an exception if the Critter with this name does not exist
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try
		{
			critter_class_name = "assignment4." +critter_class_name;
			Class c = Class.forName(critter_class_name);

			for(Critter source: population)
			{
				if(c.isInstance(source))
				{
					result.add(source);
				}
			}
		}
		catch(Exception ex)
		{
			throw new InvalidCritterException(ex.toString());
		}
		return result;
	}

	/**
	 * This method will print to the console the number of critters in the list provided in the parameter
	 * Then the method will print to the console the number of instances for each of the different critters in the list of Critters provided
	 * Creates a hashmap that verses the name of critter against the number of critters
	 * @param critters is a list of Critters
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/**
	 * This method will clear all of the Critters in the population List
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}

	/**
	 * This method will first make each of the Critters in the population perform a time step
	 * Then once done, it will then handle all the super imposing critters by making them perform their fight methods
	 * Based on the fight method it will then remove all dead critters
	 */
	public static void worldTimeStep() {

		for (Critter c : population) {
			c.doTimeStep();
		}
		checkDead();
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.size(); j++) {
				if (i == j) {
					continue;
				}
				Critter firstCritter = population.get(i);
				Critter secondCritter = population.get(j);
				if ((firstCritter.x_coord == secondCritter.x_coord) && (firstCritter.y_coord == secondCritter.y_coord)) {

					int oldXCoordFirst = firstCritter.x_coord;
					int oldYCoordFirst = firstCritter.y_coord;
					boolean firstWantFight = firstCritter.fight(secondCritter.toString());
					if (!firstWantFight) {
						if (firstCritter.getEnergy() < 0) {
							population.remove(firstCritter);
							//System.out.println("Critter rans away exhausted");

							continue;
						}
						for (Critter c : population) {
							if (c == firstCritter) {
								continue;
							}
							if ((firstCritter.x_coord == c.x_coord) && (firstCritter.y_coord == c.y_coord)) {
								firstCritter.x_coord = oldXCoordFirst;
								firstCritter.y_coord = oldYCoordFirst;
								break;
							}
						}
					}

					int oldXCoordSecond = secondCritter.x_coord;
					int oldYCoordSecond = secondCritter.y_coord;
					boolean secondWantFight = secondCritter.fight(firstCritter.toString());
					if (!secondWantFight) {
						if (secondCritter.getEnergy() < 0) {
							population.remove(secondCritter);
							//System.out.println("Critter rans away exhausted");

							continue;
						}
						for (Critter c : population) {
							if (c == secondCritter) {
								continue;
							}
							if ((c.x_coord == secondCritter.x_coord) && (c.y_coord == secondCritter.y_coord)) {
								secondCritter.x_coord = oldXCoordSecond;
								secondCritter.y_coord = oldYCoordSecond;
								break;
							}
						}
					}
					checkDead();
					if(population.contains(firstCritter) && population.contains(secondCritter)&&((firstCritter.x_coord == secondCritter.x_coord) && (firstCritter.y_coord == secondCritter.y_coord))){
						Critter winner;
						Critter loser;
						int firstRoll =0;
						int secondRoll =0;

						if(firstWantFight){
							firstRoll = getRandomInt(firstCritter.energy);
						}
						if(secondWantFight){
							secondRoll = getRandomInt(secondCritter.energy);
						}

						if(firstRoll == secondRoll){
							winner = firstCritter;
							loser = secondCritter;
						}
						else if(firstRoll>secondRoll){
							winner = firstCritter;
							loser = secondCritter;
						}
						else{
							loser = firstCritter;
							winner = secondCritter;
						}
						winner.energy += (loser.energy/2);
						//System.out.println("Critter killed");

						population.remove(loser);
						if(loser == firstCritter){
							i--;
							break;
						}
					}

				}
			}
		}
		for (Critter c : population) {
			c.energy -= Params.rest_energy_cost;
		}
		for(int i =0; i < Params.refresh_algae_count;i++){
			try{
			makeCritter("Algae");
			}
			catch (InvalidCritterException e){
				System.out.println("error processing: "+ e.offending_class);

			}
			}
		checkDead();
		population.addAll(babies);
		babies.clear();

	}

	/**
	 * This method is responsible for displaying the world on the console as a Matrix
	 * It will take all Critters from the population list and plot them on the matrix and print it out
	 */
	public static void displayWorld() {
		char[][] worldVeiw = new char[Params.world_width + 2][Params.world_height + 2];

		worldVeiw[0][0] = '+';
		worldVeiw[Params.world_width + 1][Params.world_height + 1] = '+';
		worldVeiw[0][Params.world_height + 1] = '+';
		worldVeiw[Params.world_width + 1][0] = '+';

		for (int i = 1; i < (Params.world_height + 1); i++) {
			worldVeiw[0][i] = '|';
			worldVeiw[Params.world_width + 1][i] = '|';
		}
		for (int i = 1; i < (Params.world_width + 1); i++) {
			worldVeiw[i][0] = '-';
			worldVeiw[i][Params.world_height+1] = '-';
		}
		for(int i = 1; i < (Params.world_width + 1); i++){
			for(int j = 1; j < (Params.world_height+1);j++){
				worldVeiw[i][j]= ' ';
			}
		}

		for (Critter c : population) {
			worldVeiw[c.x_coord + 1][c.y_coord + 1] = c.toString().charAt(0);
		}

		for (int i = 0; i < (Params.world_height + 2); i++) {
			for (int j = 0; j < (Params.world_width + 2); j++) {
				System.out.print(worldVeiw[j][i]);
			}
			System.out.println();
		}

	}

	/**
	 * This method will go though the population list and will remove the Citters with less than or equal to zero energy
	 */
	public static void checkDead(){
		for (Iterator<Critter> iterator = population.iterator(); iterator.hasNext();) {
			Critter c = iterator.next();
			if(c.getEnergy() <= 0){
				iterator.remove();
				//System.out.println("Critter exhausted");
			}
		}
	}

	/**
	 * This class is an extension of the Critter class including methods to directly modify variables of Critter
	 */
	abstract static class TestCritter extends Critter {

		/**
		 * Default constructor for the Test Critter, Does nothing
		 */
		TestCritter() {
		}

		/**
		 * This method allows the user to change the value of the energy variable
		 * @param new_energy_value this is the new integer value we wish to use to update the energy variable
		 */
		protected void setEnergy(int new_energy_value) { super.energy = new_energy_value; }

		/**
		 * This method allows the user to change the value of the x coordinate position
		 * @param new_x_coord this is the new integer value we wish to user to update the x coordinate position
		 */
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		/**
		 * This method allows the user to change the value of the y coordinate position
		 * @param new_y_coord this is the new integer value we wish to user to update the y coordinate position
		 */
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		/**
		 * This method will return the x coordinate variable corresponding to the position of the Critter
		 * @return the x position of the critter
		 */
		protected int getX_coord() {
			return super.x_coord;
		}

		/**
		 * This method will return the y coordinate variable corresponding to the position of the Critter
		 * @return the y position of the critter
		 */
		protected int getY_coord() {
			return super.y_coord;
		}

		/**
		 * This method will return the list of Critters containing all of the existing Critters in the world
		 * @return the list value corresponding to all the critters existent
		 */
		protected static List<Critter> getPopulation() {
			return Critter.population;
		}

		/**
		 * This method will return the list of all Critters that are children of this Critter
		 * @return a list of all critters that are children of this Critter
		 */
		protected static List<Critter> getBabies() {
			return Critter.babies;
		}
	}
}
