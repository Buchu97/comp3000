package assignment1;
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
            if(!input.isEmpty()){
            processInput(input);
            }
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
            System.out.println(result);
            stack.clear();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void execute(String action) throws InvalidRepetitionActionException, StackUnderFlowException{
       
        if(action.isEmpty()){
            return;
        }
        // System.out.println(action);
        char firstChar = action.charAt(0);
        switch (firstChar) {
            case '*':
                if(action.length()<2 || !Character.isDigit(action.charAt(1))){
                    throw new InvalidRepetitionActionException("error in action: " + action);
                }
                int times = Character.getNumericValue(action.charAt(1));
                char delim = action.charAt(1);
                repeatTop(times, delim);
                
                break;
            case '+':
            if(stack.size()<2){
                    throw new StackUnderFlowException("error in action: empty stack");
                }
               char joinDelim = action.length()>1 ? action.charAt(1) : '\0';
                System.out.println("l"+joinDelim+"r");
                concat(joinDelim);
                break;
            default:
            if(Character.isLetter(firstChar)){
                stack.push(action);
            }else{
                throw new InvalidRepetitionActionException("error in action: " + action);
            }
                
        }
       
    }
    private void repeatTop(int times, char delim) throws StackUnderFlowException{
        if(stack.isEmpty()){
            throw new StackUnderFlowException("error in action: empty stack");
        }
        String top = stack.pop();
        String result = repeatTopHelper(times, delim, top);
        stack.push(result);

        

    }
    private String repeatTopHelper(int times, char delim, String str){
        if(times<=1){
            return str;
        }else{
            return str+delim+repeatTopHelper(times-1, delim,  str);
        }
    }
    private void concat(char joinDelim){
        String first = stack.pop();
        String second = stack.pop();
        stack.push( second+ joinDelim + first);
    }
}
