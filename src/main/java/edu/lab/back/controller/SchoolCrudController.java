package edu.lab.back.controller;

import edu.lab.back.json.request.SchoolRequestJson;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.crud.SchoolCrudService;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = SchoolCrudController.CONTROLLER_BASE_URL)
@NoArgsConstructor
public class SchoolCrudController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private SchoolCrudService schoolCrudService;

    @Inject
    private SchoolValidator validator;

    @Override
    protected void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final SchoolResponseJson school = this.schoolCrudService.getById(idString);
                this.writeResult(school, resp);
            } catch (InvalidPayloadException e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
            } catch (ResourceNotFound e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
            }
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
        try {
            final SchoolRequestJson schoolRequestJson = this.readRequest(req, SchoolRequestJson.class);
            this.validator.validateSave(schoolRequestJson);
            final SchoolResponseJson saved = this.schoolCrudService.save(schoolRequestJson);
            this.writeResult(saved, resp);
        } catch (IOException e) {
            this.writeErrorJson(ValidationMessages.INVALID_REQUEST_JSON, resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final SchoolRequestJson schoolRequestJson = this.readRequest(req, SchoolRequestJson.class);
            this.validator.validateUpdate(schoolRequestJson);
            final SchoolResponseJson saved = this.schoolCrudService.update(schoolRequestJson);
            this.writeResult(saved, resp);
        } catch (IOException e) {
            this.writeErrorJson(ValidationMessages.INVALID_REQUEST_JSON, resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (ResourceNotFound e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        try {
            final SchoolResponseJson deleted = this.schoolCrudService.deleteById(idString);
            this.writeResult(deleted, resp);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (ResourceNotFound e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
        } catch (EJBException e) {
            //Костыль, нужен более подробный ексепшн
            this.writeErrorJson(ValidationMessages.CONTRAIN_VIOLATION, resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
