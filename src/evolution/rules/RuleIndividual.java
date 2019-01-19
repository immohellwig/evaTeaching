package evolution.rules;

import evolution.RandomNumberGenerator;
import evolution.individuals.ArrayIndividual;

import java.util.ArrayList;
import java.util.Comparator;

public class RuleIndividual extends ArrayIndividual {

    ArrayList<Rule> rules;
    int maxRules;
    int numConditionsPerRule;
    double[] lb;
    double[] ub;
    int numClasses;

    public RuleIndividual(int maxRules, int numConditionsPerRule, int numClasses, double[] lb, double[] ub) {
        this.numConditionsPerRule = numConditionsPerRule;
        this.maxRules = maxRules;
        this.ub = ub;
        this.lb = lb;
        this.rules = new ArrayList<Rule>();
        this.numClasses = numClasses;
    }

    @Override
    public Object get(int n) {
        return rules.get(n);
    }

    @Override
    public void set(int n, Object o) {
        rules.set(n, (Rule)o);
    }

    @Override
    public int length() {
        return this.rules.size();
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    @Override
    public void randomInitialization() {

//        int ruleNum = RandomNumberGenerator.getInstance().nextInt(maxRules);

        rules = new ArrayList<Rule>();

        for (int i = 0; i < maxRules; i++) {
            rules.add(RuleFactory.createRandomRule(numConditionsPerRule, (i%7)+3, lb, ub));
            rules.get(i).setPriority(i%7);
        }

    }

    @Override
    public Object clone() {
        RuleIndividual r = new RuleIndividual(maxRules, numConditionsPerRule, numClasses, lb, ub);
        r.rules = new ArrayList<Rule>(rules.size());
        for (int i = 0; i < rules.size(); i++) {
            r.rules.add((Rule) rules.get(i).clone());
        }
        return r;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rules.size(); i++) {
            str.append(rules.get(i));
            if (i < rules.size() - 1)
                str.append("\n");
        }
        return str.toString();
    }

	public void mutatePriority() {
		Rule r1 = rules.get(RandomNumberGenerator.getInstance().nextInt(rules.size()));
		r1.setPriority(RandomNumberGenerator.getInstance().nextInt(rules.size()));
	}

	public void orderByPrio() {
		Comparator<Rule> c = new Comparator<Rule>() {
			@Override
			public int compare(Rule arg0, Rule arg1) {
				return arg0.getPriority() > arg1.getPriority() ? 1 : -1;
			}
		};
		rules.sort(c);
	}
}
