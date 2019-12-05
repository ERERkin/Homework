package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        String text = "";
        try {
            FileReader fileReader = new FileReader(".\\src\\com\\company\\Main.java");
            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                text += sc.nextLine() + "\n";
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter("file1");
            fileWriter.write(text);
            fileWriter.close();
            File file = new File("file1");
            long fileSize = file.length();
            if (fileSize >= 100) {
                throw new IllegalStateException("file size <= 100");
            }
        }catch (IllegalStateException ex){
            ex.printStackTrace();
            System.out.println("Ну и что дальше делать?");
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            System.out.println("Создай файл умник");
        }finally {
            String text2 = "";
            FileWriter fileWriter = new FileWriter("file2");
            for(int i = 0; i < text.length(); i++){
                if(text.charAt(i) != ' '){
                    fileWriter.write(text.charAt(i));
                }
            }
            fileWriter.close();
        }
    }
}
