package com.adobe.aem.guides.wknd.core.servlet;

import com.adobe.aem.guides.wknd.core.utils.SearchUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.List;

@Component(service = {Servlet.class}, property = {
        "service.description=Search Utils Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.resourceTypes=sling/servlet/default",
        "sling.servlet.extensions=json",
        "sling.servlet.selectors=searchutilsservlet"
})
public class SearchUtilsServlet extends SlingAllMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(MySampleServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            String path = request.getParameter("path");
            String propertyName = request.getParameter("propertyName");
            String propertyValue = request.getParameter("propertyValue");
            String isXPath = request.getParameter("isXPath");
            JsonObject jsonResult = new JsonObject();

            if (StringUtils.isNotBlank(path) && StringUtils.isNotBlank(propertyName) && StringUtils.isNotBlank(propertyValue)) {
                List<Resource> list = SearchUtils.getResults((request.getResourceResolver()), path, propertyName, propertyValue, Boolean.parseBoolean(isXPath));

                for (Resource r : list) {
                    jsonResult.addProperty(r.getPath(), r.getValueMap().toString());
                }

                writeResponse(response, jsonResult);
            } else {
                response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonParseException e) {
            logger.error("Could not get JSON", e.getMessage());
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void writeResponse(SlingHttpServletResponse response, JsonObject jsonResult) throws IOException {
        response.setStatus(SlingHttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResult.toString());
        response.getWriter().close();
    }
}
