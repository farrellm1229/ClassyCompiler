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



public class CCCodeGen {

    CCHashMap idAndValue = new CCHashMap<String, String>();
    CCHashMap idAndVar = new CCHashMap<String, String>();
    CCHashMap idAndCounter = new CCHashMap<String, String>();
    CCHashMap decimalAndHex = new CCHashMap<String, String>();
    CCHashMap varStorage = new CCHashMap<String, String>();
    CCHashMap varStorage2 = new CCHashMap<String, String>();







    int errorCounter = 0;
    int scope = -1;
    int numOfBytes = 0;
    String[] memory = new String[256];
    public void newScope(){
        scope++;
    }
    public void oldScope(){
        scope--;
    }

    int memCount = 0;
    int varDeclCounter = 0;
    int varDecID = 0x1B;
    int f7 = 46;
    int place = 255;
    int place2 = 255;


    CCHashMap idAndAssignVal = new CCHashMap<String, String>();
    CCHashMap idAndf7 = new CCHashMap<String, String>();


    public void CodeGenVarDecl(int i) {
        if((tokens.get(i+1).getTypeOfToken().equals("CHAR")) && (!tokens.get(i).getValueOfToken().equals("string"))){
            
            numOfBytes += 5;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ VarDecl ] in scope " + scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
            memory[memCount] = "A9";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
            memory[memCount] = "8D";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ T" + varDeclCounter + " ] byte in memory...");
           
            
            
            idAndValue.add(tokens.get(i+1).getValueOfToken(), "T"+varDeclCounter);
            varDeclCounter++;
            
            String idk = Integer.toHexString(f7+1);
            idAndf7.add(tokens.get(i+1).getValueOfToken(), idk);
            f7++;
            varStorage2.add(tokens.get(i+1).getValueOfToken(), idk);

            System.out.println(idk);
            memory[memCount] = idk.toUpperCase();
            //memory[memCount] ="" + varDecID;
            memCount+=1;
            idAndVar.add(tokens.get(i+1).getValueOfToken(), "" +idk.toUpperCase());
varDecID++;
            
