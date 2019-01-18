package evolution.selectors;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;

public class SUS implements Selector {
	
	RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

	@Override
	public void select(int howMany, Population from, Population to) {
		
		double fitnessSum = 0.0;

        for (int i = 0; i < from.getPopulationSize(); i++) {
            fitnessSum += from.get(i).getFitnessValue();
        }

        double[] fitnesses = new double[from.getPopulationSize()];

        for (int i = 0; i < fitnesses.length; i++) {
            fitnesses[i] = from.get(i).getFitnessValue() / fitnessSum;
        }

        double pointerDistance = (fitnessSum / howMany) / fitnessSum;
        
        int i = 0;
        double spacing = rng.nextDouble() * pointerDistance;
        double sum = 0;
        int j = 0;
        double nextPointer;
        for (j = 0 ; j < fitnesses.length ; j++) {
        	sum += fitnesses[j];
        	nextPointer = i * pointerDistance + spacing;
        	while (nextPointer <= sum) {
        		to.add((Individual) from.get(j).clone());
        		from.get(j).setLogNotes(from.get(j).getLogNotes() + " " + this.getClass().getCanonicalName());
        		i++;
        		nextPointer = i * pointerDistance + spacing;
        	}       	
        }

	}

}