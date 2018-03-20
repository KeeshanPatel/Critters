package assignment4;

import assignment4.Critter.TestCritter;

//Will run in a random diagonal direction

public class MyCritter7 extends TestCritter {

	@Override
	public String toString () {
		return "7";
	}

	@Override
	public void doTimeStep() {
		int[] moves = {1,3,6,8};
		run(moves[getRandomInt(3)]);
	}

	@Override
	public boolean fight(String opponent) {
		if(getEnergy() > 30)
			return true;
		else
			return false;
	}


}
