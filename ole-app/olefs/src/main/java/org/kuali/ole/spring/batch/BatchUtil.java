package org.kuali.ole.spring.batch;

import org.kuali.ole.utility.OleDsNgRestClient;
import org.kuali.ole.utility.OleHttpRestClient;

/**
 * Created by SheikS on 12/8/2015.
 */
public class BatchUtil {
    private OleDsNgRestClient oleDsNgRestClient;

    public OleDsNgRestClient getOleDsNgRestClient() {
        if(null == oleDsNgRestClient) {
            oleDsNgRestClient = new OleDsNgRestClient();
        }
        return oleDsNgRestClient;
    }

    public void setOleDsNgRestClient(OleDsNgRestClient oleDsNgRestClient) {
        this.oleDsNgRestClient = oleDsNgRestClient;
    }
}