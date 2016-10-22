package assignment4;
//Crazy ant
public class Critter3 extends Critter{
	@Override
	public String toString() { return "3"; }
	




	
	public Critter3() {

	}
	@Override
	public boolean fight(String critterType) { 
return true;
	
	}

	@Override
	public void doTimeStep() {
		if(getEnergy()>Params.start_energy*5){
			walk(Critter.getRandomInt(8));
		}
		while(this.getEnergy()>Params.min_reproduce_energy*1.5){
			Critter newCrit=new Critter3();
			reproduce(newCrit,Critter.getRandomInt(8));
		}
	}
}
