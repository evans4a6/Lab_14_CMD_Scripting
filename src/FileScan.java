import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String message = "";

        System.out.print("Choose Your Message: ");
        message = in.nextLine();
        System.out.println("You said: " + message);
    }
}