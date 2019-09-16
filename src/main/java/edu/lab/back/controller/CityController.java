package edu.lab.back.controller;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.service.crud.CityCrudService;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
@NoArgsConstructor
public class CityController extends HttpServlet {

    @Inject
    private CityCrudService cityCrudService;

    public String test() {
        final CityEntity city = this.cityCrudService.getById(2L);
        return city.toString();
    }

    @Override
    public void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException
    {
        final String test = this.test();
        final ServletOutputStream outputStream = resp.getOutputStream();

        outputStream.print(test);

        outputStream.flush();
        outputStream.close();
    }
}
