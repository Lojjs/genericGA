import java.util.*;

/**
 * Generic genetic algorithm.
 */
public class GA<T extends Gene> {
	
	protected List<T> list;
	protected int generationSize; // typically 50 - 100
	protected int nbrGenerations;
	protected Class<T> clazz; // to be able to crete new T instances

	public GA(int generationSize, int nbrGenerations, Class<T> clazz) {
		this.generationSize  = generationSize;
		this.nbrGenerations  = nbrGenerations;
		this.clazz           = clazz;

		/* Represent the problem as binary vector, integer vector or 
		   real value genes. */
		list = new LinkedList<T>();
		
		run();
	}

	void run(){
		
		/* Randomize the first generation of genes */
		for (int k = 0; k < generationSize; k++) {
			T gene = newT();
			gene.setRandomValue();
			list.add(gene);
		}

		for (int k = 0; k < nbrGenerations; k++) {

			/* Calculate fitness for each gene according 
			   to a pre-determined function */
			for (int i = 0; i < generationSize; i++) {
				list.get(i).setFitness();
			}

			/* Sort the genes in the list according to fitness,
			   best on top */
			sort();
			
			for(int i = 0; i < generationSize; i+= 2){
				/* Mating/cross over: recombine the genes two by two */		
				T gene1       = list.get(i);
				T gene2       = list.get(i+1);
				
				T offspring = newT(); 
				offspring.setValue(gene1.combineValues(gene2.getValue()));
				
				/* Calculate fitness for the offspring and put it
				   on the right place in the list. */
				offspring.setFitness();
				place(offspring);
			}

			/* Throw away the worst 1/3 genes to maintain 
			   the generation size */
			LinkedList<T> tmp = new LinkedList<T>();
			for (int i = 0; i < generationSize; i++) {
				tmp.add(list.get(i));
			}
			list = tmp;

			/* Mutation, often the best values (e.g. the top 20%) 
				is held mutation free */
			for (int i = generationSize/5; i < generationSize; i++) {
				T gene = list.get(i);
				gene.setValue(gene.mutateValue());
			}	
		}

		/* Print the top 3 results. */
		for (int i = 0; i < 3; i++) {
			System.out.println(list.get(i).getValue());
		}
	}

	/* Sort according to fitness. */
	private void sort(){
		List<T> tmp = list;
		list = new ArrayList<T>();
		for(T g : tmp){
			place(g);
		}
	}

	/* Insert a gene on the correct place in a list. */
	private void place(T gene){
		for (int k = 0; k < list.size(); k++) {
			if (list.get(k).getFitness() > gene.getFitness()) {
				list.add(k, gene);
				return;
			}
		}
		list.add(list.size(), gene);
	}

	/* In order to create new instances of T */
	private T newT() {
		try{
			return clazz.newInstance();
		}catch(Exception e){
			return null;
		}
	}
}