package edu.lab.back.controller;

import edu.lab.back.service.crud.SchoolCrudService;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/school")
@NoArgsConstructor
public class SchoolController extends HttpServlet {

    @Inject
    private SchoolCrudService schoolCrudService;

    @Override
    protected void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String test = this.schoolCrudService.getById(1L);
        final ServletOutputStream outputStream = resp.getOutputStream();

        outputStream.print(test);

        outputStream.flush();
        outputStream.close();
    }
}
