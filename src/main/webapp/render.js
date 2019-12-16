function drawSchools(schools) {
    let col = $("#schoolPlaceholder");
    col.empty();

    schools.forEach((schl) => {
        col.append($('<div/>', {
                'class': 'cell',
                'text': schl.name,
                // 'id': data.id,
                // 'click': () => {
                //     getSchoolsByCityId(data.id, drawSchools)
                // }
            }))
    });
}

function drawCityes(cityes) {
    let col = $("#cityPlaceholder");
    col.empty();

    cityes.forEach((ct) => {
        col.append($('<div/>', {
            'class': 'cell',
            'text': ct.name,
            'click': () => {
                getSchoolsByCityId(ct.id, drawSchools)
            }
        }))
    });
}