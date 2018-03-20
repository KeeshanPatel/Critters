
//Critter.class

package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Critter
{
	private static String myPackage = Critter.class.getPackage().toString().split(" ")[1];
	private static List<Critter> population = new ArrayList();
	private static List<Critter> babies = new ArrayList();
	private static Random rand = new Random();
	private int energy = 0;
	private int x_coord;
	private int y_coord;

	public Critter() {
	}

	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new Random(new_seed);
	}

	public String toString() {
		return "";
	}

	protected int getEnergy() {
		return this.energy;
	}

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

	protected final void run(int direction) {
		this.energy += (2*Params.walk_energy_cost);
		walk(direction);
		walk(direction);
		this.energy -= Params.run_energy_cost;
	}

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
		population.add(offspring);
		babies.add(offspring);
		System.out.println("Critter born");

	}

	public abstract void doTimeStep();

	public abstract boolean fight(String var1);

	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try
		{

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

	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try
		{
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

	public static void runStats(List<Critter> critters) {
		System.out.print(critters.size() + " critters as follows -- ");
		Map<String, Integer> critter_count = new HashMap();
		Iterator var3 = critters.iterator();

		while(var3.hasNext()) {
			Critter crit = (Critter)var3.next();
			String crit_string = crit.toString();
			Integer old_count = (Integer)critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string, Integer.valueOf(1));
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}

		String prefix = "";

		for(Iterator var8 = critter_count.keySet().iterator(); var8.hasNext(); prefix = ", ") {
			String s = (String)var8.next();
			System.out.print(prefix + s + ":" + critter_count.get(s));
		}

		System.out.println();
	}

	public static void clearWorld() {
		population.clear();
	}

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
							System.out.println("Critter rans away exhausted");

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
							System.out.println("Critter rans away exhausted");

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
						System.out.println("Critter killed");

						population.remove(loser);
						if(loser == firstCritter){
							j = population.size();
							i--;
						}
					}

				}
			}
		}
	}



	public static void displayWorld() {
		char[][] worldVeiw = new char[Params.world_width + 2][Params.world_height + 2];

		worldVeiw[0][0] = '+';
		worldVeiw[Params.world_width + 1][Params.world_height + 1] = '+';
		worldVeiw[0][Params.world_height + 1] = '+';
		worldVeiw[Params.world_width + 1][0] = '+';

		for (int i = 1; i < (Params.world_height + 1); i++) {
			worldVeiw[0][i] = '-';
			worldVeiw[Params.world_width + 1][i] = '-';
		}
		for (int i = 1; i < (Params.world_width + 1); i++) {
			worldVeiw[i][0] = '|';
			worldVeiw[i][Params.world_height+1] = '|';
		}
		for(int i = 1; i < (Params.world_width + 1); i++){
			for(int j = 1; j < (Params.world_height+1);j++){
				worldVeiw[i][j]= ' ';
			}
		}

		for (Critter c : population) {
			worldVeiw[c.x_coord + 1][c.y_coord + 1] = c.toString().charAt(0);
		}

		for (int i = 0; i < (Params.world_width + 2); i++) {
			for (int j = 0; j < (Params.world_height + 2); j++) {
				System.out.print(worldVeiw[i][j]);
			}
			System.out.println();
		}
	}

	public static void checkDead(){
		for(Critter c: population){
			if(c.getEnergy() <0){
				population.remove(c);
				System.out.println("Critter exhausted");

			}

		}
	}

	abstract static class TestCritter extends Critter {
		TestCritter() {
		}

		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}

		protected static List<Critter> getPopulation() {
			return Critter.population;
		}

		protected static List<Critter> getBabies() {
			return Critter.babies;
		}
	}
}
