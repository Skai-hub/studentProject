package com.skai.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//登录界面
public class InterFace {
    public static void main(String[] args) {
        System.out.println("==========欢迎来到学生管理系统==========");

        //创建管理员对象
        ArrayList<Manage> list = new ArrayList<>();
        Manage m1 = new Manage("Skai1", "123", "123", "123");
        list.add(m1);
        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("请选择操作：\n" + "1.登录 \n" + "2.没有账号？注册一个！\n" + "3.忘记密码");
            //输入要选择的操作
            String choose = sc.next();
            //功能选项
            switch (choose) {
                //登录
                case "1":
                    if (register(list)) {
                        System.out.println("登陆成功！");
                        //调用StudentSystem中的main方法
                        StudentSystem.main(args);
                        break loop;
                    } else {
                        System.out.println("用户名或密码错误,请重新输入！");
                    }

                    break;
                //注册
                case "2":
                    //用户名
                    String newUse = "";
                    //两次输入的密码
                    String passWord = "";
                    //身份证
                    String id = "";
                    //手机号
                    String phoneNum = "";
                    login(list, newUse, passWord, id, phoneNum);
                    System.out.println(".\n" + "..\n" + "...\n" + "注册成功！");
                    break;
                //忘记密码
                case "3":
                    forgetCode(list);
                    break;

                default:
                    System.out.println("我是你爸，千变万化！");
            }
        }
    }

    //登录方法
    public static boolean register(ArrayList<Manage> list) {
        //用户名输入
        System.out.println("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        //密码输入
        System.out.println("请输入密码：");
        String paw = sc.next();
        //接收验证码
        String code;
        //遍历mange数组
        for (Manage manage : list) {
            //检验用户名和密码以及验证码
            if (manage.getUserName().equals(userName) && manage.getPassWord().equals(paw)) {
                while (true) {
                    code = code();
                    System.out.println("\n请输入验证码：");
                    String myCode = sc.next();
                    if (!(myCode.equalsIgnoreCase(code))) {
                        System.out.println("验证码错误，请重新输入！");
                    } else {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //注册方法
    public static void login(ArrayList<Manage> list, String newUse, String passWord,
                             String id, String phoneNum) {
        String oncePaw;
        Scanner sc = new Scanner(System.in);

        while (true) {
            //请输入要注册的用户名
            System.out.println("请输入要注册的用户名：");
            newUse = sc.next();
            //检查用户名是否满足要求
            if (useNameTest(list, newUse)) {
                System.out.println("恭喜，该用户名可以使用！");
                break;
            }
        }


        while (true) {
            //检查密码是否满足要求
            System.out.println("请输入密码：");
            passWord = sc.next();
            System.out.println("请再次输入密码：");
            oncePaw = sc.next();
            //验证两次输入的密码是否相同
            if (pawTest(passWord, oncePaw)) {
                System.out.println("密码一致");
                break;
            } else {
                System.out.println("密码不同，请重新输入");
            }
        }


        while (true) {
            //身份证验证
            System.out.println("请输入身份证号码：");
            id = sc.next();
            if (idTest(id)) {
                System.out.println("身份证验证成功！");
                break;
            } else {
                System.out.println("身份证格式错误，请重新输入！");
            }
        }

        while (true) {
            //手机号码验证
            System.out.println("请输入手机号：");
            phoneNum = sc.next();
            if (phoneTest(phoneNum)) {
                System.out.println("手机号验证成功！");
                break;
            } else {
                System.out.println("手机号格式错误，请重新输入！");
            }
        }
        //new一个manage对象
        Manage manage = new Manage();
        //添加用户名
        manage.setUserName(newUse);
        //添加密码
        manage.setPassWord(passWord);
        //添加身份证
        manage.setIdNumber(id);
        //添加手机号
        manage.setPhoneNumber(phoneNum);
        list.add(manage);
    }

    //忘记密码
    public static void forgetCode(ArrayList<Manage> list) {
        Scanner sc = new Scanner(System.in);
        String userName;
        String phoneNum;
        String idNum;
        //用户名存在
        System.out.println("请输入用户名：");
        userName = sc.next();
        //遍历list
        for (Manage user : list) {
            if (userName.equals(user.getUserName())) {
                System.out.println("请输入手机号：");
                phoneNum = sc.next();
                System.out.println("请输入身份证号码：");
                idNum = sc.next();
                //判断手机号和身份证号码是否匹配
                if (phoneNum.equals(user.getPhoneNumber()) && idNum.equals(user.getIdNumber())) {
                    System.out.println("请输入新的密码：");
                    //新密码
                    String newPaw = sc.next();
                    //修改密码
                    user.setPassWord(newPaw);
                    System.out.println("密码修改成功！");
                } else {
                    System.out.println("账号信息不匹配，修改失败！");
                }
            } else {
                System.out.println("该用户名不存在！");
            }

        }
    }


    //用户名验证
    public static boolean useNameTest(ArrayList<Manage> list, String newUse) {
        //list为空，只用检查格式
        if (list.size() == 0) {
            //调用检查用户名格式方法
            return formTest(newUse);
        } else {
            //list不为空，用户名不重复
            if (onlyUseTest(list, newUse)) {
                //用户名符合格式
                return formTest(newUse);
            }
        }
        return false;
    }

    //检查用户名是否满足格式
    public static boolean formTest(String newUse) {
        //定义三个计数器判断用户名格式
        int smallCount = 0;
        int bigCount = 0;
        int numCount = 0;

        //定义char数组存储用户名的每一个字符
        char[] useChar = newUse.toCharArray();
        //判断用户名长度是否在3~5之间
        if (useChar.length >= 3 && useChar.length <= 15) {
            //遍历char数组
            for (char aChar : useChar) {
                //判断小写字母的个数
                if (aChar >= 'a' && aChar <= 'z') {
                    smallCount++;
                }
                //判断大写字母的个数
                if (aChar >= 'A' && aChar <= 'Z') {
                    bigCount++;
                }
                //判断数字的个数
                if (aChar >= '0' && aChar <= '9') {
                    numCount++;
                }
            }
            if (smallCount == 0 || bigCount == 0 || numCount == 0) {
                System.out.println("用户名必须包含数字和大小写字母，请重新输入！");
                return false;
            }
        }
        return true;
    }

    //检查用户名是否重复
    public static boolean onlyUseTest(ArrayList<Manage> list, String newUse) {
        for (Manage manage : list) {
            String use = manage.getUserName();
            //用户名没重复
            if (!use.equals(newUse)) {
                return true;
            }
        }
        return false;
    }


    // 检查密码
    public static boolean pawTest(String passWord01, String passWord02) {
        //检查两次输入是否相同
        return passWord01.equals(passWord02);
    }

    //身份证验证
    public static boolean idTest(String id) {
        //验证身份证号码长度
        while (id.length() == 18) {
            char[] idChar = id.toCharArray();
            //验证身份证不以0开头
            if (idChar[0] != 0) {
                //验证身份证前17位位数字
                for (char c : idChar) {
                    if (c <= '9' && c >= '0') {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //手机号验证
    public static boolean phoneTest(String phone) {
        //将Phone转换为字符组
        char[] phoneChars = phone.toCharArray();

        //长度为11
        if (phoneChars.length == 11) {

            //非0开头
            if (phoneChars[0] != 0) {

                //遍历字符串
                for (char p : phoneChars) {
                    //全是数字
                    if (p <= '9' && p >= '0') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //登录验证码
    public static String code() {
        Random random = new Random();
        //定义一个数组来存储字母
        char[] chars = new char[52];

        //遍历数组放入小写字母 ASCII表
        //小写字母97-122
        for (int i = 0; i < 26; i++) {
            //存入数组chars
            chars[i] = (char) (97 + i);
        }


        //遍历数组放入大写字母  65-90
        //chars数组中已有26个小写字母，接下来开头的索引应该是26
        int count = 0;
        for (int i = 26; i < chars.length; i++) {

            //将大写字母存入数组中
            chars[i] = (char) (65 + count);
            count++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {

            //生成随机数,随机抽取四个字母
            int r = random.nextInt(chars.length);
            result.append(chars[r]);
        }

        //生成数字
        int num = random.nextInt(10);
        result.append(num);
        //将字符串result转换为字符数组
        char[] arr = result.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            //打乱数组的顺序
            int r = random.nextInt(arr.length);
            //交换位置
            char first;
            first = arr[i];
            arr[i] = arr[r];
            arr[r] = first;
        }
        //打印验证码
        System.out.println("你的验证码为：");
        StringBuilder code = new StringBuilder();
        for (char c : arr) {
            System.out.print(c);
            code.append(c);
        }
        return code.toString();
    }
}

