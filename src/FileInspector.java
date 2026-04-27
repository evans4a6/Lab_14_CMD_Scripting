import javax.swing.*;
import java.io.*;
import java.nio.file.*;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("src"));

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();

            try (BufferedReader reader = Files.newBufferedReader(selectedFile.toPath()))
            {
                String line;
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                    lineCount++;

                    charCount += line.length();

                    String trimmed = line.trim();
                    if (!trimmed.isEmpty()) {
                        String[] words = trimmed.split("\\s+");
                        wordCount += words.length;
                    }
                }

                System.out.println("\n--- Summary Report ---");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);

            } catch (IOException e)
            {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

    }
}