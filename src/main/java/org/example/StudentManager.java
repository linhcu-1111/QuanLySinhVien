package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {

    public static String COMMA = ", ";
    public static String FILE_NAME = "students.csv";
    private ArrayList<Students> students;
    Scanner sc = new Scanner(System.in);

    public StudentManager() {
        ArrayList<Students> students = new ArrayList<>();
        this.students = students;
    }

    boolean isExistID(int id) {
        List<Students> list;
        list = readListStudentsFromFile();
        if (!list.isEmpty()) {
            for (Students student : list) {
                if (student.getStudentId() == id) {
                    return true;
                }
            }
        }
        return false;
    }


    //Them thong tin sinh vien
    public Students inputStudentData() {
        int studentId;
        String studentName;
        int age;
        String sex;
        String address;
        float mark;

        do {
            System.out.println("Nhap ID sinh vien: ");
            studentId = sc.nextInt();
            sc.nextLine();
            if (isExistID(studentId)) {
                System.out.println("ID da ton tai");
            }
        } while (isExistID(studentId));

        System.out.println("Nhap ten sinh vien: ");
        studentName = sc.nextLine();

        System.out.println("Nhap tuoi sinh vien: ");
        age = sc.nextInt();
        System.out.println("Nhap gioi tinh: ");
        sc.nextLine();
        sex = sc.nextLine();
        System.out.println("Nhap dia chi: ");
        address = sc.nextLine();
        System.out.println("Nhap diem cua sinh vien: ");
        mark = sc.nextFloat();

        return new Students(studentId, studentName, age, sex, address, mark);
    }

    //Them san pham va ghi vao file
    public void addStudent() {
        Students newStudent = inputStudentData();
        String line = null;
//        Ghi object
        line = newStudent.getStudentId() + COMMA + newStudent.getStudentName() + COMMA + newStudent.getAge() +
                COMMA + newStudent.getSex() + COMMA + newStudent.getAddress() + COMMA + newStudent.getMark();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_NAME, true); //them true de khong ghi de len ban ghi cu
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Them thong tin thanh cong");
    }

    public ArrayList<Students> getStudents() {
        return students;
    }


    //Tim kiem san pham
    public Students findById(int id) {
        List<Students> list;
        list = readListStudentsFromFile();
        if (!list.isEmpty()) {
            for (Students student : list) {
                if (student.getStudentId() == id)
                    return student;
            }
        }
        return null;
    }

    //Hien thi
    public void displayStudent(Students students) {
        System.out.println(students);
    }

    public List<Students> readListStudentsFromFile() {
        List<String> list = new ArrayList();
        List<Students> studentList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineToRead = null;
            while ((lineToRead = bufferedReader.readLine()) != null) {
                list.add(lineToRead);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String list1 : list) {
            String[] lineSplit = list1.split(COMMA);
            Students students = new Students(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]),
                    lineSplit[3], lineSplit[4], Float.parseFloat(lineSplit[5]));
            studentList.add(students);
        }
        return studentList;
    }

    public int inputId() {
        System.out.println("Nhap ID: ");
        int inputId = sc.nextInt();
        return inputId;
    }


    public void showMenu() {
        int choice;
        do {
            System.out.println("--CHUONG TRINH QUAN LY SINH VIEN--");
            System.out.println("Chon chuc nang theo so:");
            System.out.println("1. Xem danh sach sinh vien");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Thoat");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Students> productList = readListStudentsFromFile();
                    for (Students student : productList) {
                        System.out.println(student);
                    }
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    int updateId = inputId();
                    updateStudentByID(updateId);
                    break;
                case 4:
//                    removeById();
                case 5:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 5);
    }

    private void removeById(int id) {

    }

    private void updateStudentByID(int id) {
        List<Students> list = readListStudentsFromFile();
        boolean found = false;
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStudentId() == id) {
                    found = true;
                    System.out.println("Nhap ten:");
                    sc.nextLine();

                    String newName = sc.nextLine();// Tiêu thụ kí tự Enter còn lại trong bộ đệm
                    System.out.println("Nhap tuoi:");
                    int newAge = sc.nextInt();

                    System.out.println("Nhap gioi tinh:");
                    sc.nextLine();
                    String newSex = sc.nextLine();

                    System.out.println("Nhap dia chi:");
                    String newAddr = sc.nextLine();

                    System.out.println("Nhap diem trung binh:");
                    float newMark = sc.nextFloat();
                    list.get(i).setStudentName(newName);
                    list.get(i).setAge(newAge);
                    list.get(i).setSex(newSex);
                    list.get(i).setAddress(newAddr);
                    list.get(i).setMark(newMark);
                }
            }
            if (!found) {
                System.out.println("Khong tim thay sinh vien");
            }
            for (int i = 0; i < list.size(); i++) {
//                  ghi lai vao file
                String line = null;
                line = list.get(i).getStudentId() + COMMA + list.get(i).getStudentName() + COMMA + list.get(i).getAge()
                        + COMMA + list.get(i).getSex() + COMMA + list.get(i).getAddress() + COMMA + list.get(i).getMark();
                FileWriter fileWriter = null;
                try {
                    if (i == 0) {
                        fileWriter = new FileWriter(FILE_NAME, false);
                    } else {
                        fileWriter = new FileWriter(FILE_NAME, true);
                    }
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

//    private void removeById(int id) {
//        List<Students> list = readListStudentsFromFile();
//        Students studentToRemove = null;
//        for (Students student : list) {
//            if (student.getStudentId() == id) {
//                studentToRemove = student;
//                break;
//            }
//        }
//        if (studentToRemove != null) {
//            list.remove(studentToRemove);
//            // Update the CSV file
//            try (FileWriter fileWriter = new FileWriter(FILE_NAME, false);
//                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
//                for (Students student : list) {
//                    String line = student.getStudentId() + COMMA + student.getStudentName() + COMMA + student.getAge()
//                            + COMMA + student.getSex() + COMMA + student.getAddress() + COMMA + student.getMark();
//                    bufferedWriter.write(line);
//                    bufferedWriter.newLine();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Xoa thanh cong");
//        } else {
//            System.out.println("Khong tim thay sinh vien");
//        }
//    }
}



