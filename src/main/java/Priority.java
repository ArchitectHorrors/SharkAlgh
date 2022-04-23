public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1),
    UNDEFINED(0);

    private final int priority;

    Priority(int priority){
        this.priority = priority;
    }

    public int getPriorityCode() {
        return priority;
    }
}
