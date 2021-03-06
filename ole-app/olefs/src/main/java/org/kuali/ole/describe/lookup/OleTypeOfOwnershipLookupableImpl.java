package org.kuali.ole.describe.lookup;

import org.kuali.ole.OLEConstants;
import org.kuali.ole.describe.bo.OleTypeOfOwnership;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.LookupForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: asham
 * Date: 7/16/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class OleTypeOfOwnershipLookupableImpl extends LookupableImpl {

    @Override
    protected List<?> getSearchResults(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {
        List<?> searchResults;
        List<OleTypeOfOwnership> finalSearchResult = new ArrayList<OleTypeOfOwnership>();
        List<OleTypeOfOwnership> oleTypeOfOwnership = new ArrayList<OleTypeOfOwnership>();


        oleTypeOfOwnership = (List<OleTypeOfOwnership>) super.getSearchResults(form, searchCriteria, unbounded);
        finalSearchResult.addAll(oleTypeOfOwnership);

        searchResults = finalSearchResult;

        if (searchResults.size() == 0) {
            GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, OLEConstants.NO_RECORD_FOUND);
        }
        sortSearchResults(form, searchResults);
        return searchResults;
    }
}
