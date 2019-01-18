package evolution.rules.conditions;

import java.util.Random;

import evolution.RandomNumberGenerator;

public class BetweenCondition extends Condition {

	double minVal;
	double maxVal;
	double center;
	double range;

	public BetweenCondition(double minVal, double maxVal) {
		this.minVal = minVal;
		this.maxVal = maxVal;
	}

	@Override
	public void randomInitialization() {
		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
		center = minVal + rng.nextDouble() * (maxVal - minVal);
		range = rng.nextDouble() * (center - minVal > maxVal - center ? center - minVal : maxVal - center);
	}

	@Override
	public boolean matches(double value) {
		return value > center - range && value < center + range;
	}

	@Override
	public void mutate(Object parameter) {
		RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
		if (rng.nextDouble() > 0.5)
			center += ((Double) parameter) * rng.nextGaussian();
		else
			range += ((Double) parameter) * rng.nextGaussian();
		if (center < 0) {
			center = Math.abs(center);
			if (center - range < 0)
				range = center;
		}
	}

	@Override
	public Object clone() {
		BetweenCondition cloned = new BetweenCondition(minVal, maxVal);
		cloned.center = this.center;
		cloned.range = this.range;
		return cloned;
	}

	@Override
	public String toString() {
		return Double.toString(center-range) + "-" + Double.toString(center+range);
	}

}
