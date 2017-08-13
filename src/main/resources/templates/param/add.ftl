<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Parameters</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div><h3>Add parameters</h3></div>

            <form class="form-horizontal" role="form" action="/param/save" method="post">
                <div class="form-group">
                    <label  class="col-sm-2 control-label" for="neType">NE Type</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="neType" name="neType">
                            <#list allNes as ne>
                                <#if ne.neType == currentNe.neType>
                                    <option value="${ne.neType}" selected>${ne.neType}</option>
                                <#else>
                                    <option value="${ne.neType}">${ne.neType}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="neVersion" >NE Version</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="neVersion" name="neVersion">
                            <#list allNes as ne>
                                <#if ne.neVersion == currentNe.neVersion>
                                    <option value="${ne.neVersion}" selected>${ne.neVersion}</option>
                                <#else>
                                    <option value="${ne.neVersion}">${ne.neVersion}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <#list paramList as param>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="${param.name}" >${param.name}</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="${param.name}" name="${param.name}" placeholder="${param.name}" />
                        </div>
                    </div>
                </#list>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-3"></div>
    </div>

    <#include "/inc/footer.ftl" />
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>