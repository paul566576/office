package aimprosoft.departments.model;

import java.util.Objects;

public class Department {
    private int id;
    private String name;

    public Department() {}

    public Department(int id, String name) {
        this.id = id;
        this.name = name;

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

    public String toString() {
       return "Department{" +
               "id: " + id +
               ", name: " + name +
               "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
