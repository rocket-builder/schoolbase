function empty(str) {
    return str === '';
}

$('#signup-send-code-btn').click(function () {

    var login = $('#signup-login').val();
    var email = $('#signup-email').val();
    var password = $('#signup-password').val();
    var password2 = $('#signup-password2').val();

    if(password !== password2) { alert('passwords are not equal'); } else
    if(empty(login) || empty(email) || empty(password || empty(password2))) { $('#signup-error').html("Fill all fields") } else
    if(login.length < 3) { $('#signup-error').html("Username must be longer than 2 symbols") } else
    {
        $.ajax({
            type: "POST",
            url: "/signup",
            data: {
                username: login,
                email: email,
                password: password
            },
            success: function(response) {
                console.log("SIGNUP MODULE RESPONSE: " + response);
                response = $.parseJSON(response);

                if(response.isError) {
                    $('#signup-error').html(response.message).transition('shake');
                } else {
                    var username = response.content;
                    window.location.href="/profile/" + username;
                }
            },
            error: function(response) {
                console.log('ERROR: ' + response);
            }
        });
    }
});