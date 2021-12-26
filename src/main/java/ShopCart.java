import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopcarts")
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "shopCart")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shopcart_details",
            joinColumns = @JoinColumn(name = "shopcart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "cart_number")
    private String cart_number;

    public ShopCart() {
    }

    public ShopCart(String cart_number) {
        this.cart_number = cart_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCart_number() {
        return cart_number;
    }

    public void setCart_number(String cart_number) {
        this.cart_number = cart_number;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", cart_number='" + cart_number + '\'' +
                '}';
    }
}
