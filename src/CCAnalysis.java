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
    CCHashMap idAndScope = new CCHashMap<String, Integer>();
    CCHashMap idAndType = new CCHashMap<String, Integer>();
    CCHashMap idAndValue = new CCHashMap<String, String>();





    //public static Map<String,Integer> symidAndScope = new LinkedHashMap<String,Integer>();
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
int warningCounter=0;
    public void checkForUninitializedVars(){
         //looking up type of b in b=x, which is paired with its scope

        if(listOfVars.size()>=1){
            for(int j =0; j<listOfVars.size(); j++){
                Object lookUpType2 = idAndType.getForward(listOfVars.get(j));
                String type2 = (String) lookUpType2;
                System.out.println("INFO  Analyze - WARNING! Variable [ " + listOfVars.get(j) + " ] with type [ " + type2 + " ] is declared but never initialized");
                System.out.println("-----------------------------------------------------------");
                warningCounter++;
            }
        }
        else{

        }
        
    }
    public void checkForUnusedVars(){
        //looking up type of b in b=x, which is paired with its scope

       if(listOfAssignments.size()>=1){
           for(int j =0; j<listOfAssignments.size(); j++){
               Object lookUpType3 = idAndType.getForward(listOfAssignments.get(j));
               String type3 = (String) lookUpType3;
               System.out.println("INFO  Analyze - WARNING! Variable [ " + listOfAssignments.get(j) + " ] with type [ " + type3 + " ] is initialized but never used");
               System.out.println("-----------------------------------------------------------");
               warningCounter++;
           }
       }
       else{

       }
       
   }
    boolean result;
    public boolean checkType(String type, int i){
        
        Object lookUpType = idAndType.getForward(type); //looking up type of b in b=x, which is paired with its scope

        //System.out.println(lookUpType.toString());
        if (lookUpType.equals("int")){
            if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope and type");
                System.out.println("-----------------------------------------------------------");
                Object id = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id2 = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id3 = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                //System.out.println(id);
                if(id==null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is not in the symbol table");
                    //System.exit(0);
                    System.out.println("-----------------------------------------------------------");

                    errorCounter++;
                }
                //int scopeOfID2=(int) id;

                /*if(scopeOfID2 <= scope){   //if id isnt null this will always be true              
                    System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + scope + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + scopeOfID2 + " ] are being used within the correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] have the same TYPE");
                    System.out.println("-----------------------------------------------------------");
                */
                    else if(id2.equals(id3)){
                        System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + id3 + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + id2 + " ] have the same TYPE");
                        System.out.println("-----------------------------------------------------------");
                       // System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has been assigned to [ " + tokens.get(i+1).getValueOfToken() + " ]");
                       // System.out.println("-----------------------------------------------------------");
                    
    
                    }
                    else if(!id2.equals(id3)) {
                        System.out.println("INFO  Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different TYPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");
    
                        System.out.println("-----------------------------------------------------------");
    
                        
                    }
                /*}
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different SCOPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");

                    errorCounter++;
                }   */ 
            if(idAndType.getForward(tokens.get(i+1).getValueOfToken()) != null){

                if((idAndType.getForward(tokens.get(i+1).getValueOfToken()).equals("int")) && (!idAndType.getForward(tokens.get(i+1).getValueOfToken()).equals(null))){
                    result = true;
                }
                else{
                    result = false;
                }
            }
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")){
                result = true;

            }
            else{
                result = false;
            }
        return result;

        }

        if (lookUpType.equals("string")){
            if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope");
                System.out.println("-----------------------------------------------------------");
                Object id = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id2 = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id3 = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                //System.out.println(id);
                if(id==null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is not in the symbol table");
                    //System.exit(0);
                    errorCounter++;
                }
               

                else if(id2.equals(id3)){
                    System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + id3 + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + id2 + " ] have the same TYPE");
                    System.out.println("-----------------------------------------------------------");
                    //System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has been assigned to [ " + tokens.get(i+1).getValueOfToken() + " ]");
                    //System.out.println("-----------------------------------------------------------");
                

                }
                else if(!id2.equals(id3)) {
                    System.out.println("INFO  Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different TYPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");

                    System.out.println("-----------------------------------------------------------");

                    
                }
                if(idAndType.getForward(tokens.get(i+1).getValueOfToken()).equals("string")){
                    result = true;
                }
                else{
                    result = false;
                }
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("STRING")){
                result = true;
            }
            else{
                result = false;
            }
        return result;

        }
        if (lookUpType.equals("boolean")){
            if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope");
                System.out.println("-----------------------------------------------------------");
                Object id = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id2 = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id3 = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                //System.out.println(id);
                if(id==null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is not in the symbol table");
                    //System.exit(0);
                    errorCounter++;
                }
               

                else if(id2.equals(id3)){
                    System.out.println("INFO  Analyze - PASSED! [ " + tokens.get(i-1).getValueOfToken() + "," + id3 + " ] and [ " + tokens.get(i+1).getValueOfToken() +  "," + id2 + " ] have the same TYPE");
                    System.out.println("-----------------------------------------------------------");
                    //System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has been assigned to [ " + tokens.get(i+1).getValueOfToken() + " ]");
                    //System.out.println("-----------------------------------------------------------");
                

                }
                else if(!id2.equals(id3)) {
                    System.out.println("INFO  Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] has a different TYPE than [ " + tokens.get(i+1).getValueOfToken() + " ]");

                    System.out.println("-----------------------------------------------------------");

                    
                }
                if(idAndType.getForward(tokens.get(i+1).getValueOfToken()).equals("boolean")){
                    result = true;
                }
                else{
                    result = false;
                }
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")){
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
    int numberOfVariables=0;
    List<String> listOfVars = new ArrayList<String>();
    public void VarDecl(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            listOfVars.add(tokens.get(i+1).getValueOfToken());
            numberOfVariables++;
            Object idInVarDec = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up scope of b in int b, which is paired with its scope

            Object scopeInVarDec = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up scope of b in int b, which is paired with its scope

            String typeOfVarDecID=(String) idInVarDec;
            Integer scopeOfVarDecID=(Integer) scopeInVarDec;

            if(scopeOfVarDecID!=null){
            if(scopeOfVarDecID.equals(scope)){
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being redefined within the same scope");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;

            }
            else{
                //System.out.println(scope);
                displayMessage(tokens.get(i).getValueOfToken(), letter);

            }
            
        }
        else{
            displayMessage(tokens.get(i).getValueOfToken(), letter);


            }
            //System.out.println(scopeOfVarDecID);
            //System.out.println(typeOfVarDecID);
            

            //symidAndScope.put(scope, new ArrayList<>(Arrays.asList(tokens.get(i+1).getValueOfToken())));
            idAndScope.add(tokens.get(i+1).getValueOfToken(), scope);
            idAndType.add(tokens.get(i+1).getValueOfToken(), tokens.get(i).getValueOfToken());

            counterValue++;

            
        }
    }

    int errorCounter=0;
    int numberOfAssignments=0;
    
    List<String> listOfAssignments = new ArrayList<String>();
    public void AssignStmnt(String letter, int i){
        
        if(tokens.get(i-1).getValueOfToken().equals(letter)) {
            
            numberOfAssignments++;
            Object idBeforeEquals = idAndScope.getForward(tokens.get(i-1).getValueOfToken()); //looking up scope of b in b=a, which is paired with its scope
            Object idBeforeEqualsType = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in b=a, which is paired with its scope
            if(idBeforeEqualsType==null){
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used before being declared");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
            else{
            int scopeOfID=(int) idBeforeEquals;
            
            if(scopeOfID<=scope){
            
                //checkType(tokens.get(i-1).getValueOfToken(),i);
                if(checkType(tokens.get(i-1).getValueOfToken(), i) == true){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used with its correct TYPE");
                    System.out.println("-----------------------------------------------------------");
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used with its incorrect TYPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }

            }
            else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being assigned outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;

            }
            
           
            //displayMessage(tokens.get(i).getValueOfToken(), letter);
           /* if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
                System.out.println("INFO  Analyze - Checking if [ " + tokens.get(i-1).getValueOfToken() + " ] and [ " + tokens.get(i+1).getValueOfToken() + " ] are in the symbol table with the same scope");
                System.out.println("-----------------------------------------------------------");
                Object id = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id2 = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up a in b=a, which is paired with its scope
                Object id3 = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up a in b=a, which is paired with its scope

                //System.out.println(id);
                if(id==null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is not in the symbol table");
                    System.exit(0);
                    errorCounter++;
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
        */   }
        listOfVars.remove(letter);
    
        idAndValue.add(tokens.get(i-1).getValueOfToken(), tokens.get(i+1).getValueOfToken());
        listOfAssignments.add(tokens.get(i-1).getValueOfToken());
        }
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            
            numberOfAssignments++;
            Object idBeforeEquals2 = idAndScope.getForward(tokens.get(i+1).getValueOfToken()); //looking up scope of b in b=a, which is paired with its scope
            Object idBeforeEqualsType2 = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up type of b in b=a, which is paired with its scope
            if(idBeforeEqualsType2==null){
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being used before being declared");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
            else{
            int scopeOfID2=(int) idBeforeEquals2;
            
            if(scopeOfID2<=scope){
            
                //checkType(tokens.get(i-1).getValueOfToken(),i);
                if(checkType(tokens.get(i+1).getValueOfToken(), i) == true){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being used with its correct TYPE");
                    System.out.println("-----------------------------------------------------------");
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being used with its incorrect TYPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }

            }
            else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being assigned outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;

            }
        
            }
        }
    }

    public boolean checkPrintIDScope(String expr, int i){
        Object idInPrintScope = idAndScope.getForward(expr); //looking up scope of b in print(b) which is paired with its scope
        
        if(idInPrintScope == null){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + expr + " ] is being used before being declared");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
        }
        else{
        int exprScope=(int) idInPrintScope;
        if(exprScope<= scope){ //if scope of id before = sign is less than or equal to current scope, pass
            //System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is being used within its correct SCOPE");
            //System.out.println("-----------------------------------------------------------");
            result = true;
        }
        else{
            result = false;
        }
    }
        return result;


    }
    
    String idValueType;
    public String lookupAssignmentValue(String id, int i){
        //System.out.println("once");
        Object idInPrintValue = idAndValue.getForward(id); //looking up type of b in print(1+b), which is paired with its scope
        
        String valuePrintID=(String) idInPrintValue;
        Object idInPrintType = idAndType.getForward(id); //looking up type of b in print(b), which is paired with its scope
        String typePrintID=(String) idInPrintType;
        if(valuePrintID!=null){
        if ((typePrintID.equals("int")) && ((valuePrintID.equals("0") || valuePrintID.equals("1") || valuePrintID.equals("2") || valuePrintID.equals("3") ||
        valuePrintID.equals("4") || valuePrintID.equals("5") || valuePrintID.equals("6") || valuePrintID.equals("7") ||
        valuePrintID.equals("8") || valuePrintID.equals("9") || valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
        valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
        valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
        valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
        valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
        valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
        valuePrintID.equals("z")))){
            
            if( valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
            valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
            valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
            valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
            valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
            valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
            valuePrintID.equals("z")){
                lookupAssignmentValue(valuePrintID, i); //lookup value of c in a = c to make sure c is int also
            }
            idValueType="int";
        }
        else if ((typePrintID.equals("string")) && ((valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
        valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
        valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
        valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
        valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
        valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
        valuePrintID.equals("z") || (valuePrintID.startsWith("\"") && valuePrintID.endsWith("\""))))){   
            if( valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
            valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
            valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
            valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
            valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
            valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
            valuePrintID.equals("z")){
                lookupAssignmentValue(valuePrintID, i); //lookup value of c in a = c to make sure c is string also
            }
            idValueType="string";
        }
        else if ((typePrintID.equals("boolean")) && ((valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
        valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
        valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
        valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
        valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
        valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
        valuePrintID.equals("z") ||  valuePrintID.equals("true") || valuePrintID.equals("false")))){
            
            if( valuePrintID.equals("a") || valuePrintID.equals("b") || valuePrintID.equals("c") ||
            valuePrintID.equals("d") || valuePrintID.equals("e") || valuePrintID.equals("f") || valuePrintID.equals("g") ||
            valuePrintID.equals("h") || valuePrintID.equals("i") || valuePrintID.equals("j") || valuePrintID.equals("k") || valuePrintID.equals("l") ||
            valuePrintID.equals("m") || valuePrintID.equals("n") || valuePrintID.equals("o") || valuePrintID.equals("p") ||
            valuePrintID.equals("q") || valuePrintID.equals("r") || valuePrintID.equals("s") || valuePrintID.equals("t") || valuePrintID.equals("u") ||
            valuePrintID.equals("v") || valuePrintID.equals("w") || valuePrintID.equals("x") || valuePrintID.equals("y") ||
            valuePrintID.equals("z")){
                lookupAssignmentValue(valuePrintID, i); //lookup value of c in a = c to make sure c is int also
            }
            idValueType="boolean";
        }
    }
        else {
            idValueType=null;

        }
    return idValueType;

    }
    public String lookUpIfStmnt(String expr, int i){
        
        Object idBeforeBoolOpType = idAndType.getForward(expr); //looking up type of b in if(b==a)
        //Object idAfterBoolOpType= idAndType.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of a in if(b==a)
        
        Object idBeforeBoolOpVal = idAndValue.getForward(expr); //looking up val of b in if(b==a)
        //Object idAfterBoolOpVal = idAndValue.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of a in if(b==a)
        String idType = (String) idBeforeBoolOpType;
        String idValue = (String) idBeforeBoolOpVal;
        if(idValue!=null){
            if ((idType.equals("int")) && ((idValue.equals("0") || idValue.equals("1") || idValue.equals("2") || idValue.equals("3") ||
            idValue.equals("4") || idValue.equals("5") || idValue.equals("6") || idValue.equals("7") ||
            idValue.equals("8") || idValue.equals("9") || idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
            idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
            idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
            idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
            idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
            idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
            idValue.equals("z")))){
                
                if(idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
            idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
            idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
            idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
            idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
            idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
            idValue.equals("z")){
                    lookUpIfStmnt(idValue, i); //lookup value of c in a = c to make sure c is int also
                }
                idValueType="int";
            }
            else if ((idType.equals("string")) && ((idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
            idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
            idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
            idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
            idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
            idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
            idValue.equals("z") || (idValue.startsWith("\"") && idValue.endsWith("\""))))){   
                if(idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
                    idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
                    idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
                    idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
                    idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
                    idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
                    idValue.equals("z")){
                        lookUpIfStmnt(idValue, i); //lookup value of c in a = c to make sure c is string also
                }
                idValueType="string";
            }
            else if ((idType.equals("boolean")) && ((idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
            idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
            idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
            idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
            idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
            idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
            idValue.equals("z") ||  idValue.equals("true") || idValue.equals("false")))){
                
            if(idValue.equals("a") || idValue.equals("b") || idValue.equals("c") ||
                idValue.equals("d") || idValue.equals("e") || idValue.equals("f") || idValue.equals("g") ||
                idValue.equals("h") || idValue.equals("i") || idValue.equals("j") || idValue.equals("k") || idValue.equals("l") ||
                idValue.equals("m") || idValue.equals("n") || idValue.equals("o") || idValue.equals("p") ||
                idValue.equals("q") || idValue.equals("r") || idValue.equals("s") || idValue.equals("t") || idValue.equals("u") ||
                idValue.equals("v") || idValue.equals("w") || idValue.equals("x") || idValue.equals("y") ||
                idValue.equals("z")){
                    lookUpIfStmnt(idValue, i); //lookup value of c in a = c to make sure c is int also
                }
                idValueType="boolean";
            }
        }
            else {
                idValueType=null;
    
            }

return idValueType;

    }
    public boolean ifStmnt(int i){
        Object idBeforeBoolOp = idAndType.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in if(b==a)
        Object idAfterBoolOp = idAndType.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of a in if(b==a)

        String id = (String) idBeforeBoolOp;

        if((tokens.get(i+2).getTypeOfToken()) != (tokens.get(i+4).getTypeOfToken()) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR") && (!tokens.get(i+4).getTypeOfToken().equals("CHAR")))){
            System.out.println("ERROR Analyze - FAILED! Type [ " + tokens.get(i+2).getTypeOfToken() + " ] cannot be compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR"))&& (!tokens.get(i+4).getTypeOfToken().equals("CHAR"))){
            

            String idk = lookUpIfStmnt(tokens.get(i+2).getValueOfToken(), i);
            if(id != null){
            if(idk != null){
            if(idk.equals("int")){
            if((idk.equals("int")) && (tokens.get(i+4).getTypeOfToken().equals("DIGIT"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ INT ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(idk.equals("string")){

            if((idk.equals("string")) && (tokens.get(i+4).getTypeOfToken().equals("STRING"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ STRING ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(idk.equals("boolean")){

            if((idk.equals("boolean")) && (tokens.get(i+4).getTypeOfToken().equals("BOOL_VAL"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ BOOLEAN ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(listOfAssignments.contains(tokens.get(i+2).getValueOfToken())){
            listOfAssignments.remove(tokens.get(i+2).getValueOfToken());
         }
        }
        
        

        else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being initialized a value");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;

        }
    }
    else{
        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being declared");
        System.out.println("-----------------------------------------------------------");
        errorCounter++;

}
if(tokens.get(i+4).getTypeOfToken().equals("CHAR")){
    lookUpIfStmnt(tokens.get(i+4).getValueOfToken(), i);
}
}
        return result;
    }

    public boolean whileStmnt(int i){
        Object idBeforeBoolOp = idAndType.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in if(b==a)
        Object idAfterBoolOp = idAndType.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of a in if(b==a)

        String id = (String) idBeforeBoolOp;

        if((tokens.get(i+2).getTypeOfToken()) != (tokens.get(i+4).getTypeOfToken()) && (!tokens.get(i+2).getTypeOfToken().equals("CHAR") && (!tokens.get(i+4).getTypeOfToken().equals("CHAR")))){
            System.out.println("ERROR Analyze - FAILED! Type [ " + tokens.get(i+2).getTypeOfToken() + " ] cannot be compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR"))&& (!tokens.get(i+4).getTypeOfToken().equals("CHAR"))){
            

            String idk = lookUpIfStmnt(tokens.get(i+2).getValueOfToken(), i);
            if(id != null){
            if(idk != null){
            if(idk.equals("int")){
            if((idk.equals("int")) && (tokens.get(i+4).getTypeOfToken().equals("DIGIT"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ INT ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(idk.equals("string")){

            if((idk.equals("string")) && (tokens.get(i+4).getTypeOfToken().equals("STRING"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ STRING ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(idk.equals("boolean")){

            if((idk.equals("boolean")) && (tokens.get(i+4).getTypeOfToken().equals("BOOL_VAL"))){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ BOOLEAN ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] with type [ " + idk.toUpperCase() + " ] is being compared to [ " + tokens.get(i+4).getTypeOfToken() + " ]");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
        if(listOfAssignments.contains(tokens.get(i+2).getValueOfToken())){
            listOfAssignments.remove(tokens.get(i+2).getValueOfToken());
         }
        }
        
        

        else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being initialized a value");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;

        }
    }
    else{
        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being declared");
        System.out.println("-----------------------------------------------------------");
        errorCounter++;

}
if(tokens.get(i+4).getTypeOfToken().equals("CHAR")){
    lookUpIfStmnt(tokens.get(i+4).getValueOfToken(), i);
}
}
        return result;
    }
    public boolean intExpr(int i){
        Object idBeforePlusType = idAndType.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in b=a, which is paired with its scope
        Object idAfterPlusType = idAndType.getForward(tokens.get(i+1).getValueOfToken()); //looking up type of b in b=a, which is paired with its scope


        String idAftPlus = (String) idAfterPlusType;
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")){
            if(!idAftPlus.equals("int")){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+1).getValueOfToken() + " ] is being used in an int expr but has TYPE [ "+ idAftPlus + " ]");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                    result = false;
                }

        }
        else if((!tokens.get(i-1).getTypeOfToken().equals("DIGIT")) || (!tokens.get(i+1).getTypeOfToken().equals("DIGIT"))){
            System.out.println("ERROR Analyze - FAILED! Expression [ " + tokens.get(i+1).getValueOfToken() + " ] is being used in an int expr but has TYPE [ "+ tokens.get(i+1).getTypeOfToken() + " ]");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                    result = false;
        }
        
        return result;
    }
    public boolean checkForPrintIDAssignment(String expr, int i){
        Object idValue = idAndValue.getForward(expr); //looking up value of b in b=x when printing(b)
        String exprValue=(String) idValue;
        if(exprValue != null){ //means that the variable was assigned a value
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }
    public void printStmnt(int i){
        
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (tokens.get(i+3).getValueOfToken().equals(")"))) { //case for print(b)
            //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(b) which is paired with its scope
            Object idInPrintType = idAndType.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(b)
            String typePrintID=(String) idInPrintType;
         if(checkPrintIDScope(tokens.get(i+2).getValueOfToken(), i)==true){
            if(typePrintID!=null){
                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] has been declared");
                System.out.println("-----------------------------------------------------------");
                 String ty = lookupAssignmentValue(tokens.get(i+2).getValueOfToken(), i);
               // System.out.println(ty);
               
                if(ty!=null){ //means valid var dec and assign stmnt was found
                    
                    
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used");
                    System.out.println("-----------------------------------------------------------");
                    
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] has [ " + ty + " ] type");
                    System.out.println("-----------------------------------------------------------");
                    }
                else{
            
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being initialized a value");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
                

                }
                else{
                  
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used before being declared");
                        System.out.println("-----------------------------------------------------------");
                        errorCounter++;
                }
                if(listOfAssignments.contains(tokens.get(i+2).getValueOfToken())){
                    listOfAssignments.remove(tokens.get(i+2).getValueOfToken());
                 }
            }
            else{
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used outside its correct SCOPE");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
        }
            if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+4).getTypeOfToken().equals("CHAR"))) { //case for print(1+b)
                //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(b) which is paired with its scope
                Object idInPrintType = idAndType.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of b in print(b)
                String typePrintID=(String) idInPrintType;
         if(checkPrintIDScope(tokens.get(i+4).getValueOfToken(), i)==true){

             
                if(typePrintID!=null){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] has been declared");
                    System.out.println("-----------------------------------------------------------");
                     String ty = lookupAssignmentValue(tokens.get(i+4).getValueOfToken(), i);
                    
                    if(ty!=null){ //means valid var dec and assign stmnt was found
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used");
                        System.out.println("-----------------------------------------------------------");
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] has [ " + ty + " ] type");
                        System.out.println("-----------------------------------------------------------");
                        }
                    else{
                
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used before being initialized a value");
                        System.out.println("-----------------------------------------------------------");
                        errorCounter++;
                    }
                    
    
                    }
                    else{
                      
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used before being declared");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                    }
                    if(listOfAssignments.contains(tokens.get(i+4).getValueOfToken())){
                        listOfAssignments.remove(tokens.get(i+4).getValueOfToken());
                     }
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
            }
            if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+4).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+5).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+6).getTypeOfToken().equals("CHAR"))) { //case for print(1+2+b)
            //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(b) which is paired with its scope
                Object idInPrintType = idAndType.getForward(tokens.get(i+6).getValueOfToken()); //looking up type of b in print(b)
                String typePrintID=(String) idInPrintType;
         if(checkPrintIDScope(tokens.get(i+6).getValueOfToken(), i)==true){

             
                if(typePrintID!=null){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] has been declared");
                    System.out.println("-----------------------------------------------------------");
                     String ty = lookupAssignmentValue(tokens.get(i+6).getValueOfToken(), i);
                    
                    if(ty!=null){ //means valid var dec and assign stmnt was found
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used");
                        System.out.println("-----------------------------------------------------------");
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] has [ " + ty + " ] type");
                        System.out.println("-----------------------------------------------------------");
                        }
                    else{
                
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used before being initialized a value");
                        System.out.println("-----------------------------------------------------------");
                        errorCounter++;
                    }
                    
    
                    }
                    else{
                      
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used before being declared");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                    }
                    if(listOfAssignments.contains(tokens.get(i+6).getValueOfToken())){
                        listOfAssignments.remove(tokens.get(i+6).getValueOfToken());
                     }
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
            }
            if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+4).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+5).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+6).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+7).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+8).getTypeOfToken().equals("CHAR"))) { //case for print(1+2+3+b)
            //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(b) which is paired with its scope
                Object idInPrintType = idAndType.getForward(tokens.get(i+8).getValueOfToken()); //looking up type of b in print(b)
                String typePrintID=(String) idInPrintType;
         if(checkPrintIDScope(tokens.get(i+8).getValueOfToken(), i)==true){
             
                if(typePrintID!=null){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] has been declared");
                    System.out.println("-----------------------------------------------------------");
                     String ty = lookupAssignmentValue(tokens.get(i+8).getValueOfToken(), i);
                    
                    if(ty!=null){ //means valid var dec and assign stmnt was found
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used");
                        System.out.println("-----------------------------------------------------------");
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] has [ " + ty + " ] type");
                        System.out.println("-----------------------------------------------------------");
                        }
                    else{
                
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used before being initialized a value");
                        System.out.println("-----------------------------------------------------------");
                        errorCounter++;
                    }
                    
    
                    }
                    else{
                      
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used before being declared");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                    }
                    if(listOfAssignments.contains(tokens.get(i+8).getValueOfToken())){
                        listOfAssignments.remove(tokens.get(i+8).getValueOfToken());
                     }
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
            }
                if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+4).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+5).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+6).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+7).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+8).getTypeOfToken().equals("DIGIT"))  && (tokens.get(i+9).getTypeOfToken().equals("PLUS"))
            && (tokens.get(i+10).getTypeOfToken().equals("CHAR"))) { //case for print(1+2+3+b)
            //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(b) which is paired with its scope
                Object idInPrintType = idAndType.getForward(tokens.get(i+10).getValueOfToken()); //looking up type of b in print(b)
                String typePrintID=(String) idInPrintType;
         if(checkPrintIDScope(tokens.get(i+10).getValueOfToken(), i)==true){
             
                if(typePrintID!=null){
                    System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] has been declared");
                    System.out.println("-----------------------------------------------------------");
                     String ty = lookupAssignmentValue(tokens.get(i+10).getValueOfToken(), i);
                    
                    if(ty!=null){ //means valid var dec and assign stmnt was found
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] is being used");
                        System.out.println("-----------------------------------------------------------");
                        
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] has [ " + ty + " ] type");
                        System.out.println("-----------------------------------------------------------");
                        }
                    else{
                
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] is being used before being initialized a value");
                        System.out.println("-----------------------------------------------------------");
                        errorCounter++;
                    }
                    
    
                    }
                    else{
                      
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] is being used before being declared");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                    }
                    if(listOfAssignments.contains(tokens.get(i+10).getValueOfToken())){
                        listOfAssignments.remove(tokens.get(i+10).getValueOfToken());
                     }
                }
                else{
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+10).getValueOfToken() + " ] is being used outside its correct SCOPE");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
            }
                /*
            if(typePrintID==null){
                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used in print statement before being declared");
                System.out.println("-----------------------------------------------------------");
                errorCounter++;
            }
            else{
                if(checkForPrintIDAssignment(tokens.get(i+2).getValueOfToken(), i) == false){
                    System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used in print statement before being assigned a value");
                    System.out.println("-----------------------------------------------------------");
                    errorCounter++;
                }
                else{

                    if(checkPrintIDScope(tokens.get(i+2).getValueOfToken(), i) == true){
                        System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used in print statement within its correct SCOPE");
                        System.out.println("-----------------------------------------------------------");
                    }
                    else{
                        System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+2).getValueOfToken() + " ] is being used in print statement outside its SCOPE");
                        System.out.println("-----------------------------------------------------------");
                        //i dont think this ever happens, because otherwise the var is just not declared yet
                        errorCounter++;
                    }
                }
            }
            if(listOfAssignments.contains(tokens.get(i+2).getValueOfToken())){
                listOfAssignments.remove(tokens.get(i+2).getValueOfToken());
             }

        
        }

        if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+4).getTypeOfToken().equals("CHAR"))) { //case for print(1+b)
             //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(1+b) which is paired with its scope
             Object idInPrintType = idAndType.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
             String typePrintID=(String) idInPrintType;
             Object idInPrintValue = idAndValue.getForward(tokens.get(i+4).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
             String valuePrintID=(String) idInPrintValue;
          
             if(typePrintID==null){
                 System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used in print statement before being declared");
                 System.out.println("-----------------------------------------------------------");
                 errorCounter++;
             }
             else{
                 if(checkForPrintIDAssignment(tokens.get(i+4).getValueOfToken(), i) == false){
                     System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used in print statement before being assigned a value");
                     System.out.println("-----------------------------------------------------------");
                     errorCounter++;
                 }
                 else{
 
                     if(checkPrintIDScope(tokens.get(i+4).getValueOfToken(), i) == true){
                         System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used in print statement within its correct SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         System.out.println("INFO  Analyze - Checking if Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is assigned an [ int ] value");
                         System.out.println("-----------------------------------------------------------");
                         System.out.println(valuePrintID);
                         if(typePrintID.equals("int") && lookupAssignmentValue(tokens.get(i+4).getValueOfToken(), i)=="int"){

                                System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used with correct TYPE");
                                System.out.println("-----------------------------------------------------------");
                            }
                            else{
                                System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] has type [ " + typePrintID + " ] but is assigned a value of [ " + idInPrintValue);
                                System.out.println("-----------------------------------------------------------");
                                errorCounter++;
                             } 
                        }
                         else{
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] has type [ " + typePrintID + " ] but is being used in an int expression");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                         }
                     }
                     else{
                         System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+4).getValueOfToken() + " ] is being used in print statement outside its SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         //i dont think this ever happens, because otherwise the var is just not declared yet
                         errorCounter++;
                     }
                 }
             }
             if(listOfAssignments.contains(tokens.get(i+4).getValueOfToken())){
                listOfAssignments.remove(tokens.get(i+4).getValueOfToken());
             }
        
        }
        if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+4).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+5).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+6).getTypeOfToken().equals("CHAR"))) { //case for print(1+2+b)
             //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(1+b) which is paired with its scope
             Object idInPrintType = idAndType.getForward(tokens.get(i+6).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
             String typePrintID=(String) idInPrintType;
          
             if(typePrintID==null){
                 System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used in print statement before being declared");
                 System.out.println("-----------------------------------------------------------");
                 errorCounter++;
             }
             else{
                 if(checkForPrintIDAssignment(tokens.get(i+6).getValueOfToken(), i) == false){
                     System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used in print statement before being assigned a value");
                     System.out.println("-----------------------------------------------------------");
                     errorCounter++;
                 }
                 else{
 
                     if(checkPrintIDScope(tokens.get(i+6).getValueOfToken(), i) == true){
                         System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used in print statement within its correct SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         System.out.println("INFO  Analyze - Checking if Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is assigned an [ int ] value");
                         System.out.println("-----------------------------------------------------------");
                         if(typePrintID.equals("int")){
                            System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used with correct TYPE");
                            System.out.println("-----------------------------------------------------------");
                         }
                         else{
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] has type [ " + typePrintID + " ] but is being assigned an [ int ] value");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                         }
                     }
                     else{
                         System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+6).getValueOfToken() + " ] is being used in print statement outside its SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         //i dont think this ever happens, because otherwise the var is just not declared yet
                         errorCounter++;
                     }
                 }
             }
             if(listOfAssignments.contains(tokens.get(i+6).getValueOfToken())){
                listOfAssignments.remove(tokens.get(i+6).getValueOfToken());
             }
        
        }

        if((tokens.get(i+2).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+3).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+4).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+5).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+6).getTypeOfToken().equals("DIGIT")) && (tokens.get(i+7).getTypeOfToken().equals("PLUS"))
        && (tokens.get(i+8).getTypeOfToken().equals("CHAR"))) { //case for print(1+2+3+b)
             //Object idInPrintScope = idAndScope.getForward(tokens.get(i+2).getValueOfToken()); //looking up scope of b in print(1+b) which is paired with its scope
             Object idInPrintType = idAndType.getForward(tokens.get(i+8).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
             String typePrintID=(String) idInPrintType;
          
             if(typePrintID==null){
                 System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used in print statement before being declared");
                 System.out.println("-----------------------------------------------------------");
                 errorCounter++;
             }
             else{
                 if(checkForPrintIDAssignment(tokens.get(i+8).getValueOfToken(), i) == false){
                     System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used in print statement before being assigned a value");
                     System.out.println("-----------------------------------------------------------");
                     errorCounter++;
                 }
                 else{
 
                     if(checkPrintIDScope(tokens.get(i+8).getValueOfToken(), i) == true){
                         System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used in print statement within its correct SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         System.out.println("INFO  Analyze - Checking if Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is assigned an [ int ] value");
                         System.out.println("-----------------------------------------------------------");
                         if(typePrintID.equals("int")){
                            System.out.println("INFO  Analyze - PASSED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used with correct TYPE");
                            System.out.println("-----------------------------------------------------------");
                         }
                         else{
                            System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] has type [ " + typePrintID + " ] but is being assigned an [ int ] value");
                            System.out.println("-----------------------------------------------------------");
                            errorCounter++;
                         }
                     }
                     else{
                         System.out.println("ERROR Analyze - FAILED! Variable [ " + tokens.get(i+8).getValueOfToken() + " ] is being used in print statement outside its SCOPE");
                         System.out.println("-----------------------------------------------------------");
                         //i dont think this ever happens, because otherwise the var is just not declared yet
                         errorCounter++;
                     }
                 }
             }
             if(listOfAssignments.contains(tokens.get(i+8).getValueOfToken())){
                listOfAssignments.remove(tokens.get(i+8).getValueOfToken());
             }
        
        }
*/
    }


    public static List<CCToken> tokens = new ArrayList<CCToken>();

    CCTree2 cst = new CCTree2(); //create instance of CST class
                    CCAst ast = new CCAst(); //create instance of CST class

                        //Begin Parsing Process
                    CCParser parser = new CCParser();
                    //CCAnalysis analysis = new CCAnalysis(); //create instance of Analysis class
                    CCCodeGen gen = new CCCodeGen(); //create instance of CST class

                    int programNumber =1;

    public void analyze(List<CCToken> tokenStream) {
      

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            

            String element = tokens.get(i).getValueOfToken();
           
            if(errorCounter>0){
                checkForUninitializedVars();
                    checkForUnusedVars();
                System.out.println("INFO  Analyze - FAILED! Classy Compiler Has Failed Semantic Analysis With [ " + errorCounter + " ] ERRORS And [ " + warningCounter + " ] WARNINGS");
                System.out.println("-----------------------------------------------------------");
                    
                break;
            }
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
            
                case "print":
                    printStmnt(i);
                    break;

                case "+":
                    intExpr(i);
                    break;
                   
                case "if":
                    ifStmnt(i);
                    break;
                case "while":
                    whileStmnt(i);
                    break;
                   
                case "$":
                    //counterProgram++;
                    if(errorCounter==0){
                    
                        checkForUninitializedVars();
                    checkForUnusedVars();
                        System.out.println("INFO  Analyze - SUCCESS! Classy Compiler Has Completed Semantic Analysis With [ " + errorCounter + " ] ERRORS And [ " + warningCounter + " ] WARNINGS");
                        System.out.println("-----------------------------------------------------------");
                        
                        System.out.print("Classy Compiler Is Now Building An Abstract Syntax Tree Of Program #" + (programNumber) + "\n");
                        System.out.println("-----------------------------------------------------------");
                        

                        
                        
                        parser.createAST(tokens);
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Classy Compiler Has Finished Building AST Of Program #" + (programNumber) + "\n");
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Classy Compiler Is Printing Symbol Table For Program #" + (programNumber) + "\n");
                        System.out.println("-----------------------------------------------------------");
                        ast.symbolTable();
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Classy Compiler Is Generating Code For Program #" + (programNumber) + "\n");
                        System.out.println("-----------------------------------------------------------");
                        gen.generate(tokens);
                        System.out.print("PROGRAM # " + (programNumber) + " IS COMPLETE\n");
                        System.out.println("-----------------------------------------------------------");
                        programNumber++;
                    }
                    
                break;
                    
            }
            
            //boolean test = (tokens.get(i+1).getTypeOfToken().equals("ASSIGNMENT"));
           
            //j++;
        
        
        }
        
        

    }
    
    public void symValScope(){
        int test=0;
        //System.out.println(actionMap.toString());
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
            //System.out.println(test);
            //printf("'%-5d'"
            test++;
              

           }  
           
        //System.out.println(symbolTable.toString());
        

            
    }



}