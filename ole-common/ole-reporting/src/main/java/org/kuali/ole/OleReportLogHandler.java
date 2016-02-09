package org.kuali.ole;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

/**
 * Created by angelind on 2/3/2016.
 */
public class OleReportLogHandler {

    static OleReportLogHandler oleReportLogHandler;

    protected OleReportLogHandler() {
    }

    public static OleReportLogHandler getInstance() {
        if(null == oleReportLogHandler){
            oleReportLogHandler = new OleReportLogHandler();
        }
        return oleReportLogHandler;
    }

    public void logMessage(Object message,String consumer) throws Exception {
        ProducerTemplate sedaProducer;
        CamelContext context = OleNGCamelContext.getInstance().getContext();
        sedaProducer = context.createProducerTemplate();
        sedaProducer.sendBody(consumer, message);
    }

    public void initializeEndPoint(String filePath, String fileName, String consumer, String processorKey) {
        OleNGCamelContext.getInstance().buildInitialRoute(filePath,fileName, consumer, processorKey);
    }

    public void addProcessor(String processorKey, Processor processor){
        OleNGCamelContext.getInstance().addProcessor(processorKey, processor);
    }

}
