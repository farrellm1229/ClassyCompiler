import java.util.ArrayList;

public class CCParser {

    public static ArrayList<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = 0;

    //Overall result of parse
    public boolean parseOutcome(ArrayList<CCToken> tokenStream) {

        tokens = tokenStream;
        resultOfParse = program();
        return resultOfParse;
    }

    //Checking if input is a valid program
    public boolean program() {
        //does the input start with [ { ], if yes
            //then program is valid so far
        if (blockStart() == true) {

            if (statementList() == true) {
                
                //is the next token stream [ } ] ?
                if (blockEnd() == true) {
                    //if (programEnd() == true) {
                        result = true; //yay valid program!
            //    }
            }
           }
        }
        else {
            result = false;
        }
        return result;

    }

    public boolean blockStart() {
        if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_BRACKET")) {
            //if we found block start symbol, move to next token
            //in the token stream
            indexOfToken = indexOfToken + 1;

            statementList();
            if (statementList() == true){
                result = blockEnd();
            }
            //result = statementList(); //check for statement list inside block
            //blockEnd(); //since we found the start, look for the end
           
        }
        else {
            System.out.println("ERROR Parser - Was expecting { but found " + tokens.get(indexOfToken).getValueOfToken());
            result = false;
        }
        return result;
    }
    //look for end of block token
    public boolean blockEnd() {
        
        if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
            //if we found block end symbol, move to next token
            //in the token stream
            //indexOfToken = indexOfToken + 1;

            //programEnd();
            //blockEnd(); //since we found the start, look for the end
            result = true; 
        }
        else {
            //System.out.print(tokens);
            System.out.println("ERROR Parser - Was expecting } but found " + tokens.get(indexOfToken).getValueOfToken());

            result = false;
            
        }
        return result;
    }

    public boolean programEnd() {
        if (blockEnd() == true) {
            if (tokens.get(indexOfToken).getTypeOfToken().equals("EOP")) {
                result = true;
                program();

            }
            else {
                System.out.println("Was expecting an EOP [$]");
                result = false;
            }
        }
        return result;

    }

    public boolean statementList() {

        
        //if (statement() == true) {
          //  if (statementList() == true){
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("PRINT"))) {
            indexOfToken = indexOfToken + 1; //look for ( ) after print
            result = printStatement();

        }
        //look for assignment statement ( ID = EXPR )
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("CHAR"))) { // looking for ID
            indexOfToken = indexOfToken + 1; //look for = after ID in assignment statement
            if ((tokens.get(indexOfToken).getTypeOfToken().equals("ASSIGNMENT"))) { // looking for ID
                indexOfToken = indexOfToken + 1; //look for expression after = in assignment statement
                result = expression();
               
            }
        }

        if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
            result = blockEnd(); //must be end of block so go to block end
        }
        //check for variable declaration
        if (tokens.get(indexOfToken).getTypeOfToken().equals("TYPE")) {
            indexOfToken = indexOfToken + 1;
            if (tokens.get(indexOfToken).getTypeOfToken().equals("CHAR")) {
                System.out.println("variable declared!");
                indexOfToken = indexOfToken + 1;
                result = statementList();
                if (tokens.get(indexOfToken).getTypeOfToken().equals("ASSIGNMENT")){
                    indexOfToken = indexOfToken + 1;
                    result = expression();
                }
                if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
                    result = blockEnd();

                }
            }   

        }
        if (tokens.get(indexOfToken).getTypeOfToken().equals("WHILE")) {
            indexOfToken = indexOfToken + 1;
            if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS")) {
                indexOfToken = indexOfToken + 1;
                result = booleanExpr();

            }
        }
        if (tokens.get(indexOfToken).getTypeOfToken().equals("IF")) {
            indexOfToken = indexOfToken + 1;
            if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS")) {
                indexOfToken = indexOfToken + 1;
                result = booleanExpr();

            }
        }




        //}

        //blockEnd();
        return result;
    }

    
