$(document).ready(function() {

    getAllCityes(drawCityes);

	let baseUrl = 'http://localhost:8080/lab1';

	$('#addCityButton').on('click', function(e) {
		e.preventDefault(); //Это если ссылка      TODO снизу заглушка. Поменять!!!!
		$.post(baseUrl + '/crud/city', JSON.stringify({name: 'ds_fr_fr'}), function (data) {
			console.log(data); //Посмотреть в консоли ответ
            $("#cityPlaceholder").append($('<div/>', {
				'class': 'cell',
				'text': data.name,
				// 'id': data.id,
				'click': () => {
                    getSchoolsByCityId(data.id, drawSchools)
                }
			}));
			// $("#cityColumn").append(html);

		}, 'json');
	});
});