package edu.lab.back.controller;

import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.ProfileAdvancedGettingService;
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
    ProfileBySchoolController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class ProfileBySchoolController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profiles_of_school";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private ProfileAdvancedGettingService profileAdvancedGettingService;

    @Override
    public void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final List<ProfileResponseJson> profiles = this.profileAdvancedGettingService.getProfileBySchoolId(
                    idString
                );
                this.writeResult(profiles, resp);
            } catch (InvalidPayloadException e) {
                this.writeError(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
            } catch (ResourceNotFound e) {
                this.writeError(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.writeError(ValidationMessages.INVALID_PATH_VARIABLE, resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
