

$('#btn-student-add-modal').click(function () {

    $('#modal-student-add').modal('show');
});

$('.btn-student-delete-modal').click(function () {

    let id = $(this).attr('id').split('del').pop();
    $('#approve-modal').modal({
        onDeny    : function(){
            $(this).modal('hide');
        },
        onApprove : function() {
            $.ajax({
                type: "POST",
                url: "/student/delete",
                data: { id: id },
                success: function(response) {
                    console.log('SUCCESS: ' + response);
                    $('#approve-modal').modal('hide');
                    $('#student'+id).transition('fade out');
                },
                error: function(response) {
                    console.log('ERROR: ' + response);
                }
            });
        }
    })
        .modal('show');
});

$('.btn-teacher-delete').click(function () {

    let id = $(this).attr('id').split('teacherDelete').pop();
    $('#approve-modal').modal({
        onDeny    : function(){
            $(this).modal('hide');
        },
        onApprove : function() {
            $.ajax({
                type: "POST",
                url: "/teacher/delete",
                data: { id: id },
                success: function(response) {
                    console.log('SUCCESS: ' + response);
                    $('#approve-modal').modal('hide');
                    $('#teacher-content').transition('fade out');
                },
                error: function(response) {
                    console.log('ERROR: ' + response);
                }
            });
        }
    })
        .modal('show');
});

$('select.dropdown').dropdown();

$('.ui.radio.checkbox').checkbox();

$('.inp-avatar').change(function () {

    let id = $(this).attr('id').split('avatar').pop();
    let file = $(this)[0].files[0];
    let data = new FormData();
    data.append('file', file);
    $.ajax({
        type: "POST",
        url: "/student/"+id+"/avatar/add",
        processData: false,
        contentType: false,
        data: data,
        success: function(response) {
            response = $.parseJSON(response);
            let path = response.content;

            console.log(path);
            $('#photo'+id).css('background-image', 'url('+path+')');
        },
        error: function(response) {
            console.log('ERROR: ' + response);
        }
    });
});

$('.ui.search')
    .search({
        minCharacters : 3,
        apiSettings   : {
            onResponse: function(data) {
                let response = {
                    results: []
                };

                $.each(data, function (index, item) {
                    response.results.push(item);
                });
                response.results.reverse();
                return response;
            },
            url: '/student/search?match={query}'
        }
    })
;