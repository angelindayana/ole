package org.kuali.ole;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.kuali.rice.core.api.config.property.ConfigContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by angelind on 2/3/2016.
 */
public class OleNGCamelContext {
    public static OleNGCamelContext oleNGCamelContext;
    private CamelContext context;
    private String startingURI;
    Map<String,Processor> processors;

    private OleNGCamelContext() {
        context = new DefaultCamelContext();
        try {
            context.start();
        } catch (Exception e) {
            System.out.println("Camel Context not initialized");
            e.printStackTrace();
        }
    }

    public static OleNGCamelContext getInstance() {
        if (null == oleNGCamelContext) {
            oleNGCamelContext = new OleNGCamelContext();
        }
        return oleNGCamelContext;
    }

    public CamelContext getContext() {
        return context;
    }

    public void buildInitialRoute(String filePath, String fileName, final String consumer, final String processorKey) {
        String projectHome = ConfigContext.getCurrentContextConfig().getProperty("project.home");
        String fileEndPointPath = (null == filePath ? projectHome + "/reports" : filePath) + "?fileName=" + fileName + "&fileExist=Append";
        final String endPoint = "file:" + fileEndPointPath;
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(consumer)
                            .process(getProcessors().get(processorKey))
                            .to(endPoint);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Processor> getProcessors() {
        if(null == processors) {
            processors = new HashMap<>();
        }
        return processors;
    }

    public void addProcessor(String processorKey, Processor processor) {
        getProcessors().put(processorKey, processor);
    }
    public String getStartingURI() {
        if (null == startingURI) {
            startingURI = "seda:messages";
        }
        return startingURI;
    }

    public void setStartingURI(String startingURI) {
        this.startingURI = startingURI;
    }
}
