package org.kuali.ole.oleng.batch.reports;

import org.apache.camel.Processor;
import org.kuali.ole.OleReportLogHandler;
import org.kuali.ole.constants.OleNGConstants;

/**
 * Created by angelind on 2/3/2016.
 */
public class BatchContentReportLogHandler {
    public static BatchContentReportLogHandler batchContentReportLogHandler;

    public static final String CONSUMER = "seda:batchContentMessage";
    public static final String PROCESSOR = "batchContentReportProcessor";

    protected BatchContentReportLogHandler() {

    }

    private static void injectProcessor(Processor batchReportProcessor) {
        OleReportLogHandler.getInstance().addProcessor(PROCESSOR, batchReportProcessor);
    }

    public static BatchContentReportLogHandler getInstance() {
        if(null == batchContentReportLogHandler) {
            batchContentReportLogHandler = new BatchContentReportLogHandler();
            injectProcessor(new BatchContentReportProcessor());

        }
        return batchContentReportLogHandler;
    }

    public void logMessage(String timestamp, Object message) throws Exception {
        String consumer = CONSUMER + timestamp;
        OleReportLogHandler.getInstance().logMessage(message,consumer);
    }

    public void initializeEndPoint(String timestamp, String path, String fileName, String fileExtension) {
        String consumer = CONSUMER + timestamp;
        String fileNameWithExtension = fileName + OleNGConstants.TIMESTAMP_FOR_CAMEL + fileExtension;
        OleReportLogHandler.getInstance().initializeEndPoint(path, fileNameWithExtension, consumer, PROCESSOR);
    }

}
