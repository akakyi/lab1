package edu.lab.back.controller;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.service.validator.CityValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    CityCrudController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class CityCrudController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/city";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private CityCrudService cityCrudService;

    @Inject
    private CityValidator validator;

    /**
     * Возвращает города. Если передан параметр id, то вернёт только один объект.
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @throws ServletException  if the request for the GET
     *                                  could not be handled
     */
    @Override
    public void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final CityResponseJson city = this.cityCrudService.getById(idString);
                this.writeResult(city, resp);
            } catch (InvalidPayloadException e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
            } catch (ResourceNotFound e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            final List<CityResponseJson> allCities = this.cityCrudService.getAll();
            this.writeResult(allCities, resp);
        }
    }

    @Override
    public void doPost(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final CityRequestJson cityJson = this.readRequest(req, CityRequestJson.class);
            this.validator.validateSave(cityJson);
            final CityResponseJson saved = this.cityCrudService.save(cityJson);
            this.writeResult(saved, resp);
        } catch (IOException e) {
            this.writeErrorJson(ValidationMessages.INVALID_REQUEST_JSON, resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final CityRequestJson cityJson = this.readRequest(req, CityRequestJson.class);
            this.validator.validateUpdate(cityJson);
            final CityResponseJson updated = this.cityCrudService.update(cityJson);
            this.writeResult(updated, resp);
        } catch (IOException e) {
            this.writeErrorJson(ValidationMessages.INVALID_REQUEST_JSON, resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);
        try {
            final CityResponseJson deleted = this.cityCrudService.deleteById(idString);
            this.writeResult(deleted, resp);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (ResourceNotFound e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
