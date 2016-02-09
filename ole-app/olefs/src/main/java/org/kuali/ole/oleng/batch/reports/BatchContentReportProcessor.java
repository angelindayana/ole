package org.kuali.ole.oleng.batch.reports;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by angelind on 2/3/2016.
 */
public class BatchContentReportProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String message = (String) exchange.getIn().getBody();
        exchange.getOut().setBody(message);
    }
}
