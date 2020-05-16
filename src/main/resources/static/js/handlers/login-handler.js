function empty(str) {
    return str === '';
}

$('#btn-login').click(function () {

    var username = $('#login-username').val();
    var password = $('#login-password').val();

    if(empty(username) || empty(password)) { $('#login-error').html("Fill all fields") }
    else {
        $.ajax({
            type: "POST",
            url: "/login",
            data: {
                login: username,
                password: password
            },
            success: function(response) {
                console.log("LOGIN MODULE RESPONSE: " + response);
                response = $.parseJSON(response);

                if(response.isError) {
                    $('#login-error').html(response.text).transition('fade in');
                } else {
                    window.location.href="/home";
                }
            },
            error: function(response) {
                console.log('ERROR: ' + response);
            }
        });
    }
});