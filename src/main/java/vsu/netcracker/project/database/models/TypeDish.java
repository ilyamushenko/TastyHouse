package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TypeDish")
public class TypeDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_dish_id")
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @OneToMany
    @JoinColumn(name = "type_dish_id", insertable = false, updatable = false)
    private List<Dishes> dishes;

    public TypeDish() {

    }

    public TypeDish(String title, List<Dishes> dishes) {
        this.title = title;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDish typeDish = (TypeDish) o;
        return getId().equals(typeDish.getId()) &&
                getTitle().equals(typeDish.getTitle()) &&
                getDishes().equals(typeDish.getDishes());
    }
}
