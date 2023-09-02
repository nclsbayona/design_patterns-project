package com.patrones.taller1.service.persistance.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.patrones.taller1.entity.orders.Order;
import com.patrones.taller1.entity.products.ProductTypes;
import com.patrones.taller1.entity.products.composite.IProduct;
import com.patrones.taller1.entity.products.composite.Menu;
import com.patrones.taller1.entity.products.composite.SimpleProduct;
import com.patrones.taller1.entity.providers.Provider;
import com.patrones.taller1.service.persistance.IPersistance;
import com.patrones.taller1.service.persistance.database.adapters.IAdapter;

@Service
public class DatabaseService implements IPersistance {

    IAdapter adapter = DatabaseFactory.getDefaultDBAdapter();

    @Override
    public boolean save(IProduct product) {
        boolean result = true;
        try {
            if (product instanceof SimpleProduct) {
                try (Connection con = adapter.getConnection()) {
                    String sql = "INSERT INTO tblProduct(product_id, product_name, product_price, provider_id) VALUES (?,?,?,?);";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, product.getId());
                    statement.setString(2, product.getName());
                    statement.setDouble(3, product.getPrice());
                    statement.setString(4, product.getProvider().getId());
                    statement.execute();
                }
            } else if (product instanceof Menu) {
                try (Connection con = adapter.getConnection()) {
                    Menu menu = (Menu) product;
                    String sql = "INSERT INTO tblMenu(menu_id, menu_name, provider_id) VALUES (?,?,?);";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, product.getId());
                    statement.setString(2, product.getName());
                    statement.setString(4, product.getProvider().getId());
                    statement.execute();
                    List<IProduct> children = menu.getChildren();
                    if (children.size() > 0) {
                        for (int i = 0; i < children.size(); ++i) {
                            IProduct child = children.get(i);
                            if (child instanceof SimpleProduct) {
                                this.save(child);
                                sql = "INSERT INTO tblProductForMenu(product_id, menu_id) VALUES (?,?);";
                                statement = con.prepareStatement(sql);
                                statement.setString(1, child.getId());
                                statement.setString(2, menu.getName());
                                statement.execute();
                            }
                        }
                    }
                }
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean save(String order_id, String client_id) {
        boolean result = true;
        try (Connection con = adapter.getConnection()) {
            String sql = "INSERT INTO tblOrder(order_id, client_id, payment_registered) VALUES (?,?, 'no');";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, order_id);
            statement.setString(2, client_id);
            statement.execute();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<IProduct> getProducts() {
        List<IProduct> products = new ArrayList<>();
        try (Connection con = this.adapter.getConnection()) {
            // SimpleProducts
            String sql = "SELECT product_id FROM tblProduct; ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(this.getSimpleProduct(rs.getString("product_id")));
            }

            // Menus
            sql = "SELECT menu_id FROM tblMenu; ";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                products.add(this.getMenu(rs.getString("menu_id")));
            }
        } catch (Exception e) {
            products.clear();
        }
        return products;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection con = this.adapter.getConnection()) {
            String sql = "SELECT order_id FROM tblOrder; ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(this.getOrder(rs.getString("order_id")));
            }
        } catch (Exception e) {
            orders.clear();
        }
        return orders;
    }

    @Override
    public SimpleProduct getSimpleProduct(String id) {
        SimpleProduct product = null;
        try (Connection con = this.adapter.getConnection()) {
            String sql = "SELECT prod.product_id AS product_id, prod.product_name AS product_name, prod.product_price AS product_price, prod.product_image AS product_image, prov.provider_id AS provider_id, prov.provider_name AS provider_name, prov.provider_image AS provider_image FROM tblProduct prod INNER JOIN tblProvider prov ON prod.provider_id = prov.provider_id WHERE prod.product_id = ?; ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = SimpleProduct.builder().id(rs.getString("product_id")).name(rs.getString("product_name"))
                        .image(rs.getString("product_image")).price(rs.getDouble("product_price"))
                        .provider(Provider.builder().id(rs.getString("provider_id")).name(rs.getString("provider_name"))
                                .image(rs.getString("provider_image")).build())
                        .build();
            }

        } catch (Exception e) {
        }
        return product;
    }

    @Override
    public Menu getMenu(String id) {
        Menu product = null;
        try (Connection con = this.adapter.getConnection()) {
            String sql = "SELECT menu.menu_id AS menu_id, menu.menu_name AS menu_name, menu.menu_image AS menu_image, prov.provider_id AS provider_id, prov.provider_name AS provider_name, prov.provider_image AS provider_image FROM tblMenu menu INNER JOIN tblProvider prov ON menu.provider_id = prov.provider_id WHERE menu.menu_id = ?; ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu(rs.getString("menu_id"), rs.getString("menu_name"), rs.getString("menu_image"),
                        new Provider(rs.getString("provider_id"), rs.getString("provider_name"),
                                rs.getString("provider_image")));
                sql = "SELECT prod.product_id AS product_id, prod.product_name AS product_name, prod.product_price AS product_price, prod.product_image AS product_image, prov.provider_id AS provider_id, prov.provider_name AS provider_name, prov.provider_image AS provider_image FROM tblProduct prod INNER JOIN tblProvider prov ON prod.provider_id = prov.provider_id, tblProductForMenu prodxmenu WHERE prod.product_id = prodxmenu.product_id AND prodxmenu.menu_id = ?; ";
                statement = con.prepareStatement(sql);
                statement.setString(1, menu.getId());
                ResultSet rs2 = statement.executeQuery();
                while (rs2.next()) {
                    menu.addChildren(
                            SimpleProduct.builder().id(rs2.getString("product_id")).name(rs2.getString("product_name"))
                                    .image(rs2.getString("product_image")).price(rs2.getDouble("product_price"))
                                    .provider(Provider.builder().id(rs2.getString("provider_id"))
                                            .name(rs2.getString("provider_name")).image(rs2.getString("provider_image"))
                                            .build())
                                    .build());
                }
                product = menu;
            }

        } catch (Exception e) {
        }
        return product;
    }

    @Override
    public Order getOrder(String id) {
        Order order = null;
        try (Connection con = this.adapter.getConnection()) {
            String sql = "SELECT order_id, client_id FROM tblOrder WHERE order_id = ?; ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getString("order_id"), rs.getString("client_id"));
                sql = "SELECT product_id FROM tblProductForOrder WHERE order_id = ?; ";
                statement = con.prepareStatement(sql);
                statement.setString(1, order.getId());
                ResultSet rs2 = statement.executeQuery();
                while (rs2.next())
                    order.addProduct(this.getSimpleProduct(rs2.getString("product_id")));
                sql = "SELECT menu_id FROM tblMenuForOrder WHERE order_id = ?; ";
                statement = con.prepareStatement(sql);
                statement.setString(1, order.getId());
                rs2 = statement.executeQuery();
                while (rs2.next())
                    order.addProduct(this.getMenu(rs2.getString("menu_id")));
            }
        } catch (Exception e) {
        }
        return order;
    }

    @Override
    public Order addProductToOrder(String order_id, String product_id, ProductTypes type) {
        try (Connection con = this.adapter.getConnection()) {
            if (type == ProductTypes.SimpleProduct) {
                String sql = "INSERT INTO tblProductForOrder(product_id, order_id) VALUES (?,?);";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, product_id);
                statement.setString(2, order_id);
                statement.execute();
            } else if (type == ProductTypes.Menu) {
                String sql = "INSERT INTO tblMenuForOrder(menu_id, order_id) VALUES (?,?);";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, product_id);
                statement.setString(2, order_id);
                statement.execute();
            }
        } catch (Exception e) {
        }
        return this.getOrder(order_id);
    }

    @Override
    public String getNextOrderID() {
        String order = null;
        try (Connection con = this.adapter.getConnection()) {
            String sql = "SELECT order_id FROM tblOrder ORDER BY order_id DESC LIMIT 1; ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order = rs.getString("order_id");
            }
            if (order == null) {
                order = "O1";
            } else {
                int number = Integer.parseInt(order.substring(1));
                order = order.charAt(0) + String.valueOf(number + 1);
            }
        } catch (Exception e) {
        }
        return order;
    }

    @Override
    public Order payOrder(String id) {
        try (Connection con = this.adapter.getConnection()) {
            String sql = "UPDATE tblOrder SET payment_registered = 'yes' WHERE order_id = ?; ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
        }
        return this.getOrder(id);
    }
}