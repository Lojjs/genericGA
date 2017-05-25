/**
 * Generic gene interface.
 */
public interface Gene<T> {
	
	/**
	 * Set the value of the gene to a specified value.
	 *
	 * @param value	the value to be set 
	 */
	public void setValue(T value);

	/**
	 * Set the value of the gene to a random value.
	 *
	 * @return the randomized value
	 */
	public T setRandomValue();

	/**
	 * Get the value of the gene.
	 *
	 * @return the value of the gene.
	 */
	public T getValue();
	
	/**
	 * Calculate and set the fitness of the gene.
	 *
	 * @return the calculated fitness
	 */
	public double setFitness();

	/**
	 * Get the fitness of the gene.
	 *
	 * @return the fitness of the gene
	 */
	public double getFitness();

	/**
	 * Combine this gene's value with another one. 
	 *
	 * @param value2	the value of the second gene
	 * @return the combined value
	 */
	public T combineValues(T value2);
	
	/**
	 * Mutate this gene's value
	 *
	 * @return the mutated value
	 */
	 public T mutateValue();	
}
