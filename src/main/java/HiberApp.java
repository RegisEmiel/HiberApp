import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class HiberApp {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ShopCart.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        EntityManager entityManager = factory.createEntityManager();

        // Создание двух продуктов
        entityManager.getTransaction().begin();
        Product product_1 = new Product("Product_1", 100);
        Product product_2 = new Product("Product_2", 150);
        entityManager.persist(product_1);
        entityManager.persist(product_2);
        entityManager.getTransaction().commit();

        // Создание списка из двух продуктов
        List<Product> lProd = new ArrayList<>();
        lProd.add(product_1);
        lProd.add(product_2);

        // Создание корзины
        ShopCart shopCart = new ShopCart("100000001");
        entityManager.getTransaction().begin();
        entityManager.persist(shopCart);
        entityManager.getTransaction().commit();

        // Создание пользователя и привязка ему корзины
        User user = new User("Petya", "petya@yandex.ru");
        user.setShopCart(shopCart);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        // Добавление списка товаров в корзину
        shopCart.setProducts(lProd);
        entityManager.getTransaction().commit();


        // Получение данных из базы по пользователю, его корзины и товаров из корзины
        entityManager.getTransaction().begin();
        User u = entityManager.find(User.class, 1L);
        System.out.println(u.toString());
        ShopCart cart = u.getShopCart();
        System.out.println(cart);

        List<Product> products = cart.getProducts();
        for (Product p: products) {
            System.out.println(p.toString());
        }
        entityManager.getTransaction().commit();

        factory.close();
    }
}
