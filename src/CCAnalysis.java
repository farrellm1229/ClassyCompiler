

public class CCAnalysis { //good name? maybe, maybe not...but maybe?

    public static void displayMessage(CCTokenTypes token, String value, int lineNum) {
        System.out.println("DEBUG Lexer - " + token.name + " [ " + value + " ] found at line: " + lineNum);
        
    }

    public static void errorMessage(String value, int lineNum) {
        System.out.println("ERROR Lexer - Unrecognized Token [ " + value + " ] found at line: " + (lineNum-1));
    }
	public static void warningMessage(int lineNum) {
        System.out.println("WARNING - Unterminated Comment! found at line: " + lineNum);

    }

}