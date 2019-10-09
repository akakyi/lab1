package edu.lab.back.service.crud;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;

import java.util.List;

public interface SchoolCrudService {

    SchoolResponseJson getById(final String idStr);

    List<SchoolResponseJson> getAll();

    SchoolResponseJson deleteById(String idString);

    SchoolResponseJson save(SchoolRequestJson schoolJson);

    SchoolResponseJson update(SchoolRequestJson schoolJson);

}
