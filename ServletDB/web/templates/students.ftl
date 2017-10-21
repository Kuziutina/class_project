<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>students</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/student.css" type="text/css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <h2><bold>Students</bold></h2>
            <table margin="auto">
                <tr>
                    <th class="data">name</th>
                    <th class="data">group</th>
                    <th></th>
                    <th></th>
                </tr>
                <#list students as student>
                <tr>
                    <th class="data"><a href="/students/${student.id}">${student.last_name}</a></th>
                    <th class="data">${student.group.name}</th>
                    <th><form action="/students/${student.id}/edit" method="get">     <button>edit</button></form></th>
                    <th><form action="/students/${student.id}/delete" method="get"><button>delete</button></form></th>
                </tr>
                </#list>
            </table>
            <a href="/students/create">add new </a>
        </div>
    </div>
</div>








<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>