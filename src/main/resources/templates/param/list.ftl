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
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>Parameters</h3></div>
            <form class="form-horizontal" role="form" action="/param" method="post">
                <label class="col-md-3 control-label" for="name">NE Type</label>
                <div class="col-md-3">
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
                <label class="col-md-3 control-label" for="name">NE Version</label>
                <div class="col-md-3">
                    <select class="form-control" id="neVersionSelect" name="neVersion">
                        <option value="">--Select--</option>
                        <#list neReleaseList as neRel>
                            <#if neRelId?? && neRel.id == neRelId>
                                <option value="${neRel.id}" selected>${neRel.neVersion}</option>
                            <#else>
                                <option value="${neRel.id}">${neRel.neVersion}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </form>
            <label class="col-md-12"></label>
            <#if neRelId?? && "" != neRelId>
                <div style="margin:10px">
                    <a class="btn btn-primary" href="/param/add?neRelId=${neRelId}">
                        <span class="glyphicon glyphicon-plus-sign"></span> Add
                    </a>
                </div>
            </#if>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>NE Type</th>
                    <th>NE Version</th>
                    <th>Param Version</th>
                    <th>Operations</th>
                </tr>
                </thead>
                <tbody>
                <#list neParamList as neParam>
                    <tr>
                        <td>${neParam.neType}</td>
                        <td>${neParam.neVersion}</td>
                        <td>${neParam.v}</td>
                        <td>
                            <a class="btn btn-sm btn-info" href="/param/view?id=${neParam.id}">
                                <span class="glyphicon glyphicon-edit"></span> View Details
                            </a>
                            <a class="btn btn-sm btn-info" href="/param/delete?id=${neParam.id}">
                                <span class="glyphicon glyphicon-edit"></span> Edit
                            </a>
                            <a class="btn btn-sm btn-danger" href="/param/delete?id=${neParam.id}">
                                <span class="glyphicon glyphicon-remove-sign"></span> Delete
                            </a>
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
<script src="/js/param.js"></script>
</body>
</html>