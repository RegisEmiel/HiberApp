import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "shopcart_id")
    private ShopCart shopCart;

    @Column(name = "nik_name")
    private String nik_name;


    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String nik_name, String email) {
        this.nik_name = nik_name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShopCart getShopCart() {
        return shopCart;
    }

    public void setShopCart(ShopCart shopCart) {
        this.shopCart = shopCart;
    }

    public String getNik_name() {
        return nik_name;
    }

    public void setNik_name(String nik_name) {
        this.nik_name = nik_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nik_name='" + nik_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
