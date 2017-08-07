package com.nokia.tpl;

import com.nokia.tpl.definition.TemplateDefinition;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;

public class ExcelTemplateParser  {
    private static final Logger LOG = Logger.getLogger(ExcelTemplateParser.class);
    private USExcelTemplateParser usExcelTemplateParser;
    private USExcelTemplate usExcelTemplate;
    private TemplateDefinition tplDef;
    private HSSFWorkbook wb;

    public ExcelTemplateParser(TemplateDefinition tplDef) {
                this.tplDef = tplDef;
    }

    public void parse(String templatePath) throws Exception {
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(templatePath);
            POIFSFileSystem fs = new POIFSFileSystem(fi);
            wb = new HSSFWorkbook(fs);
        } catch (Exception e) {
            LOG.error("Exception while parsing excel template", e);
            throw new Exception("Exception while parsing excel template", e);
        }
        usExcelTemplateParser = new USExcelTemplateParser(wb, tplDef);
        usExcelTemplate = usExcelTemplateParser.parse();
    }

    public USExcelTemplate getUsExcelTemplate() {
        return usExcelTemplate;
    }

    public void setUsExcelTemplate(USExcelTemplate usExcelTemplate) {
        this.usExcelTemplate = usExcelTemplate;
    }
}
