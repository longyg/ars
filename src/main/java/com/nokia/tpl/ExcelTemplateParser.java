package com.nokia.tpl;

import com.nokia.tpl.definition.TemplateDefinition;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;

public class ExcelTemplateParser  {
    private static final Logger LOG = Logger.getLogger(ExcelTemplateParser.class);
    private TemplateDefinition tplDef;
    private HSSFWorkbook wb;

    public ExcelTemplateParser(TemplateDefinition tplDef) {
        this.tplDef = tplDef;
    }

    public ExcelTemplate parse(String templatePath) throws Exception {
        try (FileInputStream fi = new FileInputStream(templatePath);
             POIFSFileSystem fs = new POIFSFileSystem(fi);)
        {
            wb = new HSSFWorkbook(fs);
        }
        catch (Exception e)
        {
            LOG.error("Exception while parsing excel template", e);
            throw new Exception("Exception while parsing excel template", e);
        }

        // User Story sheet
        USExcelTemplateParser usExcelTemplateParser = new USExcelTemplateParser(wb, tplDef);
        USExcelTemplate usExcelTemplate = usExcelTemplateParser.parse();

        ExcelTemplate excelTemplate = new ExcelTemplate();
        excelTemplate.setUsExcelTemplate(usExcelTemplate);
        return excelTemplate;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }
}
