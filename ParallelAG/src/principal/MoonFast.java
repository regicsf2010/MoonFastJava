package principal;

import auxiliaries.Configuration;
import interfaces.Crossover;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.SurvivorSelection;

/**
 * This class implements a Multipopulation Parallel Genetic Algorithm (MPGA).
 * Multipopulations are demes or islands.
 * @author reginaldo
 * @date   February/01/2016 
 *
 */
public class MoonFast {
	
	private Population p = null;
	private Population selected = null;
	private int function = -1;
	private int id = -1; // store the ID of the problem
	
	private ParentSelection parentSelecionI = null;
	private Crossover crossoverI = null;
	private Mutation mutationI = null;
	private SurvivorSelection survivorSelectionI = null;
	
	public MoonFast(int function, int id) {
		this.function = function;
		this.id = id;
	}
	
	public Population getPopulation() {
		return this.p;
	}
	
	private void initializePopulation() {
		this.p = Population.createPopulation(Configuration.NCHROMOSOME, this.function, true);
	}
	
	private void calculateFitness(Population pop) {
		Thread threads[] = new Thread[Configuration.NTHREADS];
		
		for (int t = 0; t < Configuration.NTHREADS; t++) {
			threads[t] = new Thread(new FitnessEval(pop, t));
			threads[t].start();
		}
		
		for (int i = 0; i < Configuration.NTHREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void calculateFitnessMeanAndStd(Population pop) {
		Thread threads[] = new Thread[2];
		for (int t = 0; t < 2; t++) {
			threads[t] = new Thread(new FitnessMeanAndStd(pop, t));
			threads[t].start();
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setParentSelectionInterface(ParentSelection parentSelecionI) {
		this.parentSelecionI = parentSelecionI;
	}
	
	public void setCrossoverInterface(Crossover crossoverI) {
		this.crossoverI = crossoverI;
	}
	
	public void setMutationInterface(Mutation mutationI) {
		this.mutationI = mutationI;
	}
	
	public void setSurvivorSelectionInterface(SurvivorSelection survivorSelectionI) {
		this.survivorSelectionI = survivorSelectionI;
	}
	
	public Population run() {
		this.initializePopulation();
		this.calculateFitness(p);
	
		for (int i = 0; i < Configuration.NGENERATION; i++) {
			this.selected = this.parentSelecionI.doParentSelection(p);
			this.selected = this.crossoverI.doCrossover(selected);
			this.mutationI.doMutation(selected);
			this.calculateFitness(selected);
			this.p = this.survivorSelectionI.doSurvivorSelection(p, selected);			
		}

		//Scripts.writeMatlabScript("e" + String.valueOf(this.getId()), mean, std, fittest);
		return p;
	}
}
