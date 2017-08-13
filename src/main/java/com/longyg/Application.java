package com.longyg;

import com.longyg.backend.TemplateRepository;
import com.longyg.backend.ars.tpl.ExcelTemplate;
import com.longyg.backend.ars.tpl.ExcelTemplateParser;
import com.longyg.backend.ars.tpl.definition.TemplateDefParser;
import com.longyg.backend.ars.tpl.definition.TemplateDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {
    private static final String xlsTplPath = "template.xls";
    private static final String tplDefPath = "template.xml";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        init();
    }

    private static void init() throws Exception {
        TemplateDefParser tplDefParser = new TemplateDefParser();
        TemplateDefinition tplDef = tplDefParser.parse(tplDefPath);
        ExcelTemplateParser xlsTplParser = new ExcelTemplateParser();
        ExcelTemplate template = xlsTplParser.parse(xlsTplPath, tplDef);
        TemplateRepository.setTplDef(tplDef);
        TemplateRepository.setTemplate(template);
    }
}
