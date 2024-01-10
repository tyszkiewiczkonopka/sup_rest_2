package models;

public class Student {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;


    public Student() {
    }

    public Student(int id, String first_name, String middle_name, String last_name, String date_of_birth) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }
}
