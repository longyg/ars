<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>ARS Config</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div><h3>Add ARS Config</h3></div>
            <form class="form-horizontal" role="form">
                <#if neTypeId??>
                <input type="hidden" name="neTypeId" value="${neTypeId}" />
                </#if>
                <#if neRelId??>
                <input type="hidden" name="neRelId" value="${neRelId}" />
                </#if>
                <div class="form-group">
                    <label  class="col-sm-4 control-label" for="neType">NE Type</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="neType" name="neType" placeholder="${neRelease.neType}" value="${neRelease.neType}" readonly unselectable="on"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label" for="neVersion" >NE Version</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="neVersion" name="neVersion" placeholder="${neRelease.neVersion}" value="${neRelease.neVersion}" readonly unselectable="on"/>
                    </div>
                </div>
                <hr/>
                <h4>Interfaces</h4>
                <div class="form-group">
                    <label class="col-sm-4 control-label" for="interfaceSelect" >Interfaces</label>
                    <div class="col-sm-8">
                        <div class="row form-group">
                            <div class="col-sm-5">
                                <select class="form-control" name="interface" id="interfaceSelect">
                                    <option value="">--Select--</option>
                                    <#list selectableInterfaces as iface>
                                        <option value="${iface.id}">${iface.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-sm-3" id="ifaceAddBtnDiv">
                                <button id="ifaceAddBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="ifaceTable">
                    <label class="col-sm-4 control-label">Selected Interfaces</label>
                    <div class="col-sm-8">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Operations</th>
                            </tr>
                            </thead>
                            <tbody id="ifaceTableBody">
                            <#list supportedInterfaces as iface>
                            <tr>
                                <td>${iface.name}</td>
                                <td>Delete</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
                <hr/>
                <h4>Resources</h4>
                <div class="form-group">
                    <label class="col-sm-4 control-label" for="adapIdSelect" >Adaptation ID</label>
                    <div class="col-sm-8">
                        <div class="row form-group">
                            <div class="col-sm-5">
                                <select class="form-control" name="adaptationId" id="adapIdSelect">
                                    <option value="">--Select--</option>
                                    <#if neType??>
                                        <#list neType.adaptList as adapId>
                                            <option value="${adapId}">${adapId}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-sm-3">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label" for="adapVersionSelect" >Adaptation Version</label>
                    <div class="col-sm-8">
                        <div class="row form-group">
                            <div class="col-sm-5">
                                <select class="form-control" name="adaptationRelease" id="adapVersionSelect">
                                    <option>--Select--</option>
                                </select>
                            </div>
                            <div class="col-sm-3" id="srcAddBtnDiv">
                                <button id="srcAddBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="srcTable">
                    <label class="col-sm-4 control-label">Selected Resources</label>
                    <div class="col-sm-8">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Adaptation ID</th>
                                <th>Adaptation Version</th>
                                <th>Operations</th>
                            </tr>
                            </thead>
                            <tbody id="srcTableBody">
                            <#list supportedResources as src>
                            <tr>
                                <td>${src.adaptation.id}</td>
                                <td>${src.adaptation.release}</td>
                                <td>Delete</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
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
<script src="/js/addConfig.js"></script>
</body>
</html>