$(document).ready(function () {

    getAllCityes(drawCityes);

    let baseUrl = 'http://localhost:8080/lab1';

    $('#addCityButton').on('click', function (e) {
        e.preventDefault(); //Это если ссылка      TODO снизу заглушка. Поменять!!!!
        $.post(baseUrl + '/crud/city', JSON.stringify({name: 'ds_fr_fr'}), function (data) {
            console.log(data); //Посмотреть в консоли ответ
            let col = $("#cityPlaceholder");
            drawCity(data, col)
        }, 'json');
    });
});

