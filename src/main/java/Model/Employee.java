package Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    /* ============
     *  PROPERTIES
     * ============ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no", nullable = false)
    private Integer id;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @Column(name = "salary", nullable = false)
    private Float salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deptno_fk", nullable = false)
    private Department deptnoFk;

    /* ==============
     *  CONSTRUCTORS
     * ============== */
    public Employee() {}

    public Employee(String lastName, String firstName, LocalDate birthDate, String gender, LocalDate hireDate, String role, Float salary, Department deptnoFk) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
        this.deptnoFk = deptnoFk;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Department getDeptnoFk() {
        return deptnoFk;
    }

    public void setDeptnoFk(Department deptnoFk) {
        this.deptnoFk = deptnoFk;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", deptnoFk=" + deptnoFk +
                '}';
    }
}