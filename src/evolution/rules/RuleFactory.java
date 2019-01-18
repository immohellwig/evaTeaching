package evolution.rules;

import evolution.RandomNumberGenerator;
import evolution.rules.conditions.BetweenCondition;
import evolution.rules.conditions.GreaterThanCondition;
import evolution.rules.conditions.LessThanCondition;
import evolution.rules.conditions.UniversalCondition;

public class RuleFactory {

	public static Rule createRandomRule(int numconditions, int numClasses, double[] lb, double[] ub) {

		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

		Rule r = new Rule();

		for (int i = 0; i < numconditions; i++) {
//            double ball = rng.nextDouble();
//            if (ball < 0.25) {
//                LessThanCondition lc = new LessThanCondition(lb[i], ub[i]);
//                lc.randomInitialization();
//                r.addCondition(lc);
//            }
//            else if (ball < 0.5) {
//                GreaterThanCondition gc = new GreaterThanCondition(lb[i], ub[i]);
//                gc.randomInitialization();
//                r.addCondition(gc);
//            } 
//            if (ball < 0.) {
			BetweenCondition bc = new BetweenCondition(lb[i], ub[i]);
			bc.randomInitialization();
			r.addCondition(bc);
//            } else {
//                r.addCondition(new UniversalCondition());
//            }
		}

		r.setClassLabel(rng.nextInt(7) + 3);
		r.weight = rng.nextGaussian();

		return r;
	}

	public static Rule targetRule(RuleIndividual o1) {
		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

		Rule r = new Rule();
		boolean isNew;
		int count;
		for (int i = 0; i < o1.numConditionsPerRule; i++) {
			isNew = false;
			BetweenCondition bc = new BetweenCondition(o1.lb[i], o1.ub[i]);
			BetweenCondition currCond;
			count = 0;
			while (!isNew && count++ < 100) {
				isNew = true;
				bc.randomInitialization();
				for (Rule currentRule : o1.getRules()) {
					currCond = ((BetweenCondition) currentRule.getConditions().get(i));
					if ((currCond.getLower() > bc.getLower() && currCond.getLower() < bc.getUpper()) || (currCond.getUpper() > bc.getLower() && currCond.getUpper() < bc.getUpper())) {
						isNew = false;
					} else {
						isNew = isNew && true;
					}
				}
			}
			r.addCondition(bc);
		}
		r.setClassLabel(rng.nextInt(7) + 3);
		r.weight = rng.nextGaussian();
		return r;
	}
}
