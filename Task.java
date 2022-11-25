import java.util.Date;

public class Task {

    private String name;
    private String priority; // High/Medium/Low is suggested.
    private Date created; // The date you add the task to your list.
    private boolean complete; // Marking task as complete -> is false by default.

    public Task(){} // No argument Task class constructor

    public Task(String name, String priority, Date created){ // Task class parameterized constructor
        this.name = name;
        this.priority = priority;
        this.created = created;
        this.complete = false; // New task is incomplete by default as you are unlikely to add a task which you have already complete.
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCreated() {
        return created;
    } // Date the task was first created.

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


    public String toString() {
        String strComplete;
        if (!complete) {
            strComplete = "In Progress";
        } else {
            strComplete = "Complete";
        }
        // This allows each task to be well formatted and easily readable for the user.
        return  name + "\n" + //
                "Priority: " + priority + "\n" +
                "Date Created: " + created + "\n" +
                "Status: " + strComplete + "\n";
    }
}
