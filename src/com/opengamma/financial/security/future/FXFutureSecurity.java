/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.future;

import com.opengamma.financial.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A foreign exchange future.
 *
 * @author Andrew
 */
public class FXFutureSecurity extends FutureSecurity {
  //TODO there's no reason why this shouldn't be used for FX cross futures, which means it will also need a currency for the trade itself

  /**
   * 
   */
  protected static final String NUMERATOR_KEY = "numerator";
  /**
   * 
   */
  protected static final String DENOMINATOR_KEY = "denominator";
  /**
   * 
   */
  protected static final String MULTIPLICATIONFACTOR_KEY = "multiplicationFactor";

  /** The numerator currency. */
  private final Currency _numerator;
  /** The denominator currency. */
  private final Currency _denominator;
  /** The multiplication factor. */
  private final double _multiplicationFactor;

  /**
   * Creates a foreign exchange future.
   * @param expiry  the expiry of the future
   * @param tradingExchange  the exchange that the future is trading on
   * @param settlementExchange  the exchange where the future is settled
   * @param domesticCurrency  the domestic currency
   * @param numerator  the numerator currency
   * @param denominator  the denominator currency
   * @param multiplicationFactor  the multiplication factor
   */
  public FXFutureSecurity(
      final Expiry expiry, final String tradingExchange, final String settlementExchange,
      final Currency domesticCurrency, final Currency numerator, final Currency denominator, final double multiplicationFactor) {
    super(expiry, tradingExchange, settlementExchange, domesticCurrency);
    _numerator = numerator;
    _denominator = denominator;
    _multiplicationFactor = multiplicationFactor;
  }

  /**
   * Creates a foreign exchange future.
   * @param expiry  the expiry of the future
   * @param tradingExchange  the exchange that the future is trading on
   * @param settlementExchange  the exchange where the future is settled
   * @param domesticCurrency  the domestic currency
   * @param numerator  the numerator currency
   * @param denominator  the denominator currency
   */
  public FXFutureSecurity(
      final Expiry expiry, final String tradingExchange, final String settlementExchange,
      final Currency domesticCurrency, final Currency numerator, final Currency denominator) {
    this (expiry, tradingExchange, settlementExchange, domesticCurrency, numerator, denominator, 1.0);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the numerator currency.
   * @return the numerator currency
   */
  public Currency getNumerator() {
    return _numerator;
  }

  /**
   * Gets the denominator currency.
   * @return the denominator currency
   */
  public Currency getDenominator() {
    return _denominator;
  }

  /**
   * Gets the multiplication factor.
   * @return the multiplication factor
   */
  public double getMultiplicationFactor() {
    return _multiplicationFactor;
  }

  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(FutureSecurityVisitor<T> visitor) {
    return visitor.visitFXFutureSecurity(this);
  }

}
