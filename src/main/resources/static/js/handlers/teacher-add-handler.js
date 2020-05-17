
$(function() {

    $("#teacher-add-number").mask("+375 (99) 999 99 99", {placeholder: " " });
});

function empty(text) {
    return text === '';
}

$('#btn-teacher-add-modal').click(() => {

    $('#modal-teacher-add').modal('show');
});

$('#btn-teacher-add').click(() => {

    let firstname = $('#teacher-add-firstname').val();
    let surname = $('#teacher-add-surname').val();
    let middlename = $('#teacher-add-middlename').val();

    let number = $('#teacher-add-number').val();
    
    let digit = $('#teacher-add-group-digit').val();
    let letter = $('#teacher-add-group-letter').val();

    let login = $('#teacher-add-login').val();
    let password = $('#teacher-add-password').val();

    if(empty(firstname) || empty(surname) || empty(middlename) || empty(number) || empty(login) || empty(password)) {
        $('#teacher-add-error').html('Заполните все поля').transition('fade in');
    } else {
        $.ajax({
            type: "POST",
            url: "/teacher/add",
            data: {
                firstname: firstname,
                surname: surname,
                middlename: middlename,
                number: number,
                login: login,
                password: password,
                grouptitle: digit + letter
            },
            success: function(response) {
                console.log(response);
                $('#modal-teacher-add').modal('hide');
            },
            error: function(response) {
                console.log(response);
            }
        });
    }
});