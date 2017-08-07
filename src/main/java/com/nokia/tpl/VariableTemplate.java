package com.nokia.tpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableTemplate {
    private String tpl;
    private String real;
    private boolean getCalculated;

    public VariableTemplate() {

    }

    public VariableTemplate(String tpl) {
        this.tpl = tpl;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
        Matcher m = Pattern.compile(Constants.VARIABLE_REGEX).matcher(tpl);
        while (m.find()) {
            String param = m.group(); //${xx}
            String name = param.substring(2, param.length() - 1);
            Variable variable = new Variable(name);
            VariablesRepository.addVariable(variable);
        }
    }

    public String getReal() {
        if (getCalculated) {
            return real;
        } else {
            calculateTpl();
            return real;
        }
    }

    private void calculateTpl() {
        //用参数替换模板中的${}变量
        Matcher m = Pattern.compile(Constants.VARIABLE_REGEX).matcher(tpl);

        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            String param = m.group(); //${xx}
            String value = VariablesRepository.getVariableValue(param.substring(2, param.length() - 1));
            m.appendReplacement(sb, value == null ? "" : value.toString());
        }

        m.appendTail(sb);

        real = sb.toString();

        getCalculated = true;
    }

    public static void main(String[] args) {
        VariableTemplate template = new VariableTemplate();
        template.setTpl("This is a ${NE_version} template");
        VariablesRepository.addVariable(new Variable("NE_version", "11.0"));
        System.out.println(template.getReal());
    }
}
