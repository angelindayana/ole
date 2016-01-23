package org.kuali.ole.dsng.rest.handler.holdings;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.DocumentUniqueIDPrefix;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.HoldingsRecord;
import org.kuali.ole.dsng.rest.Exchange;
import org.kuali.ole.dsng.rest.handler.Handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by pvsubrah on 12/23/15.
 */
public class UpdateHoldingsHandler extends Handler {
    protected List<HoldingsHandler> holdingMetaDataHandlers;

    public List<HoldingsHandler> getHoldingMetaDataHandlers() {
        if (null == holdingMetaDataHandlers) {
            holdingMetaDataHandlers = new ArrayList<HoldingsHandler>();
            holdingMetaDataHandlers.add(new HoldingsLocationHandler());
            holdingMetaDataHandlers.add(new CallNumberHandler());
            holdingMetaDataHandlers.add(new CallNumberTypeHandler());
            holdingMetaDataHandlers.add(new CallNumberPrefixHandler());
            holdingMetaDataHandlers.add(new CopyNumberHandler());
        }
        return holdingMetaDataHandlers;
    }

    public void setHoldingMetaDataHandlers(List<HoldingsHandler> holdingMetaDataHandlers) {
        this.holdingMetaDataHandlers = holdingMetaDataHandlers;
    }

    @Override
    public Boolean isInterested(String operation) {
        List<String> operationsList = getListFromJSONArray(operation);
        for (Iterator iterator = operationsList.iterator(); iterator.hasNext(); ) {
            String op = (String) iterator.next();
            if (op.equals("122") || op.equals("222")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void process(JSONObject requestJsonObject, Exchange exchange) {
        List<HoldingsRecord> holdingsRecords = (List<HoldingsRecord>) exchange.get(OleNGConstants.HOLDINGS);
        try {

            String updatedBy = requestJsonObject.getString(OleNGConstants.UPDATED_BY);
            String updatedDateString = (String) requestJsonObject.get(OleNGConstants.UPDATED_DATE);
            Timestamp updatedDate = getDateTimeStamp(updatedDateString);

            JSONObject holdingJsonObject = getHoldingsJsonObject(requestJsonObject);

            for (Iterator<HoldingsRecord> iterator = holdingsRecords.iterator(); iterator.hasNext(); ) {
                HoldingsRecord holdingsRecord = iterator.next();
                holdingsRecord.setUpdatedDate(updatedDate);
                holdingsRecord.setUpdatedBy(updatedBy);
                exchange.add(OleNGConstants.HOLDINGS_RECORD,holdingsRecord);
                processOverlay(exchange, holdingsRecord, holdingJsonObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        exchange.remove(OleNGConstants.HOLDINGS_RECORD);
        getHoldingDAO().saveAll(holdingsRecords);
    }

    public JSONObject getHoldingsJsonObject(JSONObject requestJsonObject) throws JSONException {
        return requestJsonObject.getJSONObject(OleNGConstants.HOLDINGS);
    }

    private HoldingsRecord processOverlay(Exchange exchange, HoldingsRecord holdingsRecord, JSONObject holdingJsonObject) throws JSONException, IOException {

        JSONObject dataMapping = holdingJsonObject.getJSONObject(OleNGConstants.DATAMAPPING);
        Map<String, Object> dataMappingsMap = new ObjectMapper().readValue(dataMapping.toString(), new TypeReference<Map<String, Object>>() {});
        for (Iterator iterator3 = dataMappingsMap.keySet().iterator(); iterator3.hasNext(); ) {
            String key1 = (String) iterator3.next();
            for (Iterator<HoldingsHandler> iterator4 = getHoldingMetaDataHandlers().iterator(); iterator4.hasNext(); ) {
                HoldingsHandler holdingsMetaDataHandlelr1 = iterator4.next();
                if (holdingsMetaDataHandlelr1.isInterested(key1)) {
                    holdingsMetaDataHandlelr1.setBusinessObjectService(getBusinessObjectService());
                    holdingsMetaDataHandlelr1.processDataMappings(dataMapping, exchange);
                }
            }
        }


        holdingsRecord.setUniqueIdPrefix(DocumentUniqueIDPrefix.PREFIX_WORK_HOLDINGS_OLEML);
        exchange.remove(OleNGConstants.MATCHED_HOLDINGS);
        return  holdingsRecord;
    }

    public void setMatchFound(Exchange exchange) {
        exchange.add(OleNGConstants.HOLDINGS_MATCH_FOUND, true);
    }
}
