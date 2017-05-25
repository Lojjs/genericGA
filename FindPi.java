/**
 * Find the value of PI by using a genetic algorithm.
 */
public class FindPi {

	public static void main(String [] args){
		new FindPi().run();
	}

	void run() {
		int generationSize = 13;
		int nbrOfGenerations = 100;
		new GA<PiGene>(generationSize, nbrOfGenerations, PiGene.class);
	}
}