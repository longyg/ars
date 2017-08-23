package com.longyg.frontend.model.ars.us;

import com.longyg.backend.TemplateRepository;
import com.longyg.backend.ars.tpl.*;
import com.longyg.backend.ars.tpl.definition.userstory.Info;
import com.longyg.backend.ars.tpl.definition.userstory.US;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.param.NeParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsBuilder {
    private NeParam neParam;

    public UserStorySpec create(NeParam neParam) {
        this.neParam = neParam;

        UserStorySpec usSpec = new UserStorySpec();

        NeRelease neRelease = new NeRelease();
        neRelease.setNeType(neParam.getNeType());
        neRelease.setNeVersion(neParam.getNeVersion());
        usSpec.setNe(neRelease);

        usSpec.setTitle(createTitle());

        usSpec.setAdapInfos(createAdapInfos());

        usSpec.setUserStories(createUs());

        return usSpec;
    }

    private Title createTitle() {
        TitleTemplate titleTemplate = TemplateRepository.getTemplate().getUsExcelTemplate().getBasicTemplate().getTitleTemplate();
        Title title = new Title();
        title.setValue(getValueFromTpl(titleTemplate.getTpl()));
        title.setRow(TemplateRepository.getTplDef().getBasic().getTitle().getRow());
        title.setCol(Constants.TITLE_COL);
        return title;
    }

    private List<AdapInfo> createAdapInfos() {
        List<AdapInfo> adapInfos = new ArrayList<>();

        for(AdapInfoTemplate adapInfoTemplate : TemplateRepository.getTemplate().getUsExcelTemplate().getBasicTemplate().getAdapInfoTemplateList()) {
            AdapInfo adapInfo = new AdapInfo();
            adapInfo.setName(adapInfoTemplate.getPresentation());
            adapInfo.setValue(getValueFromTpl(adapInfoTemplate.getTpl()));
            Info info = TemplateRepository.getTplDef().getBasic().getInfoByName(adapInfoTemplate.getName());
            adapInfo.setRow(info.getRow());
            adapInfo.setCol(Constants.ADAP_INFO_VALUE_COL);

            adapInfos.add(adapInfo);
        }

        return adapInfos;
    }

    private List<UserStory> createUs() {
        List<UserStory> userStories = new ArrayList<>();

        for (UserStoryTemplate userStoryTemplate : TemplateRepository.getTemplate().getUsExcelTemplate().getUserStoryTemplateList()) {
            UserStory userStory = new UserStory();

            US us = TemplateRepository.getTplDef().getUsByName(userStoryTemplate.getName());

            userStory.setValue(getValueFromTpl(userStoryTemplate.getTpl()));
            userStory.setRow(us.getRow());
            userStory.setCol(Constants.US_TITLE_COL);
            userStory.setSubTasks(createSubTasks(userStoryTemplate));

            userStories.add(userStory);
        }

        return userStories;
    }

    private List<SubTask> createSubTasks(UserStoryTemplate userStoryTemplate) {
        List<SubTask> subTasks = new ArrayList<>();
        for (SubTaskTemplate subTaskTemplate : userStoryTemplate.getSubTaskTemplateList()) {
            SubTask subTask = new SubTask();
            int row = subTaskTemplate.getRow();
            subTask.setRow(row);
            subTask.setSelect(new Base(Boolean.toString(subTaskTemplate.isSelected()), row, Constants.SUB_SELECTED_COL));
            if (subTaskTemplate.getName() != null) {
                subTask.setName(new Base(subTaskTemplate.getName(), row, Constants.SUB_NAME_COL));
            }
            if (subTaskTemplate.getDescription() != null) {
                subTask.setDescription(new Base(getValueFromTpl(subTaskTemplate.getDescription().getTpl()), row, Constants.SUB_DESCRIPTION_COL));
            }
            if (subTaskTemplate.getRationale() != null) {
                subTask.setRationale(new Base(getValueFromTpl(subTaskTemplate.getRationale().getTpl()), row, Constants.SUB_RATIONALE_COL));
            }
            if (subTaskTemplate.getIssue() != null) {
                subTask.setIssue(new Base(getValueFromTpl(subTaskTemplate.getIssue().getTpl()), row, Constants.SUB_ISSUE_COL));
            }
            subTasks.add(subTask);
        }
        return subTasks;
    }

    private String getValueFromTpl(String tpl) {
        Matcher m = Pattern.compile(Constants.VARIABLE_REGEX).matcher(tpl);

        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            String param = m.group();
            String paramName = param.substring(2, param.length() - 1);
            String value = getVariableValue(paramName);
            m.appendReplacement(sb, value == null ? "" : value.toString());
        }

        m.appendTail(sb);

        return sb.toString();
    }

    private String getVariableValue(String name) {
        for (Variable variable : neParam.getVariables()) {
            if (variable.getName().equals(name)) {
                return variable.getValue();
            }
        }
        return null;
    }
}
