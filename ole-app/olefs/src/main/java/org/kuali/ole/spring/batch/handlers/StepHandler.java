package org.kuali.ole.spring.batch.handlers;

import org.kuali.ole.oleng.batch.profile.model.BatchProfileDataTransformer;
import org.marc4j.marc.Record;

/**
 * Created by pvsubrah on 12/21/15.
 */
public abstract class StepHandler {

    BatchProfileDataTransformer batchProfileDataTransformer;

    public abstract void processSteps(Record marcRecord);

    public abstract Boolean isInterested(String operation);

    public BatchProfileDataTransformer getBatchProfileDataTransformer() {
        return batchProfileDataTransformer;
    }

    public void setBatchProfileDataTransformer(BatchProfileDataTransformer batchProfileDataTransformer) {
        this.batchProfileDataTransformer = batchProfileDataTransformer;
    }
}
