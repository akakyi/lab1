package edu.lab.back.service.crud;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;

import java.util.List;

public interface CityCrudService {

    CityResponseJson getById(String idString);

    List<CityResponseJson> getAll();

    CityResponseJson deleteById(String idString);

    CityResponseJson save(CityRequestJson cityJson);

    CityResponseJson update(CityRequestJson cityJson);

}
