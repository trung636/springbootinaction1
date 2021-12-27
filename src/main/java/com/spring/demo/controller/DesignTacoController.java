package com.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.demo.model.Ingredient;
import com.spring.demo.model.Ingredient.Type;
import com.spring.demo.model.Order;
import com.spring.demo.model.Taco;
import com.spring.demo.repository.IngredientRepository;
import com.spring.demo.repository.TacoRepository;
import com.spring.demo.util.Util;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);

	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping()
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), Util.filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}

	@PostMapping
	public String processDesign(Taco design, @ModelAttribute Order order) {
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		return "redirect:/orders/current";
	}
}
//	@GetMapping
//	public String showDesignForm(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//		Type[] types = Ingredient.Type.values();
//		for (Type type : types) {
//			model.addAttribute(type.toString().toLowerCase(), Util.filterByType(ingredients, type));
//		}
//		model.addAttribute("design", new Taco());
//		return "design";
//	}