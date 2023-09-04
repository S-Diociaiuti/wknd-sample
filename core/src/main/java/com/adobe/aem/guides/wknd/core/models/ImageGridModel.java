/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.classes.ImageGrid;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageGridModel {

    @SlingObject
    private ResourceResolver resourceResolver;

    @SlingObject
    private Resource currentResource;


    public ArrayList<ImageGrid> getData() {
        ArrayList<ImageGrid> items = new ArrayList<>();
        Resource adventurePage = resourceResolver.getResource("/content/wknd/language-masters/en/adventures");
        Iterator<Resource> adventurePageIterator = adventurePage.listChildren();
        while (adventurePageIterator.hasNext()){
            ImageGrid imageGrid = new ImageGrid();
            Resource page = adventurePageIterator.next();
            imageGrid.setTitle(page.getName());
            imageGrid.setLink(page.getPath());
            Iterator<Resource> pageIterator = page.listChildren().next().listChildren().next().listChildren().next().listChildren();
            while (pageIterator.hasNext()){
                Resource component = pageIterator.next();
                if (component.getName().equals("carousel")) {
                    Resource item = component.listChildren().next();
                    imageGrid.setFileReference(item.getValueMap().get("fileReference").toString());
                    items.add(imageGrid);
                }
            }
        }
        return items;
    }
}