package Model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    /* ============
     *  PROPERTIES
     * ============ */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deptno", nullable = false)
    private Integer id;

    @Column(name = "deptname", nullable = false, length = 45)
    private String deptname;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @OneToMany(mappedBy = "deptnoFk")
    private Set<Employee> employees = new LinkedHashSet<>();

    /* ==============
     *  CONSTRUCTORS
     * ============== */

    public Department() {}

    public Department(String deptname, String location) {
        this.deptname = deptname;
        this.location = location;
    }

    /* ====================
     *  GETTERS AND SETTERS
     * =================== */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}