package assignment4;

import java.util.*;

//This Horney critter will move randomly in one direction
//When prompted to fight if it has lower than the amount of energy to reproduce then try to fight

public class MyCritter1 extends Critter.TestCritter {

	public String toString() {
		return "1";
	}


	@Override
	public void doTimeStep()
	{
		int direction = getRandomInt(8);
		walk(direction);
	}

	@Override
	public boolean fight(String opponent)
	{
		if(getEnergy() < Params.min_reproduce_energy)
		{
			return true;
		}
		else
		{
			if(opponent.equals("1"))
			{
				MyCritter1 child = new MyCritter1();
				reproduce(child, 8);
			}
			walk(getRandomInt(8));
			return false;
		}
	}

	public void test (List<Critter> l)
	{

	}
}
