
import java.util.regex.*;

public class CCTokenStream {

	private Pattern regexPattern;
	private CCTokenTypes type;

	public CCTokenStream(Pattern regexPattern, CCTokenTypes type) {
		this.regexPattern=regexPattern;
		this.type=type;
	}

	public Pattern getRegExPattern() {
		return regexPattern;
	}
	
	public CCTokenTypes getType() {
		return type;
	}
	
}
