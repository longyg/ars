package com.nokia.ars;

import com.nokia.tpl.ExcelTemplateParser;
import com.nokia.tpl.definition.TemplateDefParser;
import com.nokia.tpl.definition.TemplateDefinition;

public class Main {
    private static final String xlsTplPath = "template.xls";
    private static final String tplDefPath = "template.xml";

    public static void main(String[] args) throws Exception {
        TemplateDefParser tplDefParser = new TemplateDefParser();
        TemplateDefinition templateDefinition = tplDefParser.parse(tplDefPath);

        ExcelTemplateParser xlsTplParser = new ExcelTemplateParser(templateDefinition);
        xlsTplParser.parse(xlsTplPath);




    }

    private static void readInput() {
        
    }
}
