package com.twitter.tweetshow.service.python;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonRun extends Thread {
    String keyWords;


    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public void run(){
        try {
            System.out.println("程序开始执行");
            Process p = Runtime.getRuntime().exec("python C:\\Twitter_Crawler\\TwitterStore.py "+keyWords);


            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
//                line = decodeUnicode(line);
                System.out.println(line);
            }
            in.close();

            p.waitFor();
            System.out.println("python执行完毕");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
