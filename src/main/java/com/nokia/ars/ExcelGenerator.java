package com.nokia.ars;

import com.nokia.tpl.ExcelTemplate;
import com.nokia.tpl.definition.TemplateDefinition;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;

public class ExcelGenerator {
    private static final Logger LOG = Logger.getLogger(ExcelGenerator.class);
    private TemplateDefinition tplDef;
    private ExcelTemplate template;
    private HSSFWorkbook wb;

    public ExcelGenerator(TemplateDefinition tplDef, ExcelTemplate template, HSSFWorkbook wb) {
        this.tplDef = tplDef;
        this.template = template;
        this.wb = wb;
    }

    public void generate(String outPath) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(outPath)) {

            USExcelGenerator usExcelGenerator = new USExcelGenerator(tplDef, template, wb);
            usExcelGenerator.generate();

            wb.write(fileOut);
        } catch (Exception e) {
            LOG.error("Exception while generating excel: ", e);
            throw new Exception("Exception while generating excel: ", e);
        }
    }
}
