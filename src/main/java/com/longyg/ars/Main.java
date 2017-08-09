package com.longyg.ars;

import com.longyg.tpl.ExcelTemplate;
import com.longyg.tpl.ExcelTemplateParser;
import com.longyg.tpl.Variable;
import com.longyg.tpl.VariablesRepository;
import com.longyg.tpl.definition.TemplateDefParser;
import com.longyg.tpl.definition.TemplateDefinition;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final String xlsTplPath = "template.xls";
    private static final String tplDefPath = "template.xml";
    //private static final String outXlsPath = "ars_new.xls";

    public static void main(String[] args) throws Exception {
        TemplateDefParser tplDefParser = new TemplateDefParser();
        TemplateDefinition tplDef = tplDefParser.parse(tplDefPath);

        ExcelTemplateParser xlsTplParser = new ExcelTemplateParser(tplDef);
        ExcelTemplate template = xlsTplParser.parse(xlsTplPath);

        readVariableInput();

        ExcelGenerator generator = new ExcelGenerator(tplDef, template, xlsTplParser.getWb());
        String outFileName = template.getUsExcelTemplate().getBasicTemplate().getTitleTemplate().getReal();
        generator.generate(outFileName + ".xls");
    }

    private static void readVariableInput() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        for (Variable variable : VariablesRepository.getVariables()) {
            System.out.println(variable.getName() + ":");
            input = reader.readLine();
            VariablesRepository.setValue(variable.getName(), input);
        }
    }
}
