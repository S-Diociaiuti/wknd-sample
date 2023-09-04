package com.adobe.aem.guides.wknd.core.servlet;

import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;


@Component(service = {Servlet.class}, property = {
        "service.description=My Sample Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.resourceTypes=sling/servlet/default",
        "sling.servlet.extensions=json",
        "sling.servlet.selectors=mysampleservlet"
})
public class MySampleServlet extends SlingAllMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(MySampleServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        JsonObject jsonResult = new JsonObject();

        if(firstName.isEmpty() && lastName.isEmpty() && email.isEmpty()){
            jsonResult.addProperty("firstName", false);
            jsonResult.addProperty("lastName", false);
            jsonResult.addProperty("email", false);
        } else {
            jsonResult.addProperty("firstName", true);
            jsonResult.addProperty("lastName", true);
            jsonResult.addProperty("email", true);
        }
        writeResponse(response, jsonResult);
    }

    private void writeResponse(SlingHttpServletResponse response, JsonObject jsonResult) throws IOException {
        response.setStatus(SlingHttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResult.toString());
        response.getWriter().close();
    }
}
