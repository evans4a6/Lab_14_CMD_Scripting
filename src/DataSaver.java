import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {

        ArrayList<String> records = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        boolean cont = true;

        while(cont)
        {
            String first = SafeInput.getNonZeroLenString(in, "Enter first name");
            String last = SafeInput.getNonZeroLenString(in, "Enter last name");
            int idNum = SafeInput.getInt(in, "Enter ID number");
            String id = String.format("%06d", idNum);
            String email = SafeInput.getNonZeroLenString(in, "Enter email");
            int yob = SafeInput.getRangedInt(in, "Enter year of birth", 1000,9999);

            String record =  first + ", " + last + ", " + idNum + ", " + email + ", " + yob;
               records.add(record);

            cont = SafeInput.getYNConfirm(in, "Do you want to add another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name");
        fileName += ".csv";

        Path file = Paths.get("src/" + fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String rec : records) {
                writer.write(rec);
                writer.newLine();
            }
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
