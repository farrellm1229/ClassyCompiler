import java.util.ArrayList;
import java.util.List;

public class CCParser2 {

    public static List<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = 0;

    public boolean match(String token){
        //System.out.println("match");

        boolean foundMatch;
        if (tokens.get(indexOfToken).getTypeOfToken().equals(token)) {
            //indexOfToken++;
            foundMatch = true;
            /*switch(token){
                case "LEFT_BRACKET": 
                    System.out.println("parseBlock();");
                    
                case "RIGHT_BRACKET": 
                    System.out.println("parseBlockEnd();");
                    //break;
                case "PRINT": 
                    System.out.println("statement();");
                    //break;
                case "WHILE": 
                    System.out.println("statement();");
                    //break;
                case "IF": 
                    System.out.println("statement();");
                    //break;
                case "TYPE": 
                    System.out.println("statement();");
                    //break;      
                case "CHAR": 
                    System.out.println("statement2();");
                    //break;
                
            } */
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
        proStart();
        System.out.println("farrell");

        System.out.println(result);
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

    public boolean programEnd() {
        
        System.out.println("pro end start/ curren token");

        System.out.println(tokens.get(indexOfToken).getTypeOfToken());

        //indexOfToken++;
        if (tokens.get(indexOfToken+1).getTypeOfToken().equals("EOP")) {
            /*System.out.println("FOUND EOP");
            System.out.println(indexOfToken);
            System.out.println(tokens.get(indexOfToken-1).getTypeOfToken());
            System.out.println(tokens.get(indexOfToken+1).getTypeOfToken());
            System.out.println(tokens.size());

            System.out.println(tokens.subList(indexOfToken, tokens.size()).get(0).getValueOfToken());
            */

            //tokens.clear();
            
            result = true;

        }
        else{
            System.out.println("No EOP");
            result = false;
            
        }
        return result;

    }
    //Checking if input is a valid program
    public boolean program() {
        blockStart();
        if (blockStart() == true){
            blockEnd();
            if (blockEnd() == true){

            }

        }
        else {
            result = false;
        }
        return result;
        /*
        //indexOfToken++;
        //blockStart();
        
        //indexOfToken++;
        System.out.println("PROGRAM");

        System.out.println(tokens.get(indexOfToken).getValueOfToken());

        if (match("EOP")) {
            System.out.println("FOUND EOP");
            result = true;
        }
        else{
            System.out.println("No EOP");
            result = false;
            
        }
        return result;
        */

       
    }
    public boolean test() {
        
        if (match("LEFT_PARENTHESIS")) {
            System.out.println("ok");
            result = true;
        }
        return result;

    }

    public boolean proStart() {
      

        if (match("LEFT_BRACKET")) {
            

            System.out.println("parseBlock();");


            //match("LEFT_BRACKET");
            //System.out.println("123");
            indexOfToken++;
            
            //statementList();
            if (statementList() == false) {
                result = false;
                System.out.println("matt");

            }


            if (statementList() == true) {
                System.out.print("parseStatement();\n");

                System.out.println("check");
                System.out.println(tokens.get(indexOfToken).getValueOfToken());

                //indexOfToken++;
                    
                blockEnd();
                if (blockEnd() == true) {
                    System.out.println("check2");
                    //indexOfToken++;
                    System.out.println(tokens.get(indexOfToken+1).getValueOfToken());

                    //for(CCToken name:tokens) {
                      //    System.out.println(name.getTypeOfToken());
                
                      //}
                    if (tokens.get(indexOfToken+1).getValueOfToken().equals("$")) {

                          
                          //System.out.println("matt");
                        
                            
                        //programEnd();
                        if (programEnd() == true){
                            System.out.println("987");
                            indexOfToken++;
                            System.out.println(tokens.get(indexOfToken).getValueOfToken());
                            
                            result = true;
                    }
                        
                        else {

                            System.out.println("seconderror]");

                        }

                }
                    else {

                        //System.out.println("ERROR Parser - EOP [ $ ] Expected but found: [ " + tokens.get(indexOfToken+1).getValueOfToken() + " ]");
                        result = statementList();
                        //System.out.println(tokens.get(indexOfToken+1).getTypeOfToken());
                    
                    }
                }
            }
            else{
                System.out.println("no statement");
                result = false;

            }
            
        }
        else {
            System.out.println("ERROR Parser - Not A Valid Program Start");     
        }
        
        return result;
    }
    //look for end of block token
    public boolean blockStart() {
        //indexOfToken++;
        
        if (match("LEFT_BRACKET")) {
            //System.out.println("2parseBlock();");


            //if we found block end symbol, move to next token
            //in the token stream
            //indexOfToken = indexOfToken + 1;
            

            //indexOfToken++;
            //programEnd();
            //blockEnd(); //since we found the start, look for the end
            result = true;

            
            
        }
        else {
            //System.out.print(tokens);
            System.out.println("ERROR Parser - Was here but nope } but found " + tokens.get(indexOfToken).getValueOfToken());

            result = false;
            
        }
        
        return result;
    }
    //look for end of block token
    public boolean blockEnd() {
        //indexOfToken++;
        
        if (match("RIGHT_BRACKET")) {

            System.out.print("parseBlockEnd");

        
            //if we found block end symbol, move to next token
            //in the token stream
            //indexOfToken = indexOfToken + 1;
            

            //indexOfToken++;
            //programEnd();
            //blockEnd(); //since we found the start, look for the end
            result = true;

            
            
        }
        else {
            //System.out.print(tokens);
            //System.out.println("ERROR Parser - Was expecting } but found " + tokens.get(indexOfToken).getValueOfToken());

            result = false;
            
        }
        
        return result;
    }

    
    public boolean statement() {
        if ((match("PRINT")) == true) {
            indexOfToken = indexOfToken + 1; //look for ( ) after print
            result = printStatement();

        }
        return result;

    }

    public boolean statementList() {
        if ((match("RIGHT_BRACKET")) == true) {
            //System.out.print("parseBlockEnd");

            result = blockEnd();
        }

        if ((match("LEFT_BRACKET")) == true) {
            System.out.print("parseStatementList();");

            result = blockEnd();
        }
        

        
        /*  -----------------------------------------------------------
            ---------------- SYNTAX FOR PRINT statement ---------------
            ----------------------------------------------------------- */
        if ((match("PRINT")) == true) {
            indexOfToken++;
            if ((match("LEFT_PARENTHESIS")) == true) {
                indexOfToken++;
            
                //expression();
                if (expression() == true) {
                    if ((match("RIGHT_PARENTHESIS")) == true) {

                        //System.out.println("found end of print");
                        indexOfToken++;
                        //System.out.println(tokens.get(indexOfToken).getValueOfToken());
                        
                        //blockEnd();
                        if (blockEnd() == true){
                            //System.out.println("okokokok");
                            result = true;
                        }
                        else {
                            result = statementList();
                        }
                        
                        /*for(CCToken name:tokens) {
                            System.out.println(name.getValueOfToken());

                        } */

                        result = true;
                }
                result = true;
            }
            else{
                System.out.println("Was expecting expression in Print Statement");
                result = false;
            }
                }
                else{
                    System.out.println("Imvalid Print Statement");
                    result = false;
                }
            }
         
            
        /*  -----------------------------------------------------------
            ------------- SYNTAX FOR ASSIGNMENT statement -------------
            ----------------------------------------------------------- */

        if ((match("CHAR")) == true) {
            indexOfToken++;
            if ((match("ASSIGNMENT")) == true) {
                indexOfToken++;
                    
                        //System.out.println(tokens.get(indexOfToken).getValueOfToken());

                        //expression();
                        if (expression() == true) {

                            
                            System.out.println("found end of assignment");
                            //indexOfToken++;
                            System.out.println(tokens.get(indexOfToken).getValueOfToken());
                            
                            //blockEnd();
                            if (blockEnd() == true){
                                //System.out.println("okokokok");
                                result = true;
                            }
                            else {
                                //indexOfToken++;
                                //System.out.println("parsestatementList();");

                                result = statementList();
                            }
                            
                            /*for(CCToken name:tokens) {
                                System.out.println(name.getValueOfToken());

                            } */

                            result = true;
                    }
                
                result = true;
            }
        }
                
            

            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR VAR DECL statement -------------
            ----------------------------------------------------------- */

        if ((match("TYPE")) == true) {
            indexOfToken++;
            if ((match("CHAR")) == true) {
                indexOfToken++;
                result = true;
            
                blockEnd();
                if (blockEnd() == true){
                    //System.out.println("foud blicjk end of type");
                    result = true;
                }
                else {
                    //indexOfToken++;
                    result = statementList();
                }
                
                /*for(CCToken name:tokens) {
                    System.out.println(name.getValueOfToken());

                } */

                result = true;
        }
        else{
            System.out.println("Invalid Var Dec");
            result = false;


        }
        return result;
            }

            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR WHILE statement -------------
            ----------------------------------------------------------- */

            if ((match("WHILE")) == true) {
                indexOfToken++;
                if ((match("LEFT_PARENTHESIS")) == true) {
                    indexOfToken++;
                    
                    //expression();
                    if (expression() == true){
                        //System.out.println("foud boolexprwihle");
                        //System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                        if (match("BOOL_OP")) {
                            indexOfToken++;
                           // expression();
                            if (expression() == true){
                                //System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                
                                if (match("RIGHT_PARENTHESIS")) {
                                    indexOfToken++;
                                    System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                    System.out.println("love2");

                                    

                        
    
    
                                  //  blockStart();
                                    if (blockStart() == true){
                                        System.out.println("when");

                                        //System.out.println(blockStart());
                                    
                                        indexOfToken++;
                                            
                                        //  blockEnd();
                                        if (statementList() == true) {
                                            indexOfToken++;
                                            if (blockEnd() == true) {
                                                result = true;
        
                                            }
                                         
                                        }
                                        
    
    
                                        result = blockEnd();
    
                                    }
                                    
                                    else {
                                        result = false;
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
    
                        }
    
                        //result = true;
                    }
                    else {
                        //indexOfToken++;
                        result = statementList();
                    }
                    
                    /*for(CCToken name:tokens) {
                        System.out.println(name.getValueOfToken());
    
                    } */
    
                    //result = true;
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
                    
                   // expression();
                    if (expression() == true){
                       // System.out.println("foud boolexprwihle");
                        //System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                        if (match("BOOL_OP")) {
                            indexOfToken++;
                        //    expression();
                            if (expression() == true){
                                System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                
                                
                                if (match("RIGHT_PARENTHESIS")) {
                                    indexOfToken++;
                                    System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                    System.out.println("love");

                                    

                        
    
    
                                    if (match("LEFT_BRACKET")) {
                                    //blockStart();
                                    //if (blockStart() == true){
                                        System.out.println("when");

                                        //System.out.println(blockStart());
                                        indexOfToken++;
                                        //blockEnd();
                                        if (blockEnd() == true) {
                                            result = true;
    
                                        }
    
    
    
                                        result = blockEnd();
    
                                    }
                                    
                                    else {
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
    
                        }
    
                        //result = true;
                    }
                    else {
                        //indexOfToken++;
                        result = statementList();
                    }
                    
                    /*for(CCToken name:tokens) {
                        System.out.println(name.getValueOfToken());
    
                    } */
    
                    //result = true;
            }
            //result = true;
            return result;
                
        }    
            
            
            return result;

    }

    
    public boolean intExpr() {
        if (match("DIGIT")) {
            result = expression();

        }
        return result;
        
    }
    public boolean stringExpr() {
        if (match("STRING")) {
            System.out.println("plus");

            indexOfToken = indexOfToken + 1;
            result = expression();

        }
        return result;
        
    }

    public boolean expression() {
        if ((match("DIGIT")) || match(("BOOL_VAL")) || match(("CHAR")) == true) {
            
            indexOfToken++;
                if ((match("RIGHT_PARENTHESIS")) == true) {
                    result = true;
                }
                if (match("PLUS")) {
                    System.out.println("plus");
                    indexOfToken++;
                    result = intExpr();
                }
                result = true;
            }


        //expression can be IntExpr, StringExpr, BooleanExpr, or ID
        //checking for int expr 
        if ((match("DIGIT")) || match(("BOOL_VAL")) == true) {
            indexOfToken = indexOfToken + 1;
            if (!match("RIGHT_PARENTHESIS")) {

                result = statementList();
            }
            else {
                result = true;
            }
        
            if (match("PLUS")) {
                result = intExpr(); //found digit, next token must be + so check for intExpr
            }

            /*if (match("RIGHT_PARENTHESIS")) {

                System.out.println("yup");
                indexOfToken = indexOfToken + 1;

            //    result = endOfPrintstatement();

                //result = true;
                //indexOfToken = indexOfToken + 1;
                if (match("RIGHT_BRACKET")) {
                    
                    result = blockEnd();
                }
                if(!match("RIGHT_BRACKET")) {
                
                    result = statementList();
                }
            }
            */
        }
       // else {
        //check for StringExpr
        if(match("STRING")) {
            indexOfToken = indexOfToken + 1;
            if (match("RIGHT_PARENTHESIS")){
                indexOfToken = indexOfToken + 1;
                if (match("RIGHT_BRACKET")){
                        
                    result = blockEnd();
                }
                else {
                    result = statementList();
                }
                }
                else {
                    result = statementList();
                }
    }

      //  }
    
        //checking for string expr 
        /*if ((match("STRING")) == true) {
            indexOfToken = indexOfToken + 1;

        } */
        //checking for boolean expr 
        if ((match("BOOLEAN")) == true) {
            indexOfToken = indexOfToken + 1;

        }
    //}

//}

    //statementList();
    return result;


            
    }

    public boolean printStatement() {
        if ((match("LEFT_PARENTHESIS")) == true) {
            indexOfToken = indexOfToken + 1; //look for ( expression ) after (
            //expression();
            if (expression() == true) {
                
                result = true;
            }
            else {
                result = false;
            }


            //result = true;
        }
        else {
            System.out.println("[ ( ] expected but found " + tokens.get(indexOfToken).getTypeOfToken() + " with value of " + tokens.get(indexOfToken).getValueOfToken());
        } 
        //result = statementList();
        return result;
    }
    /*public boolean endOfPrintstatement() {
        if (match("RIGHT_PARENTHESIS")) {

        }
        
    } */

}