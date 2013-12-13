/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.opengamma.financial.tool.ToolContext;

/**
 * 
 */
public abstract class AbstractRegressionTest {
  
  
  private static final double s_defaultAcceptableDelta = 0.0000001;
  
  private RegressionTestToolContextManager _contextManager;
  private GoldenCopyPersistenceHelper _goldenCopyPersistenceHelper;
  
  
  /**
   * @param regressionRoot the root for this set of tests (i.e. the directory
   * containing the dbdump and golden_copy folders)
   * @param dumpFile path to the dump. Either a zipfile or the root directory of
   * a directory-based dump.
   */
  public AbstractRegressionTest(File regressionRoot, File dumpFile) {
    _contextManager = new RegressionTestToolContextManager(dumpFile);
    _goldenCopyPersistenceHelper = new GoldenCopyPersistenceHelper(regressionRoot);
  }

  
  
  @BeforeTest
  public void initContext() {
    _contextManager.init();
  }
  
  @AfterTest
  public void closeContext() {
    _contextManager.close();
  }
  
  
  /**
   * Executes viewName against snapshotName in the running engine context.
   * @param viewName name of the view to run
   * @param snapshotName name of the snapshot to run
   */
  protected final void runTestForView(String viewName, String snapshotName) {
    
    ToolContext toolContext = _contextManager.getToolContext();
    GoldenCopy original = _goldenCopyPersistenceHelper.load(viewName, snapshotName);
    
    ViewRunner viewRunner = new ViewRunner(toolContext.getConfigMaster(),
        toolContext.getViewProcessor(),
        toolContext.getPositionSource(),
        toolContext.getSecuritySource(),
        toolContext.getMarketDataSnapshotMaster());

    CalculationResults thisRun = viewRunner.run("Test", viewName, snapshotName, original.getValuationTime());
    
    CalculationDifference result = CalculationDifference.between(original.getCalculationResults(), thisRun, getAcceptableDelta());
    
    System.out.println("Equal: " + result.getEqualResultCount());
    System.out.println("Different: " + result.getDifferent().size());
    System.out.println("Only base: " + result.getOnlyBase().size());
    System.out.println("Only test: " + result.getOnlyTest().size());
    
    assertTrue("Found results only in base", result.getOnlyBase().isEmpty());
    assertTrue("Found results only in base", result.getOnlyTest().isEmpty());
    assertTrue("Found results only in base", result.getDifferent().isEmpty());
    
  }
  
  
  //---------------------------------------------------------------------------------------------------------
  //default test settings for overriding
  //---------------------------------------------------------------------------------------------------------
  
  /**
   * The smallest acceptable delta when comparing outputs.
   * @return a double
   */
  protected double getAcceptableDelta() {
    return s_defaultAcceptableDelta;
  }
  
}
