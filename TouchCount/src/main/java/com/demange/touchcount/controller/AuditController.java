/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.controller;

import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.Entry;
import com.demange.touchcount.model.TempAudit;
import com.demange.touchcount.model.TempEntry;
import com.demange.touchcount.service.AuditServiceLayer;
import com.demange.touchcount.service.EntryServiceLayer;
import java.io.OutputStream;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jocel
 */
@Controller
@RequestMapping("/auditController")
public class AuditController {

    AuditServiceLayer service;
    EntryServiceLayer eService;

    @Inject
    public AuditController(AuditServiceLayer service, EntryServiceLayer eService) {
        this.service = service;
        this.eService = eService;
    }

    ///***
    @RequestMapping(value = "/displayNewOrExistingAudit", method = RequestMethod.GET)
    @ResponseBody
    public String displayNewOrExistingAudit() {
        return "<div class=\"choices\">\n"
                + "                                    <div class=\"card container current-code-items\">\n"
                + "                                        <div class=\"card-content\">\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner\">\n"
                + "                                                <p class=\"buttons-row\">\n"
                + "                                                    <button class=\"button color-custom button-raised button-big\" onclick=\"displayNewAuditForm()\">New Audit</button>\n"
                + "                                                </p>\n"
                + "                                                <p class=\"buttons-row\">\n"
                + "                                                    <button class=\"button color-custom button-raised button-big\" onclick=\"listAudits()\">Existing Audit</button>\n"
                + "                                                </p>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>";
    }

    ///***
    @RequestMapping(value = "/displayNewAuditForm", method = RequestMethod.GET)
    @ResponseBody
    public String displayNewAuditForm() {
        return "<div class=\"choices\">\n"
                + "                                    <div class=\"card container user-sign-in\">\n"
                + "                                        <div class=\"card-content\">\n"
                + "                                            <div class=\"row\">\n"
                + "                                                <div class=\"col-100 tablet-100 card-content-inner\">\n"
                + "                                                    <div class=\"list-block inset\">\n"
                + "                                                        <div class=\"item-content\">\n"
                + "                                                            <div class=\"item-inner\">\n"
                + "                                                                <div class=\"item-title floating-label\" align=\"center\">AUDIT DESCRIPTION</div>\n"
                + "                                                                <div class=\"item-input bg-white border-gray\">\n"
                + "                                                                    <input type=\"text\" name=\"location\" id=\"location\">\n"
                + "                                                                </div>\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <p class=\"buttons-row\">\n"
                + "\n"
                + "                                            <button class=\"button color-custom button-big button-raised\" onclick=\"createAudit()\">BEGIN AUDIT</button>\n"
                + "                                        </p>\n"
                + "\n"
                + "                                    </div>\n"
                + "                                </div>";
    }

///***    
    @RequestMapping(value = "/createAudit", method = RequestMethod.POST)
    @ResponseBody
    public Audit createAudit(@RequestBody TempAudit temp) {

        return service.addAudit(temp);

    }

    ///***
    @RequestMapping(value = "/displayAuditList/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String displayAuditList(@PathVariable int userId) {
        List<Audit> auditList = service.getAudtsByUserId(userId);
        String part1 = "<div class=\"choices\">\n"
                + "<div class=\"list-block\">\n"
                + "<ul>\n";
        String part3 = "</ul>\n"
                + "</div>\n"
                + "</div>";
        String part2 = "";

        for (Audit current : auditList) {
            String newLine
                    = "<li class=\"accordion-item  color-custom-accord\">\n"
                    + "<a href=\"#\" class=\"item-content item-link\">\n"
                    + "<div class=\"item-inner\">\n"
                    + "<div class=\"item-title\">" + current.getLocName()
                    + "</div>\n"
                    + "<div></div>\n"
                    + "</div>\n"
                    + "</a>\n"
                    + "<div class=\"accordion-item-content color-custom-accord-expand\">\n"
                    + "<div class=\"content-block\">\n"
                    + "<p>HERE ARE THE AUDIT DETAILS YOU WOULD LIKE TO SEE</p>\n"
                    + "<a class=\"button button-fill button-raised\" onclick=\"displayAuditLinesFromAuditList(" + current.getAuditId()
                    + ")\">continue audit</a>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n";

            part2 += newLine;

        }

        return part1 + part2 + part3;

    }

