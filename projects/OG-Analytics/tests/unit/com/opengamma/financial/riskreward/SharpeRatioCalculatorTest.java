/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.riskreward;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opengamma.financial.timeseries.analysis.DoubleTimeSeriesStatisticsCalculator;
import com.opengamma.math.function.Function;
import com.opengamma.util.timeseries.DoubleTimeSeries;
import com.opengamma.util.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.util.timeseries.fast.longint.FastArrayLongDoubleTimeSeries;

/**
 * 
 */
public class SharpeRatioCalculatorTest {
  private static final long[] T = new long[] {1};
  private static final double STD_DEV = 0.15;
  private static final DoubleTimeSeries<?> ASSET_RETURN = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, T, new double[] {0.12});
  private static final DoubleTimeSeries<?> RISK_FREE = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, T, new double[] {0.03});
  private static final DoubleTimeSeriesStatisticsCalculator EXCESS_RETURN = new DoubleTimeSeriesStatisticsCalculator(new Function<double[], Double>() {

    @Override
    public Double evaluate(final double[]... x) {
      return x[0][0];
    }

  });
  private static final DoubleTimeSeriesStatisticsCalculator STD = new DoubleTimeSeriesStatisticsCalculator(new Function<double[], Double>() {

    @Override
    public Double evaluate(final double[]... x) {
      return STD_DEV;
    }

  });
  private static final SharpeRatioCalculator SHARPE = new SharpeRatioCalculator(EXCESS_RETURN, STD);

  @Test(expected = IllegalArgumentException.class)
  public void testNullExcessReturnCalculator() {
    new SharpeRatioCalculator(null, EXCESS_RETURN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullStdDevCalculator() {
    new SharpeRatioCalculator(EXCESS_RETURN, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullTSArray() {
    SHARPE.evaluate(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFirstElement() {
    SHARPE.evaluate(null, RISK_FREE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullSecondElement() {
    SHARPE.evaluate(ASSET_RETURN, null);
  }

  @Test
  public void test() {
    assertEquals(SHARPE.evaluate(ASSET_RETURN, RISK_FREE), 0.6, 0);
  }
}
