<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>Object Model</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <#if spec??>
            <div><h3>Object Model of ${spec.neType} ${spec.neVersion}</h3></div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th style="width:200px;">Topology</th>
                    <th>Adaptation ID</th>
                    <th>Name In OMeS</th>
                    <th>Supported Releases</th>
                </tr>
                </thead>
                <tbody>
                <#list spec.ociMap?keys as key>
                    <#list spec.ociMap[key] as oci>
                    <tr style="font-size: 8pt;">
                        <td style="width:200px;padding:0px 0px 0px 10px;margin:0px;line-height:15px;">
                            <#list 0..oci.column as i>
                                <#if i == oci.column>
                                    <span>|- ${oci.name}</span>
                                <#else>
                                    <span>|&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                </#if>
                            </#list>
                        </td>
                        <td style="width:150px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">${oci.adaptationId}</td>
                        <td style="width:150px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">${oci.nameInOmes}</td>
                        <td style="padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                        <#list oci.supporteredVersions as version>
                            ${version},
                        </#list>
                        </td>
                    </tr>
                    </#list>
                </#list>
                </tbody>
            </table>
            </#if>
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