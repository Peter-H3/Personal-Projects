import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int seconds;
        String text;

        while (true) {
            System.out.print("Enter the number of seconds to countdown from: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter an number");
                scanner.nextLine();
                continue;
            }

            seconds = scanner.nextInt();
            scanner.nextLine();
            break;
        }

        System.out.print("Enter text to display when the timer ends: ");
        text = scanner.nextLine();

        Timer timer = new Timer();
        TimerTask task = new TimerTask(){

            int count = seconds;

            @Override
            public void run(){

                System.out.println(count);
                count--;
                if(count < 0){
                    System.out.println(text);
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // (task, delay, period)
    }
}
