package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.TypeDish;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class MenuController {
	@Autowired
	private DishesDAO dishesDAO;

	@GetMapping("menu/{dishType}")
	public List<Dishes> showTables(@PathVariable String dishType) {
		List<Dishes> dishes =  dishesDAO.findAll();
		List<Dishes> selectedDish = new ArrayList<>();
		for (Dishes elem: dishes) {
			TypeDish elemType = elem.getTypeDish();
			if (elemType.getTitle().equals(dishType)) {
				selectedDish.add(elem);
			}
		}
		return selectedDish;
	}
}
