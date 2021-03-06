
$('.btn-student-single').click(function () {

    let id = $(this).attr('id').split('preview').pop();
    $.ajax({
        type: "GET",
        url: "/student/"+id,
        success: function(response) {
            console.log('SUCCESS: ' + response);
            let student = $.parseJSON(response);

            if(student.isError === undefined) {
                $('#student-single-name').html(student.surname + ' ' + student.firstname + ' ' + student.middlename);
                $('#student-single-avatar').attr('src', student.avatarPath);

                $('#student-single-group').html('Учащийся ' + student.group_title + ' класса');
                $('#student-single-age').html(student.age);
                $('#student-single-childcount').html(student.familyChildCount);
                $('#student-single-hobby').html(student.hobby);
                $('#student-single-diseases').html(student.diseases);
                $('#student-single-birthday').html(student.birthday);
                $('#student-single-healthgroup').html(getHealthGroup(student));
                $('#student-single-gymgroup').html(getGymGroup(student));

                let parentsHTML = "Информация отсутствует";
                if(student.parents.length !== 0) {
                    parentsHTML = "";
                    $.each(student.parents, function(index, parent){

                        if (parent.firstname !== '' && parent.surname !== '' && parent.middlename !== '' && parent.number !== '')
                        {
                            parentsHTML+=
                                "<p>" + parent.surname + " " + parent.firstname + " " + parent.middlename + "<br>" +
                                "<b>Место работы</b><br>" + parent.job + "<br>" +
                                "<b>Телефон </b>" + parent.number +
                                "</p>";
                        }
                    });
                }
                $('#student-single-parents').html(parentsHTML);

                $('#student-single-modal').modal('show');
            }
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});

function getHealthGroup(student) {
    switch(student.healthgroup) {
        case 'FIRST': return 'Первая';
        case 'SECOND': return 'Вторая';
        case 'THIRD': return 'Третья';
        default: return 'Неизвестно';
    }
}

function getGymGroup(student) {
    switch (student.gymgroup) {
        case 'MAIN': return 'Основная';
        case 'PREPARE': return 'Подготовительная';
        case 'FREE': return 'Освобождение';
        case 'THERAPY': return 'ЛФК';
        case 'SPECIAL': return 'СМГ';
        default: return 'Неизвестно';
    }
}