package by.epam.onlineshop.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Category")
public class Category {

    public Category(){
    }

    private Category(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 2, max = 30)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                name.equals(category.name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +'}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);


    }
}
