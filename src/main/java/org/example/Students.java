package org.example;

public class Students {
   private int studentId;
    String studentName;
    int age;
    String sex;
    String address;
    float mark;

    public Students(int studentId, String studentName, int age, String sex, String address, float mark) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.mark = mark;
    }

    public Students() {
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getMark() {
        return mark;
    }
    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "ID: " + studentId
                + "\nHo ten: " + studentName
                + "\nTuoi: " + age
                + "\nGioi tinh: " + sex
                + "\nDia chi: " + address
                + "\nDiem trung binh: " + mark;
    }
}
