import java.util.ArrayList;

public class CCParser {

    //public static ArrayList<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = 0;

    //Overall result of parse
    public boolean parseOutcome(ArrayList<CCToken> tokenStream) {

        resultOfParse = program();
        return resultOfParse;
    }

    //Checking if input is a valid program
    public boolean program() {
        //does the input start with [ { ], if yes
            //then program is valid so far
        if (blockStart() == true) {
            //is the next token stream [ } ] ?
            if (blockEnd() == true){
                result = true; //yay valid program!
            }
        }
        else {
            result = false;
        }
        return result;

    }
    
}
