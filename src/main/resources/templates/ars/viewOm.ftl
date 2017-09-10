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
                    <th style="width:20px;">
                        <span style="transform:rotate(-90deg);line-height: 10px;text-align: center;margin:0px">Alarming</span>
                    </th>
                    <th style="width:20px;">Measured</th>
                    <th style="width:20px;">Configured</th>
                    <th style="width:20px;">Icon</th>
                    <th style="width:20px;">GUI launch</th>
                    <th style="width:60px;">3GPP NRM object</th>
                    <th style="width:30px;">Integration Version</th>
                    <th style="width:40px;">Integration (NASDA) </th>
                    <th style="width:20px;">Min</th>
                    <th style="width:20px;">Max</th>
                    <th style="width:20px;">Avg</th>
                    <th>Adaptation ID</th>
                    <th>Name In OMeS</th>
                    <th>Supported Releases</th>
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
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="width:150px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">${oci.adaptationId}</td>
                        <td style="width:150px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">${oci.nameInOmes}</td>
                        <td style="padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">${oci.supportedReleases}</td>
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