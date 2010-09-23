/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.math.statistics.descriptive;

import org.apache.commons.lang.Validate;

import com.opengamma.math.function.Function1D;

/**
 * The semi-standard deviation of a series of data is a less general version of a partial moment (see {@link PartialMomentCalculator}). Instead of a user-defined
 * threshold, the mean is used.
 */
public class SemiStandardDeviationCalculator extends Function1D<double[], Double> {
  private static final MeanCalculator MEAN = new MeanCalculator();
  private final boolean _useDownSide;

  /**
   * Creates calculator with the default value for useDownSide (= true)
   */
  public SemiStandardDeviationCalculator() {
    _useDownSide = true;
  }

  /**
   * Creates calculator
   * @param useDownSide If true, data below the mean is used in the calculation
   */
  public SemiStandardDeviationCalculator(final boolean useDownSide) {
    _useDownSide = useDownSide;
  }

  /**
   * @param x The array of data
   * @return The semi-standard deviation
   * @throws IllegalArgumentException If the array is null
   */
  @Override
  public Double evaluate(final double[] x) {
    Validate.notNull(x, "x");
    final double mean = MEAN.evaluate(x);
    final int n = x.length;
    return new PartialMomentCalculator(mean, _useDownSide).evaluate(x) * n / (n - 1);
  }

}