//}
    public boolean booleanExpr(){


        if (tokens.get(indexOfToken).getTypeOfToken().equals("CHAR")) {
            indexOfToken = indexOfToken + 1;        
            if (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_OP")) {
                indexOfToken = indexOfToken + 1;        
                if ((tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT")) || (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_VAL"))) {
                    indexOfToken = indexOfToken + 1;
                    if(tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {
                        indexOfToken = indexOfToken + 1;
                        if(tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_BRACKET")) {
                            indexOfToken = indexOfToken + 1;
                            statementList();
                            if (statementList()==true){
                                //System.out.println(tokens.get(indexOfToken).getValueOfToken());
                                
                            //}
                                if(!tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {
                                    //end of program not found yet, so check for another statement
                                    result = statementList();
                                }
                                else {
                                    result = blockEnd();
                                }
                        }

                        }
                }
                }
            }
        }
        return result;


    }
    public boolean intExpr() {
        if (tokens.get(indexOfToken).getTypeOfToken().equals("PLUS")) {
            indexOfToken = indexOfToken + 1;
            result = expression();

        }
        return result;
        
    }
    public boolean stringExpr() {
        if (tokens.get(indexOfToken).getTypeOfToken().equals("STRING")) {
            indexOfToken = indexOfToken + 1;
            result = statementList();

        }
        return result;
        
    }

    public boolean expression() {
        //expression can be IntExpr, StringExpr, BooleanExpr, or ID
        //checking for int expr 
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT")) || tokens.get(indexOfToken).getTypeOfToken().equals(("BOOL_VAL"))) {
            indexOfToken = indexOfToken + 1;
            if (!tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

                result = statementList();
            }
        
            if (tokens.get(indexOfToken).getTypeOfToken().equals("PLUS")) {
                result = intExpr(); //found digit, next token must be + so check for intExpr
            }

            if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

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
        }
       // else {
           //check for StringExpr
            if(tokens.get(indexOfToken).getTypeOfToken().equals("STRING")) {
                //indexOfToken = indexOfToken + 1;
                System.out.println("Found string");
                result = stringExpr();
                
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

    public boolean exprForBoolExpr() { 
        //expression can be IntExpr, StringExpr, BooleanExpr, or ID
        if (tokens.get(indexOfToken).getTypeOfToken().equals("CHAR")) {
            indexOfToken = indexOfToken + 1;
            result = booleanExpr();
        }
        /*
        if (tokens.get(indexOfToken).getTypeOfToken().equals("CHAR")) {
            indexOfToken = indexOfToken + 1;
            if (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_OP")) {
                indexOfToken = indexOfToken + 1;
                if (tokens.get(indexOfToken).getTypeOfToken().equals("BOOL_VAL")) {
                    System.out.println("working");
                    indexOfToken = indexOfToken + 1;
                    if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {
                        System.out.println("ok");
                        indexOfToken = indexOfToken + 1;
                        if (tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_BRACKET")) {

                            indexOfToken = indexOfToken + 1;
                            statementList();
                            if (statementList() == true){
                                System.out.println("success");
                                indexOfToken = indexOfToken + 1;
                                if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_BRACKET")) {

                                    result = blockEnd();
                                    System.out.println("end of boolexpr");
                                
                                    indexOfToken = indexOfToken + 1;
                                    

                                }
                                else{
                                    System.out.println("not end yet of program");
                                    result = statementList();

                                }






                            }


                        }

                    }


                }


            }

            

        } */
        /*
        //checking for int expr 
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("DIGIT"))) {
            indexOfToken = indexOfToken + 1;
            if (!tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

                result = statementList();
            }
        
            if (tokens.get(indexOfToken).getTypeOfToken().equals("PLUS")) {
                indexOfToken = indexOfToken + 1;
                //result = intExpr(); //found digit, next token must be + so check for intExpr
            }

            if (tokens.get(indexOfToken).getTypeOfToken().equals("RIGHT_PARENTHESIS")) {

                System.out.println("nope");
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
        }
       // else {
           //check for StringExpr
            else if(tokens.get(indexOfToken).getTypeOfToken().equals("STRING")) {
                //indexOfToken = indexOfToken + 1;
                System.out.println("Found string");
                result = stringExpr();
                
        }

      //  }
    
        //checking for string expr 
        /*if ((tokens.get(indexOfToken).getTypeOfToken().equals("STRING"))) {
            indexOfToken = indexOfToken + 1;

        } */ /*
        //checking for boolean expr 
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("BOOLEAN"))) {
            indexOfToken = indexOfToken + 1;

        }
    //}

//}
*/
    //statementList();
    return result;


            
    }
    public boolean printStatement() {
        if ((tokens.get(indexOfToken).getTypeOfToken().equals("LEFT_PARENTHESIS"))) {
            indexOfToken = indexOfToken + 1; //look for ( expression ) after (
            result = expression();

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


