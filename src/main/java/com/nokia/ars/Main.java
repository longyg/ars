package com.nokia.ars;

import com.nokia.tpl.ExcelTemplate;
import com.nokia.tpl.ExcelTemplateParser;
import com.nokia.tpl.Variable;
import com.nokia.tpl.VariablesRepository;
import com.nokia.tpl.definition.TemplateDefParser;
import com.nokia.tpl.definition.TemplateDefinition;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final String xlsTplPath = "template.xls";
    private static final String tplDefPath = "template.xml";
    private static final String outXlsPath = "ars_new.xls";

    public static void main(String[] args) throws Exception {
        TemplateDefParser tplDefParser = new TemplateDefParser();
        TemplateDefinition tplDef = tplDefParser.parse(tplDefPath);

        ExcelTemplateParser xlsTplParser = new ExcelTemplateParser(tplDef);
        ExcelTemplate template = xlsTplParser.parse(xlsTplPath);

        readVariableInput();

        ExcelGenerator generator = new ExcelGenerator(tplDef, template, xlsTplParser.getWb());
        generator.generate(outXlsPath);
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
