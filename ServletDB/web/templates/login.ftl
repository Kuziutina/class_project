<!DOCTYPE html>
<html lang="ru">
<head>
    <title> Bootstrap 101</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/loginStyle.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="post" class="form_signin">
        <div class="col-xs-4">
        </div>
        <div class="col-xs-4">
            <h2 class="form_signin_header form">
                Please sign in
            </h2>
            <div class="form">
                <input id="inputEmail" class="form_control" placeholder="login" type="text" name="username">
            </div>
            <div class="form">
                <input id="inputPassword" class="form_control" placeholder="password" type="password" name="password">
            </div>

            <div class="form">
                <button class="btn" type="submit">Sign in</button>
            </div>
        </div>
    </form>

</div>
</body>