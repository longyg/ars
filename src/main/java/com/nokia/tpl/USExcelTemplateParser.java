package com.nokia.tpl;

import com.nokia.tpl.definition.Info;
import com.nokia.tpl.definition.TemplateDefinition;
import com.nokia.tpl.definition.UserStory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class USExcelTemplateParser {
    private USExcelTemplate template;
    private HSSFWorkbook wb;
    private TemplateDefinition tplDef;

    public USExcelTemplateParser(HSSFWorkbook wb, TemplateDefinition tplDef) {
        this.wb = wb;
        this.tplDef = tplDef;
    }

    public USExcelTemplate parse() {
        template = new USExcelTemplate();
        template.setName(tplDef.getName());

        HSSFSheet usSheet = wb.getSheetAt(tplDef.getSheet());

        BasicTemplate basicTemplate = new BasicTemplate();
        TitleTemplate titleTemplate = new TitleTemplate();

        HSSFRow titleRow = usSheet.getRow(tplDef.getBasic().getTitle().getRow());
        titleTemplate.setTpl(titleRow.getCell(Constants.TITLE_COL).getStringCellValue());

        List<AdapInfoTemplate> adapInfoTemplateList = new ArrayList<>();
        for (Info info : tplDef.getBasic().getInfoList()) {
            AdapInfoTemplate adapTpl = new AdapInfoTemplate();
            adapTpl.setName(tplDef.getName());
            adapTpl.setPresentation(usSheet.getRow(info.getRow()).getCell(Constants.ADAP_INFO_PRESENTATION_COL).getStringCellValue());
            adapTpl.setTpl(usSheet.getRow(info.getRow()).getCell(Constants.ADAP_INFO_VALUE_COL).getStringCellValue());
            if (!adapInfoTemplateList.contains(adapTpl)) {
                adapInfoTemplateList.add(adapTpl);
            }
        }
        basicTemplate.setAdapInfoTemplateList(adapInfoTemplateList);
        basicTemplate.setTitleTemplate(titleTemplate);

        List<UserStoryTemplate> userStoryTemplates = new ArrayList<>();
        for (UserStory us : tplDef.getUsList()) {
            UserStoryTemplate userStoryTemplate = new UserStoryTemplate();
            userStoryTemplate.setName(tplDef.getName());
            userStoryTemplate.setTpl(usSheet.getRow(us.getRow()).getCell(Constants.US_TITLE_COL).getStringCellValue());

            parseSubTasks(userStoryTemplate, us, usSheet);

            if (!userStoryTemplates.contains(userStoryTemplate)) {
                userStoryTemplates.add(userStoryTemplate);
            }
        }

        template.setBasicTemplate(basicTemplate);
        template.setUserStoryTemplateList(userStoryTemplates);
        return template;
    }

    public USExcelTemplate getTemplate() {
        return template;
    }

    public void setTemplate(USExcelTemplate template) {
        this.template = template;
    }

    private void parseSubTasks(UserStoryTemplate template, UserStory us, HSSFSheet usSheet) {
        String sub = us.getSub();
        if (sub.contains(",")) {
            String[] ss = sub.split(",");
            int startRow = Integer.valueOf(ss[0].trim());
            int endRow = Integer.valueOf(ss[1].trim());

            for (int i = startRow; i <= endRow; i++) {
                HSSFRow row = usSheet.getRow(i);
                SubTaskTemplate subTaskTemplate = createSubTaskTemplate(row);
                template.addSubTask(subTaskTemplate);
            }
        } else {
            int rowIndex = Integer.valueOf(sub.trim());
            HSSFRow row = usSheet.getRow(rowIndex);
            SubTaskTemplate subTaskTemplate = createSubTaskTemplate(row);
            template.addSubTask(subTaskTemplate);
        }
    }

    private SubTaskTemplate createSubTaskTemplate(HSSFRow row) {
        SubTaskTemplate subTaskTemplate = new SubTaskTemplate();
        String s = row.getCell(Constants.SUB_SELECTED_COL).getStringCellValue().trim();
        if (s.equals("X")) {
            subTaskTemplate.setSelected(true);
        }
        subTaskTemplate.setName(row.getCell(Constants.SUB_NAME_COL).getStringCellValue());
        subTaskTemplate.setDescription(new VariableTemplate(row.getCell(Constants.SUB_DESCRIPTION_COL).getStringCellValue()));
        subTaskTemplate.setRationale(new VariableTemplate(row.getCell(Constants.SUB_RATIONALE_COL).getStringCellValue()));
        subTaskTemplate.setIssue(new VariableTemplate(row.getCell(Constants.SUB_ISSUE_COL).getStringCellValue()));
        return subTaskTemplate;
    }
}
