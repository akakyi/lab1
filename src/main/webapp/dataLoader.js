let baseUrl = "lab1";

function getSchoolsByCityId(cityId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    // $.ajax({
    //     url: "school_of_city",
    //     type: "GET",
    //     data: {
    //         id: cityId
    //     },
    //     success: callback ,
    //     error: onError,
    //     content: cont
    // })
    let data = [{"profilesIds":[1,2,4],"id":1,"name":"Школа №46"},{"profilesIds":[3],"id":2,"name":"Лицей №51"},{"profilesIds":[],"id":11,"name":"valid_school"}];
    callback(data);
}

function getProfilesBySchoolId(schoolID, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    // $.ajax({
    //     url: "profiles_of_school",
    //     type: "GET",
    //     data: {
    //         id: schoolID
    //     },
    //     success: callback ,
    //     error: onError,
    //     content: cont
    // })
    let data = [{"id":1,"name":"Миша","age":22,"profile_type":"Студент","class_level":"11"},{"id":2,"name":"Не Миша","age":99,"profile_type":"Учитель","class_level":null},{"id":4,"name":"valid_prof","age":123,"profile_type":"Учитель","class_level":null}];
    callback(data);
}

function getAllCityes(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    // $.ajax({
    //     url: "crud/city",
    //     type: "GET",
    //     success: callback,
    //     error: onError,
    //     content: cont
    // })
    let data = [{"id":1,"name":"default city 1","schools_ids":[1,2,11]},{"id":2,"name":"default city 2","schools_ids":[3,6]},{"id":11,"name":"idea_test","schools_ids":[]},{"id":20,"name":"valid_city","schools_ids":[]},{"id":17,"name":"valid_city","schools_ids":[4,10]},{"id":22,"name":"ds_fr_fr","schools_ids":[]},{"id":23,"name":"Руссконазванный","schools_ids":[]}];
    callback(data);
}

function getAllStudents (callback = _defaultCallback, onError = _defaultOnError, cont = this){
    // $.ajax ({
    //     url: "crud/profile",
    //     type: "GET",
    //     success: callback,
    //     error: onError,
    //     content: cont
    // })
    let data = [{"id":1,"name":"Миша","age":22,"profile_type":"Студент","class_level":"11"},{"id":2,"name":"Не Миша","age":99,"profile_type":"Учитель","class_level":null},{"id":3,"name":"Таня","age":22,"profile_type":"Студент","class_level":"11"},{"id":4,"name":"valid_prof","age":123,"profile_type":"Учитель","class_level":null}];
    callback(data);
}

function sendEditedSchoolToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/school",
    //     type: "PUT",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.prop('contenteditable', false); //посылаем запрос на изменение в сити и студент поменяй если что id и name на нужные параметры
    //     },
    //     error: onError,
    // })
    $this.prop('contenteditable', false);
}

function sendEditedCityToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/city",
    //     type: "PUT",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.prop('contenteditable', false);
    //     },
    //     error: onError,
    // })
    $this.prop('contenteditable', false);
}

function sendEditedStudentToServer(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/profile",
    //     type: "PUT",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.prop('contenteditable', false);
    //     },
    //     error: onError,
    // })
    $this.prop('contenteditable', false);
}

function sendDeleteCity(id, elem) { // удаление города снизу тож самое для студента и школы
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/city",
    //     type: "DELETE",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.closest('cell').remove();
    //         $this.remove();
    //     },
    //     error: onError,
    // })
    $this.closest('cell').remove();
    $this.remove();
}


function sendDeleteSchool(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/school",
    //     type: "DELETE",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.closest('.cell').remove();
    //         $this.remove()
    //     },
    //     error: onError,
    // })
    $this.closest('.cell').remove();
    $this.remove()
}

function sendDeleteStudent(id, elem) {
    let $this = $(elem);
    let content = $this[0].innerHTML;
    // $.ajax({
    //     url: "crud/profile",
    //     type: "DELETE",
    //     data: {
    //         id: id,
    //         name: content,
    //     },
    //     success: function () {
    //         $this.closest('.cell').remove();
    //         $this.remove()
    //     },
    //     error: onError,
    // })
    $this.closest('.cell').remove();
    $this.remove()

}


function _defaultCallback(data) {
    console.log(data)
}

function _defaultOnError(jqXHR, textStatus, errorThrown) {
    let errorObj = JSON.parse(jqXHR.responseText);
    alert(errorObj.message);
}