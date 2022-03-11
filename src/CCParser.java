import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CCParser {

    public static List<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = 0;

    public boolean match(String token){ //match function for matching tokens
        
        boolean foundMatch;
        if (tokens.get(indexOfToken).getTypeOfToken().equals(token)) {
            foundMatch = true;
        }
        else {
            //indexOfToken++;
            foundMatch = false;
        }
        return foundMatch;
    }

    //Overall result of parse
    public boolean parseOutcome(List<CCToken> tokenStream) {

        tokens = tokenStream;
        proStart(); //check if program is valid
        if (result == true){
            indexOfToken=0; //program found, so set index back to 0
                            //after clearing the tokenList
            result = true;
        }
        else{
            indexOfToken = 0;
            result = false;
        }
        return result;
    }

    public boolean createCST(List<CCToken> tokenStream) {
        CCTree cst = new CCTree(); //create instance of class for CST
        
        tokens = tokenStream; //create instance of tokenStream
        result = true; //unnecessary but haven't changed it yet
    
        cst.create(tokenStream); //create CST
        return result;

    }

    public boolean programEnd() {
        //if I do index++ before instead of looking for index+1
        //I get an out of bounds error, not sure why this
        //works and the other doesn't
        if (tokens.get(indexOfToken+1).getTypeOfToken().equals("EOP")) {
            result = true;
        }
        else{
            System.out.println("No EOP"); //program end not found
            result = false;
        }
        return result;
    }
    public boolean proStart() { //this is what we call to see if program is valid :)
        if (match("LEFT_BRACKET")) {
            indexOfToken++;
            if (statementList() == false) {
                result = false; //no Statement list found, therefore false
            }

            if (statementList() == true) {
                if (blockEnd() == true) {
                    if (tokens.get(indexOfToken+1).getValueOfToken().equals("$")) { //right bracket found
                                        //so check if EOP is next token.
                                        //if it isn't, check for another statement
                        if (programEnd() == true){
                            indexOfToken++;
                            result = true;
                        }
                        
                        else {

                        }
                    }
                    else {
                        System.out.println("ERROR Parser - EOP [ $ ] Expected but found: [ " + tokens.get(indexOfToken+1).getValueOfToken() + " ]");
                        result = false;
                    }
                }
                else {
                    indexOfToken++;
                    result = statementList();
                }
            }
            else{
                //System.out.println("ERROR Parser - Was Expecting <Statement> but found: [ " +
                //tokens.get(indexOfToken).getValueOfToken() + " ]");
                result = statementList();
            }

        }
        else {
            System.out.println("ERROR Parser - Not A Valid Program Start");     
        }
        
        return result;
    }
    //look for start of block token
    public boolean blockStart() {
        if (match("LEFT_BRACKET")) {
            result = true;
        }
        else {
            System.out.println("ERROR Parser - Was Expecting [ { ] but found " + tokens.get(indexOfToken).getValueOfToken());
            result = false;  
        }
        
        return result;
    }
    //look for end of block token
    public boolean blockEnd() {        
        if (match("RIGHT_BRACKET")) {
            result = true;
        }
        else {
            System.out.println("ERROR Parser - Was Expecting [ } ] but found " + tokens.get(indexOfToken).getValueOfToken());
            result = false;
        }
        
        return result;
    }

    //I messed around with this for hours and coudn't get it perfect.
    //there was always one small case which didn't return correctly.
    //the goal is to make sure the # of brackets are even

    //This is going to be commented out and I will revert back to what I originally had
    //because this is giving me a major headache. This should work, and I've tried so many similar variations!
    
    //int bracketCounter = 1;
//check for statements and statementlists
    public boolean statementList() {
        //look for [ } ]
      /*  if ((match("RIGHT_BRACKET")) == true) {
            bracketCounter--;

            if (bracketCounter == 0){ //this means the brackets are even
                //indexOfToken++;
                result = true;
            }
            
            else {
                indexOfToken++;
                result = false;    
            }
            return result;
        } 
        //look for [ { ]
        if ((match("LEFT_BRACKET")) == true) {
            bracketCounter++;

            if (bracketCounter == 0){ //brackets are even, yay!
                //indexOfToken++;
                result = true;
            }
            else {
                indexOfToken++;
                result = false;
            }
            
            return result;
        } */
/* saving this just in case I come back and mess around with it
        if ((match("LEFT_BRACKET")) == true) {
            bracketCounter++;
            if (bracketCounter == 0){
            //indexOfToken++;
            System.out.print("parseStatementList();");
            System.out.print(tokens.get(indexOfToken).getTypeOfToken());
            if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")){
                result = blockEnd();
            }
        }
            else{
                //indexOfToken++;
                result = statementList();
            }
            return result;
        }
*/

// This doesn't look right, but it is going to have to do for now. I am defeated...temporarily
        if ((match("RIGHT_BRACKET")) == true) {
            if (blockEnd()==true){
                result = true;
            }
            else{
                result = false;
            }

            return result;
        }

        if ((match("LEFT_BRACKET")) == true) {
            if (blockStart()==true){
                result = true;
            }
            else{
                result = false;
            }

            return result;
        }
        
        /*  -----------------------------------------------------------
            ---------------- SYNTAX FOR PRINT statement ---------------
            ----------------------------------------------------------- */
        if ((match("PRINT")) == true) { //print token
            indexOfToken++;
            if ((match("LEFT_PARENTHESIS")) == true) { //so far so good
                indexOfToken++;
                result = true;

                if (expression() == true) { // ( expr ) <--- syntax

                    if ((match("RIGHT_PARENTHESIS")) == true) {
                        result = true;
                        indexOfToken++; //move on
                        if (blockEnd() == true){ //check for block end, if not then must be another statement
                            result = true;
                        }
                        else {
                            result = statementList(); //check for statement list
                        }                        
                        result = true;
                    }
                    else {
                        indexOfToken++;
                        //System.out.println("ERROR Parser - Was Expecting [ ) ] but found " + tokens.get(indexOfToken).getValueOfToken());
                        result = false;
                    }
                }
                else{
                    indexOfToken++;
                    //System.out.println("Was expecting expression in Print Statement");
                    result = false;
                }
                }
                else{
                    //System.out.println("Imvalid Print Statement");
                    result = false;
                }
                return result;
            }
         
            
        /*  -----------------------------------------------------------
            ------------- SYNTAX FOR ASSIGNMENT statement -------------
            ----------------------------------------------------------- */

        if ((match("CHAR")) == true) { //ID 
            indexOfToken++;
            if ((match("ASSIGNMENT")) == true) { // =
                
                indexOfToken++;
                if (expression() == true) { // EXPR
 
                    //blockEnd();
                    if (blockEnd() == true){
                        //System.out.println("okokokok");
                        result = true;
                    }
                    else {
                        result = statementList(); //if blockend not found, must be another statement
                    }

                    result = true;
            }
            else {
                //System.out.println("Was expecting expression after assignment;");
                result = false;
            }
        
                result = true;
            }
            return result;
        }
                
            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR VAR DECL statement -------------
            ----------------------------------------------------------- */

        if ((match("TYPE")) == true) {//TYPE
            indexOfToken++; //move on
            if ((match("CHAR")) == true) {//ID
                indexOfToken++;
                result = true;
            
                if (blockEnd() == true){
                    result = true;
                }
                else {
                    //indexOfToken++;
                    result = statementList(); //no block end found sooooo
                }

                result = true;
        }
        else{
            result = false;
        }
        return result;
            }


        /*  -----------------------------------------------------------
            ------------- SYNTAX FOR WHILE statement -------------
            ----------------------------------------------------------- */

            if ((match("WHILE")) == true) { //While (BooleanExpr Block)
                indexOfToken++;
                if ((match("LEFT_PARENTHESIS")) == true) {
                    indexOfToken++; //look for booleanexpr
                    if (expression() == true){ //expr
                        if (match("BOOL_OP")) { //so far so good
                            indexOfToken++;
                       
                            if (expression() == true){ //expr
                                
                                if (match("RIGHT_PARENTHESIS")) {
                                    indexOfToken++;
    
                                    if (match("LEFT_BRACKET")) { //block start
                                        indexOfToken++;
                                        if (statementList() == true){ //statement in block

                                            if (blockEnd() == true) {
                                                result = true;
                                            }
                                        }     
                                        result = blockEnd();
                                    }
                                    else {
                                        result = false;
                                        System.out.println(tokens.get(indexOfToken).getValueOfToken());
                                        System.out.println("Was Expecting [ { ] but found [ "+ 
                                        tokens.get(indexOfToken).getValueOfToken() + " ]");
                                    }
                                }
                                else{
                                    result=false;
                                    //System.exit(0); //this is if the if statement doesnt have 
                                    //a right parenthesis: if() <----
                                }
                                
                            }
                            else{
                                
                            }
    
                        }
                        else{

                        }
    
                        //result = true;
                    }
                    else {
                        //indexOfToken++;
                        result = statementList();
                    }
            }
            else{

            }
            //result = true;
            return result;
                
        }   

            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR IF statement -------------
            ----------------------------------------------------------- */

            if ((match("IF")) == true) {
                indexOfToken++;
                if ((match("LEFT_PARENTHESIS")) == true) {
                    indexOfToken++; 
                    if (expression() == true){
                      //so far so good
                        if (match("BOOL_OP")) {
                            indexOfToken++;
                            if (expression() == true){
                                
                                if (match("RIGHT_PARENTHESIS")) {
                                    indexOfToken++;
                                    if (match("LEFT_BRACKET")) {
                                        indexOfToken++;
                                        if (statementList() == true){
                                        
                                            if (blockEnd() == true) {
                                                result = true;
        
                                            }
                                        }
                                        result = blockEnd();
                                    }
                                    
                                    else {
                                        result = false;
                                        System.out.println(tokens.get(indexOfToken).getValueOfToken());
                                        System.out.println("Was Expecting [ { ] but found [ "+ 
                                        tokens.get(indexOfToken).getValueOfToken() + " ]");
                                    }

                                }
                                else{
                                    result=false;
                                    //System.exit(0); //this is if the if statement doesnt have 
                                    //a right parenthesis: if() <----
                                }
                                
                            }
                            else{

                            }
                        }
                        else{

                        }
    
                        //result = true;
                    }
                    else {
                        //indexOfToken++;
                        result = statementList();
                    }
                }
            //result = true;
            return result;
                
        }
        else{
            
        }   
        return result;
    } 
            
           
    
    public boolean intExpr() {
        if (match("DIGIT")) { //plus around found, so check for digit
            result = expression(); //then look for another digit or int expr

        }
        return result;
        
    }
    public boolean stringExpr() {
        if (match("STRING")) {
            
            indexOfToken = indexOfToken + 1;
            result = expression();

        }
        return result;
        
    }

    public boolean expression() {
        if ((match("DIGIT")) || match(("BOOL_VAL")) || match(("CHAR")) || match(("STRING")) == true) {
            
            indexOfToken++;
                if ((match("RIGHT_PARENTHESIS")) == true) {
                    result = true;
                    
                }
                else{
                    result = true;
                }
                if (match("PLUS")) {
                    indexOfToken++; //move to next
                    result = intExpr();
                }
                else {
                    //result = false;
                }
                return result;//result = true;
            }
        
        return result;
    
    }

}