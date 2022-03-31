import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.swing.Action;



public class CCAnalysis { //good name? maybe, maybe not...but maybe?
    CCHashMap syms = new CCHashMap<String, Integer>();


    //public static Map<String,Integer> symValueAndScope = new LinkedHashMap<String,Integer>();
    Map<Integer, LinkedHashMap<String, Integer>> actionMap = new HashMap<Integer, LinkedHashMap<String, Integer>>();


    public static void scopeMessage(){//, int lineNum) {
        System.out.println("DEBUG Analyze - Creating new scope in Symbol Table");
        
    }

    public static void displayMessage(String type, String value){//, int lineNum) {
        System.out.println("DEBUG Analyze - PASSED! Variable [ " + value + " ] with type [ " + type + " ] found");
        
    }

    public static void errorMessage(String value, int lineNum) {
        System.out.println("ERROR Lexer - Unrecognized Token [ " + value + " ] found at line: " + (lineNum-1));
    }
	public static void warningMessage(int lineNum) {
        System.out.println("WARNING - Unterminated Comment! found at line: " + lineNum);

    }

    int scope;

    public void newScope(String letter, int i){
        if(tokens.get(i).getValueOfToken().equals(letter)) {
            scopeMessage();
            if(i==0) {
                scope = 0;
            }
            else{
                scope++;
            }
        }
    }

    public void blockEnd(String letter, int i){
        scope--;
        
    }
    int counterValue=0;
    public void VarDecl(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            //symValueAndScope.put(scope, new ArrayList<>(Arrays.asList(tokens.get(i+1).getValueOfToken())));
            syms.add(tokens.get(i+1).getValueOfToken(), scope);
            counterValue++;

            displayMessage(tokens.get(i).getValueOfToken(), letter);
            
        }
    }

    int errorCounter=0;
    public void AssignStmnt(String letter, int i){
        if(tokens.get(i-1).getValueOfToken().equals(letter)) {
           
            //displayMessage(tokens.get(i).getValueOfToken(), letter);
            if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("DEBUG Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope");
                Object id = syms.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                if(id.equals(scope)){                
                    System.out.println("DEBUG Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + scope + " ] has an equal scope to [ " + tokens.get(i+1).getValueOfToken() +  "," + scope + " ]");
                    
                    System.out.println("DEBUG Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has been assigned to [ " + tokens.get(i+1).getValueOfToken() + " ]");
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different scope than [ " + tokens.get(i+1).getValueOfToken() + " ]");

                    errorCounter++;
                }    
            
            }
        }
    }


    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void analyze(List<CCToken> tokenStream) {
      

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            

            String element = tokens.get(i).getValueOfToken();
           

            switch (element) {
                //SCOPE
                case "{":
                    newScope("{", i);
                break;

                case "}":
                    blockEnd("}", i);
                break;

                case "=":
                    AssignStmnt("a", i);AssignStmnt("g", i);AssignStmnt("m", i);AssignStmnt("s", i);                    
                    AssignStmnt("b", i);AssignStmnt("h", i);AssignStmnt("n", i);AssignStmnt("t", i);     
                    AssignStmnt("c", i);AssignStmnt("i", i);AssignStmnt("o", i);AssignStmnt("u", i);
                    AssignStmnt("d", i);AssignStmnt("j", i);AssignStmnt("p", i);AssignStmnt("v", i);     
                    AssignStmnt("e", i);AssignStmnt("k", i);AssignStmnt("q", i);AssignStmnt("w", i);
                    AssignStmnt("f", i);AssignStmnt("l", i);AssignStmnt("r", i);AssignStmnt("x", i);
                    AssignStmnt("y", i);AssignStmnt("z", i);
                break;

                //VARDECL
                case "int":
                    VarDecl("a", i);VarDecl("g", i);VarDecl("m", i);VarDecl("s", i);                    
                    VarDecl("b", i);VarDecl("h", i);VarDecl("n", i);VarDecl("t", i);     
                    VarDecl("c", i);VarDecl("i", i);VarDecl("o", i);VarDecl("u", i);
                    VarDecl("d", i);VarDecl("j", i);VarDecl("p", i);VarDecl("v", i);     
                    VarDecl("e", i);VarDecl("k", i);VarDecl("q", i);VarDecl("w", i);
                    VarDecl("f", i);VarDecl("l", i);VarDecl("r", i);VarDecl("x", i);
                    VarDecl("y", i);VarDecl("z", i);
                break;

                case "string":
                    VarDecl("a", i);VarDecl("g", i);VarDecl("m", i);VarDecl("s", i);                    
                    VarDecl("b", i);VarDecl("h", i);VarDecl("n", i);VarDecl("t", i);     
                    VarDecl("c", i);VarDecl("i", i);VarDecl("o", i);VarDecl("u", i);
                    VarDecl("d", i);VarDecl("j", i);VarDecl("p", i);VarDecl("v", i);     
                    VarDecl("e", i);VarDecl("k", i);VarDecl("q", i);VarDecl("w", i);
                    VarDecl("f", i);VarDecl("l", i);VarDecl("r", i);VarDecl("x", i);
                    VarDecl("y", i);VarDecl("z", i);
                break;

                case "boolean":
                    VarDecl("a", i);VarDecl("g", i);VarDecl("m", i);VarDecl("s", i);                    
                    VarDecl("b", i);VarDecl("h", i);VarDecl("n", i);VarDecl("t", i);     
                    VarDecl("c", i);VarDecl("i", i);VarDecl("o", i);VarDecl("u", i);
                    VarDecl("d", i);VarDecl("j", i);VarDecl("p", i);VarDecl("v", i);     
                    VarDecl("e", i);VarDecl("k", i);VarDecl("q", i);VarDecl("w", i);
                    VarDecl("f", i);VarDecl("l", i);VarDecl("r", i);VarDecl("x", i);
                    VarDecl("y", i);VarDecl("z", i); 
                break;
            
                    
                    
            }
            //boolean test = (tokens.get(i+1).getTypeOfToken().equals("ASSIGNMENT"));
           
            //j++;
        
        
        }
        
        

    }
    
    public void symValScope(){
        System.out.println("Name    Type    Scope");
        System.out.println("---------------------");
        int test=0;
        System.out.println(actionMap.toString());
        LinkedHashMap<String, Integer> actionMapEntry = actionMap.get(0);

        //if(actionMapEntry.getKey().equals("a") && actionMapEntry.getValue().equals(1)){
          //  System.out.println("thisiswhereiam");
        //}

        //for (LinkedHashMap<Integer, LinkedHashMap<String, Integer>> LinkedHashMap : actionMap.entrySet()) {
          //  System.out.println(LinkedHashMap.getKey() + ":" + LinkedHashMap.getValue().toString());
      //  }

      

        for (Entry<Integer, java.util.LinkedHashMap<String, Integer>> LinkedHashMap : actionMap.entrySet()) {
            Integer key = LinkedHashMap.getKey();
            Collection<java.util.LinkedHashMap<String, Integer>> value = ((Map<Integer, java.util.LinkedHashMap<String, Integer>>) LinkedHashMap).values();
        
            System.out.println(key+"       "+value);
            System.out.println(test);
            //printf("'%-5d'"
            test++;
              

           }  
           
        //System.out.println(symbolTable.toString());
        

            
    }



}