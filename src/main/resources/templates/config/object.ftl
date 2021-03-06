<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ars.css" rel="stylesheet">
    <title>Global Objects</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>Global Objects</h3></div>
            <div style="margin:10px">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createObjectFromModal">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add
                </button>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Presentation</th>
                    <th>Name In OMeS</th>
                    <th>Is Transient Object</th>
                    <th>Operations</th>
                </tr>
                </thead>
                <tbody>
                    <#list objectList as obj>
                    <tr>
                        <td>${obj.name}</td>
                        <td>${obj.presentation}</td>
                        <td>${obj.nameInOMeS}</td>
                        <td>
                            <#if obj.transient == true>
                                <input type="checkbox" checked disabled/>
                            <#else>
                                <input type="checkbox" disabled/>
                            </#if>
                        <td>
                            <a class="btn btn-sm btn-danger" href="/object/delete?name=${obj.name}">
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

<div class="modal fade" id="createObjectFromModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Add Global Object
                </h4>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="/object/add" method="post">
                    <div class="form-group">
                        <label  class="col-sm-4 control-label" for="name">Name</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="name" name="name" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="presentation" >Presentation</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="presentation" name="presentation" placeholder="Presentation"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="nameInOMeS" >Name In OMeS</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="nameInOMeS" name="nameInOMeS" placeholder="Name In OMeS"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="transient" >Is Transient Object</label>
                        <div class="col-sm-8">
                            <input type="checkbox" id="transient" name="transient"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-8 col-sm-4">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>