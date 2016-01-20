package org.kuali.ole.dsng.rest.handler.eholdings;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.HoldingsRecord;
import org.kuali.ole.dsng.rest.Exchange;
import org.kuali.ole.dsng.rest.handler.holdings.HoldingsHandler;

import java.util.List;

/**
 * Created by SheikS on 12/31/2015.
 */
public class EResourceIdHandler extends HoldingsHandler {

    private final String TYPE = "EResource Id";

    @Override
    public Boolean isInterested(String operation) {
        return operation.equals(TYPE);
    }

    @Override
    public void process(JSONObject requestJsonObject, Exchange exchange) {
        HoldingsRecord holdingRecord = (HoldingsRecord) exchange.get(OleNGConstants.HOLDINGS_RECORD);
        String eresourceId = getStringValueFromJsonObject(requestJsonObject, TYPE);
        if (StringUtils.equals(holdingRecord.geteResourceId(), eresourceId)) {
            exchange.add(OleNGConstants.MATCHED_HOLDINGS, holdingRecord);
        }
    }

    @Override
    public void processDataMappings(JSONObject requestJsonObject, Exchange exchange) {
        JSONArray jsonArrayeFromJsonObject = getJSONArrayeFromJsonObject(requestJsonObject, TYPE);
        List<String> listFromJSONArray = getListFromJSONArray(jsonArrayeFromJsonObject.toString());
        if(CollectionUtils.isNotEmpty(listFromJSONArray)) {
            String eresourceId = listFromJSONArray.get(0);
            HoldingsRecord holdingRecord = (HoldingsRecord) exchange.get(OleNGConstants.HOLDINGS_RECORD);
            holdingRecord.seteResourceId(eresourceId);
            exchange.add(OleNGConstants.HOLDINGS_RECORD, holdingRecord);
        }
    }
}