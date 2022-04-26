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


    int errorCounter = 0;
    int scope = -1;
    String[] memory = new String[256];
    public void newScope(){
        scope++;
    }
    public void oldScope(){
        scope--;
    }

    int memCount = 0;
    int varDeclCounter = 0;
    int varDecID = 27;
    CCHashMap valAndByteCode = new CCHashMap<String, String>();

    public void CodeGenVarDecl(int i) {
        
        if(tokens.get(i+1).getTypeOfToken().equals("CHAR")) {
            System.out.println("INFO  CodeGen - Generating code for [ VarDecl ] in scope " + scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Pushing [ A9 ] byte to memory...");
            memory[memCount] = "A9";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 00 ] byte to memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 8D ] byte to memory...");
            memory[memCount] = "8D";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ T" + varDeclCounter + " ] byte to memory...");
            varDeclCounter++;
            idAndValue.add(tokens.get(i+1).getValueOfToken(), "T"+varDeclCounter);

            memory[memCount] ="" + varDecID;
            memCount+=1;
            idAndVar.add(tokens.get(i+1).getValueOfToken(), "" +varDecID);
varDecID++;
            
            // memory[memCount] = "1D";
            //memCount+=1;
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ XX ] byte to memory...");
            
            System.out.println("-----------------------------------------------------------");

        }
    
    }
    public void CodeGenAssignStmnt(int i) {
        if(tokens.get(i-1).getTypeOfToken().equals("CHAR")) {
            Object idInPrintValue = idAndValue.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            System.out.println("INFO  CodeGen - Generating code for [ AssignStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Variable [ " + tokens.get(i-1).getValueOfToken() + " ] is assigned [ 0"+ tokens.get(i+1).getValueOfToken() + " ]...");
            System.out.println("INFO  CodeGen - Pushing [ A9 ] byte to memory...");
            memory[memCount] = "A9";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 0"+ tokens.get(i+1).getValueOfToken() + " ] byte to memory...");
            memory[memCount] = "0"+ tokens.get(i+1).getValueOfToken().toString();
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 8D ] byte to memory...");
            memory[memCount] = "8D";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ " + valuePrintID + " ] byte to memory...");

            Object idInPrintValue2 = idAndVar.getForward(tokens.get(i-1).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID2=(String) idInPrintValue2;
            memory[memCount] =valuePrintID2;
            memCount+=1;
            varDecID++;
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ XX ] byte to memory...");
            
            System.out.println("-----------------------------------------------------------");

        }
    
    }

    public void CodeGenPrintStmnt(int i) {
        
        if(tokens.get(i+2).getTypeOfToken().equals("CHAR")) {
            Object idInPrintValue = idAndVar.getForward(tokens.get(i+2).getValueOfToken()); //looking up type of b in print(1+b), which is paired with its scope
        
            String valuePrintID=(String) idInPrintValue;
            System.out.println("INFO  CodeGen - Generating code for [ PrintStmnt ] in scope "+ scope);
            System.out.println("-----------------------------------------------------------");
            System.out.println("INFO  CodeGen - Pushing [ AC ] byte to memory...");
            memory[memCount] = "AC";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ T0 ] byte to memory...");
            memory[memCount] = valuePrintID;
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ XX ] byte to memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ A2 ] byte to memory...");
            memory[memCount] = "A2";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 01 ] byte to memory...");
            memory[memCount] = "01";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ FF ] byte to memory...");
            memory[memCount] = "FF";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 00 ] byte to memory...");
            memory[memCount] = "00";
            memCount+=1;


            System.out.println("INFO  CodeGen - Pushing [ 66 ] byte to memory...");
            memory[memCount] = "66";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 61 ] byte to memory...");
            memory[memCount] = "61";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 6C ] byte to memory...");
            memory[memCount] = "6C";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 73 ] byte to memory...");
            memory[memCount] = "73";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 65 ] byte to memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 00 ] byte to memory...");
            memory[memCount] = "00";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 74 ] byte to memory...");
            memory[memCount] = "74";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 72 ] byte to memory...");
            memory[memCount] = "72";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 75 ] byte to memory...");
            memory[memCount] = "75";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 65 ] byte to memory...");
            memory[memCount] = "65";
            memCount+=1;
            System.out.println("INFO  CodeGen - Pushing [ 00 ] byte to memory...");
            memory[memCount] = "00";
            memCount+=1;
        }
    }
    public void CodeGenIfStmnt(int i) {
    
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
        System.out.println(Arrays.toString(memory).replaceAll("[\\,]", ""));
    }

    public void generate(List<CCToken> tokenStream) {
      

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            String element = tokens.get(i).getValueOfToken();

            switch (element) {
                case "{":
                    newScope();
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
                    memArray();
                    if(errorCounter==0){
                    
                        //System.out.println("INFO  Analyze - SUCCESS! Classy Compiler Has Completed Semantic Analysis With [ " + errorCounter + " ] ERRORS And [ " + warningCounter + " ] WARNINGS");
                        //System.out.println("-----------------------------------------------------------");
                    }
                break;
                    
            }
        }
    }



}