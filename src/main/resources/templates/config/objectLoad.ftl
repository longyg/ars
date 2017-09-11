<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>Object Load</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>Object Load</h3></div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="neTypeSelect">NE Type</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="neTypeSelect" name="neType">
                            <option value="">--Select--</option>
                            <#list allNeTypes as type>
                                <#if neTypeId?? && type.id == neTypeId>
                                    <option value="${type.id}" selected>${type.name}</option>
                                <#else>
                                    <option value="${type.id}">${type.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                    <#if neTypeId?? && "" != neTypeId>
                        <div class="col-sm-1">
                            <a class="btn btn-primary" href="/load/add?neTypeId=${neTypeId}">
                                <span class="glyphicon glyphicon-plus-sign"></span> Add
                            </a>
                        </div>
                    </#if>
                </div>
            </form>

            <label class="col-md-12"></label>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Operations</th>
                </tr>
                </thead>
                <tbody>
                <#list loadConfigs as config>
                    <tr>
                        <td>${config.name}</td>
                        <td>
                            <a class="btn btn-sm btn-success" href="/load/view?id=${config.id}">
                                <span class="glyphicon glyphicon-eye-open"></span> View
                            </a>
                            <a class="btn btn-sm btn-info" href="/load/edit?id=${config.id}&neTypeId=${neTypeId}">
                                <span class="glyphicon glyphicon-edit"></span> Edit
                            </a>
                            <a class="btn btn-sm btn-danger" href="/load/delete?id=${config.id}&neTypeId=${neTypeId}">
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