/**
 * Represents a user with a unique ID and a priority level.
 */
class MyUser {
    Integer id;
    Integer priority;

    /**
     * Constructs a MyUser instance with the given ID and priority.
     * 
     * @param id       The unique ID of the user.
     * @param priority The priority level of the user.
     */
    public MyUser(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Retrieves the user's ID.
     * 
     * @return The user's ID.
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Retrieves the user's priority level.
     * 
     * @return The user's priority.
     */
    public Integer getPriority() {
        return this.priority;
    }
}