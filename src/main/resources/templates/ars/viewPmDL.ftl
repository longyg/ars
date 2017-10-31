<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>PM Data Load</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-12">
            <#if spec??>
            <div><h3>PM Data Load of ${spec.neType} ${spec.neVersion}</h3></div>
            <#list neType.adaptSet as adap>
                <div><h2>adap</h2></div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width:200px;">Name</th>
                        <th style="width:20px;">Name In Omes</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list neType.adaptSet as adap>
                            <#list spec.ociMap[adap] as oci>
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
                                <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                    <#if oci.alarmingObject == true>
                                        A
                                    </#if>
                                </td>
                                <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                    <#if oci.measuredObject == true>
                                        M
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                        </#list>
                    </tbody>
                </table>
            </#list>
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