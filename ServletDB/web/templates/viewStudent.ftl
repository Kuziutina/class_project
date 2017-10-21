<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>create student</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/viewStudent.css" type="text/css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="back_link">
            <a href="/students">back</a>
        </div>
        <div class="col-md-12 text-center">
            <table>
                <tr>
                    <th>Name:</th>
                    <th>${student.last_name}</th>
                </tr>
                <tr>
                    <th>Group:</th>
                    <th>${student.group.name}</th>
                </tr>
            </table>
            <!--            <p class="info">Kado 604</p>-->
            <form method="GET" action="/students/${student.id}/edit">
                <button>edit</button>
            </form>
            <form method="get" action="/students/${student.id}/delete">
                <button>delete</button>
            </form>

        </div>
    </div>
</div>








<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>