import javax.swing.*;
import java.io.*;
import java.nio.file.*;

public class FileScan {

    public static void main(String[] args) {

        File selectedFile = null;

        if (args.length > 0)
        {
            selectedFile = new File(args[0]);

            if (!selectedFile.exists())
            {
                System.out.println("Error: File not found " + args[0]);
                return;
            }

            System.out.println("Scanning file from command line argument: " + args[0]);

        }
        else
        {

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("src"));

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
            } else
            {
                System.out.println("No file selected. Exiting.");
                return;
            }
        }

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
                    if (!trimmed.isEmpty())
                    {
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