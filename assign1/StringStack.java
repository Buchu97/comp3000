import java.util.*;

public class StringStack {
    private Stack<String> stack;
    public StringStack(){
        this.stack = new Stack<>();
    }


    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public String peek(){
        return stack.peek();
    }
    public String pop(){
        return stack.pop();
    }
    public void push(String str){
        stack.push(str);
    }
}
