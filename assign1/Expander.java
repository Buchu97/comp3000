import java.util.Scanner;

public class Expander {
    private static final Scanner scan = new Scanner(System.in);
    private static StringStack stack = new StringStack();
    public void start(){
        repl();
    }
    private void repl(){
        System.out.print("exapnder >");
        String input = scan.nextLine();
        if("exit".equalsIgnoreCase(input)){
            System.out.println("bye bye");
            return;
        }else{
            processInput(input);
            repl();
        }
        

    }
    private void processInput(String input){
        String[] actions = input.split(";");
        String initial = stack.isEmpty() ? "" : stack.peek();

    }

}
