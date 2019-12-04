package edu.lab.back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.lab.back.json.JsonPojo;
import edu.lab.back.json.response.ErrorMessageJson;
import lombok.NonNull;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class BaseHttpServlet extends HttpServlet {

    protected void writeStringResult(
        @NonNull final String res,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(res);
        outputStream.close();
    }

    protected void writeResult(
        final JsonPojo pojo,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        if (pojo == null) {
            this.writeStringResult("", response);
        } else {
            final String jsonString = mapper.writeValueAsString(pojo);
            this.writeStringResult(jsonString, response);
        }
    }

    protected void writeResult(
        @NonNull final List<? extends JsonPojo> pojos,
        @NonNull final HttpServletResponse response
    ) throws IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(pojos);

        this.writeStringResult(jsonString, response);
    }

    protected <JsonType extends JsonPojo> JsonType readRequest(
        @NonNull final HttpServletRequest req,
        Class<JsonType> clazz
    ) throws IOException
    {
        final ServletInputStream inputStream = req.getInputStream();
        final ObjectMapper mapper = new ObjectMapper();
        final JsonType result = mapper.readValue(inputStream, clazz);

        return result;
    }

    protected void writeErrorObject(
        @NonNull final Object errObj,
        @NonNull final HttpServletResponse response,
        @NonNull final Integer responseStatus
    ) throws IOException
    {
        response.setStatus(responseStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final ObjectMapper om = new ObjectMapper();
        final ServletOutputStream outputStream = response.getOutputStream();
        om.writeValue(outputStream, errObj);
    }

    protected void writeErrorJson(
        @NonNull final String errMsg,
        @NonNull final HttpServletResponse response,
        @NonNull final Integer responseStatus
    ) throws IOException
    {
        response.setStatus(responseStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final ObjectMapper om = new ObjectMapper();
        final ServletOutputStream outputStream = response.getOutputStream();

        final ErrorMessageJson errorJson = new ErrorMessageJson(errMsg);
        om.writeValue(outputStream, errorJson);
    }

    protected void writeError(
            @NonNull final String errMsg,
            @NonNull final HttpServletResponse response,
            @NonNull final Integer responseStatus
    ) throws IOException
    {
        response.setStatus(responseStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(errMsg);
        outputStream.close();
    }

}
