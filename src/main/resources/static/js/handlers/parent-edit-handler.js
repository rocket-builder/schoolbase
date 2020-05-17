function empty(text) {
    return text === '';
}
$(function() {

    $("#parent-edit-number").mask("+375 (99) 999 99 99", {placeholder: " " });
    $("#parent-edit-number2").mask("+375 (99) 999 99 99", {placeholder: " " });
});

let studentEditId;
$('.btn-parents-modal-update').click(function () {

    studentEditId = $(this).attr("id").split('parents').pop();
    $.ajax({
        type: "GET",
        url: "/student/"+studentEditId+"/parent/get",
        success: function(response) {
            console.log('SUCCESS: ' + response);
            let student = $.parseJSON(response);

            if(student.isError === undefined) {

                let parent = student.parents[0];
                let parent2 = student.parents[1];

                $('#parent-edit-firstname').val(parent.firstname);
                $('#parent-edit-surname').val(parent.surname);
                $('#parent-edit-middlename').val(parent.middlename);
                $('#parent-edit-job').val(parent.job);
                $('#parent-edit-number').val(parent.number);

                $('#parent-edit-firstname2').val(parent2.firstname);
                $('#parent-edit-surname2').val(parent2.surname);
                $('#parent-edit-middlename2').val(parent2.middlename);
                $('#parent-edit-job2').val(parent2.job);
                $('#parent-edit-number2').val(parent2.number);

                $('#modal-parent-edit').modal('show');
            }
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});

$('#btn-parent-edit').click(function () {

    let firstname = $('#parent-edit-firstname').val();
    let surname = $('#parent-edit-surname').val();
    let middlename = $('#parent-edit-middlename').val();
    let job = $('#parent-edit-job').val();
    let number = $('#parent-edit-number').val();

    let firstname2 = $('#parent-edit-firstname2').val();
    let surname2 = $('#parent-edit-surname2').val();
    let middlename2 = $('#parent-edit-middlename2').val();
    let job2 = $('#parent-edit-job2').val();
    let number2 = $('#parent-edit-number2').val();

    if (empty(firstname) || empty(surname) || empty(middlename) || empty(job) || empty(number)) {
        console.log('Fill all fields');
    } else {
        $.ajax({
            type: "POST",
            url: "/parent/"+studentEditId+"/edit",
            data: {
                firstname: firstname,
                surname: surname,
                middlename: middlename,
                job: job,
                number: number,

                firstname2: empty(firstname2)? '' : firstname2,
                surname2: empty(surname2)? '' : surname2,
                middlename2: empty(middlename2)? '' : middlename2,
                job2: empty(job2)? '' : job2,
                number2: empty(number2)? '' : number2
            },
            success: function(response) {
                console.log(response);
                $('#modal-parent-edit').modal('hide');
            },
            error: function(response) {
                console.log(response);
            }
        });
    }
});