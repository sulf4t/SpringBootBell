package com.bellproject.domain;

import com.bellproject.entity.LineItem;
import com.bellproject.entity.Product;
import com.bellproject.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class will do all necessary queries to the database.
 */

public class DBQueries implements ProductDao, CartDao, UserRepository{

    private static DBQueries INSTANCE = null;
    final Connection conn;

        private DBQueries() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:test;Mode=MySql", "sa", "sa");
    }

    static public DBQueries getInstance() throws SQLException {
        if (INSTANCE == null)
        {
            INSTANCE = new DBQueries();
            INSTANCE.initTable();
        }
        return  INSTANCE;
    }

    private void initTable()
    {
        createTableProduct();
        createFewProducts();
    }

    private void createTableProduct() {
        String sqlCreateTableProduct = "CREATE TABLE PRODUCT ( id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), desc VARCHAR(255), PRICE FLOAT)" ;
        try( PreparedStatement ps = createPrepareStatement(sqlCreateTableProduct, conn);)
        {
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SqlException(e.getMessage());
        }
    }

    private void createFewProducts() {
        addProduct( new Product(1, "MacBook Pro", "Line of a Macintosh portable computers", 2400.00));
        addProduct( new Product(2, "Sony Vaio", "Line of a Sony portable computers", 2000.00 ));
        addProduct( new Product(3, "HP Envy", "Line of a HP portable computers", 1500.00));
    }


    @Override
    public Product findProductFrom(int id)
    {
        String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
        try( PreparedStatement ps = createPrepareStatement(sql, conn, id);
        ResultSet rs = ps.executeQuery();)
        {
            if (rs.next())
            {

                int prodId = rs.getInt("id");
                String name = rs.getString("Name");
                String desc = rs.getString("desc");
                double price = rs.getDouble("price");
                return new Product(prodId, name, desc, price);
            }
            return null;
        } catch (SQLException e) {
            throw new SqlException(e.getMessage());
        }
    }


    @Override
    public Collection<Product> getAllProduct() {
        String sql = "SELECT * FROM PRODUCT ";
        try( PreparedStatement ps = createPrepareStatement(sql, conn);
             ResultSet rs = ps.executeQuery();)
        {
            Collection<Product> prodList = new ArrayList<>();
            while (rs.next())
            {
                int prodId = rs.getInt("id");
                String name = rs.getString("Name");
                String desc = rs.getString("desc");
                double price = rs.getDouble("price");
                prodList.add(new Product(prodId, name, desc, price));
            }
            return prodList;
        } catch (SQLException e) {
            throw new SqlException(e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO PRODUCT (Name, desc, price) VALUES(?,?,?)";
        try( PreparedStatement ps = createPrepareStatement(sql, conn);)
        {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SqlException(e.getMessage());
        }
    }

    public static PreparedStatement createPrepareStatement(String sqlQuery, Connection conn, Object ... params) throws SQLException
    {
        PreparedStatement prepareStatement = conn.prepareStatement(sqlQuery);
        int i = 1;
        for (Object param : params )
        {
            if (param instanceof Integer)
                prepareStatement.setInt(i, (Integer)param);
            else if (param instanceof Double)
                prepareStatement.setDouble(i, (Double)param);
            else if (param instanceof String)
                prepareStatement.setString(i, (String)param);
            i++;
        }
        return prepareStatement;
    }

    //TODO To implement
    @Override
    public Collection<LineItem> getCartByUserId(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void removeProductFromCartByUserId(int userId, int productId) {
        throw new NotImplementedException();
    }

    @Override
    public void addProductFromCartBy(int userId, int productId) {
        throw new NotImplementedException();
    }

    @Override
    public User findUserFrom(int id) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<User> getAllUsers() {
        throw new NotImplementedException();
    }
}
