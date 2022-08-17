package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping (path = "static-filtering")
	public SecuredBean retrieveStaticFilteredBean() {
		return new SecuredBean("value1", "value2", "securedValue");
	}
	
	@GetMapping (path = "static-filtering-list")
	public List<SecuredBean> retrieveStaticFilteredBeanList() {
		return Arrays.asList(new SecuredBean("value1", "value2", "securedValue"),
				new SecuredBean("value12", "value22", "securedValue2"));
	}
	
	//only return field1, field2
	@GetMapping (path = "dynamic-filtering")
	public MappingJacksonValue retrieveDynamicFilteredBean() {
		SecuredBean bean = new SecuredBean("value1", "value2", "securedValue");
		return createFilter("bean-filter", bean, "field1", "field2");
	}
	
	//only return field2, field3
		@GetMapping (path = "dynamic-filtering-list")
	public MappingJacksonValue retrieveDynamicFilteredBeanList() {
		List<SecuredBean> beanList = Arrays.asList(new SecuredBean("value1", "value2", "securedValue"),
				new SecuredBean("value12", "value22", "securedValue2"));

		return createFilter("bean-filter", beanList, "field2", "field3");
	}
		
	public MappingJacksonValue createFilter(String beanFilterName, Object holder, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter(beanFilterName, filter);
		MappingJacksonValue mapping = new MappingJacksonValue(holder);
		mapping.setFilters(filters);
		return mapping;
	}

}
