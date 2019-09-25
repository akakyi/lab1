package edu.lab.back.controller;

import edu.lab.back.json.CityJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.util.UrlPatterns;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
    CityController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class CityController extends HttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/city";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private CityCrudService cityCrudService;

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
            final CityJson city = this.cityCrudService.getById(idString);
            this.writeStringResult(city.toJsonString(), resp);
            return;
        } else {

        }
    }

    @Override
    public void doPost(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
    }

    @Override
    protected void doPut(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        super.doDelete(req, resp);
    }

    private void writeStringResult(
        @NonNull final String res,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(res);
        outputStream.close();
    }

}
