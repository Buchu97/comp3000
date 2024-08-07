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
            System.out.println(result);
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
                    throw new InvalidRepetitionActionException("not good" + action);
                }
                int times = Character.getNumericValue(action.charAt(1));
                String delim = action.substring(2);
                repeatTop(times, delim);
                
                break;
            
            default:
                stack.push(action);
        }
       
    }
    private void repeatTop(int times, String delim) throws StackUnderFlowException{
        if(stack.isEmpty()){
            throw new StackUnderFlowException("Empty stack on repeat operation." + delim);
        }
        String top = stack.pop();
        String result = repeatTopHelper(times, delim, top);
        stack.push(result);

        

    }
    private String repeatTopHelper(int times, String delim, String str){
        if(times<=1){
            return str;
        }else{
            return str+delim+repeatTopHelper(times-1, delim,  str);
        }
    }
}
