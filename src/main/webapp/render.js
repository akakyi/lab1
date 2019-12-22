var selectedCityId;
var selectedSchoolId;

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
                selectedCityId = ct.id;
                getSchoolsByCityId(ct.id, drawSchools)
            }
        })).append($('<button/>', {
            'class': 'edit',
            'text': 'редактировать',//метод редактирования, функция внизу
            'click': () => {
                editCityDialog(ct)
            }
        })
        ).append($('<button/>', {
            'class': 'delete',
            'text': 'удалить',//метод редактирования, функция внизу
            'click': () => {
                sendDeleteCity(ct.id)
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
                selectedSchoolId = schl.id;
                getProfilesBySchoolId(schl.id, drawProfiles)
            }
        })).append($('<button/>', {
            'class': 'edit',
            'text': 'редактировать',//метод редактирования, функция внизу
            'click': () => {
                editSchoolDialog(schl)
            }
        })
        ).append($('<button/>', {
            'class': 'delete',
            'text': 'удалить',//метод редактирования, функция внизу
            'click': () => {
                sendDeleteSchool(schl.id)
            }
        })
        )
    );
}


function drawProfiles(students) {
    let col = $("#studentPlaceholder");
    col.empty();

    students.forEach((stdnt) => {
        drawProfile(stdnt, col);
    });
}

function drawProfile(stdnt, col) {
    col.append($('<div/>', {
            'class': 'cell'
        }).append($('<div/>', {
            'class': 'studentName',
            'data-id': stdnt.id,
            'text': "Имя: " + stdnt.name
        })).append($('<div/>', {
            'class': 'studentAge',
            'data-id': stdnt.id,
            'text': "Возраст: " + stdnt.age
        }
        )).append($('<div/>', {
            'class': 'studentProfile',
            'data-id': stdnt.id,
            'text': "Должность: " + stdnt.profile_type.name
        })).append($('<div/>', {
            'class': 'studentLevel',
            'data-id': stdnt.id,
            'text': "Класс: " + stdnt.class_level == null ? "--" : stdnt.class_level
        }))
            .append($('<button/>', {
                    'class': 'edit',
                    'text': 'редактировать',//метод редактирования, функция внизу
                    'click': () => {
                        editStudentDialog(stdnt)
                    }
                })
            )
            .append($('<button/>', {
                    'class': 'delete',
                    'text': 'удалить',//метод редактирования, функция внизу
                    'click': () => {
                        sendDeleteStudent(stdnt.id)
                    }
                })
            )
    );
}

function editCityDialog(city) {
    // let id = city.id;
    // let elem = $('body').find('.cityName[data-id="'+ id +'"]');
    let updText = $("#cityNameUpd");
    updText.val(city.name);

    let updDialog = $("#updateCityDialog");
    updDialog.attr("e_id", city.id);
    updDialog.dialog("open");

}

function editSchoolDialog(schl) {
    // let id = schl.id;
    // let elem = $('body').find('.schoolName[data-id="'+ id +'"]');
    let updText = $("#schoolNameUpd");
    updText.val(schl.name);

    let updDialog = $("#updateSchoolDialog");
    updDialog.attr("e_id", schl.id);
    updDialog.attr("p_id", schl.city_id);
    updDialog.dialog("open");
}

function editStudentDialog(stdnt) {
    $("#profileNameUpd").val(stdnt.name);
    $("#profileAgeUpd").val(stdnt.age);
    $("#profileTypeUpd").val(stdnt.profile_type.id);
    $("#classLevelUpd").val(stdnt.class_level);

    let updDialog = $("#updateProfileDialog");
    updDialog.attr("e_id", stdnt.id);
    updDialog.attr("p_id", stdnt.school_id);
    updDialog.dialog("open");
}

function initUpdateDialogs() {
    let updCityDialog = $("#updateCityDialog");
    initUpdDialog(updCityDialog, sendEditedCityToServer);

    let updSchoolDialog = $("#updateSchoolDialog");
    initUpdDialog(updSchoolDialog, sendEditedSchoolToServer);

    let updProfileDialog = $("#updateProfileDialog");
    initUpdDialog(updProfileDialog, sendEditedStudentsToServer);

    getAllProfileTypes((data) => {
        setProfileTypes($("#profileTypeUpd"), data)
    })
}

function initUpdDialog(dialogElem, updateFunc) {
    dialogElem.dialog({
        autoOpen: false,
        modal: true,
        height: "auto",
        resizable: false,
        buttons: {
            "Обновить": () => {
                //TODO валидация!!!
                updateFunc();
                dialogElem.dialog("close");
            },
            "Отмена": () => {
                dialogElem.dialog("close");
            }
        }
    });
}

function setProfileTypes(optEl, types) {
    types.forEach((t) => {
        setProfileType(optEl, t)
    })
}

function setProfileType(optEl, type) {
    optEl.append($('<option/>', {
        'value': type.id,
        'text': type.name
    }));
}