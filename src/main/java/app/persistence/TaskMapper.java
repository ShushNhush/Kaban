package app.persistence;

import app.entities.Task;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper
{

    public static List<Task> getAllTasksPerTeam(int teamId, ConnectionPool connectionPool) throws DatabaseException {

        List<Task> taskList = new ArrayList<>();
        String sql = "SELECT * FROM c1_task WHERE team_id =? ORDER BY task_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("task_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String user = rs.getString("user");
                int usId = rs.getInt("us_id");
                int lifecycleId = rs.getInt("lifecycle_id");
                taskList.add(new Task(id, title, description, user, usId, lifecycleId, teamId));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return taskList;
    }

    public static void changeCycle(int taskId, int lifecycleId, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "UPDATE public.c1_task SET lifecycle_id = ? WHERE task_id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1, lifecycleId);
            ps.setInt(2, taskId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("DB cant change lifecycle", e.getMessage());
        }
    }

}
