package evolution.rules;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.operators.Operator;
import evolution.rules.conditions.BetweenCondition;
import evolution.rules.conditions.UniversalCondition;

public class DeleteConditionMutationOperator implements Operator{

    double mutProb;
    double mutProbPerBit;
    double mutSigma;
    RandomNumberGenerator rng;

    public DeleteConditionMutationOperator(double mutProb, double mutProbPerBit, double mutSigma) {
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

            if (rng.nextDouble() < mutProb) {
                for (int j = 0; j < o1.length(); j++) {
                    for (int k = 0; k < o1.getRules().get(j).getConditions().size(); k++)
                    if (o1.getRules().get(j).getConditions().get(k) instanceof BetweenCondition && rng.nextDouble() < mutProbPerBit) {
                        o1.getRules().get(j).getConditions().set(k, new UniversalCondition());
                    }
                }
            }

            offspring.add(o1);
        }

    }

}