            // memory[memCount] = "1D";
            //memCount+=1;
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            
            System.out.println("-----------------------------------------------------------");

        }

        else if((tokens.get(i+1).getTypeOfToken().equals("CHAR")) && (tokens.get(i).getValueOfToken().equals("string"))){
           //System.out.println("ijdojodjoisjs");
           System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ VarDecl ] in scope " + scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Preparing to store string in heap");

            idAndValue.add(tokens.get(i+1).getValueOfToken(), "T"+varDeclCounter);
            varDeclCounter++;
            
            String idk = Integer.toHexString(f7+1);
            idAndf7.add(tokens.get(i+1).getValueOfToken(), idk);
            f7++;
            idAndVar.add(tokens.get(i+1).getValueOfToken(), "" +idk.toUpperCase());
           
        }
        
    
    }

    
    
    public void CodeGenAssignStmnt(int i) {
        if(tokens.get(i-1).getTypeOfToken().equals("CHAR")) {
            
            numOfBytes += 5;
            idAndAssignVal.add(tokens.get(i-1).getValueOfToken(),tokens.get(i+1).getValueOfToken());
            Object idInPrintValue = idAndValue.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ AssignStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")) {
                System.out.println("INFO  CodeGen - Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is assigned [ 0"+ tokens.get(i+1).getValueOfToken() + " ]...");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 0"+ tokens.get(i+1).getValueOfToken() + " ] byte in memory...");
            memory[memCount] = "0"+ tokens.get(i+1).getValueOfToken().toString();
            memCount+=1;

                System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
                memory[memCount] = "8D";
                memCount+=1;

            Object idInPrintValue4 = idAndf7.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope

                String valuePrintID4=(String) idInPrintValue4;
            String idk = Integer.toHexString(f7);
            
            if(valuePrintID4 !=null){
            memory[memCount] = valuePrintID4.toUpperCase();
            //memory[memCount] =valuePrintID2;
            memCount+=1;
            }

            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
                memory[memCount] = "00";
                memCount+=1;
            }
            else if(tokens.get(i+1).getTypeOfToken().equals("BOOL_VAL")) {
                if(tokens.get(i+1).getValueOfToken().equals("true")) {

                System.out.println("INFO  CodeGen - Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is assigned [ 01 ]...");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            
            System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
            memory[memCount] = "8D";
            memCount+=1;
            
            
            Object idInPrintValue3 = idAndValue.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID3=(String) idInPrintValue3;
            //System.out.println("INFO  CodeGen - Storing [ " + valuePrintID + " ] byte in memory...");
            System.out.println("INFO  CodeGen - Storing [ " + valuePrintID3 + " ] byte in memory...");

            Object idInPrintValue2 = idAndVar.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID2=(String) idInPrintValue2;
            Object idInPrintValue4 = idAndf7.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID4=(String) idInPrintValue4;
            String idk = Integer.toHexString(f7);
            
            if(valuePrintID4 !=null){
            memory[memCount] = valuePrintID4.toUpperCase();
            //memory[memCount] =valuePrintID2;
            memCount+=1;
            }
            //varDecID++;
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            
            System.out.println("-----------------------------------------------------------");

                }
                else if(tokens.get(i+1).getValueOfToken().equals("false")) {
                    
                System.out.println("INFO  CodeGen - Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is assigned [ 00 ]...");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
                }
                
            
                System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
                memory[memCount] = "8D";
                memCount+=1;
                
                
                Object idInPrintValue3 = idAndValue.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
            
                String valuePrintID3=(String) idInPrintValue3;
                //System.out.println("INFO  CodeGen - Storing [ " + valuePrintID + " ] byte in memory...");
                System.out.println("INFO  CodeGen - Storing [ " + valuePrintID3 + " ] byte in memory...");
    
                Object idInPrintValue2 = idAndVar.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
            
                String valuePrintID2=(String) idInPrintValue2;
                Object idInPrintValue4 = idAndf7.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
            
                String valuePrintID4=(String) idInPrintValue4;
                String idk = Integer.toHexString(f7);
                if(valuePrintID4 !=null){
                memory[memCount] = valuePrintID4.toUpperCase();
                //memory[memCount] =valuePrintID2;
                memCount+=1;
                }
                //varDecID++;
                memory[memCount] = "00";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
                
                System.out.println("-----------------------------------------------------------");
    
    
            }
            
            else if(tokens.get(i+1).getTypeOfToken().equals("STRING")) {
               // System.out.println("hehrhiehoieh");
                int lengthOfString = (tokens.get(i+1).getValueOfToken().length()-2);
                String stringForAscii = tokens.get(i+1).getValueOfToken().substring(1, lengthOfString+1);
                
                int heapLocation = (place-stringForAscii.length());
               

                int test = heapLocation;
                boolean marker = false;
                boolean marker2 = false;

                for(int k=0; k<stringForAscii.length(); k++){
                    memory[test-1] = "00";
                    char character = stringForAscii.charAt(k); 
                    int ascii = (int) character;
                    //System.out.println(ascii);
                    String decimalToHex = Integer.toHexString((ascii)).toUpperCase();

                    decimalAndHex.add(ascii, decimalToHex);
                    //System.out.println(decimalToHex);

                    //add string to heap in correct locations based on string length
                    
                 

                    memory[heapLocation] = decimalToHex;
                    heapLocation++;
                    marker = true;
                    
                   
                    memory[255] = "00";

                
            }

            if(marker == true){
                place = (place-(stringForAscii.length()+1));
               // marker = false;
                //System.out.println(place);
             
            }
                

                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                //System.out.println(lengthOfString);
                System.out.println("INFO  CodeGen - Adding [ string ] to heap...");

                System.out.println(place);
                if(marker2 == true){
                    place2 = place;

                }
                String stringStorage = Integer.toHexString((place+1));

                System.out.println("INFO  CodeGen - Storing [ " + stringStorage.toUpperCase() + " ] byte in memory...");

                memory[memCount] = "" + stringStorage.toUpperCase();
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
                memory[memCount] = "8D";
                memCount+=1;

                System.out.println("INFO  CodeGen - Storing [ T" + (varDeclCounter) + " ] byte in memory...");
           
            
            
            idAndValue.add(tokens.get(i+1).getValueOfToken(), "T"+varDeclCounter);
            //varDeclCounter++;
            
            String idk = Integer.toHexString(f7+1);
            idAndf7.add(tokens.get(i+1).getValueOfToken(), idk);
            //f7++;
           // System.out.println("idk");
            //System.out.println(idk);

            varStorage.add(tokens.get(i-1).getValueOfToken(), idk.toUpperCase());

            memory[memCount] = idk.toUpperCase();
            //memory[memCount] ="" + varDecID;
            memCount+=1;
            idAndVar.add(tokens.get(i+1).getValueOfToken(), "" +idk.toUpperCase());
varDecID++;
            
                //System.out.println("INFO  CodeGen - Storing [ 0F ] byte in memory...");
                //memory[memCount] = "0F";
                //memCount+=1;
                //System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
                memory[memCount] = "00";
                memCount+=1;
                
            
               
                
            }

        }
    
    }

    public void CodeGenPrintStmnt(int i) {
        
        if(tokens.get(i+2).getTypeOfToken().equals("CHAR")) {

            
            Object idInPrintAssignedVal = idAndAssignVal.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valueInPrint=(String) idInPrintAssignedVal;
            //System.out.println(valueInPrint);
            
if(valueInPrint.equals("0") || valueInPrint.equals("1") ||valueInPrint.equals("2") || valueInPrint.equals("3") ||valueInPrint.equals("4") || valueInPrint.equals("5") ||
valueInPrint.equals("6") || valueInPrint.equals("7") ||valueInPrint.equals("8") || valueInPrint.equals("9")){ 
            Object idInPrintValue = idAndVar.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            Object idk1 = idAndValue.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String idk2=(String) idk1;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ PrintStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ AC ] byte in memory...");
            memory[memCount] = "AC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ " + idk2 + " ] byte in memory...");

            memory[memCount] = valuePrintID;
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ FF ] byte in memory...");
            memory[memCount] = "FF";
            memCount+=1;
            //System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            //memory[memCount] = "00";
            //memCount+=1;

