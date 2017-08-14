package com.longyg.backend.adaptation.topology;

import com.longyg.backend.TemplateRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Collections;
import java.util.List;

public class ObjectModelSheetGenerator {
    private HSSFSheet objectModelSheet;
    private TopologyRepository topologyRepository = TopologyRepository.getInstance();
    private int row = TemplateRepository.getObjectModelTplDef().getStartRow();

    public ObjectModelSheetGenerator(HSSFSheet objectModelSheet) {
        this.objectModelSheet = objectModelSheet;
    }

    public void generate() throws Exception {
        topologyRepository.buildTopology();

        List<PmbObject> rootObjects = topologyRepository.getRootObjects();

        for (PmbObject parentObj : rootObjects) {
            HSSFRow hssfRow = objectModelSheet.getRow(row);
            setCellValue(hssfRow, 0,"|- " + parentObj.getName());
            setObjectAttributes(hssfRow, parentObj);

            List<PmbObject> childObjs = parentObj.getChildObjects();
            Collections.sort(childObjs);
            for (PmbObject childObj : childObjs) {
                int level = 1;
                row++;
                printChildTopology(childObj, level);
            }
        }
    }

    private void printChildTopology(PmbObject rootObj, int level) {
        HSSFRow hssfRow = objectModelSheet.getRow(row);
        for (int i = 0; i < level; i++)
        {
            setCellValue(hssfRow, i, "|");
        }

        setCellValue(hssfRow, level,"|- " + rootObj.getName());
        setObjectAttributes(hssfRow, rootObj);

        List<PmbObject> childObjs = rootObj.getChildObjects();
        Collections.sort(childObjs);
        for (PmbObject childObj : childObjs) {
            row++;
            printChildTopology(childObj, level + 1);
        }
    }

    private void setObjectAttributes(HSSFRow hssfRow, PmbObject pmbObject) {
        setCellValue(hssfRow, 8, pmbObject.isAlarmingObject() ? "A" : "");
        setCellValue(hssfRow, 9, pmbObject.isMeasuredObject() ? "M" : "");
        setCellValue(hssfRow, 10, pmbObject.isCmObject() ? "C" : "");
        setCellValue(hssfRow, 11, pmbObject.isHasIcon() ? "X" : "");
        setCellValue(hssfRow, 12, pmbObject.isHasGuiLuanch() ? "X" : "");
        setCellValue(hssfRow, 13, pmbObject.getTgppObject());
        setCellValue(hssfRow, 14, pmbObject.getIntVersion());
        setCellValue(hssfRow, 15, pmbObject.getClassType().toString());
        setCellValue(hssfRow, 16, Integer.toString(pmbObject.getMin()));
        setCellValue(hssfRow, 17, Integer.toString(pmbObject.getMax()));
        setCellValue(hssfRow, 18, Integer.toString(pmbObject.getAvg()));
        setCellValue(hssfRow, 19, Integer.toString(pmbObject.getAvgPerNet()));
        setCellValue(hssfRow, 20, Integer.toString(pmbObject.getMaxPerNet()));
        setCellValue(hssfRow, 21, Integer.toString(pmbObject.getMaxPerNE()));
        setCellValue(hssfRow, 24, pmbObject.isMocrNeeded() ? "X" : "");
        setCellValue(hssfRow, 25, listToString(pmbObject.getSupportedVersions()));
        setCellValue(hssfRow, 26, pmbObject.isTransient() ? "Transient" : "MO");
        setCellValue(hssfRow, 27, pmbObject.getPresentation());
        setCellValue(hssfRow, 28, pmbObject.getNameInOmes());
    }


    private void setCellValue(HSSFRow hssfRow, int cell, String value) {
        HSSFCell hssfCell = hssfRow.getCell(cell);
        if (null == hssfCell) {
            hssfCell = hssfRow.createCell(cell);
        }
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue((null == value) ? "" : value);
    }

    private String listToString(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String l : list) {
            sb.append(l).append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
