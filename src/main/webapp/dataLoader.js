let baseUrl = "lab1";

function getSchoolsByCityId(cityId, callback = drawCityes, onError = _defaultOnError, cont = this) {
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
    let data = [{"profilesIds": [2, 4, 1], "id": 1, "name": "Школа №46", "city_id": 1}, {
        "profilesIds": [3],
        "id": 2,
        "name": "Лицей №51",
        "city_id": 1
    }, {"profilesIds": [], "id": 11, "name": "valid_school", "city_id": 1}];
    callback(data);
}

function getProfilesBySchoolId(schoolId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    // $.ajax({
    //     url: "profiles_of_school",
    //     type: "GET",
    //     data: {
    //         id: schoolId
    //     },
    //     success: callback ,
    //     error: onError,
    //     content: cont
    // })
    let data = [{
        "id": 2,
        "name": "Не Миша",
        "age": 99,
        "profile_type": {"id": 2, "name": "Учитель"},
        "class_level": null,
        "school_id": 1
    }, {
        "id": 4,
        "name": "valid_prof",
        "age": 123,
        "profile_type": {"id": 2, "name": "Учитель"},
        "class_level": null,
        "school_id": 1
    }, {
        "id": 1,
        "name": "Миша",
        "age": 23,
        "profile_type": {"id": 1, "name": "Студент"},
        "class_level": "11",
        "school_id": 1
    }];
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
    let data = [{"id": 2, "name": "default city 2", "schools_ids": [3, 6]}, {
        "id": 11,
        "name": "idea_test",
        "schools_ids": []
    }, {"id": 20, "name": "valid_city", "schools_ids": []}, {
        "id": 17,
        "name": "valid_city",
        "schools_ids": [4, 10]
    }, {"id": 22, "name": "ds_fr_fr", "schools_ids": []}, {
        "id": 23,
        "name": "Руссконазванный",
        "schools_ids": []
    }, {"id": 1, "name": "default city 1 upd", "schools_ids": [1, 2, 11]}];
    callback(data);
}

function getAllProfiles(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    // $.ajax ({
    //     url: "crud/profile",
    //     type: "GET",
    //     success: callback,
    //     error: onError,
    //     content: cont
    // })
    let data = [{
        "id": 2,
        "name": "Не Миша",
        "age": 99,
        "profile_type": {"id": 2, "name": "Учитель"},
        "class_level": null,
        "school_id": 1
    }, {
        "id": 3,
        "name": "Таня",
        "age": 22,
        "profile_type": {"id": 1, "name": "Студент"},
        "class_level": "11",
        "school_id": 2
    }, {
        "id": 4,
        "name": "valid_prof",
        "age": 123,
        "profile_type": {"id": 2, "name": "Учитель"},
        "class_level": null,
        "school_id": 1
    }, {
        "id": 1,
        "name": "Миша",
        "age": 23,
        "profile_type": {"id": 1, "name": "Студент"},
        "class_level": "11",
        "school_id": 1
    }];
    callback(data);
}

function getAllProfileTypes(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let data = [{"id": 1, "name": "Студент"}, {"id": 2, "name": "Учитель"}];
    callback(data);
}

function sendEditedSchoolToServer() {
    let dialog = $("#updateSchoolDialog");
    let id = dialog.attr("e_id");
    let cityId = dialog.attr("p_id");
    let name = $("#schoolNameUpd").val();
    let data = {id: id, name: name, city_id: cityId};
    // let $this = $('body').find('.schoolName[data-id="'+ id +'"]');
    // let content = $this.html();
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
    // })
    console.log(data);
}

function sendEditedCityToServer() {
    let id = $("#updateCityDialog").attr("e_id");
    let name = $("#cityNameUpd").val();
    let data = {id: id, name: name};
    // let $this = $('body').find('.cityName[data-id="'+ id +'"]');
    // let content = $this.html();
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
    //
    // });
    // console.log("fefed");
    console.log(data);
}

function sendEditedStudentsToServer() {
    let dialog = $("#updateProfileDialog");
    let id = dialog.attr("e_id");
    let sch_id = dialog.attr("p_id");
    let name = $("#profileNameUpd").val();
    let age = $("#profileAgeUpd").val();
    let typeId = $("#profileTypeUpd").val();
    let classLevel = $("#classLevelUpd").val();
    let data = {id: id, name: name, age: age, profile_type_id: typeId, class_level: classLevel, school_id: sch_id};
    // let $name = $('body').find('.studentName[data-id="'+ elem +'"]');
    // let $age = $('body').find('.studentAge[data-id="'+ elem +'"]');
    // let $profile = $('body').find('.studentProfile[data-id="'+ elem +'"]');
    // let $level = $('body').find('.studentLevel[data-id="'+ elem +'"]');
    //
    // $.ajax({
    //     url: "crud/profile",
    //     type: "PUT",
    //     data: {
    //         id: elem,
    //         name: $name.html(),
    //         age: $age.html(),
    //         profile:$profile.html(),
    //         level: $level.html(),
    //
    //     },
    //     success: function () {
    //         $this.prop('contenteditable', false);
    //     },
    // })
    console.log(data);
}

function sendDeleteCity(id, elem) { // удаление города снизу тож самое для студента и школы
    // let $this = $('body').find('.cityName[data-id="'+ id +'"]');
    // let content = $this.html();
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
    // })
    alert(elem)
}


function sendDeleteSchool(id, elem) {
    // let $this = $('body').find('.schoolName[data-id="'+ id +'"]');
    // let content = $this.html();
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
    // })
    alert(elem)
}

function sendDeleteStudent(id, elem) {
    // let $this = $('body').find('.studentName[data-id="'+ id +'"]');
    // let content = $this.html();
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
    // })
    alert(elem)
}

function _defaultCallback(data) {
    console.log(data)
}

function _defaultOnError(jqXHR, textStatus, errorThrown) {
    let errorObj = JSON.parse(jqXHR.responseText);
    alert(errorObj.message);
}