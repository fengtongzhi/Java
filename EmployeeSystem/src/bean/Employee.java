package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private String gender;
    private int age;
    private String phone;
    private String position;
    private String hireDate;
    private double salary;
    private String department;
}
