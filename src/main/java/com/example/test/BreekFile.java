package com.example.test;

import java.io.*;

/**
 * Created by finup on 2019/9/9.
 */
public class BreekFile {

    public static void main(String args[]) {

        try {

            FileReader read = new FileReader("/Users/finup/Desktop/log.txt");

            BufferedReader br = new BufferedReader(read);

            String row;


            FileWriter fw1 = new FileWriter("/Users/finup/Desktop/log1.txt");
            FileWriter fw2 = new FileWriter("/Users/finup/Desktop/log2.txt");
            while ((row = br.readLine()) != null) {
                if (row.contains("BTS")) {
                    fw1.append(row + "\r\n");
                } else {
                    fw2.append(row + "\r\n");
                }
            }

            fw1.close();
            fw2.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

