/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.math.function.special;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opengamma.math.function.Function1D;

public class IncompleteGammaFunctionTest {
  private static final double A = 1;
  private static final Function1D<Double, Double> FUNCTION = new IncompleteGammaFunction(A);
  private static final double EPS = 1e-9;
  private static final int MAX_ITER = 10000;

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeA1() {
    new IncompleteGammaFunction(-A);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeA2() {
    new IncompleteGammaFunction(-A, MAX_ITER, EPS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIter() {
    new IncompleteGammaFunction(A, -MAX_ITER, EPS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEps() {
    new IncompleteGammaFunction(A, MAX_ITER, -EPS);
  }

  @Test
  public void testLimits() {
    assertEquals(FUNCTION.evaluate(0.), 0, EPS);
    assertEquals(FUNCTION.evaluate(100.), 1, EPS);
  }

  @Test
  public void test() {
    final Function1D<Double, Double> f = new Function1D<Double, Double>() {

      @Override
      public Double evaluate(final Double x) {
        return 1 - Math.exp(-x);
      }

    };
    final double x = 4.6;
    assertEquals(f.evaluate(x), FUNCTION.evaluate(x), EPS);
  }
}
