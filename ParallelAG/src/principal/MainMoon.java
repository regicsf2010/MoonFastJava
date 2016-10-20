package principal;

import auxiliaries.Configuration.Ackley;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.GaussianMutation;
import implementations.TournamentSelection;

public class MainMoon {
	
	public static void main(String args[]) {
		MoonFast mf = new MoonFast(Ackley.ID, 1);
 
		mf.setParentSelectionInterface(new TournamentSelection());
		mf.setCrossoverInterface(new ArithmeticCrossover());
		mf.setMutationInterface(new GaussianMutation());
		mf.setSurvivorSelectionInterface(new BestPairSurvivorSelection());
		
		long startTime = System.currentTimeMillis();
		Population p = mf.run();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Time: " + totalTime / 1000f + " (s)");
		
		System.out.println("Mean before: " + p.getFitnessMean());
		mf.calculateFitnessMeanAndStd(p);
		System.out.println("Mean after: " + p.getFitnessMean());
		
	}

}
