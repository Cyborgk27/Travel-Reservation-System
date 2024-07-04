package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.DbConnection;
import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 *
 * @author CyborgK27
 */
public class UserRepository implements IUserRepository {
    private final DbConnection dbConnection;

    public UserRepository() {
        this.dbConnection = new DbConnection();
    }
    @Override
    public Future<User> login(String email, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var conn = dbConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM User WHERE email = ? AND password = ?");
                pstmt.setString(1, email);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    User user = User.builder()
                            .id(rs.getInt("id"))
                            .state(rs.getInt("state"))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .roleId(rs.getInt("role_id"))
                            .build();
                    // Aquí puedes cargar el rol del usuario si es necesario
                    return user;
                } else {
                    return null; // O lanza una excepción si prefieres
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                dbConnection.disconnect();
            }
        }) ;
   }

    @Override
    public Future<Boolean> registerUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
