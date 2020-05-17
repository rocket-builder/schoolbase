function empty(text) {
    return text === '';
}

$(function() {

    $("#parent-add-number").mask("+375 (99) 999 99 99", {placeholder: " " });
    $("#parent-add-number2").mask("+375 (99) 999 99 99", {placeholder: " " });
});

let studentId;
$('.btn-parents-modal').click(function () {
    studentId = $(this).attr('id').split('parents').pop();

    $('#modal-parent-add').modal('show');
});

$('#btn-parent-add').click(function () {

    let firstname = $('#parent-add-firstname').val();
    let surname = $('#parent-add-surname').val();
    let middlename = $('#parent-add-middlename').val();
    let job = $('#parent-add-job').val();
    let number = $('#parent-add-number').val();

    let firstname2 = $('#parent-add-firstname2').val();
    let surname2 = $('#parent-add-surname2').val();
    let middlename2 = $('#parent-add-middlename2').val();
    let job2 = $('#parent-add-job2').val();
    let number2 = $('#parent-add-number2').val();

    if (empty(firstname) || empty(surname) || empty(middlename) || empty(job) || empty(number)) {
        console.log('Fill all fields');
    } else {
        $.ajax({
            type: "POST",
            url: "/parent/"+studentId+"/add",
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
                $('#modal-parent-add').modal('hide');
            },
            error: function(response) {
                console.log(response);
            }
        });
    }
});