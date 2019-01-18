package evolution.rules;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.operators.Operator;
import evolution.rules.conditions.BetweenCondition;
import evolution.rules.conditions.GreaterThanCondition;
import evolution.rules.conditions.LessThanCondition;

public class OverwriteConditionMutationOperator implements Operator {

	double mutProb;
	double mutProbPerBit;
	double mutSigma;
	RandomNumberGenerator rng;

	public OverwriteConditionMutationOperator(double mutProb, double mutProbPerBit, double mutSigma) {
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
						if (rng.nextDouble() < mutProbPerBit) {
//							double ball = rng.nextDouble();
//							if (ball < 1 / 3) {
								BetweenCondition btw = new BetweenCondition(o1.lb[k], o1.ub[k]);
								btw.randomInitialization();
								o1.getRules().get(j).getConditions().set(k, btw);
//							} else if (ball < 2 / 3) {
//								LessThanCondition btw = new LessThanCondition(o1.lb[k], o1.ub[k]);
//								btw.randomInitialization();
//								o1.getRules().get(j).getConditions().set(k, btw);
//							} else {
//								GreaterThanCondition btw = new GreaterThanCondition(o1.lb[k], o1.ub[k]);
//								btw.randomInitialization();
//								o1.getRules().get(j).getConditions().set(k, btw);
//							}
						}
				}
			}

			offspring.add(o1);
		}

	}

}
