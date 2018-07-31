package test;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JythonTest {
    public static void main(String args[]) {
        try{
//            Process pr = Runtime.getRuntime().exec("python E:\\Twitter_crawler\\JythonTest.py");
//            Thread.sleep(5000);
//            InputStream is = pr.getInputStream();
//
//            DataInputStream dis = new DataInputStream(is);
//            String line;
//            while((line = dis.readLine()) != null){
//                System.out.print(line);
//            }
//
//            pr.waitFor();
//            System.out.print("结束");
            String exe = "python";
            String command1 = "E:\\Twitter_crawler\\JythonTest.py";

            String num1 = "1";
            String num2 = "2";
            String[] cmdArr = new String[] {exe, command1, num1, num2};
            Process process = Runtime.getRuntime().exec(cmdArr);
            Thread.sleep(10000);
            InputStream is = process.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String str = dis.readLine();
            process.waitFor();
            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
