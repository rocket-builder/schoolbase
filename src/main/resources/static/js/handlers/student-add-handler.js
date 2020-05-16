
function empty(text) {
    return text === '';
}
var childCount = 1;
$('input[type=radio]').change(function () {
    childCount = $(this).next().text();
});

$('#btn-student-add').click(function(){

    var firstname = $('#student-add-firstname').val();
    var surname = $('#student-add-surname').val();
    var middlename = $('#student-add-middlename').val();

    var hobby = $('#student-add-hobby').val();
    var birthday = $('#student-add-birthday').val();

    var healthgroup = $('#student-add-health-group').val();
    var gymgroup = $('#student-add-gym-group').val();

    var diseases = $('#student-add-diseases').val();

    var digit = $('#student-add-group-digit').val();
    var letter = $('#student-add-group-letter').val();

    if(empty(firstname) || empty(surname) || empty(middlename) || empty(hobby) || empty(birthday) || empty(diseases)) {
        $('#login-error').html('Заполните все поля').transition('fade in');
    } else {
        $.ajax({
            type: "POST",
            url: "/student/add",
            data: {
                firstname: firstname,
                surname: surname,
                middlename: middlename,
                hobby: hobby,
                birthday: birthday,
                healthgroup: healthgroup,
                gymgroup: gymgroup,
                diseases: diseases,
                familychildcount: childCount,
                grouptitle: digit + letter
            },
            success: function(response) {
                console.log(response);
                $('#modal-student-add').modal('hide');
            },
            error: function(response) {
                console.log(response);
            }
        });
    }
});