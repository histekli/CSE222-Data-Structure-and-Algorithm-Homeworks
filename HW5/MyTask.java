/**
 * Represents a task associated with a user and a unique task ID.
 */
class MyTask implements Comparable<MyTask> {
    MyUser user;
    Integer id;

    /**
     * Constructs a MyTask instance with the given user and task ID.
     * 
     * @param user   The user associated with the task.
     * @param taskId The unique ID of the task.
     */
    public MyTask(MyUser user, Integer id) {
        this.user = user;
        this.id = id;
    }

    /**
     * Compares this task with another task based on the user's priority.
     * 
     * @param other The other task to compare to.
     * @return A negative integer, zero, or a positive integer as this task's
     *         priority is less than, equal to, or greater than the other task's
     *         priority.
     */
    @Override
    public int compareTo(MyTask other) {
        int priorityComparison = this.user.getPriority().compareTo(other.user.getPriority());
        if (priorityComparison == 0) {
            return this.id.compareTo(other.id);
        }
        return priorityComparison;
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return A string containing the task ID and associated user's ID and
     *         priority.
     */
    @Override
    public String toString() {
        return "Task " + id + " User " + user.getID();
    }
}