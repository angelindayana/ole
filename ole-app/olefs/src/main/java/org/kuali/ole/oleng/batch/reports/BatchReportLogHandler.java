package org.kuali.ole.oleng.batch.reports;

import org.apache.camel.Processor;
import org.kuali.ole.OleReportLogHandler;
import org.kuali.ole.constants.OleNGConstants;

/**
 * Created by pvsubrah on 1/27/16.
 */
public class BatchReportLogHandler  {

    public static BatchReportLogHandler batchReportLogHandler;
    public static final String CONSUMER = "seda:batchLogMessage";
    public static final String PROCESSOR = "batchReportProcessor";

    protected BatchReportLogHandler() {

    }

    private static void injectProcessor(Processor batchReportProcessor) {
        OleReportLogHandler.getInstance().addProcessor(PROCESSOR, batchReportProcessor);
    }

    public static BatchReportLogHandler getInstance() {
        if(null == batchReportLogHandler) {
            batchReportLogHandler = new BatchReportLogHandler();
            injectProcessor(new BatchReportProcessor());
        }
        return batchReportLogHandler;
    }

    public void logMessage(String timeStamp, Object message) throws Exception {
        String consumer = CONSUMER + timeStamp;
        OleReportLogHandler.getInstance().logMessage(message,consumer);
    }

    public void initializeEndPoint(String timeStamp, String path, String fileName, String fileExtension) {
        String consumer = CONSUMER + timeStamp;
        String fileNameWithExtension = fileName + OleNGConstants.TIMESTAMP_FOR_CAMEL + fileExtension;
        OleReportLogHandler.getInstance().initializeEndPoint(path, fileNameWithExtension,consumer, PROCESSOR);
    }

}
