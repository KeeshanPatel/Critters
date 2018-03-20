package assignment4;

import assignment4.Critter.TestCritter;

//Berserker Critter will randomly runs in a random direction
//If its energy is below 40 during a fight it will enter berserker mode and will gain 100 energy,
// once the round is over a cooldown will be placed on the berserker mode

public class MyCritter6 extends TestCritter {

	boolean berserkerMode = false;
	int cooldownTimer = 0;

	@Override
	public String toString () {
		return "5";
	}

	@Override
	public void doTimeStep() {
		int direction = getRandomInt(8);
		run(direction);
	}

	@Override
	public boolean fight(String opponent) {
		if(berserkerMode && cooldownTimer == 0)
		{
			setEnergy(10);
			cooldownTimer = 5;
			if(getEnergy() > Params.run_energy_cost)
			{
				run(getRandomInt(8));
			}
			else
			{
				walk(getRandomInt(8));
			}
			return false;
		}

		if(getEnergy() < 40 && cooldownTimer == 0)
		{
			berserkerMode = true;
			setEnergy(getEnergy() + 100);
			return true;
		}
		cooldownTimer--;
		return true;
	}


}