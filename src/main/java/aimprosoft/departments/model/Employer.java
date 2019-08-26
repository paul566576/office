package aimprosoft.departments.model;

import java.util.Date;
import java.util.Objects;

public class Employer {

    private int id;
    private String name;
    private int depId;
    private String email;
    private Date employedAt;

    public Employer() {}

    public Employer(int id, String name, int depId, String email, Date employedAt) {
        this.id = id;
        this.name = name;
        this.depId = depId;
        this.email = email;
        this.employedAt = employedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmployedAt() {
        return employedAt;
    }

    public void setEmployedAt(Date employedAt) {
        this.employedAt = employedAt;
    }

    @Override
    public String toString() {
        return "Employer {" +
                "id: " + id +
                ". name: " + name +
                ", dep_id: " + depId +
                ", email: " + email +
                ", employed ad: " + employedAt +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer)) return false;
        Employer employer = (Employer) o;
        return
                getDepId() == employer.getDepId() &&
                getName().equals(employer.getName()) &&
                getEmail().equals(employer.getEmail()) &&
                getEmployedAt().equals(employer.getEmployedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDepId(), getEmail(), getEmployedAt());
    }
}
