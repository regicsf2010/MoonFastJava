package principal;

import auxiliaries.Configuration;

public class FitnessEval implements Runnable {
	private Population p = null;
	private int t; 
	
	public FitnessEval(Population p, int t) {
		this.p = p;
		this.t = t;
	}
	
	@Override
	public void run() {
		int partition = Configuration.NCHROMOSOME / Configuration.NTHREADS;
		for (int i = partition * this.t; i < partition * this.t + partition; i++) {
			p.getChromosome(i).evaluate();
		}
		
	}

}
