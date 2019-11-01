package edu.lab.back.controller;

import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.SchoolAdvancedGettingService;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    SchoolByCityController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class SchoolByCityController extends BaseHttpServlet {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school_of_city";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private SchoolAdvancedGettingService schoolAdvancedGettingService;

    @Override
    public void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final List<SchoolResponseJson> schools = this.schoolAdvancedGettingService.getSchoolsByCityId(idString);
                this.writeResult(schools, resp);
            } catch (InvalidPayloadException e) {
                this.writeValidationError(e.getMessage(), resp);
            }
        } else {
            this.writeValidationError(ValidationMessages.INVALID_PATH_VARIABLE, resp);
        }
    }

}
