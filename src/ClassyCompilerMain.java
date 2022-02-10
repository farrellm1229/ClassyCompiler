import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassyCompilerMain { //good name? maybe, maybe not...but maybe?

    public static void displayMessage(ClassyCompilerTokenTypes token, String value, int lineNum){
        System.out.println("DEBUG Lexer -  " + token.name + " [ " + value + " ] found at line: " + lineNum);

    }

    public static void matchTokens(String inputFile) throws IOException{
        //Read Sample/Input File
        File sampleProgram = new File(inputFile);
		BufferedReader lineScanner = new BufferedReader(new FileReader(sampleProgram));

        //Variable Declaration
		Matcher lexemeValue;
        String currentLine;
        int programNumber=1;
        int lineNumber=1;


        //REGEX pattern matching for creating tokens and matching lexemes
        //symbols
        Pattern openBracketToken = Pattern.compile("\\{", Pattern.MULTILINE);
        Pattern closeBracketToken = Pattern.compile("\\}", Pattern.MULTILINE);
        Pattern endOfProgram = Pattern.compile("[$]$");
        Pattern openParenthesisToken = Pattern.compile("\\(");
        Pattern closeParenthesisToken = Pattern.compile("\\)");
        Pattern assignmentToken = Pattern.compile("(?<!=)={1}(?!=)");
        
        //keywords
        Pattern boolValToken = Pattern.compile("(?i:false|true)");
        Pattern stringToken = Pattern.compile("(?i:string)");
		Pattern printToken = Pattern.compile("(?i:print)");
		Pattern whileToken = Pattern.compile("(?i:while)");
		Pattern intToken = Pattern.compile("((?i:\\bint\\b))", Pattern.MULTILINE | Pattern.COMMENTS);
		Pattern ifToken = Pattern.compile("(?i:if)");

        
		while ((currentLine = lineScanner.readLine()) != null) { //while current line in text file is not blank

            if (currentLine.length() == 0) { //if current line is empty move to next
				continue;
            }
        
            while(currentLine != null){
                currentLine = currentLine.trim(); //get rid of whitespace from current line

                if (currentLine.length() == 0){ // | currentLine.endsWith("$")){
                    break;
                }

               /* if (currentLine.endsWith("$")){
                    System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.EOP + " [ " + currentLine.substring(0, currentLine.length() - 0) + " ] found at line: " + lineNumber + "\n");
                    System.out.println("------------- Classy Compiler has finished Lexical Analysis of Program: #" + programNumber + " -------------" + "\n");
                    
                    programNumber++;

                    break;

                } */ //this ^^^^ is a work in progress. Issue is could be whitespace after $

/*start of searching for symbols*/
            //Find symbol matches in text file

            
            //find openBracket
            lexemeValue = openBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                                //token type from enum                       lexeme (the match that is found)                       number of line
                displayMessage(ClassyCompilerTokenTypes.LEFT_BRACKET, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                //System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_BRACKET + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openBracketToken.matcher(currentLine);
					continue;
                }
            //find closeBracket
            lexemeValue = closeBracketToken.matcher(currentLine);
            if (lexemeValue.find()) {
                displayMessage(ClassyCompilerTokenTypes.RIGHT_BRACKET, currentLine.substring(lexemeValue.start(), lexemeValue.end()), lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = closeBracketToken.matcher(currentLine);
                    continue;
                }
                //find open Parenthesis
            lexemeValue = openParenthesisToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.LEFT_PARENTHESIS + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = openParenthesisToken.matcher(currentLine);
					continue;
                }
            //find close Parenthesis
            lexemeValue = closeParenthesisToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.RIGHT_PARENTHESIS + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue =closeParenthesisToken.matcher(currentLine);
                    continue;
                }

            //find if Keyword
            lexemeValue = ifToken.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.IF + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber);
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = ifToken.matcher(currentLine);
                    continue;
                }

            //Looking for End Of Program symbol ($)   
            /*lexemeValue = endOfProgram.matcher(currentLine);
            if (lexemeValue.find()) {
                System.out.println("DEBUG Lexer - " + ClassyCompilerTokenTypes.EOP + " [ " + currentLine.substring(lexemeValue.start(), lexemeValue.end()) + " ] found at line: " + lineNumber + "\n");
                System.out.println("------------- Classy Compiler has finished Lexical Analysis of Program: #" + programNumber + " -------------" + "\n");
                
                currentLine = currentLine.substring(lexemeValue.end());
                currentLine = currentLine.trim();
                lexemeValue = endOfProgram.matcher(currentLine);
                programNumber++;

                    continue;
                }*/

                continue;
            }
            
            lineNumber = lineNumber + 1; //increment line number when scanner reaches a new line

        }
            
            
            }
        
    public static void main(String[] args) throws IOException{
        System.out.println("\n------------- Welcome To The Classy Compiler! -------------\n");
        
        matchTokens(args[0]);
        //call match tokens, args[0] represents the sample program file
        //when running java ClassyCompilerMain sampleProgram.txt



    }
    
}
