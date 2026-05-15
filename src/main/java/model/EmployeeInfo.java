package model;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeInfo {

    StringBuilder name;
    String code;

    String deptId;
    Pattern p;
    Scanner scanner;

    public EmployeeInfo() {
        scanner = new Scanner(System.in);
        setName();
        p = Pattern.compile("[A-Z][a-z]{3}[0-9]{2}");
    }

    public StringBuilder getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private void setName() {
        String input = inputName();
        name.append(input);
        createEmployeeCode(name);
    }

    private void createEmployeeCode(StringBuilder name) {
        if (checkName(name)) {
            String surname = name.toString().split(" ")[1];
            String firstLetter = String.valueOf(name.toString().charAt(0)).toUpperCase();

            code = firstLetter + surname;
        } else {
            code = "guest";
        }
    }

    private String inputName() {
        String s = scanner.nextLine();
        return s;
    }

    private boolean checkName(StringBuilder name) {
        return name.toString().contains(" ");
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId() {
        String id = scanner.nextLine();
        if (validId(id)) {
            deptId = reverseString(id); 
        }else {
            deptId = "None01";
        }
    }

    boolean validId(String id) {
        return p.matcher(id).matches();
    }

    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    @Override
    public String toString() {
        return "Code : " + code + "\nDepartment Id : " + deptId;
    }
}
