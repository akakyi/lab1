package edu.lab.back.service.crud;

import edu.lab.back.json.JsonPojo;

import java.util.List;

public interface BaseCrudService<RequestJsonType extends JsonPojo, ResponseJsonType extends JsonPojo> {

    ResponseJsonType getById(String idString);

    List<ResponseJsonType> getAll();

    ResponseJsonType deleteById(String idString);

    ResponseJsonType save(RequestJsonType cityJson);

    ResponseJsonType update(RequestJsonType cityJson);

}
