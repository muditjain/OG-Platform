/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.multicurve.discounting;

import static com.opengamma.engine.value.ValuePropertyNames.CURVE;
import static com.opengamma.engine.value.ValueRequirementNames.CURVE_BUNDLE;
import static com.opengamma.engine.value.ValueRequirementNames.PV01;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.threeten.bp.Instant;

import com.google.common.collect.Iterables;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.analytics.financial.provider.calculator.discounting.PV01CurveParametersCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.analytics.util.amount.ReferenceAmount;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.CompiledFunctionDefinition;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.util.money.Currency;
import com.opengamma.util.tuple.Pair;

/**
 * Calculates the PV01 of swaps, cash and FRAs using curves constructed using
 * the discounting method.
 */
public class LinearFIDiscountingPV01Function extends DiscountingFunction {
  private static final InstrumentDerivativeVisitor<MulticurveProviderInterface, ReferenceAmount<Pair<String, Currency>>> CALCULATOR =
      PV01CurveParametersCalculator.getInstance();

  /**
   * Sets the value requirements to {@link ValueRequirementNames#PV01}
   */
  public LinearFIDiscountingPV01Function() {
    super(PV01);
  }

  @Override
  public CompiledFunctionDefinition compile(final FunctionCompilationContext context, final Instant atInstant) {
    return new DiscountingCompiledFunction(getTargetToDefinitionConverter(context), getDefinitionToDerivativeConverter(context), true) {

      @Override
      protected Set<ComputedValue> getValues(final FunctionInputs inputs, final ComputationTarget target, final Set<ValueRequirement> desiredValues, final InstrumentDerivative derivative) {
        final MulticurveProviderInterface data = (MulticurveProviderInterface) inputs.getValue(CURVE_BUNDLE);
        final ValueRequirement desiredValue = Iterables.getOnlyElement(desiredValues);
        final String desiredCurveName = desiredValue.getConstraint(CURVE);
        final ValueProperties properties = desiredValue.getConstraints();
        final ReferenceAmount<Pair<String, Currency>> pv01 = derivative.accept(CALCULATOR, data);
        final Set<ComputedValue> results = new HashSet<>();
        boolean curveNameFound = false;
        for (final Map.Entry<Pair<String, Currency>, Double> entry : pv01.getMap().entrySet()) {
          final String curveName = entry.getKey().getFirst();
          if (desiredCurveName.equals(curveName)) {
            curveNameFound = true;
          }
          final ValueProperties curveSpecificProperties = properties.copy()
              .withoutAny(CURVE)
              .with(CURVE, curveName)
              .get();
          final ValueSpecification spec = new ValueSpecification(PV01, target.toSpecification(), curveSpecificProperties);
          results.add(new ComputedValue(spec, entry.getValue()));
        }
        if (!curveNameFound) {
          throw new OpenGammaRuntimeException("Could not get PV01 for curve named " + desiredCurveName);
        }
        return results;
      }

      @Override
      protected ValueProperties.Builder getResultProperties(final ComputationTarget target) {
        final ValueProperties.Builder properties = super.getResultProperties(target);
        return properties.withAny(CURVE);
      }

      @Override
      protected boolean requirementsSet(final ValueProperties constraints) {
        if (super.requirementsSet(constraints)) {
          final Set<String> curves = constraints.getValues(CURVE);
          if (curves == null) {
            return false;
          }
          return true;
        }
        return false;
      }
    };
  }
}
