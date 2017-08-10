<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>ARS</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/">ARS Tool</a>
                    </div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">iOS</a></li>
                            <li><a href="#">SVN</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    Java
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">jmeter</a></li>
                                    <li><a href="#">EJB</a></li>
                                    <li><a href="#">Jasper Report</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">分离的链接</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">另一个分离的链接</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div><h3>Network Elements</h3></div>
            <div style="margin:10px">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createNEFromModal">Add</button>
                <button type="button" class="btn btn-primary">Delete</button>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>NE Type</th>
                    <th>NE Version</th>
                    <th>Remarks</th>
                </tr>
                </thead>
                <tbody>
                    <#list neList as ne>
                    <tr>
                        <td>${ne.neType}</td>
                        <td>${ne.neVersion}</td>
                        <td>${ne.remarks}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<div class="modal fade" id="createNEFromModal" tabindex="-1" role="dialog"
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
                    Add Network Element
                </h4>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">

                <form class="form-horizontal" role="form" action="/ne/add" method="post">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" for="neType">NE Type</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="neType" name="neType" placeholder="NE Type"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="neVersion" >NE Version</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="neVersion" name="neVersion" placeholder="NE Version"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="remarks" >Remarks</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="remarks" name="remarks" placeholder="remarks"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Modal Footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    Close
                </button>
                <!--
                <button type="button" class="btn btn-primary">
                    Save
                </button>
                -->
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>