package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        Song song = new Song("a","b","c","d");
        song.setOutputFileTitle("file.txt");
        song.setInputFileTitle("file1");
        song.setOutputFileTitle("file.txt");
    }
}

class Song {
    String title;
    String singer;
    String text;
    String genre;
    String inputFileTitle;
    String outputFileTitle;
    public Song(String title, String singer, String text, String genre) {
        this.title = title;
        this.singer = singer;
        this.text = text;
        this.genre = genre;
    }

    public Song(String inputFileTitle, String outputFileTitle) throws Exception{
        this.inputFileTitle = inputFileTitle;
        this.outputFileTitle = outputFileTitle;
        FileWriter fileWriter = new FileWriter(outputFileTitle);
        fileWriter.write(title + '\n');
        fileWriter.write(singer + '\n');
        fileWriter.write(text + '\n');
        fileWriter.write(genre + '\n');
        fileWriter.close();
        FileReader fileReader = new FileReader(inputFileTitle);
        Scanner sc = new Scanner(fileReader);
        title = sc.nextLine();
        singer = sc.nextLine();
        genre = sc.nextLine();
        while (sc.hasNextLine()){
            text += sc.nextLine() + "\n";
        }
        fileReader.close();
    }

    public Song(String inputFileTitle) throws Exception{
        this.inputFileTitle = inputFileTitle;
        FileWriter fileWriter = new FileWriter(outputFileTitle);
        fileWriter.write(title + '\n');
        fileWriter.write(singer + '\n');
        fileWriter.write(text + '\n');
        fileWriter.write(genre + '\n');
        fileWriter.close();
        FileReader fileReader = new FileReader(inputFileTitle);
        Scanner sc = new Scanner(fileReader);
        title = sc.nextLine();
        singer = sc.nextLine();
        genre = sc.nextLine();
        while (sc.hasNextLine()){
            text += sc.nextLine() + "\n";
        }
        fileReader.close();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInputFileTitle() {
        return inputFileTitle;
    }

    public void setInputFileTitle(String inputFileTitle) throws Exception{
        this.inputFileTitle = inputFileTitle;
        this.outputFileTitle = outputFileTitle;
        FileWriter fileWriter = new FileWriter(outputFileTitle);
        fileWriter.write(title + '\n');
        fileWriter.write(singer + '\n');
        fileWriter.write(text + '\n');
        fileWriter.write(genre + '\n');
        fileWriter.close();
        FileReader fileReader = new FileReader(inputFileTitle);
        Scanner sc = new Scanner(fileReader);
        title = sc.nextLine();
        singer = sc.nextLine();
        genre = sc.nextLine();
        while (sc.hasNextLine()){
            text += sc.nextLine() + "\n";
        }
        fileReader.close();
    }

    public String getOutputFileTitle() {
        return outputFileTitle;
    }

    public void setOutputFileTitle(String outputFileTitle) throws Exception{
        this.outputFileTitle = outputFileTitle;
        FileWriter fileWriter = new FileWriter(outputFileTitle);
        fileWriter.write(title + '\n');
        fileWriter.write(singer + '\n');
        fileWriter.write(genre + '\n');
        fileWriter.write(text + '\n');
        fileWriter.close();
    }
}