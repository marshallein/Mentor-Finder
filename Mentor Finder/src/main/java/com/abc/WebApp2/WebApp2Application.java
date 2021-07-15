package com.abc.WebApp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp2Application {

    public static void main(String[] args)  throws FileNotFoundException  {
        SpringApplication.run(WebApp2Application.class, args);
        
//         final String folderPath = "E:\\Study\\Sum2021\\SWP\\Mentor-Finder-new\\Mentor-Finder\\Mentor Finder";
//
//        
//        long totalLineCount = 0;
//    final List<File> folderList = new LinkedList<>();
//        folderList.add(new File(folderPath));
//    while (!folderList.isEmpty()) {
//        final File folder = folderList.remove(0);
//        if (folder.isDirectory() && folder.exists()) {
//            System.out.println("Scanning " + folder.getName());
//            final File[] fileList = folder.listFiles();
//            for (final File file : fileList) {
//                if (file.isDirectory()) {
//                    folderList.add(file);
//                } else if (file.getName().endsWith(".java")
//                        || file.getName().endsWith(".sql")) {
//                    long lineCount = 0;
//                    final Scanner scanner = new Scanner(file);
//                    while (scanner.hasNextLine()) {
//                        scanner.nextLine();
//                        lineCount++;
//                    }
//                    totalLineCount += lineCount;
//                    final String lineCountString;
//                    if (lineCount > 99999) {
//                        lineCountString = "" + lineCount;
//                    } else {
//                        final String temp = ("     " + lineCount);
//                        lineCountString = temp.substring(temp.length() - 5);
//                    }
//                    System.out.println(lineCountString + " lines in " + file.getName());
//                }
//            }
//        }
//    }
//    System.out.println("Scan Complete: " + totalLineCount + " lines total");
    }

}
