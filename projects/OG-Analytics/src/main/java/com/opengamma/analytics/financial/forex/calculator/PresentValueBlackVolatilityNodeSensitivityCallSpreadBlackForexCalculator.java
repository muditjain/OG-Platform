/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.forex.calculator;

import com.opengamma.analytics.financial.forex.derivative.ForexOptionDigital;
import com.opengamma.analytics.financial.forex.method.ForexOptionDigitalCallSpreadBlackMethod;
import com.opengamma.analytics.financial.forex.method.PresentValueForexBlackVolatilityNodeSensitivityDataBundle;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorAdapter;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.model.option.definition.SmileDeltaTermStructureDataBundle;

/**
 * Calculator of the present value volatility sensitivity for Forex derivatives in the Black (Garman-Kohlhagen) world. The volatilities are given by delta-smile descriptions.
 * @deprecated Curve builders that use and populate {@link YieldCurveBundle}s are deprecated.
 */
@Deprecated
public final class PresentValueBlackVolatilityNodeSensitivityCallSpreadBlackForexCalculator extends
    InstrumentDerivativeVisitorAdapter<SmileDeltaTermStructureDataBundle, PresentValueForexBlackVolatilityNodeSensitivityDataBundle> {

  /**
   * The methods used by the different instruments.
   */
  private final ForexOptionDigitalCallSpreadBlackMethod _methodFxOptionDigital;

  /**
   * Private constructor.
   * @param spread The relative spread used in the call-spread pricing. The call spread strikes are (for an original strike K), K*(1-spread) and K*(1+spread).
   */
  public PresentValueBlackVolatilityNodeSensitivityCallSpreadBlackForexCalculator(final double spread) {
    _methodFxOptionDigital = new ForexOptionDigitalCallSpreadBlackMethod(spread);
  }

  @Override
  public PresentValueForexBlackVolatilityNodeSensitivityDataBundle visitForexOptionDigital(final ForexOptionDigital derivative, final SmileDeltaTermStructureDataBundle data) {
    return _methodFxOptionDigital.presentValueBlackVolatilityNodeSensitivity(derivative, data);
  }

}
