/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.math.rootfinding.newton;

import com.opengamma.math.linearalgebra.Decomposition;
import com.opengamma.math.linearalgebra.LUDecompositionCommons;
import com.opengamma.math.matrix.MatrixAlgebra;
import com.opengamma.math.matrix.OGMatrixAlgebra;

/**
 * Uses the Sherman-Morrison formula to invert Broyden's Jacobian update formula, thus providing a direct update formula for the inverse Jacobian 
 */
public class ShermanMorrisonVectorRootFinder extends NewtonVectorRootFinder {
  private static final double DEF_TOL = 1e-7;
  private static final int MAX_STEPS = 100;

  public ShermanMorrisonVectorRootFinder() {
    this(DEF_TOL, DEF_TOL, MAX_STEPS);
  }

  public ShermanMorrisonVectorRootFinder(final double absoluteTol, final double relativeTol, final int maxSteps) {
    this(absoluteTol, relativeTol, maxSteps, new LUDecompositionCommons());
  }

  public ShermanMorrisonVectorRootFinder(final double absoluteTol, final double relativeTol, final int maxSteps, final Decomposition<?> decomp) {
    this(absoluteTol, relativeTol, maxSteps, decomp, new OGMatrixAlgebra());
  }

  public ShermanMorrisonVectorRootFinder(final double absoluteTol, final double relativeTol, final int maxSteps, final Decomposition<?> decomp, MatrixAlgebra algebra) {
    super(absoluteTol, relativeTol, maxSteps, new InverseJacobianDirectionFunction(algebra), new InverseJacobianEstimateInitializationFunction(decomp),
        new ShermanMorrisonMatrixUpdateFunction(algebra));
  }

}
