/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.conversion;

import javax.time.calendar.LocalDate;
import javax.time.calendar.ZonedDateTime;

import com.opengamma.analytics.financial.ExerciseDecisionType;
import com.opengamma.analytics.financial.equity.option.EquityIndexOption;
import com.opengamma.analytics.financial.equity.option.EquityIndexOptionDefinition;
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.financial.convention.ConventionBundle;
import com.opengamma.financial.convention.ConventionBundleSource;
import com.opengamma.financial.security.ExerciseTypeAnalyticsVisitorAdapter;
import com.opengamma.financial.security.FinancialSecurityVisitorAdapter;
import com.opengamma.financial.security.option.EquityIndexOptionSecurity;
import com.opengamma.financial.security.option.EquityOptionSecurity;
import com.opengamma.financial.security.option.OptionType;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Converts an EquityIndexOptionSecurity into OG-Financial's version of one: EquityIndexOptionDefinition
 */
public class EquityIndexOptionConverter extends FinancialSecurityVisitorAdapter<InstrumentDefinition<EquityIndexOption>> {
  private final ConventionBundleSource _conventionSource;

  public EquityIndexOptionConverter(final ConventionBundleSource conventionSource) {
    ArgumentChecker.notNull(conventionSource, "convention source");
    _conventionSource = conventionSource;
  }

  @Override
  public InstrumentDefinition<EquityIndexOption> visitEquityIndexOptionSecurity(final EquityIndexOptionSecurity security) {
    ArgumentChecker.notNull(security, "security");
    final boolean isCall = security.getOptionType() == OptionType.CALL;
    final double strike = security.getStrike();
    final ZonedDateTime expiryDT = security.getExpiry().getExpiry();
    final Currency ccy = security.getCurrency();
    final double unitNotional = security.getPointValue();
    final ExerciseDecisionType exerciseType = security.getExerciseType().accept(ExerciseTypeAnalyticsVisitorAdapter.getInstance());
    final ExternalIdBundle indexId = security.getExternalIdBundle();
    final ConventionBundle indexConventions = _conventionSource.getConventionBundle(indexId);
    // TODO !!! We need to know how long after expiry does settlement occur?
    // IndexOptions are obviously Cash Settled
    final LocalDate settlementDate = expiryDT.toLocalDate(); // FIXME !!! Needs to come from convention !!!

    return new EquityIndexOptionDefinition(isCall, strike, ccy, indexId, exerciseType, expiryDT, settlementDate, unitNotional);
  }

  //TODO this should call into a specific type
  @Override
  public InstrumentDefinition<EquityIndexOption> visitEquityOptionSecurity(final EquityOptionSecurity security) {
    ArgumentChecker.notNull(security, "security");
    final boolean isCall = security.getOptionType() == OptionType.CALL;
    final double strike = security.getStrike();
    final ZonedDateTime expiryDT = security.getExpiry().getExpiry();
    final Currency ccy = security.getCurrency();
    final double unitNotional = security.getPointValue();
    final ExerciseDecisionType exerciseType = security.getExerciseType().accept(ExerciseTypeAnalyticsVisitorAdapter.getInstance());
    final ExternalIdBundle indexId = security.getExternalIdBundle();
    final ConventionBundle indexConventions = _conventionSource.getConventionBundle(indexId);
    // TODO !!! We need to know how long after expiry does settlement occur?
    // IndexOptions are obviously Cash Settled
    final LocalDate settlementDate = expiryDT.toLocalDate(); // FIXME !!! Needs to come from convention !!!

    return new EquityIndexOptionDefinition(isCall, strike, ccy, indexId, exerciseType, expiryDT, settlementDate, unitNotional);
  }
}


