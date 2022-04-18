package dev.rwamasirabo.entities;

public class Employee {

    private int empl_id;
    private String first_name;
    private String last_name;
  public Employee(){

  }

    public Employee(int empl_id, String first_name, String last_name) {
        this.empl_id = empl_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(int empl_id) {
        this.empl_id = empl_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
