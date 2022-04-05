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
    CCHashMap valueAndScope = new CCHashMap<String, Integer>();
    CCHashMap valueAndType = new CCHashMap<String, Integer>();




    //public static Map<String,Integer> symValueAndScope = new LinkedHashMap<String,Integer>();
    Map<Integer, LinkedHashMap<String, Integer>> actionMap = new HashMap<Integer, LinkedHashMap<String, Integer>>();


    public static void scopeMessage(){//, int lineNum) {
        
        System.out.println("INFO  Analyze - Creating new scope in Symbol Table");
        System.out.println("-----------------------------------------------------------");
        
    }

    public static void displayMessage(String type, String value){//, int lineNum) {
        System.out.println("INFO  Analyze - PASSED! Variable [ " + value + " ] with type [ " + type + " ] found");
        System.out.println("-----------------------------------------------------------");
        
    }

    public static void errorMessage(String value, int lineNum) {
        System.out.println("ERROR Lexer - Unrecognized Token [ " + value + " ] found at line: " + (lineNum-1));
    }
	public static void warningMessage(int lineNum) {
        System.out.println("WARNING - Unterminated Comment! found at line: " + lineNum);

    }

    boolean result;
    public boolean checkType(String type, int i){
        
        Object lookUpType = valueAndType.getForward(type); //looking up type of b in b=x, which is paired with its scope

        System.out.println(lookUpType.toString());
        if (lookUpType.equals("int")){
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
                result = true;
            }
            else{
                result = false;
            }
        return result;

        }

        if (lookUpType.equals("string")){
            if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
                result = true;
            }
            else{
                result = false;
            }
        return result;

        }
        if (lookUpType.equals("boolean")){
            if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
                result = true;
            }
            else{
                result = false;
            }
        return result;


        }
        return result;
    
        
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
            Object idInVarDec = valueAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up scope of b in int b, which is paired with its scope

            Object scopeInVarDec = valueAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up scope of b in int b, which is paired with its scope

            String typeOfVarDecID=(String) idInVarDec;
            Integer scopeOfVarDecID=(Integer) scopeInVarDec;

            if(scopeOfVarDecID!=null){
            if(scopeOfVarDecID.equals(scope)){
                System.out.println("ERROR  Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being redefined within the same scope");

            }
            else{
                System.out.println(scope);
                displayMessage(tokens.get(i).getValueOfToken(), letter);

            }
            
        }
        else{
            displayMessage(tokens.get(i).getValueOfToken(), letter);


            }
            //System.out.println(scopeOfVarDecID);
            //System.out.println(typeOfVarDecID);
            

            //symValueAndScope.put(scope, new ArrayList<>(Arrays.asList(tokens.get(i+1).getValueOfToken())));
            valueAndScope.add(tokens.get(i+1).getValueOfToken(), scope);
            valueAndType.add(tokens.get(i+1).getValueOfToken(), tokens.get(i).getValueOfToken());

            counterValue++;

            
        }
    }

    int errorCounter=0;
    public void AssignStmnt(String letter, int i){
        if(tokens.get(i-1).getValueOfToken().equals(letter)) {
            Object idBeforeEquals = valueAndScope.getForward(tokens.get(i-1).getValueOfToken()); //looking up scope of b in b=a, which is paired with its scope
            Object idBeforeEqualsType = valueAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in b=a, which is paired with its scope
            if(idBeforeEqualsType==null){
                System.out.println("ERROR  Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used before being declared");
                System.out.println("-----------------------------------------------------------");

            }
            else{
            int scopeOfID=(int) idBeforeEquals;
            
            if(scopeOfID<= scope){ //if scope of id before = sign is less than or equal to current scope, pass
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used within its correct SCOPE");
                System.out.println("-----------------------------------------------------------");

                if(checkType(tokens.get(i-1).getValueOfToken(), i) == true){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used with its correct TYPE");
                    System.out.println("-----------------------------------------------------------");
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used with its incorrect TYPE");
                    System.out.println("-----------------------------------------------------------");
                }


            }
            else{
                //System.out.println(scope);
                

                System.out.println("ERROR  Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used outside its assigned scope");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;

            }
            //displayMessage(tokens.get(i).getValueOfToken(), letter);
            if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope");
                System.out.println("-----------------------------------------------------------");
                Object id = valueAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id2 = valueAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id3 = valueAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                //System.out.println(id);
                if(id==null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is not in the symbol table");

                    System.exit(0);
                }
                int scopeOfID2=(int) id;

                if(scopeOfID2 <= scope){                
                    System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + scope + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + scopeOfID2 + " ] are being used within the correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] have the same TYPE");
                    System.out.println("-----------------------------------------------------------");
                
                    if(id2.equals(id3)){
                        System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + id3 + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + id2 + " ] have the same TYPE");
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has been assigned to [ " + tokens.get(i+1).getValueOfToken() + " ]");
                        System.out.println("-----------------------------------------------------------");
                    
    
                    }
                    else{
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different TYPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");
    
                        System.out.println("-----------------------------------------------------------");
    
                        errorCounter++;
                    }
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different SCOPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");

                    errorCounter++;
                }    
            
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