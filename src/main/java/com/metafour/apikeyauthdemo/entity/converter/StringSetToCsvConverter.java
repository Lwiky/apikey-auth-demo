package com.metafour.apikeyauthdemo.entity.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

public class StringSetToCsvConverter implements AttributeConverter<Set<String>, String>{

	@Override
	public String convertToDatabaseColumn(Set<String> attribute) {
		if (attribute == null) return null;
		else if (attribute.isEmpty()) return "";
		else attribute.stream().filter(a -> a != null && !a.isEmpty()).collect(Collectors.joining(","));
		
		return null;
	}

	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) return new TreeSet<>();
		else return new TreeSet<>(Arrays.asList(dbData.split(",")));
	}

}