/*
            System.out.println("INFO  CodeGen - Storing [ 66 ] byte in memory...");
            memory[memCount] = "66";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 61 ] byte in memory...");
            memory[memCount] = "61";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 6C ] byte in memory...");
            memory[memCount] = "6C";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 73 ] byte in memory...");
            memory[memCount] = "73";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 65 ] byte in memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 74 ] byte in memory...");
            memory[memCount] = "74";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 72 ] byte in memory...");
            memory[memCount] = "72";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 75 ] byte in memory...");
            memory[memCount] = "75";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 65 ] byte in memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
*/
        }

    else if(valueInPrint.equals("false") || valueInPrint.equals("true")){
        Object idInPrintValue = idAndVar.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            Object idk1 = idAndValue.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String idk2=(String) idk1;
            System.out.println("-----------------------------------------------------------");

            System.out.println("INFO  CodeGen - Generating code for [ PrintStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ AC ] byte in memory...");
            memory[memCount] = "AC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ " + idk2 + " ] byte in memory...");

            memory[memCount] = valuePrintID;
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ FF ] byte in memory...");
            memory[memCount] = "FF";
            memCount+=1;
          //  System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
           // memory[memCount] = "00";
           // memCount+=1;
             /* System.out.println("INFO  CodeGen - Storing [ 66 ] byte in memory...");
            memory[memCount] = "66";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 61 ] byte in memory...");
            memory[memCount] = "61";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 6C ] byte in memory...");
            memory[memCount] = "6C";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 73 ] byte in memory...");
            memory[memCount] = "73";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 65 ] byte in memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 74 ] byte in memory...");
            memory[memCount] = "74";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 72 ] byte in memory...");
            memory[memCount] = "72";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 75 ] byte in memory...");
            memory[memCount] = "75";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 65 ] byte in memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            int test = memCount;
            System.out.println(test);
*/
            }
    else if(valueInPrint.startsWith("\"")){
        //Object idInPrintValue = idAndVar.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
          //  String valuePrintID=(String) idInPrintValue;
          Object idInPrintValue = varStorage.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            Object idk1 = idAndValue.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String idk2=(String) idk1;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ PrintStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ AC ] byte in memory...");
            memory[memCount] = "AC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ " + idk2 + " ] byte in memory...");

            System.out.println(valuePrintID);
            memory[memCount] = valuePrintID;
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 02 ] byte in memory...");
            memory[memCount] = "02";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ FF ] byte in memory...");
            memory[memCount] = "FF";
            memCount+=1;
          //  System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
           // memory[memCount] = "00";
            //memCount+=1;

    
    }

        }
    }
    public void CodeGenIfStmnt(int i) {

        
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (tokens.get(i+4).getTypeOfToken().equals("DIGIT"))){
            
            numOfBytes += 5;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ IfStmnt ] in scope " + scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 0"+ tokens.get(i+4).getValueOfToken() + " ] byte in memory...");
            memory[memCount] = "0"+ tokens.get(i+4).getValueOfToken().toString();
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ EC ] byte in memory...");
            memory[memCount] = "EC";
            memCount+=1;
            //System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
            //memory[memCount] = "8D";
            //memCount+=1;
            Object tVar = idAndValue.getForward(tokens.get(i+2).getValueOfToken());
            String tVarCounter = (String) tVar;
            Object tVar2 = idAndf7.getForward(tokens.get(i+2).getValueOfToken());
            String tVarCounter2 = (String) tVar2.toString().toUpperCase();

            System.out.println("INFO  CodeGen - Storing [ "+ tVarCounter + " ] byte in memory...");
            memory[memCount] = tVarCounter2;
            memCount+=1;
            System.out.println("here");
            System.out.println(tVarCounter2);
            System.out.println(tVarCounter);

            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            System.out.println("-----------------------------------------------------------");

            memory[memCount] = "00";
            memCount+=1;

            memory[memCount] = "D0";
            memCount+=1;

            
            memory[memCount] = "06";
            memCount+=1;

        }

      
        
    
    }
    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void memArray(){


        int k;
        int j = memory.length;
        for(k=memCount; k<j; k++){
            if(memory[memCount]== null){
                memory[memCount] = "00";
                memCount+=1;
            }
        }
        System.out.println(Arrays.toString(memory).replaceAll("[\\,]", "").replaceAll("[\\[]", "").replaceAll("[\\]]", ""));
    }

    public void checkForLengthOfCodeGen(int count){
        System.out.println(count);

        //generateCode(count);

    }

    public void generateCode(){

    }
    public void loadIntCode(){
            

    }
    public void generate(List<CCToken> tokenStream) {
      

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            String element = tokens.get(i).getValueOfToken();

            switch (element) {
                case "{":
                    newScope();
                   // checkForLengthOfCodeGen();
                break;

                case "}":
                    oldScope();
                break;
    
                case "=":
                    CodeGenAssignStmnt(i);
                break;

                //VARDECL
                case "int":
                   CodeGenVarDecl(i);
                  // loadIntCode();
                break;

                case "string":
                    CodeGenVarDecl(i);
                break;

                case "boolean":
                    CodeGenVarDecl(i);
                break;
            
                case "print":
                    CodeGenPrintStmnt(i);
                break;
                case "if":
                    CodeGenIfStmnt(i);
                break;
                
                   
                case "$":

               // generateCode();
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("INFO  CodeGen - SUCCESS! Classy Compiler Has Generated The Following Code:");
                    System.out.println("-----------------------------------------------------------");
                    memArray();
                    System.out.println("-----------------------------------------------------------");

                    if(errorCounter==0){
                    
                        //System.out.println("INFO  Analyze - SUCCESS! Classy Compiler Has Completed Semantic Analysis With [ " + errorCounter + " ] ERRORS And [ " + warningCounter + " ] WARNINGS");
                        //System.out.println("-----------------------------------------------------------");
                    }
                break;
                    
            }
        }
    }



}