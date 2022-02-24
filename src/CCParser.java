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
            //is the next token stream [ } ] ?
        //    if (blockEnd() == true){
                result = true; //yay valid program!
        //    }
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

            //blockEnd(); //since we found the start, look for the end
            result = true; 
        }
        else {
            System.out.println("ERROR Parser - Was expecting { but found " + tokens.get(indexOfToken).getValueOfToken());
            result = false;
        }
        return result;
    }
    
}
