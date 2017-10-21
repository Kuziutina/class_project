<html>
<head>
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<input type="text" name="q" id="q_id" oninput="f()"/>
<div id="results"></div>
<script type="application/javascript">
    function f() {
        console.log(1);
        $.ajax({
            url: "/search",
            data: {"q": $("#q_id").val()},
            dataType: "json",
            success: function (result) {
                $("#results").html("");
                for (var i = 0; i < result.lastnames.length; i++) {
                    $("#results").append("<li>" + result.lastnames[i] + "</li>");
                }
            },
            error: function (jqXHR, exception) {

                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            },
        });
    }
</script>
</body>
</html>