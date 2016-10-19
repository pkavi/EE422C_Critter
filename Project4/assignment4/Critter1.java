package assignment4;
//Grasshopper critter
//Always try to hop away unless has high energy; if can't elects to fight but tries to move away first
public class Critter1 extends Critter{

	@Override
	public String toString() { return "1"; }
	
private int critter1timeStep=0;
private int reproducePeriod=1;
private int runPeriod=1;
private int reproduceEnergyMinimum=1;
private int fightEnergyMinimum=1;



	
	public Critter1() {
		fightEnergyMinimum=Params.rest_energy_cost*10;
		reproduceEnergyMinimum=Params.rest_energy_cost*5;
		reproducePeriod=Critter.getRandomInt(Params.rest_energy_cost)+1;
		runPeriod=Critter.getRandomInt(Params.rest_energy_cost);
	}
	@Override
	public boolean fight(String critterType) { 
		if(getEnergy()>fightEnergyMinimum){
			return true;
		}else{
			run(Critter.getRandomInt(8));
			return true;
		}
	}

	@Override
	public void doTimeStep() {
		if(getEnergy()>reproduceEnergyMinimum && critter1timeStep%reproducePeriod==0){
			Critter1 newCritter=new Critter1();
			reproduce(newCritter,Critter.getRandomInt(8));
		}
		if(critter1timeStep%runPeriod==0){
			run(Critter.getRandomInt(8));
		}
		critter1timeStep++;
	}
	
}
