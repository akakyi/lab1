package edu.lab.back.controller;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolCrudService;
import edu.lab.back.util.UrlPatterns;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = SchoolController.CONTROLLER_BASE_URL)
@NoArgsConstructor
public class SchoolController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private SchoolCrudService schoolCrudService;

    @Override
    protected void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            final SchoolResponseJson city = this.schoolCrudService.getById(idString);
            this.writeStringResult(city.toJsonString(), resp);
            return;
        } else {
            final List<SchoolResponseJson> allCities = this.schoolCrudService.getAll();
            this.writeResult(allCities, resp);
        }
    }

    @Override
    protected void doPost(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final SchoolRequestJson schoolRequestJson = this.readRequest(req, SchoolRequestJson.class);
        final SchoolResponseJson saved = this.schoolCrudService.save(schoolRequestJson);
        this.writeStringResult(saved.toJsonString(), resp);
    }

    @Override
    protected void doPut(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final SchoolRequestJson schoolRequestJson = this.readRequest(req, SchoolRequestJson.class);
        final SchoolResponseJson saved = this.schoolCrudService.update(schoolRequestJson);
        this.writeStringResult(saved.toJsonString(), resp);
    }

    @Override
    protected void doDelete(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        final SchoolResponseJson schoolResponseJson = this.schoolCrudService.deleteById(idString);
        this.writeStringResult(schoolResponseJson.toJsonString(), resp);
    }
}
