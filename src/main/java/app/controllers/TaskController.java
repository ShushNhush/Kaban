package app.controllers;

import app.entities.Task;
import app.entities.Team;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.TaskMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class TaskController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {


        app.post("movetask", ctx -> moveTask(ctx, connectionPool));


//        app.post("addtask", ctx -> addTask(ctx, connectionPool));
//        app.post("done", ctx -> done(ctx, true, connectionPool));
//        app.post("undo", ctx -> done(ctx, false, connectionPool));
//        app.post("deletetask", ctx -> deleteTask(ctx, connectionPool));

    }

    private static void moveTask(Context ctx, ConnectionPool connectionPool) {

        Team team = ctx.sessionAttribute("teamname");

        try {
            int taskId = Integer.parseInt(ctx.formParam("taskId"));
            int lifecycle = Integer.parseInt(ctx.formParam("lifecycleId"));
            TaskMapper.changeCycle(taskId, lifecycle, connectionPool);
            List<Task> taskList = TaskMapper.getAllTasksPerTeam(team.getTeamId(), connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("dashboard.html");

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }


//    private static void done(Context ctx, boolean done,ConnectionPool connectionPool) {
//
//        User user = ctx.sessionAttribute("currentUser");
//        try {
//            int taskId = Integer.parseInt(ctx.formParam("taskId"));
//            TaskMapper.setDoneTo(done, taskId, connectionPool);
//            List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
//            ctx.attribute("taskList", taskList);
//            ctx.render("task.html");
//        } catch (NumberFormatException e) {
//
//            ctx.attribute("message", "You need to send a number");
//            ctx.render("task.html");
//        } catch (DatabaseException e) {
//            ctx.attribute("message", e.getMessage());
//        }
//    }

//    private static void addTask(Context ctx, ConnectionPool connectionPool) {
//
//        String taskName = ctx.formParam("taskname");
//        if (taskName.length() > 3) {
//
//            User user = ctx.sessionAttribute("currentUser");
//            try {
//                Task newTask = TaskMapper.addTask(user, taskName, connectionPool);
//                List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
//                ctx.attribute("taskList", taskList);
//                ctx.render("task.html");
//            } catch (DatabaseException e) {
//
//                ctx.attribute("message", "Something went wrong, try again !");
//
//            }
//        }
//
//    }
}
