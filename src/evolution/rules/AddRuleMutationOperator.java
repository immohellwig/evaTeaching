package evolution.rules;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.operators.Operator;

public class AddRuleMutationOperator implements Operator{

    double mutProb;
    double mutProbPerBit;
    double mutSigma;
    RandomNumberGenerator rng;

    public AddRuleMutationOperator(double mutProb, double mutProbPerBit, double mutSigma) {
        this.mutProb = mutProb;
        this.mutProbPerBit = mutProbPerBit;
        this.mutSigma = mutSigma;
        this.rng = RandomNumberGenerator.getInstance();
    }

    @Override
    public void operate(Population parents, Population offspring) {

        int size = parents.getPopulationSize();

        for (int i = 0; i < size; i++) {

            RuleIndividual p1 = (RuleIndividual) parents.get(i);
            RuleIndividual o1 = (RuleIndividual) p1.clone();

            if (rng.nextDouble() < mutProb/5) {
                        o1.getRules().add(RuleFactory.targetRule(o1));
            }

            offspring.add(o1);
        }

    }

}
