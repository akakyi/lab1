let baseUrl = "lab1";

function getSchoolsByCityId(cityId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "school_of_city",
        type: "GET",
        data: {
            id: cityId
        },
        success: callback,
        error: onError,
        content: cont
    })
}

function getAllCityes(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "crud/city",
        type: "GET",
        success: callback,
        error: onError,
        content: cont
    })
}

function _defaultCallback(data) {
    console.log(data)
}

function _defaultOnError(jqXHR, textStatus, errorThrown) {
    let errorObj = JSON.parse(jqXHR.responseText);
    alert(errorObj.message);
}