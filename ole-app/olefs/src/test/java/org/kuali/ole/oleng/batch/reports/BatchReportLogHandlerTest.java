package org.kuali.ole.oleng.batch.reports;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.ole.OLETestCaseBase;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.common.response.BibResponse;
import org.kuali.ole.docstore.common.response.HoldingsResponse;
import org.kuali.ole.docstore.common.response.ItemResponse;
import org.kuali.ole.docstore.common.response.OleNGBibImportResponse;
import org.kuali.rice.core.api.config.property.ConfigContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by angelind on 1/27/16.
 */
public class BatchReportLogHandlerTest extends OLETestCaseBase {

    @Test
    public void injectIntermediateBatchPoint() throws Exception {
        OleNGBibImportResponse oleNGBibImportResponse = buildOleNGBibImportResponse();
        BatchReportLogHandler batchReportLogHandler = BatchReportLogHandler.getInstance();
        String timeStamp = OleNGConstants.DATE_FORMAT.format(new Date());
        batchReportLogHandler.initializeEndPoint(timeStamp,null,"batch-report",".txt");
        batchReportLogHandler.logMessage(timeStamp, oleNGBibImportResponse);
        Thread.sleep(5000);
        String fileContent = FileUtils.readFileToString(new File(ConfigContext.getCurrentContextConfig().getProperty("project.home") + "/reports/batch-report"+"-${date:now:yyyyMMddHHmmssSSS}"+".txt"));
        assertNotNull(fileContent);
        assertTrue(StringUtils.isNotBlank(fileContent));
        System.out.println(fileContent);
    }

    @Test
    public void testBatchContentReportLogHandler() throws Exception {
        String message = "02705ngm a2200529 a 4500001001200000005001700012006001900029007001000048007001500058008004100073028002300114040002600137043001200163050002300175082001500198245021500213246003700428260005900465300005900524538013900583538003600722500002900758506005100787511002900838508012600867500005700993521001501050588010401065520032201169651004701491650005101538651006601589650005001655650003701705650003801742655003001780655002901810655002601839700002801865700002901893700003201922710003201954710002001986856007402006856008102080990001402161\u001Edoc00000268\u001E20141104134036.0\u001Em    eo  c        \u001Evz czazuu\u001Ecr cna---auuuu\u001E141028p20142001pau055        o   vleng d\u001E41\u001Fabf-aban\u001FbDocuseek2\u001E  \u001FaCtWfDGI\u001Fbeng\u001FcCtWfDGI\u001E  \u001Fan-us---\u001E14\u001FaJV6483\u001Fb.A22 2014b\u001E04\u001Fa325.73\u001F223\u001E00\u001FaAbandoned\u001Fh[electronic resource] :\u001Fbthe betrayal of America's immigrants /\u001FcBullfrog Films presents ; produced by David Belle ; directed by David Belle and Nicholas Wrathall ; a Crowing Rooster Arts production.\u001E30\u001FaBetrayal of America's immigrants\u001E  \u001Fa[Oley, PA] :\u001Fb[Distributed by] Bullfrog Films,\u001Fc[2014]\u001E  \u001Fa1 streaming video file (55 min.) :\u001Fbdigital, sd., col.\u001E  \u001FaSystem requirements: Firefox 4 and up; Safari 5.0 and up; Chrome version 21 and up; Internet Explorer 8 and up; Flash or HTML5 player.\u001E  \u001FaMode of access: World Wide Web.\u001E  \u001FaTitle from title frames.\u001E  \u001FaAccess restricted to subscribing institutions.\u001E0 \u001FaNarrator: Gilbert Giles.\u001E  \u001FaEdited by Robert Edwards ; camera, David Belle ... [et al.] ; writer, Gabriel Morgan ; music, Stuffy Shmitt ... [et al.].\u001E  \u001FaOriginally produced by: Crowing Rooster Arts, c2001.\u001E2 \u001Fa10 and up.\u001E  \u001FaDescription based on online resource; title from title frames (Docuseek2, viewed October 28, 2014).\u001E  \u001FaIllustrates the impact of 1996 immigration laws. Through intimate portraits of legal residents and asylum seekers, and shocking footage of their treatment behind bars, this video reveals the truths behind the current anti-immigrant sentiment and the multimillion dollar prison industry's profit from this legislation.\u001E 0\u001FaUnited States\u001FxEmigration and immigration.\u001E 0\u001FaEmigration and immigration law\u001FzUnited States.\u001E 0\u001FaUnited States\u001FxEmigration and immigration\u001FxGovernment policy.\u001E 0\u001FaImmigrants\u001FxGovernment policy\u001FzUnited States.\u001E 0\u001FaAsylum, Right of\u001FzUnited States.\u001E 0\u001FaPrison industries\u001FzUnited States.\u001E 7\u001FaDocumentary films.\u001F2lcgft\u001E 7\u001FaNonfiction films.\u001F2lcgft\u001E 7\u001FaFeature films.\u001F2lcgft\u001E1 \u001FaBelle, David.\u001F4pro\u001F4drt\u001E1 \u001FaWrathall, Nicholas.\u001F4drt\u001E1 \u001FaGiles, Gilbert,\u001Fd1963-\u001F4nrt\u001E2 \u001FaCrowing Rooster Arts (Firm)\u001E2 \u001FaBullfrog Films.\u001E40\u001Fuhttp://docuseek2.com/v/a/s7j\u001FyView film\u001FzPublic Note 1\u001F3Public Note 2\u001E40\u001Fuhttp://docuseek2.com/v/a/s7j\u001FyView film Part 2\u001FzPublic Note 1\u001F3Public Note 2\u001E  \u001Favidstream\u001E\u001D";
        BatchContentReportLogHandler batchContentReportLogHandler = BatchContentReportLogHandler.getInstance();
        String timeStamp = OleNGConstants.DATE_FORMAT.format(new Date());
        batchContentReportLogHandler.initializeEndPoint(timeStamp, null,"batch-content-report",".mrc");
        batchContentReportLogHandler.logMessage(timeStamp, message);
        Thread.sleep(5000);
        String fileContent = FileUtils.readFileToString(new File(ConfigContext.getCurrentContextConfig().getProperty("project.home") + "/reports/batch-report"+"-${date:now:yyyyMMddHHmmssSSS}"+".txt"));
        assertNotNull(fileContent);
        assertTrue(StringUtils.isNotBlank(fileContent));
        System.out.println(fileContent);
    }

