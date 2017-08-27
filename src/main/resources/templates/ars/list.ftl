<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>ARS</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>ARS</h3></div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="neTypeSelect">NE Type</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="neTypeSelect" name="neType">
                            <option value="">--Select--</option>
                            <#list allNeTypeList as type>
                                <#if neTypeId?? && type.id == neTypeId>
                                    <option value="${type.id}" selected>${type.name}</option>
                                <#else>
                                    <option value="${type.id}">${type.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
            </form>

            <label class="col-md-12"></label>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>NE Type</th>
                    <th>NE Version</th>
                    <th>ARS Config</th>
                    <th>ARS Details</th>
                </tr>
                </thead>
                <tbody>
                <#list neArsList as neArs>
                    <tr>
                        <td>${neArs.neRelease.neType}</td>
                        <td>${neArs.neRelease.neVersion}</td>
                        <td>
                            <#if neArs.arsConfig??>
                                <a class="btn btn-sm btn-success" href="/ars/viewConfig?id=${neArs.arsConfig.id}">
                                    <span class="glyphicon glyphicon-eye-open"></span> View
                                </a>
                                <a class="btn btn-sm btn-success" href="/ars/editConfig?id=${neArs.arsConfig.id}">
                                    <span class="glyphicon glyphicon-eye-open"></span> Edit
                                </a>
                            <#else>
                                <a class="btn btn-sm btn-primary" href="/ars/addConfig?neTypeId=${neTypeId}&neRelId=${neArs.neRelease.id}">
                                    <span class="glyphicon glyphicon-plus"></span> Add
                                </a>
                            </#if>
                        </td>
                        <td>
                            <#if neArs.ars??>
                                <a class="btn btn-sm btn-success" href="/ars/viewUs?id=${neArs.ars.userStory}">
                                    <span class="glyphicon glyphicon-eye-open"></span> User Story
                                </a>
                                <a class="btn btn-sm btn-success" href="/ars/viewOm?id=${neArs.ars.objectModel}">
                                    <span class="glyphicon glyphicon-eye-open"></span> Object Model
                                </a>
                                <a class="btn btn-sm btn-success" href="/ars/viewPmDL?id=${neArs.ars.pmDataLoad}">
                                    <span class="glyphicon glyphicon-eye-open"></span> PM Data Load
                                </a>
                                <a class="btn btn-sm btn-success" href="/ars/viewCounter?id=${neArs.ars.counter}">
                                    <span class="glyphicon glyphicon-eye-open"></span> Counter
                                </a>
                                <a class="btn btn-sm btn-success" href="/ars/viewAlarm?id=${neArs.ars.alarm}">
                                    <span class="glyphicon glyphicon-eye-open"></span> Alarm
                                </a>
                            <#elseif neArs.arsConfig??>
                                <a id="arsCreateBtn" class="btn btn-sm btn-success" href="/ars/create?neTypeId=${neTypeId}&neRelId=${neArs.neRelease.id}">
                                    <span class="glyphicon glyphicon-eye-open"></span> Generate
                                </a>
                            <#else>
                                <p class="text-danger"><span class="glyphicon glyphicon-warning-sign"></span> ARS is not available!</p>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-1"></div>
    </div>

    <#include "/inc/footer.ftl" />
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ars.js"></script>
</body>
</html>