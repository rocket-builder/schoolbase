
let teacherId;
$('.btn-teacher-edit').click(function () {

    teacherId = $(this).attr('id').split('teacherEdit').pop();
    $.ajax({
        type: "GET",
        url: "/teacher/"+teacherId+"/get",
        success: function(response) {
            console.log('SUCCESS: ' + response);
            let teacher = $.parseJSON(response);

            if(teacher.isError === undefined) {

                $('#teacher-edit-firstname').val(teacher.firstname);
                $('#teacher-edit-surname').val(teacher.surname);
                $('#teacher-edit-middlename').val(teacher.middlename);
                $('#teacher-edit-number').val(teacher.number);

                let grouptitle = teacher.grouptitle;
                let result = grouptitle.match(/\d{1,2}/);
                let digit = result[0];
                let letter = grouptitle[grouptitle.length-1];

                console.log(digit + ' ' + letter);
                $("#teacher-edit-group-digit").dropdown('set exactly', [digit]);
                $("#teacher-edit-group-letter").dropdown('set exactly', [letter]);

                $('#modal-teacher-edit').modal('show');
            }
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});

$('#btn-teacher-edit').click(function () {

    let firstname = $('#teacher-edit-firstname').val();
    let surname = $('#teacher-edit-surname').val();
    let middlename = $('#teacher-edit-middlename').val();

    let number = $('#teacher-edit-number').val();

    let digit = $('#teacher-edit-group-digit').val();
    let letter = $('#teacher-edit-group-letter').val();

    $.ajax({
        type: "POST",
        url: "/teacher/"+teacherId+"/save",
        data: {
            firstname: firstname,
            surname: surname,
            middlename: middlename,
            number: number,
            grouptitle: digit + letter
        },
        success: function(response) {
            console.log('SUCCESS: ' + response);
            $('#modal-teacher-edit').modal('hide');
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});