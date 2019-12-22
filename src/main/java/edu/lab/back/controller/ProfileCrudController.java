package edu.lab.back.controller;

import edu.lab.back.json.request.ProfileRequestJson;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.crud.ProfileCrudService;
import edu.lab.back.service.validator.ProfileValidator;
import edu.lab.back.util.UrlPatterns;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
    ProfileCrudController.CONTROLLER_BASE_URL
})
@NoArgsConstructor
public class ProfileCrudController extends BaseHttpServlet {

    public static final String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile";

    private static final String ID_PATH_VARIABLE_NAME = "id";

    @Inject
    private ProfileCrudService profileCrudService;

    @Inject
    private ProfileValidator profileValidator;

    @Override
    protected void doGet(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String idString = req.getParameter(ID_PATH_VARIABLE_NAME);

        if (idString != null) {
            try {
                final ProfileResponseJson profile = this.profileCrudService.getById(idString);
                this.writeResult(profile, resp);
            } catch (InvalidPayloadException e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
            } catch (ResourceNotFound e) {
                this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            final List<ProfileResponseJson> allProfiles = this.profileCrudService.getAll();
            this.writeResult(allProfiles, resp);
        }
    }

    @Override
    protected void doPost(
        @NonNull final HttpServletRequest req,
        @NonNull final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        try {
            final ProfileRequestJson profileJson = this.readRequest(req, ProfileRequestJson.class);
            this.profileValidator.validateSave(profileJson);
            final ProfileResponseJson saved = this.profileCrudService.save(profileJson);
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
            final ProfileRequestJson profileJson = this.readRequest(req, ProfileRequestJson.class);
            this.profileValidator.validateUpdate(profileJson);
            final ProfileResponseJson updated = this.profileCrudService.update(profileJson);
            this.writeResult(updated, resp);
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
            final ProfileResponseJson deleted = this.profileCrudService.deleteById(idString);
            this.writeResult(deleted, resp);
        } catch (InvalidPayloadException e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_BAD_REQUEST);
        } catch (ResourceNotFound e) {
            this.writeErrorJson(e.getMessage(), resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
