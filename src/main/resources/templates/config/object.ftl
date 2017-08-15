<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Object</title>
</head>
<body>

<div class="container-fluid">
    <#include "/inc/nav.ftl" />

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>Interface objects</h3></div>
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
                    <th>Max</th>
                    <th>Min</th>
                    <th>Avg</th>
                    <th>Max Per Network</th>
                    <th>Avg Per Network</th>
                </tr>
                </thead>
                <tbody>
                    <#list objectList as obj>
                    <tr>
                        <td>${obj.name}</td>
                        <td>${obj.presentation}</td>
                        <td>${obj.nameInOmes}</td>
                        <td>${obj.max}</td>
                        <td>${obj.min}</td>
                        <td>${obj.avg}</td>
                        <td>${obj.maxPerNet}</td>
                        <td>${obj.avgPerNet}</td>
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
                    Add Object
                </h4>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="/object/add" method="post">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" for="name">Name</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="name" name="name" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="presentation" >Presentation</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="presentation" name="presentation" placeholder="Presentation"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="description" >Description</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="description" name="description" placeholder="Description"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Modal Footer -->
            <!--
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    Close
                </button>
                <button type="submit" formtarget="" class="btn btn-primary">
                    Save
                </button>
            </div>
            -->
        </div>
    </div>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>