package br.edu.ifpb;

import java.io.*;

public class Node3 {

    public static void main(String[] args) throws IOException {

        File file = new File("./disk/banco.txt");

        if (file.exists()) {

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("update");
            bufferedWriter.newLine();
            System.out.println("update");
            bufferedWriter.close();

        } else {
            System.out.println("Arquivo não existe!");
        }
    }
}
