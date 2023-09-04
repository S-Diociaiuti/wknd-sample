package com.adobe.aem.guides.wknd.core.utils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

public class SearchUtils {

    private static final Logger log = LoggerFactory.getLogger(SearchUtils.class);

    private static String getQuery(String path, String propertyName, String propertyValue) {
        String property = "[@" + propertyName + "=" + "'"+ propertyValue +"']";
        String mainQuery = "/jcr:root" + path + "//*" + property;
        return mainQuery;
    }

    private static String getQuerySql(String path, String propertyName, String propertyValue) {
        String baseQuery = "SELECT * FROM [cq:Page] AS page";
        String property = " and [jcr:content/" + propertyName + "] =\"" + propertyValue +"\"";
        String whereCondition = " where ISDESCENDANTNODE (page,'" + path + "')" + property;
        return baseQuery + whereCondition;
    }

    public static List<Resource> getResults (ResourceResolver resolver, String path, String propertyName,
                                             String propertyValue, boolean isXPath) {
        List<Resource> list = new ArrayList<>();
        Session session = resolver.adaptTo(Session.class);
        QueryManager queryManager;
        QueryResult queryResult;
        NodeIterator nodes;
        Resource resource;
        Query finalQuery;
        Node node;

        if (session != null) {
            try {
                queryManager = session.getWorkspace().getQueryManager();
                finalQuery = isXPath ? queryManager.createQuery(getQuery(path, propertyName, propertyValue), "xpath") :
                        queryManager.createQuery(getQuerySql(path, propertyName, propertyValue),Query.JCR_SQL2);
                queryResult = finalQuery.execute();
                nodes = queryResult.getNodes();

                log.debug("FINAL QUERY: ", finalQuery.getStatement());

                while (nodes.hasNext()) {
                    node = (Node) nodes.next();

                    if (!isXPath) {
                        node = node.getNode("jcr:content");
                    }
                    resource = resolver.getResource(node.getPath());
                    list.add(resource);
                }
            } catch (PathNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvalidQueryException e) {
                throw new RuntimeException(e);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
