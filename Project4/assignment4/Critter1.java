package assignment4;

public class Critter1 extends Critter{

	@Override
	public String toString() { return "1"; }
	
	private static final int GENE_TOTAL = 3;
	private int[] genes = new int[3];
	private int dir;
	
	public Critter1() {
		for (int k = 0; k < 3; k++) {
			genes[k] = Critter.getRandomInt(10);
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String critterType) { 
		if (critterType.equals("3") || critterType.equals("4")) {
			return true;
		}
		else {
			run(Critter.getRandomInt(8));
			return false;
		} 
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		int rand = Critter.getRandomInt(30);
		if (rand >= 15){
			walk(dir);
		}
			
		if (getEnergy() < 80) {
			Critter1 child = new Critter1();
			for (int k = 0; k < 3; k++) {
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
	
}
