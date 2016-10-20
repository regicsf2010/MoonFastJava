package principal;

import auxiliaries.Configuration;

public class FitnessMeanAndStd implements Runnable {
	private Population p = null;
	private int t;
	
	public FitnessMeanAndStd(Population p, int t) {
		this.p = p;
		this.t = t;
	}
	
	@Override
	public void run() {
		if(this.t == 0) {
			while(!Configuration.isReady){}
			p.calculateFitnessStd();
			Configuration.isReady = false;
		} else {
			p.calculateFitnessMean();
			Configuration.isReady = true;
		}

	}

}
