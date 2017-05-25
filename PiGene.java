import java.util.*;

/**
 * A gene for solving the Pi problem which has a Double value 
 * between 3 and 4.
 */
public class PiGene implements Gene<Double> {
	
	protected Double value;
	protected double fitness;
	protected Random rand;

	public PiGene() {
		rand = new Random();
	}

	/**
	 * Set the value of the gene to a specified value.
	 *
	 * @param value	the value to be set 
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Set the value of the gene to a random double between 3 and 4.
	 *
	 * @return the randomized value
	 */
	public Double setRandomValue() {
		value = new Double(3 + rand.nextDouble());
		return value;
	}

	/**
	 * Get the value of the gene.
	 *
	 * @return the value of the gene.
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * Calculate and set the fitness of the gene.
	 *
	 * @return the calculated fitness
	 */
	public double setFitness() {
		fitness = Math.abs(Math.PI-value);
		return fitness;
	}

	/**
	 * Get the fitness of the gene.
	 *
	 * @return the fitness of the gene
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Combine this gene's value with another one. 
	 *
	 * @param value2	the value of the second gene
	 * @return the combined value
	 */
	public Double combineValues(Double value2) {
		double recombo1 = 0.5 * value + 0.5 * value2;
		double recombo2 = 1.5 * value - 0.5 * value2;
		double recombo3 = 1.5 * value2 - 0.5 * value;

		return new Double(best(recombo1, recombo2, recombo3));
	}

	/* Find the best offspring. */
	private double best(double a, double b, double c) {
		double fA = Math.abs(Math.PI-a);
		double fB = Math.abs(Math.PI-b);
		double fC = Math.abs(Math.PI-c);
		
		if (fA < fB && fA < fC) {
			return a;
		}
		else if (fB < fC) {
			return b;
		}
		return c;
			
	}

	/**
	 * Mutate this gene's value
	 *
	 * @return the mutated value
	 */
	public Double mutateValue() {
		/* Not supported for Pi gene*/
		return value;
	}
}
