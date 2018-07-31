package com.twitter.tweetshow.service.python;

import org.python.core.Py;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestMain {
    public static void main (String[] args) {
        try {
            PythonRun t = new PythonRun();
            t.setKeyWords("china");
            t.start();
            System.out.println(t.getId());
            System.out.println(t.getName());
            Scanner sc = new Scanner(System.in);
            System.out.println("输入指令切换关键字");
            String s =sc.nextLine();

            t.stop();
            PythonRun t2 = new PythonRun();
            t2.setKeyWords(s);
            t2.start();




        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
