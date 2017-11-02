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
                <div><h2>${adap}</h2></div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width:20px;">Name</th>
                        <th style="width:20px;">Name In Omes</th>
                        <th style="width:20px;">Measured Object</th>
                        <th style="width:20px;">Is supported by ${spec.neVersion}</th>
                        <th style="width:20px;">Supported other versions</th>
                        <th style="width:20px;">Dimension</th>
                        <th style="width:20px;">Avg Per Network</th>
                        <th style="width:20px;">Max Per Network</th>
                        <th style="width:20px;">Max Per NE</th>
                        <th style="width:20px;">Nbr of Counters</th>
                        <th style="width:20px;">N-1 Nbr of Counters</th>
                        <th style="width:20px;">Delta</th>
                        <th style="width:20px;">NW Agg Object</th>
                        <th style="width:20px;">Time Agg</th>
                        <th style="width:20px;">BH</th>
                        <th style="width:20px;">Active</th>
                        <th style="width:20px;">Default Interval</th>
                        <th style="width:20px;">Minimum Interval</th>
                        <th style="width:20px;">Storage</th>
                        <th style="width:20px;">Bytes of counter</th>
                        <th style="width:20px;">1 NE Measurements/h</th>
                        <th style="width:20px;">1 NE Counters/h</th>
                        <th style="width:20px;">1 NE hourly aggregation Counter/h</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list spec.measurementMap[adap] as meas>
                        <tr style="font-size: 8pt;">
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height:15px;">
                                ${meas.name}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.nameInOmes}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.measuredObject}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.supported?string('Yes', 'No')}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.supportedOtherReleases}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.dimension}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.avgPerNet}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.maxPerNet}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.maxPerNe}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.counterNumber}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.counterNumberOfLastVersion}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.delta}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.aggObject}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.timeAgg!""}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.bh!""}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.active}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.defaultInterval}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                                ${meas.minimalInterval}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.storageDays}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.bytesPerCounter}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.mphPerNE}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.cphPerNE}
                            </td>
                            <td style="width:20px;padding:0px 0px 0px 10px;margin:0px;line-height: 15px;">
                            ${meas.chaPerNE}
                            </td>
                        </tr>
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