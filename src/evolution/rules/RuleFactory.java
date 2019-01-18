package evolution.rules;

import evolution.RandomNumberGenerator;
import evolution.rules.conditions.BetweenCondition;
import evolution.rules.conditions.UniversalCondition;

public class RuleFactory {

    public static Rule createRandomRule(int numconditions, int numClasses, double[] lb, double[] ub) {

        RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

        Rule r = new Rule();

        for (int i = 0; i < numconditions; i++) {
        	
            double ball = rng.nextDouble();
            if (ball >= 0.5) {
            	BetweenCondition lc = new BetweenCondition(lb[i], ub[i]);
            	lc.randomInitialization();
            	r.addCondition(lc);
            } else {
                r.addCondition(new UniversalCondition());
            }
//                LessThanCondition lc = new LessThanCondition(lb[i], ub[i]);
//                lc.randomInitialization();
//                r.addCondition(lc);
//            }
//            else if (ball < 0.5) {
//                GreaterThanCondition gc = new GreaterThanCondition(lb[i], ub[i]);
//                gc.randomInitialization();
//                r.addCondition(gc);
//            }
        }

        r.setClassLabel(rng.nextInt(numClasses));
        r.initializeWeights();
        
        return r;
    }
}
