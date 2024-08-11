package assign1;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    private static final Scanner scan = new Scanner(System.in);
    private static Stack<String> stack = new Stack<>();
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
        int intialLength = input.length();
        String[] actions = input.split(";");
       
        try{
            for(String action : actions){
                execute(action);
                // System.out.println(action + " from processInput");
            }
            String result = stack.pop();
            int resultLength = result.length();

            System.out.println(result);
            System.out.println(intialLength);
            System.out.println(resultLength);
            
            System.out.println("expansion multiplier: "+ String.format("%.2f", (double)resultLength / intialLength)+"x");
            stack.clear();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void execute(String action) throws Exception{
       
        if(action.isEmpty()){
            return;
        }
        // System.out.println(action);
        char firstChar = action.charAt(0);
        switch (firstChar) {
            case '*':
                if(action.length()<2){
                    throw new Exception("error in action: " + action);
                }
                if(!Character.isDigit(action.charAt(1))){
                    throw new Exception("error in action: " + action.charAt(1));
                }
                int times = Character.getNumericValue(action.charAt(1));
                char delim = '\0';
                if(action.length()==3){
                    if(Character.isDigit(action.charAt(2))){
                        throw new Exception("error in action: " + action.charAt(2));
                    }
                    delim = action.charAt(2);
                    
                }
                if(action.length()>3){
                    throw new Exception("error in action: " + action.charAt(3));
                }
                repeatTop(times, delim);
                
                break;
            case '+':
            if(stack.size()<2){
                    throw new Exception("error in action: empty stack");
                }
                if(action.length()>2){
                    throw new Exception("error in action: " + action.charAt(2));
                }
               char joinDelim = action.length()>1 ? action.charAt(1) : '\0';
                concat(joinDelim);
                break;
            default:
            if(Character.isLetter(firstChar)){
                stack.push(action);
            }else{
                throw new Exception("error in action: " + action);
            }
                
        }
       
    }
    private void repeatTop(int times, char delim) throws Exception{
        if(stack.isEmpty()){
            throw new Exception("error in action: empty stack");
        }
        String top = stack.pop();
        String result = repeatTopHelper(times, delim, top);
        stack.push(result);

        

    }
    private String repeatTopHelper(int times, char delim, String str){
        if(times<=1){
            return str;
        }else{
            if(delim ==  '\0'){
                return str+repeatTopHelper(times-1, delim,  str);
            }
            return str+delim+repeatTopHelper(times-1, delim,  str);
        }
    }
    private void concat(char joinDelim){
        String first = stack.pop();
        String second = stack.pop();
        stack.push( second+ joinDelim + first);
    }
    public static void main(String[] args){
        Main expander = new Main();
        expander.start();
        
        }
}
