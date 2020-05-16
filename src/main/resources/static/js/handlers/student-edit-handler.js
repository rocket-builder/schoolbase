function empty(text) {
    return text === '';
}

let familyChildCount = 1;
$('input[type=radio]').change(function () {
    familyChildCount = $(this).next().text();
});

let id;
$('.btn-student-edit-modal').click(function () {

    id = $(this).attr('id').split('edit').pop();
    $.ajax({
        type: "GET",
        url: "/student/"+id+"/edit",
        success: function(response) {
            console.log('SUCCESS: ' + response);
            let student = $.parseJSON(response);

            $('#student-edit-firstname').val(student.firstname);
            $('#student-edit-surname').val(student.surname);
            $('#student-edit-middlename').val(student.middlename);

            $('#student-edit-hobby').val(student.hobby);
            $('#student-edit-birthday').val(student.birthday);

            $('#student-edit-diseases').val(student.diseases);
            $('#rad' + student.familyChildCount).checkbox('set checked');
            familyChildCount = student.familyChildCount;

            let grouptitle = student.group_title;
            let result = grouptitle.match(/\d{1,2}/);
            let digit = result[0];
            let letter = grouptitle[grouptitle.length-1];

            $("#student-edit-group-digit").dropdown('set exactly', [digit]);
            $("#student-edit-group-letter").dropdown('set exactly', [letter]);
            $("#student-edit-health-group").dropdown('set exactly', [student.healthgroup]);
            $("#student-edit-gym-group").dropdown('set exactly', [student.gymgroup]);

            $('#modal-student-edit').modal('show');
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});

$('#btn-student-edit').click(() => {

    let firstname = $('#student-edit-firstname').val();
    let surname = $('#student-edit-surname').val();
    let middlename = $('#student-edit-middlename').val();

    let hobby = $('#student-edit-hobby').val();
    let birthday = $('#student-edit-birthday').val();

    let healthgroup = $('#student-edit-health-group').val();
    let gymgroup = $('#student-edit-gym-group').val();

    let diseases = $('#student-edit-diseases').val();

    let digit = $('#student-edit-group-digit').val();
    let letter = $('#student-edit-group-letter').val();
    console.log(digit + letter);

    if(empty(firstname) || empty(surname) || empty(middlename) || empty(hobby) || empty(birthday) || empty(diseases)) {
        $('#login-error').html('Заполните все поля').transition('fade in');
    } else {
        $.ajax({
            type: "POST",
            url: "/student/"+id+"/save",
            data: {
                firstname: firstname,
                surname: surname,
                middlename: middlename,
                hobby: hobby,
                birthday: birthday,
                healthGroup: healthgroup,
                gymGroup: gymgroup,
                diseases: diseases,
                familyChildCount: familyChildCount,
                groupTitle: digit + letter
            },
            success: function(response) {
                console.log(response);
                $('#modal-student-edit').modal('hide');
            },
            error: function(response) {
                console.log(response);
            }
        });
    }
});