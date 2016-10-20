package auxiliaries;

public class Configuration {
	public static final int NCHROMOSOME = 100; // must be even ('cause of crossover)
	public static final int NGENERATION = 1000;
	
	public static final double CROSSOVERRATE = 0.8;
	public static final double MUTATIONRATE = 0.01;
	public static final double SD = 0.1;
	public static final int RANK = 3;
	
	public static final int NTHREADS = 10;
	public static boolean isReady = false;
	
	/*Simple structures for each problem*/
	public class Ackley { public static final int ID = 0; public static final int NGENES = 500; }
	public class SchafferN2 { public static final int ID = 1; public static final int NGENES = 2; }
	public class SchafferN4 { public static final int ID = 2; public static final int NGENES = 2; }
	public class SchafferF7 { public static final int ID = 3; public static final int NGENES = 2; }
	public class Eggholder { public static final int ID = 4; public static final int NGENES = 2; }
	public class Griewank { public static final int ID = 5; public static final int NGENES = 1; }
	public class Rastrigin { public static final int ID = 6; public static final int NGENES = 2; }
	public class Rosenbrock { public static final int ID = 7; public static final int NGENES = 2; }
}
