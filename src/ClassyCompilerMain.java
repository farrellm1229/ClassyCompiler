import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassyCompilerMain { //good name? maybe, maybe not...but maybe?

    public static void matchTokens(String inputFile) throws IOException{
        //Read Sample/Input File
        File sampleProgram = new File(inputFile);
		BufferedReader lineScanner = new BufferedReader(new FileReader(sampleProgram));

        //Variable Declaration
		Matcher lexemeValue;
        String currentLine;

        //REGEX pattern matching for creating tokens and matching lexemes
        Pattern openBracketToken = Pattern.compile("\\{");
        Pattern closeBracketToken = Pattern.compile("\\}");

		while ((currentLine = lineScanner.readLine()) != null) { //while current line in text file is not blank
            
            lexemeValue = openBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " );//+ lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openBracketToken.matcher(currentLine);
					continue;
                }
            }
    }
    public static void main(String[] args) throws IOException{
        System.out.println("\n------------- Welcome To The Classy Compiler! -------------\n");
        
        matchTokens(args[0]);
        //call match tokens, args[0] represents the sample program file
        //when running java ClassyCompilerMain sampleProgram.txt



    }
    
}
