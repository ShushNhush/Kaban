package app.persistence;

import app.entities.Team;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper {


    public static Team login(String teamName, String password, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "SELECT * FROM c1_team WHERE name=? AND password=?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, teamName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("team_id");
                return new Team(id, teamName, password);
            } else {
                throw new DatabaseException("Error on login");
            }

        } catch (SQLException e) {
            throw new DatabaseException("DB Error", e.getMessage());
        }
    }


}
