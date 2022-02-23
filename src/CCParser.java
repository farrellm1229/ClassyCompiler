import java.util.ArrayList;

public class CCParser {

    //public static ArrayList<CCToken> tokens = new ArrayList<CCToken>();
    public Boolean resultOfParse = null;
    public boolean result;
    static int indexOfToken = 0;

    public boolean parseOutcome(ArrayList<CCToken> tokenStream){

        resultOfParse = program();
        return resultOfParse;
    }
    
}
