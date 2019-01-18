package evolution.rules.conditions;

import evolution.RandomNumberGenerator;

public class isEvenCondition extends Condition {

    public isEvenCondition() {
    }

    @Override
    public void randomInitialization() {
    }

    public boolean matches(double value) {
        return value == Math.round(value);
    }

    public void mutate(Object parameter) {
    }

    public Object clone() {
        return new isEvenCondition();
    }

    public String toString() {
        return String.format("is Even");
    }

}
