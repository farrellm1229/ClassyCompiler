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
    CCHashMap assignedValue = new CCHashMap<String, String>();
    CCHashMap assignedValue2 = new CCHashMap<String, String>();









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
    int f7 = 65;
    int place = 255;
    int place2 = 255;


    CCHashMap idAndAssignVal = new CCHashMap<String, String>();
    CCHashMap idAndf7 = new CCHashMap<String, String>();


    public void CodeGenVarDecl(int i) {
        try{
    
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
        
    }catch(ArrayIndexOutOfBoundsException e) {
            
            System.out.println("ERROR CodeGen - Classy Compiler Has Stopped Generating Code Due To A Memory Overflow");
            System.out.println("-----------------------------------------------------------");
            System.exit(1);

         }
    }

    
    
    public void CodeGenAssignStmnt(int i) {
        try{
        if(tokens.get(i-1).getTypeOfToken().equals("CHAR")) {
            
            numOfBytes += 5;
            idAndAssignVal.add(tokens.get(i-1).getValueOfToken(),tokens.get(i+1).getValueOfToken());
            Object idInPrintValue = idAndValue.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
            
            String valuePrintID=(String) idInPrintValue;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ AssignStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            if(tokens.get(i+1).getTypeOfToken().equals("DIGIT")) {
                if(!tokens.get(i+2).getValueOfToken().equals("+")){
                    assignedValue2.add(tokens.get(i-1).getValueOfToken(), tokens.get(i+1).getValueOfToken());
                   // System.out.println("okookoo");
                    //System.out.println(tokens.get(i+1).getValueOfToken());
                }
                assignedValue.add(tokens.get(i-1).getValueOfToken(), tokens.get(i+1).getValueOfToken()); //store a, 3 in a=3
                System.out.println("INFO  CodeGen - Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is assigned [ 0"+ tokens.get(i+1).getValueOfToken() + " ]...");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                if((tokens.get(i+2).getValueOfToken().equals("+")) && (tokens.get(i+3).getTypeOfToken().equals("DIGIT"))){
                    System.out.println("INFO  CodeGen - Storing [ 0"+ (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(tokens.get(i+3).getValueOfToken())) +" ] byte in memory...");
                    int v = (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(tokens.get(i+3).getValueOfToken()));
                    String v2 = Integer.toString(v);
                    memory[memCount] = "0"+ v2;
                memCount+=1;
                assignedValue.add(tokens.get(i-1).getValueOfToken(), v2);
                }
                else if((tokens.get(i+2).getValueOfToken().equals("+")) && (tokens.get(i+3).getTypeOfToken().equals("CHAR"))){
                    //A9 03 6D 39 00 8D 39 00 A2 01 EC FF 00 D0 DE 00
                    if(mark==true){
                    Object lookUpValue = assignedValue2.getForward(tokens.get(i+3).getValueOfToken());
                    String lUpVal = (String) lookUpValue;
                   // System.out.println("imhere");
                    //System.out.println(lUpVal);
                    System.out.println("INFO  CodeGen - Storing [ 0"+ (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(lUpVal)) +" ] byte in memory...");
                    int v = (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(lUpVal));
                    String v2 = Integer.toString(v);
                  
                    //memory[memCount] = "0"+ v2;
                    memory[memCount] = "0" + tokens.get(i+1).getValueOfToken();
                memCount+=1;
                assignedValue.add(tokens.get(i-1).getValueOfToken(), v2);
                   
                    
                Object idInPrintValue4 = idAndf7.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope

                String valuePrintID4=(String) idInPrintValue4;
                memory[memCount] = "6D";
                memCount+=1;
                memory[memCount] = valuePrintID4.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "8D";
                memCount+=1;
                
//System.out.println("value in print id 4");
//System.out.println(valuePrintID4.toUpperCase());
                memory[memCount] = valuePrintID4.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "D5";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                    }
                    else{
                        Object lookUpValue = assignedValue2.getForward(tokens.get(i+3).getValueOfToken());
                    String lUpVal = (String) lookUpValue;
                   // System.out.println("imhere");
                    //System.out.println(lUpVal);
                    System.out.println("INFO  CodeGen - Storing [ 0"+ (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(lUpVal)) +" ] byte in memory...");
                    int v = (Integer.valueOf(tokens.get(i+1).getValueOfToken()) + Integer.valueOf(lUpVal));
                    String v2 = Integer.toString(v);
                    memory[memCount] = "0"+ v2;
                memCount+=1;
                assignedValue.add(tokens.get(i-1).getValueOfToken(), v2);
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
            
                }
                else{
                System.out.println("INFO  CodeGen - Storing [ 0"+ tokens.get(i+1).getValueOfToken() + " ] byte in memory...");
            memory[memCount] = "0"+ tokens.get(i+1).getValueOfToken().toString();
            memCount+=1;
                }

               //maybe this is wrong System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
               if(!tokens.get(i+2).getValueOfToken().equals("+")) {
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

                //System.out.println(place);
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
    }catch(ArrayIndexOutOfBoundsException e) {
            
            System.out.println("ERROR CodeGen - Classy Compiler Has Stopped Generating Code Due To A Memory Overflow");
            System.out.println("-----------------------------------------------------------");
            System.exit(1);

         }
    
    }

    public void CodeGenPrintStmnt(int i) {

        try{
        
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
        else if(tokens.get(i+2).getTypeOfToken().equals("STRING")) {
            // System.out.println("hehrhiehoieh");
             int lengthOfString = (tokens.get(i+2).getValueOfToken().length()-2);
             String stringForAscii = tokens.get(i+2).getValueOfToken().substring(1, lengthOfString+1);
             
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
             

             System.out.println("INFO  CodeGen - Storing [ A0 ] byte in memory...");
             memory[memCount] = "A0";
             memCount+=1;
             //System.out.println(lengthOfString);
             System.out.println("INFO  CodeGen - Adding [ string ] to heap...");

             //System.out.println(place);
             if(marker2 == true){
                 place2 = place;

             }
             String stringStorage = Integer.toHexString((place+1));

             System.out.println("INFO  CodeGen - Storing [ " + stringStorage.toUpperCase() + " ] byte in memory...");

             memory[memCount] = "" + stringStorage.toUpperCase();
             memCount+=1;
             System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
             memory[memCount] = "A2";
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

         //memory[memCount] = idk.toUpperCase();
         memory[memCount] ="02";;
         memCount+=1;
         idAndVar.add(tokens.get(i+1).getValueOfToken(), "" +idk.toUpperCase());
varDecID++;
         
             //System.out.println("INFO  CodeGen - Storing [ 0F ] byte in memory...");
             //memory[memCount] = "0F";
             //memCount+=1;
             //System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
             memory[memCount] = "FF";
             memCount+=1;
             
         
            
             
         }
        }catch(ArrayIndexOutOfBoundsException e) {
            
            System.out.println("ERROR CodeGen - Classy Compiler Has Stopped Generating Code Due To A Memory Overflow");
            System.out.println("-----------------------------------------------------------");
            System.exit(1);

         }
    }
    public void CodeGenIfStmnt(int i) {
try{
        
        if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (tokens.get(i+4).getTypeOfToken().equals("DIGIT"))){

            if(tokens.get(i+3).getValueOfToken().equals("==")){
            numOfBytes += 5;
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ IfStmnt - Equality ] in scope " + scope);
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
           

            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            System.out.println("-----------------------------------------------------------");

            memory[memCount] = "00";
            memCount+=1;

            memory[memCount] = "D0";
            memCount+=1;

            
            memory[memCount] = "06";
            memCount+=1;
            
        }
        else if(tokens.get(i+3).getValueOfToken().equals("!=")){
            if(tokens.get(i+4).getTypeOfToken().equals("BOOL_VAL")){
                System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ IfStmnt - Inequality ] in scope " + scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            if(tokens.get(i+4).getValueOfToken().equals("true")){
            System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            }
            else if(tokens.get(i+4).getValueOfToken().equals("false")){
                System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
                memory[memCount] = "00";
                memCount+=1;
                }
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

            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            System.out.println("-----------------------------------------------------------");

            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ D0 ] byte in memory...");
            memory[memCount] = "D0";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 02 ] byte in memory...");
            memory[memCount] = "02";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            //hereiam
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ EC ] byte in memory...");
            memory[memCount] = "EC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ FF ] byte in memory...");
            memory[memCount] = "FF";
            memCount+=1;

            }

            else if(tokens.get(i+4).getTypeOfToken().equals("DIGIT")){
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Generating code for [ IfStmnt - Inequality ] in scope " + scope);
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

            System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
            System.out.println("-----------------------------------------------------------");

            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ D0 ] byte in memory...");
            memory[memCount] = "D0";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 02 ] byte in memory...");
            memory[memCount] = "02";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ A2 ] byte in memory...");
            //hereiam
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 01 ] byte in memory...");
            memory[memCount] = "01";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ EC ] byte in memory...");
            memory[memCount] = "EC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ FF ] byte in memory...");
            memory[memCount] = "FF";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");

            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ D0 ] byte in memory...");
            memory[memCount] = "D0";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 06 ] byte in memory...");
            memory[memCount] = "06";
            memCount+=1;
          /*  System.out.println("INFO  CodeGen - Storing [ AC ] byte in memory...");
            memory[memCount] = "AC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ " + tVarCounter + " ] byte in memory...");
            memory[memCount] = tVarCounter2;
            memCount+=1;
            System.out.println("INFO  CodeGen - Storing [ 00 ] byte in memory...");
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
            */
        }}
    }

      
}catch(ArrayIndexOutOfBoundsException e) {
            
        System.out.println("ERROR CodeGen - Classy Compiler Has Stopped Generating Code Due To A Memory Overflow");
        System.out.println("-----------------------------------------------------------");
        System.exit(1);

     }
    
    }

    boolean mark;

    public void CodeGenWhileStmnt(int i) {
        mark=true;
        try{
            if((tokens.get(i+2).getTypeOfToken().equals("CHAR")) && (tokens.get(i+4).getTypeOfToken().equals("DIGIT"))){

                if(tokens.get(i+3).getValueOfToken().equals("!=")){
                numOfBytes += 5;
                //A9 04 8D 35 00 AE 34 00 EC 35 00 A2 00 D0 02 A2 01 EC FF 00 D0 07 A2 01 EC FF 00 D0 E3 00 66 61 6C 73 65 00 74 72 75 65 00
                System.out.println("-----------------------------------------------------------");
                System.out.println("INFO  CodeGen - Generating code for [ WhileStmnt - Inequality ] in scope " + scope);
                System.out.println("-----------------------------------------------------------");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 0"+ tokens.get(i+4).getValueOfToken() + " ] byte in memory...");
                memory[memCount] = "0"+ tokens.get(i+4).getValueOfToken().toString();
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
                memory[memCount] = "8D";
                memCount+=1;
                
                Object tVar = idAndValue.getForward(tokens.get(i+2).getValueOfToken());
                String tVarCounter = (String) tVar;
                Object tVar2 = idAndf7.getForward(tokens.get(i+2).getValueOfToken());
                //int tVar3 = (int) tVar2;
                String ic = (String) tVar2;
                int ix = Integer.decode("0x" + ic);
               // System.out.println(ic);
                //System.out.println(ix);
                ix+=1;
                String ix2 = Integer.toHexString(ix);
                //System.out.println(ix2); //this is 2F + 1

               // String tVarCounter2 = (String) tVar2.toString().toUpperCase();
                //System.out.println("INFO  CodeGen - Storing [ "+ tVarCounter + " ] byte in memory...");
                //memory[memCount] = tVarCounter2;
             ///   memCount+=1;
               
                System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
                System.out.println("-----------------------------------------------------------");
    
                memory[memCount] = ix2.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "AE";
                memCount+=1;
                memory[memCount] = ic.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = ix2.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
//im here A2 00 D0 02 A2 01 EC FF 00 D0 15

                    
                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "02";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;
                
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "15";
                memCount+=1;/*
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "02";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;

                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "02";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;
                
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
               /* memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "07";
                memCount+=1;

                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "E3";
                memCount+=1;
                
                memory[memCount] = "00";
                memCount+=1;
                
*/

                memory[245] = "66";
                memory[246] = "61";
                memory[247] = "6C";
                memory[248] = "73";
                memory[249] = "65";
                memory[250] = "00";
                memory[251] = "74";
                memory[252] = "72";
                memory[253] = "75";
                memory[254] = "65";
                memory[255] = "00";


                
            }
            else if(tokens.get(i+3).getValueOfToken().equals("==")){

                
                numOfBytes += 5;
                //A9 04 8D 35 00 AE 34 00 EC 35 00 A2 00 D0 02 A2 01 EC FF 00 D0 07 A2 01 EC FF 00 D0 E3 00 66 61 6C 73 65 00 74 72 75 65 00
                System.out.println("-----------------------------------------------------------");
                System.out.println("INFO  CodeGen - Generating code for [ WhileStmnt - Equality ] in scope " + scope);
                System.out.println("-----------------------------------------------------------");
                System.out.println("INFO  CodeGen - Storing [ A9 ] byte in memory...");
                memory[memCount] = "A9";
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 0"+ tokens.get(i+4).getValueOfToken() + " ] byte in memory...");
                memory[memCount] = "0"+ tokens.get(i+4).getValueOfToken().toString();
                memCount+=1;
                System.out.println("INFO  CodeGen - Storing [ 8D ] byte in memory...");
                memory[memCount] = "8D";
                memCount+=1;
                
                Object tVar = idAndValue.getForward(tokens.get(i+2).getValueOfToken());
                String tVarCounter = (String) tVar;
                Object tVar2 = idAndf7.getForward(tokens.get(i+2).getValueOfToken());
                //int tVar3 = (int) tVar2;
                String ic = (String) tVar2;
                int ix = Integer.decode("0x" + ic);
                //System.out.println(ic);
                //System.out.println(ix);
                ix+=1;
                String ix2 = Integer.toHexString(ix);
                //System.out.println(ix2); //this is 2F + 1

               // String tVarCounter2 = (String) tVar2.toString().toUpperCase();
                //System.out.println("INFO  CodeGen - Storing [ "+ tVarCounter + " ] byte in memory...");
                //memory[memCount] = tVarCounter2;
             ///   memCount+=1;
               
                System.out.println("INFO  CodeGen - Storing [ XX ] byte in memory...");
                System.out.println("-----------------------------------------------------------");
    
                memory[memCount] = ix2.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "AE";
                memCount+=1;
                memory[memCount] = ic.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = ix2.toUpperCase();
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
//im here A2 00 D0 02 A2 01 EC FF 00 D0 15
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "15";
                memCount+=1;
                /*
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "02";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;

                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "02";
                memCount+=1;
                memory[memCount] = "A2";
                memCount+=1;
                
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
               /* memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "07";
                memCount+=1;

                memory[memCount] = "A2";
                memCount+=1;
                memory[memCount] = "01";
                memCount+=1;
                memory[memCount] = "EC";
                memCount+=1;
                memory[memCount] = "FF";
                memCount+=1;
                memory[memCount] = "00";
                memCount+=1;
                memory[memCount] = "D0";
                memCount+=1;
                memory[memCount] = "E3";
                memCount+=1;
                
                memory[memCount] = "00";
                memCount+=1;
                
*/

                memory[245] = "66";
                memory[246] = "61";
                memory[247] = "6C";
                memory[248] = "73";
                memory[249] = "65";
                memory[250] = "00";
                memory[251] = "74";
                memory[252] = "72";
                memory[253] = "75";
                memory[254] = "65";
                memory[255] = "00";


                
            }
    }

        }catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR CodeGen - Classy Compiler Has Stopped Generating Code Due To A Memory Overflow");
            System.out.println("-----------------------------------------------------------");
            System.exit(1);
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
        //System.out.println(count);

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
                case "while":
                    CodeGenWhileStmnt(i);
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