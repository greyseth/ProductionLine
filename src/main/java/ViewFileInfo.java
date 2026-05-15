
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.ProcessFiles;
import model.EmployeeInfo;

public class ViewFileInfo {

    public static void main(String[] args) {
        try {
            ProcessFiles pdf = new ProcessFiles();
            EmployeeInfo employee = new EmployeeInfo();
            pdf.WriteFile(employee);

            File file = new File("C:\\TestResults.txt");
            Scanner scanner = new Scanner(file);

            System.out.println("Contents of TestResults.txt:");
            System.out.println("-----------------------------");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: The file 'TestResults.txt' was not found.");
            System.out.println("Please make sure the file exists in the correct location.");
        }
    }
}
