package assignment4;
//Grasshopper critter
//Always try to hop away unless has high energy; if can't elects to fight but tries to move away first
public class Critter2 extends Critter{

	@Override
	public String toString() { return "2"; }
	




	
	public Critter2() {

	}
	@Override
	public boolean fight(String critterType) { 
	if(critterType.equals("1")){
		return true;
	}
	else if(critterType.equals("C")){
		return false;
	}
	else if(critterType.equals("2")){
		return true;
	}else{
		return true;
	}
	
	}

	@Override
	public void doTimeStep() {
	if(Critter.getRandomInt(3)==2){
		walk(Critter.getRandomInt(8));
	}
	walk(Critter.getRandomInt(8));
	if(Critter.getRandomInt(3)==0){
		Critter2 newCritter2=new Critter2();
		reproduce(newCritter2,Critter.getRandomInt(8));
	}
	}
	
}
