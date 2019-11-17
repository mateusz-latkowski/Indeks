package com.example.indeks;

public class LessonPlanInformation {
    private String Room;
    private String Subject;
    private String Teacher;
    private String Time;

    public LessonPlanInformation() {}

    public LessonPlanInformation(String room, String subject, String teacher, String time) {
        Room = room;
        Subject = subject;
        Teacher = teacher;
        Time = time;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
