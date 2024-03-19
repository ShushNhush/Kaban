package app.controllers;

import app.entities.Task;
import app.entities.Team;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.TaskMapper;
import app.persistence.TeamMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class TeamController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));

    }

    private static void logout(Context ctx) {

        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    private static void login(Context ctx, ConnectionPool connectionPool) {


        String teamName = ctx.formParam("teamname");
        String password = ctx.formParam("password");

        try {

            Team team = TeamMapper.login(teamName, password, connectionPool);
            ctx.sessionAttribute("teamname", team);
            List<Task> taskList = TaskMapper.getAllTasksPerTeam(team.getTeamId(),connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("dashboard.html");


        } catch (DatabaseException e) {

            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }

    }
}
