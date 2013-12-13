/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.google.common.base.Preconditions;
import com.opengamma.component.tool.AbstractTool;
import com.opengamma.integration.tool.DataTrackingToolContext;

/**
 * 
 */
public class GoldenCopyCreationTool extends AbstractTool<DataTrackingToolContext> {

  
  public static void main(String[] args) {
    new GoldenCopyCreationTool().initAndRun(args, DataTrackingToolContext.class);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {
    
    CommandLine commandLine = getCommandLine();
    
    String regressionDirectory = commandLine.getOptionValue("db-dump-output-dir");

    GoldenCopyCreator goldenCopyCreator = new GoldenCopyCreator(getToolContext());
    
    String[] viewSnapshotPairs = commandLine.getArgs();
    Preconditions.checkArgument(viewSnapshotPairs.length % 2 == 0, "Should be an even number of view/snapshot pairs. Found %s", Arrays.toString(viewSnapshotPairs));
    
    for (int i = 0; i < viewSnapshotPairs.length; i += 2) {
      String viewName = viewSnapshotPairs[i];
      String snapshotName = viewSnapshotPairs[i + 1];
      GoldenCopy goldenCopy = goldenCopyCreator.run(viewName, snapshotName, "Base");
      new GoldenCopyPersistenceHelper(new File(regressionDirectory)).save(goldenCopy);
    }
    
    DataTrackingToolContext tc = getToolContext();
    
    try (DatabaseDumpWriter writer = createDumpWriter()) {
      GoldenCopyDumpCreator goldenCopyDumpCreator = new GoldenCopyDumpCreator(writer, 
          tc.getSecurityMaster(),
          tc.getPositionMaster(),
          tc.getPortfolioMaster(),
          tc.getConfigMaster(),
          tc.getHistoricalTimeSeriesMaster(),
          tc.getHolidayMaster(),
          tc.getExchangeMaster(),
          tc.getMarketDataSnapshotMaster(),
          tc.getOrganizationMaster());
      
      goldenCopyDumpCreator.execute();
    
    }
  }
  
  private DatabaseDumpWriter createDumpWriter() throws IOException {
    CommandLine commandLine = getCommandLine();
    String outputDirRootStr = commandLine.getOptionValue("db-dump-output-dir");
    
    File dumpDir = Paths.get(outputDirRootStr, GoldenCopyDumpCreator.DB_DUMP_SUBDIR).toFile();
    
    if (commandLine.hasOption("zipfile-name")) {
      String zipfileName = commandLine.getOptionValue("zipfile-name");
      return DatabaseDumpWriter.createZipWriter(dumpDir, zipfileName);
    } else {
      return DatabaseDumpWriter.createFileWriter(dumpDir);
    }
  }

  @Override
  protected Options createOptions(boolean mandatoryConfig) {
    Options options = super.createOptions(mandatoryConfig);
    options.addOption(createDbDumpOutputDirectory());
    options.addOption(createZipfileNameOption());
    return options;
  }


  @SuppressWarnings("static-access")
  private static Option createDbDumpOutputDirectory() {
    return OptionBuilder.isRequired(true)
        .hasArg(true)
        .withArgName("outputdir")
        .withDescription("Where to write the golden copy(ies) and the corresponding dump.")
        .withLongOpt("db-dump-output-dir")
        .create("o");
  }
  
  @SuppressWarnings("static-access")
  private static Option createZipfileNameOption() {
    return OptionBuilder.isRequired(false)
        .hasArg(true)
        .withArgName("zipfile")
        .withDescription("The zip file to write the dump to")
        .withLongOpt("zipfile-name")
        .create("z");
  }

}
