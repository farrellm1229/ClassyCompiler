import java.util.ArrayList;
import java.util.List;

public class CCAnalysis { //good name? maybe, maybe not...but maybe?

    public static void displayMessage(String type, String value){//, int lineNum) {
        System.out.println("DEBUG Analyze - PASSED! Variable [ " + value + " ] with type [ " + type + " ] found");
        
    }

    public static void errorMessage(String value, int lineNum) {
        System.out.println("ERROR Lexer - Unrecognized Token [ " + value + " ] found at line: " + (lineNum-1));
    }
	public static void warningMessage(int lineNum) {
        System.out.println("WARNING - Unterminated Comment! found at line: " + lineNum);

    }


    public void VarDecl(String letter, int i){
        if(tokens.get(i+1).getValueOfToken().equals(letter)) {
            displayMessage(tokens.get(i).getValueOfToken(), letter);

        }

    }


    public static List<CCToken> tokens = new ArrayList<CCToken>();

    public void analyze(List<CCToken> tokenStream) {
      

        tokens = tokenStream; //getting tokens from stream in Main file
        
        int size = tokens.size();
        
        for (int i=0; i<size; i++) {
            

            String element = tokens.get(i).getValueOfToken();
           

            switch (element) {
                
                case "int":
                    VarDecl("a", i);VarDecl("g", i);VarDecl("m", i);VarDecl("s", i);                    
                    VarDecl("b", i);VarDecl("h", i);VarDecl("n", i);VarDecl("t", i);     
                    VarDecl("c", i);VarDecl("i", i);VarDecl("o", i);VarDecl("u", i);
                    VarDecl("d", i);VarDecl("j", i);VarDecl("p", i);VarDecl("v", i);     
                    VarDecl("e", i);VarDecl("k", i);VarDecl("q", i);VarDecl("w", i);
                    VarDecl("f", i);VarDecl("l", i);VarDecl("r", i);VarDecl("x", i);
                    VarDecl("y", i);VarDecl("z", i);
                    
                break;
                case "string":
                    VarDecl("a", i);VarDecl("g", i);VarDecl("m", i);VarDecl("s", i);                    
                    VarDecl("b", i);VarDecl("h", i);VarDecl("n", i);VarDecl("t", i);     
                    VarDecl("c", i);VarDecl("i", i);VarDecl("o", i);VarDecl("u", i);
                    VarDecl("d", i);VarDecl("j", i);VarDecl("p", i);VarDecl("v", i);     
                    VarDecl("e", i);VarDecl("k", i);VarDecl("q", i);VarDecl("w", i);
                    VarDecl("f", i);VarDecl("l", i);VarDecl("r", i);VarDecl("x", i);
                    VarDecl("y", i);VarDecl("z", i);
                    
                break;
            
                    
                    
            }
            //boolean test = (tokens.get(i+1).getTypeOfToken().equals("ASSIGNMENT"));
           
            //j++;
        
        
        }
        
        

    }

}