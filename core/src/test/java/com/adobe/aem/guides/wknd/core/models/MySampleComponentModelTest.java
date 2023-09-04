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

import com.day.cq.wcm.api.Page;
import com.google.common.collect.ImmutableMap;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class MySampleComponentModelTest {

    private MySampleComponentModel model;

    private Page page;
    private Resource resource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {

        // prepare a page with a test resource
        page = context.create().page("/content/mypage");
        resource = context.create().resource(page, "SampleModel",
                new ValueMapDecorator(ImmutableMap.of(
                        "title", "titolo",
                        "description", "descrizione")));

        // create sling model
        model = resource.adaptTo(MySampleComponentModel.class);
    }

    @Test
    void testGetTitle() throws Exception {
        // some very basic junit tests
        String msg = model.getTitle();
        assertNotNull(msg);
        assertEquals("titolo", msg);
    }

    @Test
    void testGetDescription() throws Exception {
        // some very basic junit tests
        String msg = model.getDescription();
        assertNotNull(msg);
        assertEquals("descrizione", msg);
    }
}
