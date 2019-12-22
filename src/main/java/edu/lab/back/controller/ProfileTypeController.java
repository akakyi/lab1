package edu.lab.back.controller;

import edu.lab.back.json.ProfileTypeJson;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.util.UrlPatterns;
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

@WebServlet(urlPatterns = ProfileTypeController.CONTROLLER_BASE_URL)
@NoArgsConstructor
public class ProfileTypeController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile_type";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private ProfileTypeCrudService profileTypeCrudService;

    @Override
    public void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final ProfileTypeJson profileType = this.profileTypeCrudService.getById(idString);
                this.writeResult(profileType, resp);
            } catch (InvalidPayloadException e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
            } catch (ResourceNotFound e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            final List<ProfileTypeJson> allTypes = this.profileTypeCrudService.getAll();
            this.writeResult(allTypes, resp);
        }
    }

}
