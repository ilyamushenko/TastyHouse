package vsu.netcracker.project.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.TypeDish;
import vsu.netcracker.project.utils.HibernateUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class MenuController {
	@Autowired
	private DishesDAO dishesDAO;

	@Autowired
	private OrdersDAO ordersDAO;

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

	@PostMapping("/buy") // ToDo - как достать номер столика, с которого заказал посетитель?
    public void buyDish(@RequestBody Map<String, Object> json) {
        /* Передаем сюда блюдо - к заказу из метода getFirstEmptyTable добавляем новый dishesFromOrder -
         в этот dishesFromOrder передаем блюдо и др. необходимые параметры */
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            String name = (String) json.values().toArray()[0];
            Long tableNumber = (Long) json.values().toArray()[1];
            Orders order = ordersDAO.findByTableNumber(tableNumber);
            Dishes dish = new Dishes();
            for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
            	if (dishFromOrder.getDishesSet().getName().equals(name)) {
					dish = dishFromOrder.getDishesSet();
					break;
				}
			}
            Time realTime = dish.getPreparingTime();
            DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, "В ожидании",
                    session.load(Orders.class, Objects.requireNonNull(order).getId()),
                    session.load(Dishes.class, dish.getId()));
            session.save(dishesFromOrder);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    @PostMapping("/remove")
    public void removeDish(@RequestBody Map<String, Object> json) {
        /* Передаем сюда блюдо - из заказа с getFirstEmptyTable удаляем ненужный dishesFromOrder */
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			String name = (String) json.values().toArray()[0];
			Long tableNumber = (Long) json.values().toArray()[1];
			Orders order = ordersDAO.findByTableNumber(tableNumber);
			Dishes dish = new Dishes();
			for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
				if (dishFromOrder.getDishesSet().getName().equals(name)) {
					dish = dishFromOrder.getDishesSet();
					break;
				}
			}
			Time realTime = dish.getPreparingTime();
			DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, "В ожидании",
					session.load(Orders.class, Objects.requireNonNull(order).getId()),
					session.load(Dishes.class, dish.getId()));
			session.delete(dishesFromOrder);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
