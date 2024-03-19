package app.entities;

public class Task {

    private int taskId;
    private String title;
    private String description;
    private String user;
    private int usId;
    private int lifecycleId;
    private int teamId;

    public Task(int taskId, String title, String description, String user, int usId, int lifecycleId, int teamId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.user = user;
        this.usId = usId;
        this.lifecycleId = lifecycleId;
        this.teamId = teamId;
    }

    public Task(String title, String description, String user, int usId, int lifecycleId, int teamId) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.usId = usId;
        this.lifecycleId = lifecycleId;
        this.teamId = teamId;
    }

    public Task(String title, String description, int lifecycleId, int teamId) {
        this.title = title;
        this.description = description;
        this.lifecycleId = lifecycleId;
        this.teamId = teamId;
    }

    public Task(String title, String description, int teamId) {
        this.title = title;
        this.description = description;
        this.teamId = teamId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUser() {
        return user;
    }

    public int getUsId() {
        return usId;
    }

    public int getLifecycleId() {
        return lifecycleId;
    }

    public int getTeamId() {
        return teamId;
    }
}
