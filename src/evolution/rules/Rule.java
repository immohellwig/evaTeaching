package evolution.rules;

import evolution.RandomNumberGenerator;
import evolution.rules.conditions.Condition;

import java.util.ArrayList;
import java.util.Arrays;

public class Rule {

    ArrayList<Condition> conditions;
    double[] weights;
    int classLabel;

    public Rule() {
        conditions = new ArrayList<Condition>();
    }


    boolean matches(double[] x) {
//        boolean match = true;
    	double fitting = 0;
        for (int i = 0; i < conditions.size(); i++) {
            fitting += conditions.get(i).matches(x[i]) ? weights[i] : 0;    
        }
        return fitting >= 0.5;
    }

    public int getClassLabel() {
        return this.classLabel;
    }

    public void setClassLabel(int classLabel) {
        this.classLabel = classLabel;
    }

    public void setCondition(int i, Condition c) {
        conditions.set(i, c);
    }

    public void addCondition(Condition c) {
        conditions.add(c);
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public Object clone() {
        Rule n = new Rule();
        n.classLabel = classLabel;
        n.conditions = new ArrayList<Condition>(conditions.size());
        n.weights = this.weights;
        for (int i = 0; i < conditions.size(); i++) {
            n.conditions.add((Condition) conditions.get(i).clone());
        }
        return n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(conditions.toArray()));
        sb.append(" -> ");
        sb.append(classLabel);
        return sb.toString();
    }


	public void initializeWeights() {
		int size = conditions.size();
		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
		weights = new double[size];
		for (int i = 0 ; i < size; i++) {
			weights[i] = 1/size;
		}
	}


	public void mutateWeights() {
		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
		double current;
		for (int i = 0 ; i < weights.length; i++) { 
			if (rng.nextDouble() < 0.5) {
				current = rng.nextGaussian() / 10;
				weights[i] += current;
				weights[rng.nextInt(weights.length)] -= current;
			}
		}
	}
}
