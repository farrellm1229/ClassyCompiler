import java.util.ArrayList;
import java.util.List;

public class CCParser2 {

    public static List<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = -1;

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
            result = false;
        }
        return result;
    }

    public boolean programEnd() {
        

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

        if (tokens.get(indexOfToken).getTypeOfToken().equals("EOP")) {
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
        
        if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS")) {
            System.out.println("ok");
            result = true;
        }
        return result;

    }

    public boolean proStart() {
        //boolean idk = true;
        indexOfToken++;
        if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_BRACKET")) {
            //System.out.println("123");
            indexOfToken++;
            
            statementList();
            if (statementList() == false) {
                result = false;
                System.out.println("matt");

            }


            if (statementList() == true) {
                System.out.println("check");
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
                        
                            
                            programEnd();
                            if (programEnd() == true){
                                System.out.println("987");
                                indexOfToken++;
                                System.out.println(tokens.get(indexOfToken).getValueOfToken());
                                
                                result = true;
                        }
                    }
                }
            }
            else{
                System.out.println("no statement");
                result = false;

            }
            
        }
        
        return result;
    }
    //look for end of block token
    public boolean blockStart() {
        //indexOfToken++;
        
        if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_BRACKET")) {
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
        
        if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
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
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("PRINT"))) {
            indexOfToken = indexOfToken + 1; //look for ( ) after print
            result = printStatement();

        }
        return result;

    }

    public boolean statementList() {
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET"))) {
            result = blockEnd();
        }
        

        
        /*  -----------------------------------------------------------
            ---------------- SYNTAX FOR PRINT STATEMENT ---------------
            ----------------------------------------------------------- */
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("PRINT"))) {
            indexOfToken++;
            if ((tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS"))) {
                indexOfToken++;
            
                expression();
                if (expression() == true) {
                    if ((tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS"))) {

                        //System.out.println("found end of print");
                        indexOfToken++;
                        //System.out.println(tokens.get(indexOfToken).getValueOfToken());
                        
                        blockEnd();
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
                }
            }
         
            
        /*  -----------------------------------------------------------
            ------------- SYNTAX FOR ASSIGNMENT STATEMENT -------------
            ----------------------------------------------------------- */

        if ((tokens.get(indexOfToken).getTypeOfToken().equals("CHAR"))) {
            indexOfToken++;
            if ((tokens.get(indexOfToken).getTypeOfToken().equals("ASSIGNMENT"))) {
                indexOfToken++;
                    
                        //System.out.println(tokens.get(indexOfToken).getValueOfToken());

                        expression();
                        if (expression() == true) {

                            
                            System.out.println("found end of assignment");
                            //indexOfToken++;
                            //System.out.println(tokens.get(indexOfToken).getValueOfToken());
                            
                            blockEnd();
                            if (blockEnd() == true){
                                //System.out.println("okokokok");
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
                
                result = true;
            }
        }
                
            

            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR VAR DECL STATEMENT -------------
            ----------------------------------------------------------- */

        if ((tokens.get(indexOfToken).getTypeOfToken().equals("TYPE"))) {
            indexOfToken++;
            if ((tokens.get(indexOfToken).getTypeOfToken().equals("CHAR"))) {
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
        result = true;
            }

            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR WHILE STATEMENT -------------
            ----------------------------------------------------------- */

        if ((tokens.get(indexOfToken).getTypeOfToken().equals("WHILE"))) {
            indexOfToken++;
            if ((tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS"))) {
                indexOfToken++;
                
                expression();
                if (expression() == true){
                    //System.out.println("foud boolexprwihle");
                    //System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                    if (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_OP")) {
                        indexOfToken++;
                        expression();
                        if (expression() == true){
                            System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                    
                            if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {
                                indexOfToken++;
                    
                                


                                blockStart();
                                if (blockStart() == true){
                                    
                                    indexOfToken++;
                                    blockEnd();
                                    if (blockEnd() == true) {
                                        result = true;

                                    }



                                    result = blockEnd();

                                }
                                else {
                                    System.out.println("Was Expecting [ { ] but found [ "+ 
                                    tokens.get(indexOfToken).getValueOfToken() + " ]");
                                }
                            }
                        }

                    }

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
        result = true;
    }
        
            /*  -----------------------------------------------------------
            ------------- SYNTAX FOR IF STATEMENT -------------
            ----------------------------------------------------------- */

            if ((tokens.get(indexOfToken).getTypeOfToken().equals("IF"))) {
                indexOfToken++;
                if ((tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS"))) {
                    indexOfToken++;
                    
                    expression();
                    if (expression() == true){
                        System.out.println("foud boolexprwihle");
                        //System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                        if (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_OP")) {
                            indexOfToken++;
                            expression();
                            if (expression() == true){
                                System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                
                                if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {
                                    indexOfToken++;
                                    System.out.println(tokens.get(indexOfToken).getTypeOfToken());
                                    System.out.println("love");

                                    

                        
    
    
                                    blockStart();
                                    if (blockStart() == true){
                                        System.out.println("when");

                                        //System.out.println(blockStart());
                                        indexOfToken++;
                                        blockEnd();
                                        if (blockEnd() == true) {
                                            result = true;
    
                                        }
    
    
    
                                        result = blockEnd();
    
                                    }
                                    
                                    else {
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
        if (tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT")) {
            result = expression();

        }
        return result;
        
    }
    public boolean stringExpr() {
        if (tokens.get(indexOfToken).getTypeOfToken().equals("STRING")) {
            System.out.println("plus");

            indexOfToken = indexOfToken + 1;
            result = expression();

        }
        return result;
        
    }

    public boolean expression() {
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT")) || tokens.get(indexOfToken).getTypeOfToken().equals(("BOOL_VAL")) || tokens.get(indexOfToken).getTypeOfToken().equals(("CHAR"))) {
            
            indexOfToken++;
                if ((tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS"))) {
                    result = true;
                }
                if (tokens.get(indexOfToken).getTypeOfToken().equals("PLUS")) {
                    System.out.println("plus");
                    indexOfToken++;
                    result = intExpr();
                }
                result = true;
            }


        //expression can be IntExpr, StringExpr, BooleanExpr, or ID
        //checking for int expr 
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT")) || tokens.get(indexOfToken).getTypeOfToken().equals(("BOOL_VAL"))) {
            indexOfToken = indexOfToken + 1;
            if (!tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

                result = statementList();
            }
            else {
                result = true;
            }
        
            if (tokens.get(indexOfToken).getTypeOfToken().equals("PLUS")) {
                result = intExpr(); //found digit, next token must be + so check for intExpr
            }

            /*if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

                System.out.println("yup");
                indexOfToken = indexOfToken + 1;

            //    result = endOfPrintStatement();

                //result = true;
                //indexOfToken = indexOfToken + 1;
                if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
                    
                    result = blockEnd();
                }
                if(!tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
                
                    result = statementList();
                }
            }
            */
        }
       // else {
        //check for StringExpr
        if(tokens.get(indexOfToken).getTypeOfToken().equals("STRING")) {
            indexOfToken = indexOfToken + 1;
            if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")){
                indexOfToken = indexOfToken + 1;
                if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")){
                        
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
        /*if ((tokens.get(indexOfToken).getTypeOfToken().equals("STRING"))) {
            indexOfToken = indexOfToken + 1;

        } */
        //checking for boolean expr 
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("BOOLEAN"))) {
            indexOfToken = indexOfToken + 1;

        }
    //}

//}

    //statementList();
    return result;


            
    }

    public boolean printStatement() {
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS"))) {
            indexOfToken = indexOfToken + 1; //look for ( expression ) after (
            expression();
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
    /*public boolean endOfPrintStatement() {
        if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

        }
        
    } */

}