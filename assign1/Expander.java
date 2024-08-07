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
        try{
            for(String action : actions){
                execute(action);
                // System.out.println(action + " from processInput");
            }
            String result = stack.peek();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void execute(String action) throws InvalidRepetitionActionException{
        if(action.isEmpty()){
            return;
        }
        System.out.println(action);
        char firstChar = action.charAt(0);
        switch (firstChar) {
            case '*':
                if(action.length()<2 || !Character.isDigit(action.charAt(1))){
                    throw new InvalidRepetitionActionException("not good" + action);
                }
                
                break;
        
            default:
                break;
        }
    }

}
