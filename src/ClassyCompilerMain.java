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
        int programNumber=1;


        //REGEX pattern matching for creating tokens and matching lexemes
        //symbols
        Pattern openBracketToken = Pattern.compile("\\{", Pattern.MULTILINE);
        Pattern closeBracketToken = Pattern.compile("\\}", Pattern.MULTILINE);
        Pattern endOfProgram = Pattern.compile("[$]$");
        
        //keywords
        
		Pattern ifToken = Pattern.compile("(?i:if)");


        //Pattern insideString = Pattern.compile(\\"\".*?\\"), Pattern.MULTILINE | Pattern.COMMENTS);
        
		while ((currentLine = lineScanner.readLine()) != null) { //while current line in text file is not blank
            if (currentLine.length() == 0) { //if current line is empty move to next
				continue;
            }
            
            lexemeValue = openBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " );//+ lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openBracketToken.matcher(currentLine);
					continue;
                }
            lexemeValue = closeBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.RIGHT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " );//+ lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = closeBracketToken.matcher(currentLine);
                    continue;
                }
            lexemeValue = ifToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.IF + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " );//+ lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = ifToken.matcher(currentLine);
                    continue;
                }

             //Looking for End Of Program symbol ($)   
            lexemeValue = endOfProgram.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.EOP + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: \n" );//+ lineNumber);
                System.out.println("------------- Classy Compiler has finished Lexical Analysis of Program: #" + programNumber + " -------------" + "\n");
                
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = endOfProgram.matcher(currentLine);
                programNumber++;

                    continue;
                }
                continue;
            }

            }
    public static void main(String[] args) throws IOException{
        System.out.println("\n------------- Welcome To The Classy Compiler! -------------\n");
        
        matchTokens(args[0]);
        //call match tokens, args[0] represents the sample program file
        //when running java ClassyCompilerMain sampleProgram.txt



    }
    
}
