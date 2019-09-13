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
package com.aem.community.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;

@Model(adaptables = Resource.class)
public class UtilityNavigation {

	private static final Logger LOGGER = Logger.getLogger(UtilityNavigation.class.getName());

	@ChildResource(injectionStrategy = InjectionStrategy.OPTIONAL)
	private Resource utilityNavigationLinks;

	private List<UtilityNavigationItem> utilityNavigationItems = new ArrayList<>();

	@PostConstruct
	protected void init() {

		if (utilityNavigationLinks != null) {
			Iterator<Resource> iterator = utilityNavigationLinks.listChildren();
			while (iterator.hasNext()) {
				Resource value = iterator.next();
				UtilityNavigationItem utilityNavigationItem = value.adaptTo(UtilityNavigationItem.class);
				utilityNavigationItems.add(utilityNavigationItem);
			}
		}
	}

	public List<UtilityNavigationItem> getUtilityNavigationItems() {
		return utilityNavigationItems;
	}

}
