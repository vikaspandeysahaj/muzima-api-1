/**
 * Copyright 2012 Muzima Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muzima.api.dao.impl;

import com.muzima.api.dao.FormDao;
import com.muzima.api.model.Form;
import com.muzima.search.api.filter.Filter;
import com.muzima.search.api.filter.FilterFactory;
import com.muzima.search.api.util.StringUtil;
import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormDaoImpl extends OpenmrsDaoImpl<Form> implements FormDao {

    private static final String TAG = FormDaoImpl.class.getSimpleName();

    protected FormDaoImpl() {
        super(Form.class);
    }

    /**
     * Get form by the name of the form. Passing empty string will returns all registered forms.
     *
     * @param name the partial name of the form or empty string.
     * @return the list of all matching form on the form name.
     * @throws ParseException when parsing lucene query in the internal saving process happen.
     * @throws IOException    when reading resource descriptor happen.
     */
    @Override
    public List<Form> getByName(final String name) throws ParseException, IOException {
        List<Filter> filters = new ArrayList<Filter>();
        if (!StringUtil.isEmpty(name)) {
            Filter filter = FilterFactory.createFilter("name", name + "*");
            filters.add(filter);
        }
        return service.getObjects(filters, daoClass);
    }
}