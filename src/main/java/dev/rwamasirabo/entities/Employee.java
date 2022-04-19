package dev.rwamasirabo.entities;

public class Employee {

    private int emplid;
    private String firstName;
    private String lastName;
  public Employee(){

  }

    public Employee(int emplid, String firstName, String lastName) {
        this.emplid = emplid;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getEmplid() {
        return emplid;
    }

    public void setEmplid(int emplid) {
        this.emplid = emplid;
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

    @Override
    public String toString() {
        return "Employee{" +
                "emplid=" + emplid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}