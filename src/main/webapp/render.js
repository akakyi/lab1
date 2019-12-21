function drawCityes(cityes) {
    let col = $("#cityPlaceholder");
    col.empty();

    cityes.forEach((ct) => {
        drawCity(ct, col)
    });
}

function drawCity(ct, col) {
    col.append($('<div/>', {
            'class': 'cell'
        }).append($('<div/>', {
            'class': 'cityName',
            'data-id': ct.id,
            'text': ct.name,
            'click': () => {
                getSchoolsByCityId(ct.id, drawSchools)
            },
            'change': () => {
                sendEditedCityToServer(ct.id, this);//отправляем изменения на сервер
            }
        })).append($('<span/>', {
            'class': 'edit',
            'text': 'редактировать',//метод редактирования, функция внизу
            'click': () => {
                editCity(ct)
            }
        })
        )
    );
}


function drawSchools(schools) {
    let col = $("#schoolPlaceholder");
    col.empty();

    schools.forEach((schl) => {
        drawSchool(schl, col)
    });
}

function drawSchool(schl, col) {
    col.append($('<div/>', {
            'class': 'cell',

        }).append($('<div/>', {
            'class': 'schoolName',
            'data-id': schl.id,
            'text': schl.name,
            'click': () => {
                getProfilesBySchoolId(schl.id, drawStudents)
            },
            'change': () => {
                sendEditedSchoolToServer(schl.id, this);//отправляем изменения на сервер
            }
        })).append($('<span/>', {
            'class': 'edit',
            'text': 'редактировать',//метод редактирования, функция внизу
            'click': () => {
                editSchool(schl)
            }
        })
        )
    );
}


function drawStudents(students) {
    let col = $("studentPlaceholder");
    col.empty();

    students.forEach((stdnt) => {
        drawStudent(stdnt, col);
    });
}

function drawStudent(stdnt, col) {
    col.append($('<div/>', {
            'class': 'cell',

        }).append($('<div/>', {
            'class': 'sstudentName',
            'data-id': stdnt.id,
            'text': stdnt.name,
            'change': () => {
                sendEditedStudentToServer(stdnt.id, this);//отправляем изменения на сервер
            }
        })).append($('<span/>', {
            'class': 'edit',
            'text': 'редактировать',//метод редактирования, функция внизу
            'click': () => {
                editStudent(stdnt)
            }
        })
        )
    );
}

function editCity(elem) {
    let $this = $(elem);
    $this.closest('.cityName').prop('contenteditable', true);
}

function editSchool(elem) {
    let $this = $(elem);
    $this.closest('.schoolName').prop('contenteditable', true);
}

function editStudent(elem) {
    let $this = $(elem);
    $this.closest('.studentName').prop('contenteditable', true);
}


function sendEditedSchoolToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/school",
        type: "PUT",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.prop('contenteditable', false); //посылаем запрос на изменение в сити и студент поменяй если что id и name на нужные параметры
        },
        error: onError,
    })
}

function sendEditedCityToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/city",
        type: "PUT",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.prop('contenteditable', false);
        },
        error: onError,
    })
}

function sendEditedStudentToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/profile",
        type: "PUT",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.prop('contenteditable', false);
        },
        error: onError,
    })
}

function sendDeleteCity(id, elem) { // удаление города снизу тож самое для студента и школы
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/city",
        type: "DELETE",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.closest('cell').remove();
            $this.remove();
        },
        error: onError,
    })
}


function sendDeleteSchool(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/school",
        type: "DELETE",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.closest('.cell').remove();
            $this.remove()
        },
        error: onError,
    })
}

function sendDeleteStudent(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    $.ajax({
        url: "crud/profile",
        type: "DELETE",
        data: {
            id: id,
            name: content,
        },
        success: function () {
            $this.closest('.cell').remove();
            $this.remove()
        },
        error: onError,
    })
}

