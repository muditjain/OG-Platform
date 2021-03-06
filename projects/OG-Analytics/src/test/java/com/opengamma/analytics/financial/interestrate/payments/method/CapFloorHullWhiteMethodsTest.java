/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.payments.method;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIborMaster;
import com.opengamma.analytics.financial.instrument.index.IndexSwap;
import com.opengamma.analytics.financial.instrument.payment.CapFloorCMSDefinition;
import com.opengamma.analytics.financial.interestrate.TestsDataSetsSABR;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CapFloorCMS;
import com.opengamma.analytics.financial.model.interestrate.TestsDataSetHullWhite;
import com.opengamma.analytics.financial.model.interestrate.definition.HullWhiteOneFactorPiecewiseConstantDataBundle;
import com.opengamma.analytics.financial.model.interestrate.definition.HullWhiteOneFactorPiecewiseConstantParameters;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.util.money.CurrencyAmount;
import com.opengamma.util.time.DateUtils;

/**
 * @deprecated This class tests deprecated functionality.
 */
@Deprecated
public class CapFloorHullWhiteMethodsTest {

  private static final Calendar TARGET = new MondayToFridayCalendar("TARGET");
  private static final GeneratorSwapFixedIborMaster GENERATOR_SWAP_MASTER = GeneratorSwapFixedIborMaster.getInstance();
  private static final GeneratorSwapFixedIbor GENERATOR_EUR1YEURIBOR6M = GENERATOR_SWAP_MASTER.getGenerator("EUR1YEURIBOR6M", TARGET);
  private static final Period TENOR_SWAP = Period.ofYears(10);
  private static final IndexSwap SWAP_EUR10Y = new IndexSwap(GENERATOR_EUR1YEURIBOR6M, TENOR_SWAP);

  private static final ZonedDateTime REFERENCE_DATE = DateUtils.getUTCDate(2012, 1, 17);

  // Cap floor CMS: 6m fixing in advance (payment in arrears); ACT/360
  private static final Period TENOR_COUPON = Period.ofMonths(6);
  private static final Period TENOR_FIXING = Period.ofMonths(60);
  private static final DayCount ACT360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
  private static final ZonedDateTime FIXING_DATE = ScheduleCalculator.getAdjustedDate(REFERENCE_DATE, TENOR_FIXING, GENERATOR_EUR1YEURIBOR6M.getBusinessDayConvention(), TARGET,
      GENERATOR_EUR1YEURIBOR6M.isEndOfMonth());
  private static final ZonedDateTime START_DATE = ScheduleCalculator.getAdjustedDate(FIXING_DATE, GENERATOR_EUR1YEURIBOR6M.getSpotLag(), TARGET);
  private static final ZonedDateTime PAYMENT_DATE = ScheduleCalculator.getAdjustedDate(START_DATE, TENOR_COUPON, GENERATOR_EUR1YEURIBOR6M.getBusinessDayConvention(), TARGET,
      GENERATOR_EUR1YEURIBOR6M.isEndOfMonth());
  private static final double NOTIONAL = 100000000; //100m
  private static final double ACCRUAL_FACTOR = ACT360.getDayCountFraction(START_DATE, PAYMENT_DATE);
  private static final double[] STRIKE = new double[] {0.00, 0.01, 0.02, 0.03};
  private static final int NB_STRIKE = STRIKE.length;

  private static final YieldCurveBundle CURVES = TestsDataSetsSABR.createCurves2();
  private static final String[] CURVE_NAMES = TestsDataSetsSABR.curves2Names();
  private static final HullWhiteOneFactorPiecewiseConstantParameters PARAMETERS_HW = TestsDataSetHullWhite.createHullWhiteParameters();
  private static final HullWhiteOneFactorPiecewiseConstantDataBundle BUNDLE_HW = new HullWhiteOneFactorPiecewiseConstantDataBundle(PARAMETERS_HW, CURVES);

  private static final CapFloorCMSDefinition[] CAP_CMS_DEFINITION = new CapFloorCMSDefinition[NB_STRIKE];
  private static final CapFloorCMS[] CAP_CMS = new CapFloorCMS[NB_STRIKE];
  static {
    for (int loopstrike = 0; loopstrike < NB_STRIKE; loopstrike++) {
      CAP_CMS_DEFINITION[loopstrike] = CapFloorCMSDefinition.from(PAYMENT_DATE, START_DATE, PAYMENT_DATE, ACCRUAL_FACTOR, NOTIONAL, SWAP_EUR10Y, STRIKE[loopstrike], true, TARGET);
      CAP_CMS[loopstrike] = (CapFloorCMS) CAP_CMS_DEFINITION[loopstrike].toDerivative(REFERENCE_DATE, new String[] {CURVE_NAMES[0], CURVE_NAMES[2]});
    }
  }

  private static final CapFloorCMSHullWhiteNumericalIntegrationMethod METHOD_NI = CapFloorCMSHullWhiteNumericalIntegrationMethod.getInstance();
  private static final CapFloorCMSHullWhiteApproximationMethod METHOD_APP = CapFloorCMSHullWhiteApproximationMethod.getInstance();
  private static final double BP1 = 1.0E-4; // 1 bp

  @Test
  public void presentValueApproximation() {
    for (int loopstrike = 0; loopstrike < NB_STRIKE; loopstrike++) {
      final CurrencyAmount pvNumericalIntegration = METHOD_NI.presentValue(CAP_CMS[loopstrike], BUNDLE_HW);
      final CurrencyAmount pvApproximation = METHOD_APP.presentValue(CAP_CMS[loopstrike], BUNDLE_HW);
      assertEquals("Cap floor CMS - Hull-White - present value - approximation - strike: " + STRIKE[loopstrike], pvNumericalIntegration.getAmount(), pvApproximation.getAmount(), NOTIONAL
          * ACCRUAL_FACTOR * BP1);
      // Error of less than 1.0bp in rate
    }
    //TODO: Review if error can be decreased.
  }

}
