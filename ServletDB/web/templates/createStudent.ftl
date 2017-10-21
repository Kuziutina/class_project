<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>create student</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/createStyle.css" type="text/css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="back_link">
            <a href="/students">back</a>
        </div>
        <div class="col-md-12 text-center">

        <#if student??>
            <h2><bold>Edit students</bold></h2>
            <form method="POST" action="/students/${student.id}">
                <p><input type="text" name="last_name" value="${student.last_name}"></p>
                <p><input type="text" name="group" value="${student.group.name}"></p>
                <p><input type="submit" value="ok"></p>
            </form>
        <#else>
            <h2><bold>Create students</bold></h2>
            <form method="POST" action="/students/create">
                <p><input type="text" name="last_name" placeholder="sername"></p>
                <p><input type="text" name="group" placeholder="group"></p>
                <p><input type="submit" value="create"></p>
            </form>
        </#if>

        </div>
    </div>
</div>








<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>