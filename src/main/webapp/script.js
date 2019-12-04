$(document).ready(function() {
	let baseUrl = 'http://localhost:8080/lab1';

	$('.addcity').on('click', function(e) {
		e.preventDefault(); //Это если ссылка
		$.post(baseUrl + '/crud/city', JSON.stringify({name: 'ds_fr_fr'}), function (data) {
			console.log(data); //Посмотреть в консоли ответ
			let html = $('<div/>', {
				'class': 'row'
			}).append($('<div/>', {
				'class': 'cell',
				'text': data.name // Повторять конструкцию append пока все ячейки нужные не закончишь
			}));
			$(".school").append(html);

		}, 'json');
	});
	$('.addschool').on('click', function(e) {
		e.preventDefault(); //Это если ссылка
		$.get('http://www.json-generator.com/api/json/get/ceRvLAhUKq?indent=2', function (data) {
			console.log(data); //Посмотреть в консоли ответ
			let html = $('<div/>', {
				'class': 'row'
			}).append($('<div/>', {
				'class': 'cell',
				'text': data[0].balance // Повторять конструкцию append пока все ячейки нужные не закончишь
			}));
			$(".students").append(html);

		});
	});
});