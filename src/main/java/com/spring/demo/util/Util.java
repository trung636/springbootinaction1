package com.spring.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.spring.demo.model.Ingredient;
import com.spring.demo.model.Ingredient.Type;

public class Util {

	public static Object filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ings = new ArrayList<Ingredient>();
		for (Ingredient ing : ingredients) {
			if (ing.getType().equals(type)) {
				ings.add(ing);
			}
		}
		return ings;
	}
}
