package com.skai.studentsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//搭建主界面
public class StudentSystem {
    public static void main(String[] args) {
        System.out.println("========欢迎来到学生管理系统==========");
        Scanner sc = new Scanner(System.in);
        //创建一个学生列表
        ArrayList<Student> list = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("请输入您的选择！");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出！");

            //输入功能选择
            String choose = sc.next();
            switch (choose) {
                case "1":
                    addStudent(list);
                    break;
                case "2":
                    deleteStudent(list);
                    break;
                case "3":
                    updateStudent(list);
                    break;
                case "4":
                    queryStudent(list);
                    break;
                case "5":
                    System.out.println("退出");
                    break loop;
                //输入其他非指定选项
                default:
                    System.out.println("我是你爸，千变万化！");
                    break;
            }
        }
    }

    //定义添加学生的方法
    public static void addStudent(ArrayList<Student> list) {
        //定义一个学生对象
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);


        //判断输入的id是否唯一
        System.out.println("请输入学生id");
        int id = sc.nextInt();
        boolean flag = only(list, id);
        if (flag) {
            System.out.println("输入id重复，请重新输入");
        } else {
            //将id加入学生对象中
            stu.setId(id);

            System.out.println("请输入学生姓名");
            String name = sc.next();
            //将名字添加学生对象中
            stu.setName(name);

            System.out.println("请输入学生年龄");
            int age = sc.nextInt();
            //将名字添加学生对象中
            stu.setAge(age);

            System.out.println("请输入学生地址");
            String address = sc.next();
            //将名字添加学生对象中
            stu.setAddress(address);

            //将学生对象加入list
            list.add(stu);

            System.out.println("添加成功！");
        }
    }

    //定义删除学生的方法
    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生id：");
        int id = sc.nextInt();
        //检查id并返回索引
        int index = getIndex(list, id);
        //id存在
        if(index >= 0){
            list.remove(index);
            System.out.println("删除成功！");
        }else{
            System.out.println("该id不存在");
        }
    }

    //定义修改学生的方法
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生id：");
        int id = sc.nextInt();


        //判断输入的id存在
        int index =getIndex(list, id);
        if(index >= 0){
            System.out.println("请输入要修改的学生姓名：");
            String newName = sc.next();
            list.get(index).setName(newName);

            System.out.println("请输入要修改的学生年龄：");
            int newAge = sc.nextInt();
            list.get(index).setAge(newAge);

            System.out.println("请输入要修改的学生地址：");
            String newAddress = sc.next();
            list.get(index).setAddress(newAddress);
            System.out.println("修改成功！");
        }else {
            System.out.println("该id不存在请重新输入!");
            return;
        }


    }

    //定义查询学生的方法
    public static void queryStudent(ArrayList<Student> list) {
        //判定是否存入学生信息
        if (list.size() == 0) {
            System.out.println("当前学生信息为空，请添加后查询");
            return;
        } else {
            //遍历list查询
            System.out.println("id\t姓名\t年龄\t地址");
            for (int i = 0; i < list.size(); i++) {
                Student stu = list.get(i);
                    //打印信息
                    System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
                }
            }
        }

    //判断添加信息中的id是否唯一
    public static boolean only(ArrayList<Student> list, int id) {
      /*  //遍历list
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            //id 是否重复
            if (stu.getId() == id) {
                return true;
            }
        }
        return false;*/
        return  getIndex(list,id) > 0;
    }

    //判断id是否存在并返回索引
    public static int getIndex(ArrayList<Student> list, int id){
        //遍历list
        for (int i = 0; i < list.size(); i++) {
            Student stu =new Student();
            stu = list.get(i);
            //id存在
            if(stu.getId() == id){
                //返回索引
                return i;
            }
        }
        //id不存在
        return -1;
    }
}