    @RequestMapping(value = "/deleteEntry/{entryId}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteEntry(@PathVariable int entryId) {
        Entry entry = eService.getEntryById(entryId);
        int auditId = entry.getAudit().getAuditId();

        eService.deleteEntry(entryId);

        String partA = "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "                            <button class=\"button button-raised button-big color-custom\" onclick=\"continueAudit(" + auditId + ")\" value=\"\" name=\"\">Continue Audit</button>\n"
                + "                            <button class=\"button button-raised button-big color-custom\" onclick=\"javascript:deleteAudit(" + auditId + ")\" value=\"\" name=\"\">Delete Audit</button>\n"
                + "                            <button class=\"button button-raised button-big color-custom\" value=\"\" name=\"\"><a href=\"auditController/getExcelFile/" + auditId + "\" class=\"external\">Download Audit</a></button>\n"
                + "                        </p>";

        List<Entry> list = eService.getEntriesByAuditId(auditId);

        String part1 = "<div class=\"list-block\">\n"
                + "<ul>\n";
        String part2 = "";
        String part3 = "</ul>\n"
                + "</div>\n"
                + "</div>";

        for (Entry current : list) {
            String s1
                    = "<li class=\"accordion-item  color-custom-accord\">\n"
                    + "                                            <a href=\"#\" class=\"item-content item-link\">\n"
                    + "<div class=\"item-inner row\">\n"
                    + "<div class=\"item-title tablet-35 col-35\">";

            String s2 = String.valueOf(current.getMapNumber()) + ". " + current.getRoomName() + "</div>\n"
                    + "<div class=\"tablet-55 col-55\" style=\"text-align: center\">" + current.getBaseCode() + current.getExtendedCode() + "</div>\n"
                    + "<div class=\"tablet-10 col-10\" style=\"text-align: right\">" + String.valueOf(current.getQuantity()) + "</div>\n"
                    + " </div>\n"
                    + "</a>\n"
                    + "<div class=\"accordion-item-content color-custom-accord-expand\">\n"
                    + "<div class=\"content-block\">\n"
                    + "<div style=\"text-align: right\"><a href=\"javascript:editEntryLine("
                    + String.valueOf(current.getEntryId()) + ")\">EDIT</a></div>\n"
                    + "<p><b>Room Code:</b> " + current.getRoomType() + "</p>\n"
                    + "<p><b>Ceiling Height:</b> " + current.getCeilingHeight() + "</p>\n"
                    + "<p><b>Fixture Height:</b> " + current.getFixtureHeight() + "</p>\n"
                    + "<p><b>Comments:</b> " + current.getComments() + "</p>\n"
                    + "<div style=\"text-align: right\"><a href=\"javascript:deleteEntryItem("
                    + String.valueOf(current.getEntryId()) + ")\">delete</a></div>\n"
                    + " <p> </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "\n";

            part2 += s1 + s2;
        }

        return partA + part1 + part2 + part3;
    }

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST)
    @ResponseBody
    public Entry addEntry(@RequestBody TempEntry temp) {
        return eService.addEntry(temp);

    }

    @RequestMapping(value = "/displayAuditLines/{auditId}", method = RequestMethod.GET)
    @ResponseBody
    public String displayAuditLines(@PathVariable int auditId) {
        String partA = "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "                            <button class=\"button button-raised button-big color-custom\" onclick=\"continueAudit(" + auditId + ")\" value=\"\" name=\"\">Continue Audit</button>\n"
                + "                            <button class=\"button button-raised button-big color-custom\" onclick=\"javascript:deleteAudit(" + auditId + ")\" value=\"\" name=\"\">Delete Audit</button>\n"
                + "                            <button class=\"button button-raised button-big color-custom\" value=\"\" name=\"\"><a href=\"auditController/getExcelFile/" + auditId + "\" class=\"external\">Download Audit</a></button>\n"
                + "                        </p>";

        List<Entry> list = eService.getEntriesByAuditId(auditId);

        String part1 = "<div class=\"list-block\">\n"
                + "<ul>\n";
        String part2 = "";
        String part3 = "</ul>\n"
                + "</div>\n"
                + "</div>";

        for (Entry current : list) {
            String s1
                    = "<li class=\"accordion-item  color-custom-accord\">\n"
                    + "                                            <a href=\"#\" class=\"item-content item-link\">\n"
                    + "<div class=\"item-inner row\">\n"
                    + "<div class=\"item-title tablet-35 col-35\">";

            String s2 = String.valueOf(current.getMapNumber()) + ". " + current.getRoomName() + "</div>\n"
                    + "<div class=\"tablet-55 col-55\" style=\"text-align: center\">" + current.getBaseCode() + current.getExtendedCode() + "</div>\n"
                    + "<div class=\"tablet-10 col-10\" style=\"text-align: right\">" + String.valueOf(current.getQuantity()) + "</div>\n"
                    + " </div>\n"
                    + "</a>\n"
                    + "<div class=\"accordion-item-content color-custom-accord-expand\">\n"
                    + "<div class=\"content-block\">\n"
                    + "<div style=\"text-align: right\"><a href=\"javascript:editEntryLine("
                    + String.valueOf(current.getEntryId()) + ")\">EDIT</a></div>\n"
                    + "<p><b>Room Code:</b> " + current.getRoomType() + "</p>\n"
                    + "<p><b>Ceiling Height:</b> " + current.getCeilingHeight() + "</p>\n"
                    + "<p><b>Fixture Height:</b> " + current.getFixtureHeight() + "</p>\n"
                    + "<p><b>Comments:</b> " + current.getComments() + "</p>\n"
                    + "<div style=\"text-align: right\"><a href=\"javascript:deleteEntryItem("
                    + String.valueOf(current.getEntryId()) + ")\">delete</a></div>\n"
                    + " <p> </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</li>\n"
                    + "\n";

            part2 += s1 + s2;
        }

        return partA + part1 + part2 + part3;

    }

    @RequestMapping(value = "/getExcelFile/{auditId}", method = RequestMethod.GET)
    @ResponseBody
    public void getExcelFile(@PathVariable int auditId, HttpServletResponse response)
            throws Exception {
        List<Entry> list = eService.getEntriesForExcel(auditId);
        OutputStream out = null;

        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workbook.createSheet("AUDITNAME", 0);

        WritableFont titleText = new WritableFont(WritableFont.COURIER, 12, WritableFont.BOLD);
        titleText.setColour(Colour.BLACK);

        WritableCellFormat titleCell = new WritableCellFormat(titleText);
        titleCell.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM, jxl.format.Colour.BLACK);
        titleCell.setBackground(Colour.PALE_BLUE);
        titleCell.setAlignment(jxl.format.Alignment.CENTRE);
        titleCell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        titleCell.setWrap(true);

        String[] titles = {"map #", "floor #", "location", "epact code",
            "Existing Control (Y/N)", "Audit / Control Code", "Recommendation",
            "audit code Qty", "Notes - Location Related", "ceiling height", "fixture height",
            "ft. candles or range"};

        int col = 0;

        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnView(col, 20);
            sheet.addCell(new Label(col, 0, titles[i], titleCell));
            ++col;
        }

        int row = 1;
        for (Entry current : list) {
            sheet.addCell(new Label(0, row, current.getMapNumber()));
            sheet.addCell(new Label(1, row, current.getFloorNumber()));
            sheet.addCell(new Label(2, row, current.getRoomName()));
            sheet.addCell(new Label(3, row, current.getRoomType()));
            sheet.addCell(new Label(5, row, current.getBaseCode()));
            sheet.addCell(new Label(6, row, current.getExtendedCode()));
            sheet.addCell(new Label(7, row, String.valueOf(current.getQuantity())));
            sheet.addCell(new Label(8, row, current.getComments()));
            sheet.addCell(new Label(9, row, current.getCeilingHeight()));
            sheet.addCell(new Label(10, row, current.getFixtureHeight()));

            ++row;
        }

        response.setContentType("application/vnd.ms-excel");

        response.setHeader("Content-Disposition",
                "attachment; filename=sampleName.xls");

        workbook.write();
        workbook.close();

        if (out != null) {
            out.close();
        }

    }

    @RequestMapping(value = "/deleteAudit/{auditId}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteAudit(@PathVariable int auditId, HttpServletResponse response) {
        
        List<Entry> toDelete = eService.getEntriesByAuditId(auditId);
        for (Entry current : toDelete){
            eService.deleteEntry(current.getEntryId());
        }
        service.deleteAudit(auditId);
    }

}