    @Test
    public void testTwoLogHandlers() throws Exception {
        OleNGBibImportResponse oleNGBibImportResponse = buildOleNGBibImportResponse();
        BatchReportLogHandler batchReportLogHandler = BatchReportLogHandler.getInstance();
        String timeStamp1 = OleNGConstants.DATE_FORMAT.format(new Date());
        batchReportLogHandler.initializeEndPoint(timeStamp1,null,"batch-report",".txt");
        batchReportLogHandler.logMessage(timeStamp1,oleNGBibImportResponse);
        Thread.sleep(5000);

        String message = "02705ngm a2200529 a 4500001001200000005001700012006001900029007001000048007001500058008004100073028002300114040002600137043001200163050002300175082001500198245021500213246003700428260005900465300005900524538013900583538003600722500002900758506005100787511002900838508012600867500005700993521001501050588010401065520032201169651004701491650005101538651006601589650005001655650003701705650003801742655003001780655002901810655002601839700002801865700002901893700003201922710003201954710002001986856007402006856008102080990001402161\u001Edoc00000268\u001E20141104134036.0\u001Em    eo  c        \u001Evz czazuu\u001Ecr cna---auuuu\u001E141028p20142001pau055        o   vleng d\u001E41\u001Fabf-aban\u001FbDocuseek2\u001E  \u001FaCtWfDGI\u001Fbeng\u001FcCtWfDGI\u001E  \u001Fan-us---\u001E14\u001FaJV6483\u001Fb.A22 2014b\u001E04\u001Fa325.73\u001F223\u001E00\u001FaAbandoned\u001Fh[electronic resource] :\u001Fbthe betrayal of America's immigrants /\u001FcBullfrog Films presents ; produced by David Belle ; directed by David Belle and Nicholas Wrathall ; a Crowing Rooster Arts production.\u001E30\u001FaBetrayal of America's immigrants\u001E  \u001Fa[Oley, PA] :\u001Fb[Distributed by] Bullfrog Films,\u001Fc[2014]\u001E  \u001Fa1 streaming video file (55 min.) :\u001Fbdigital, sd., col.\u001E  \u001FaSystem requirements: Firefox 4 and up; Safari 5.0 and up; Chrome version 21 and up; Internet Explorer 8 and up; Flash or HTML5 player.\u001E  \u001FaMode of access: World Wide Web.\u001E  \u001FaTitle from title frames.\u001E  \u001FaAccess restricted to subscribing institutions.\u001E0 \u001FaNarrator: Gilbert Giles.\u001E  \u001FaEdited by Robert Edwards ; camera, David Belle ... [et al.] ; writer, Gabriel Morgan ; music, Stuffy Shmitt ... [et al.].\u001E  \u001FaOriginally produced by: Crowing Rooster Arts, c2001.\u001E2 \u001Fa10 and up.\u001E  \u001FaDescription based on online resource; title from title frames (Docuseek2, viewed October 28, 2014).\u001E  \u001FaIllustrates the impact of 1996 immigration laws. Through intimate portraits of legal residents and asylum seekers, and shocking footage of their treatment behind bars, this video reveals the truths behind the current anti-immigrant sentiment and the multimillion dollar prison industry's profit from this legislation.\u001E 0\u001FaUnited States\u001FxEmigration and immigration.\u001E 0\u001FaEmigration and immigration law\u001FzUnited States.\u001E 0\u001FaUnited States\u001FxEmigration and immigration\u001FxGovernment policy.\u001E 0\u001FaImmigrants\u001FxGovernment policy\u001FzUnited States.\u001E 0\u001FaAsylum, Right of\u001FzUnited States.\u001E 0\u001FaPrison industries\u001FzUnited States.\u001E 7\u001FaDocumentary films.\u001F2lcgft\u001E 7\u001FaNonfiction films.\u001F2lcgft\u001E 7\u001FaFeature films.\u001F2lcgft\u001E1 \u001FaBelle, David.\u001F4pro\u001F4drt\u001E1 \u001FaWrathall, Nicholas.\u001F4drt\u001E1 \u001FaGiles, Gilbert,\u001Fd1963-\u001F4nrt\u001E2 \u001FaCrowing Rooster Arts (Firm)\u001E2 \u001FaBullfrog Films.\u001E40\u001Fuhttp://docuseek2.com/v/a/s7j\u001FyView film\u001FzPublic Note 1\u001F3Public Note 2\u001E40\u001Fuhttp://docuseek2.com/v/a/s7j\u001FyView film Part 2\u001FzPublic Note 1\u001F3Public Note 2\u001E  \u001Favidstream\u001E\u001D";
        BatchContentReportLogHandler batchContentReportLogHandler = BatchContentReportLogHandler.getInstance();
        String timeStamp2 = OleNGConstants.DATE_FORMAT.format(new Date());
        batchContentReportLogHandler.initializeEndPoint(timeStamp2, null,"batch-content-report",".mrc");
        batchContentReportLogHandler.logMessage(timeStamp2, message);
        Thread.sleep(5000);
    }

    private OleNGBibImportResponse buildOleNGBibImportResponse() {
        OleNGBibImportResponse oleNGBibImportResponse = new OleNGBibImportResponse();
        List<ItemResponse> itemResponses = new ArrayList<>();
        List<HoldingsResponse> holdingsResponses = new ArrayList<>();
        List<BibResponse> bibResponses = new ArrayList<>();
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId("wio-300001");
        itemResponse.setOperation("Created");
        itemResponses.add(itemResponse);
        HoldingsResponse holdingsResponse = new HoldingsResponse();
        holdingsResponse.setHoldingsId("who-200001");
        holdingsResponse.setOperation("Created");
        holdingsResponse.setItemResponses(itemResponses);
        holdingsResponses.add(holdingsResponse);
        BibResponse bibResponse = new BibResponse();
        bibResponse.setBibId("wbm-100001");
        bibResponse.setOperation("Created");
        bibResponse.setHoldingsResponses(holdingsResponses);
        bibResponses.add(bibResponse);
        oleNGBibImportResponse.setBibResponses(bibResponses);
        return oleNGBibImportResponse;
    }

}
