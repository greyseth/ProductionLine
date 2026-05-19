package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ProcessFiles {

    // Fields
    private Path p;
    private Path p2;
    private Path p3;

    // Constructor
    public ProcessFiles() throws IOException {

        // Folder path
        p = Paths.get("C:\\LineTests");

        // File name
        p2 = Paths.get("C:\\TestResults.txt");

        // Full path
        p3 = p.resolve(p2);

        // Create directory and file
        CreateDirectory();
    }

    // Create folder and file
    private void CreateDirectory() throws IOException {

        // Create folder if not exists
        if (Files.notExists(p)) {
            Files.createDirectory(p);
        }

        // Create file if not exists
        if (Files.notExists(p3)) {
            Files.createFile(p3);
        }
    }

    // Write EmployeeInfo object to file
    public void WriteFile(EmployeeInfo emp) throws IOException {

        Files.write(
                p3,
                (emp.toString() + "\n").getBytes(),
                StandardOpenOption.APPEND
        );
    }

    // Write products list to file
    public void WriteFile(ArrayList<Product> products) throws IOException {

        for (Product pr : products) {
            Files.write(
                    p3,
                    (pr.toString() + "\n").getBytes(),
                    StandardOpenOption.APPEND
            );
        }
    }
}
