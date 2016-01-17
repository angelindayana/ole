package org.kuali.ole.dsng.rest.handler.items;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.ItemRecord;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.OLEItemDonorRecord;
import org.kuali.ole.dsng.rest.Exchange;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SheikS on 12/20/2015.
 */
public class DonorPublicDisplayHandler extends ItemHandler {
    private final String TYPE = "Donor Public Display";

    @Override
    public Boolean isInterested(String operation) {
        return operation.equals(TYPE);
    }

    @Override
    public void process(JSONObject requestJsonObject, Exchange exchange) {
        ItemRecord itemRecord = (ItemRecord) exchange.get(OleNGConstants.ITEM_RECORD);
        String donorPublicDisplay = getStringValueFromJsonObject(requestJsonObject,TYPE);
        List<OLEItemDonorRecord> donorList = itemRecord.getDonorList();
        if(CollectionUtils.isNotEmpty(donorList)) {
            for (Iterator<OLEItemDonorRecord> iterator = donorList.iterator(); iterator.hasNext(); ) {
                OLEItemDonorRecord oleItemDonorRecord = iterator.next();
                if(StringUtils.equals(oleItemDonorRecord.getDonorPublicDisplay(),donorPublicDisplay)) {
                    exchange.add(OleNGConstants.MATCHED_ITEM, itemRecord);
                    break;
                }
            }
        }
    }

    @Override
    public void processDataMappings(JSONObject requestJsonObject, Exchange exchange) {
        String donorPublicDisplay = getStringValueFromJsonObject(requestJsonObject, TYPE);
        ItemRecord itemRecord = (ItemRecord) exchange.get(OleNGConstants.ITEM_RECORD);
        List<OLEItemDonorRecord> donorList = itemRecord.getDonorList();
        if(CollectionUtils.isNotEmpty(donorList)) {
            for (Iterator<OLEItemDonorRecord> iterator = donorList.iterator(); iterator.hasNext(); ) {
                OLEItemDonorRecord oleItemDonorRecord = iterator.next();
                oleItemDonorRecord.setDonorPublicDisplay(donorPublicDisplay);
            }
        } else {
            donorList = new ArrayList<OLEItemDonorRecord>();
            OLEItemDonorRecord oleItemDonorRecord = new OLEItemDonorRecord();
            oleItemDonorRecord.setDonorPublicDisplay(donorPublicDisplay);
            oleItemDonorRecord.setItemId(itemRecord.getItemId());
            itemRecord.setDonorList(donorList);
        }
        exchange.add(OleNGConstants.ITEM_RECORD, itemRecord);
    }
}