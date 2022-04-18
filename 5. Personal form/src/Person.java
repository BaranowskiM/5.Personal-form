public class Person {

    private Integer room;
    private String firstName;
    private String lastName;
    private Integer startTime;
    private Integer endTime;
    private Integer workingHours;

    public Person(String firstName, String lastName, Integer room, Integer startTime, Integer endTime, Integer workingHours){
        this.room = room;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workingHours = workingHours;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getWorkingHours() {return getEndTime() - getStartTime();}

}
