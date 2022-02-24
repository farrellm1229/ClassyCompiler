
public class CCToken {
	
	public String tokenType;
	public String token;
	
	public CCToken(String tokenType, String token) {
		this.tokenType=tokenType;
		this.token=token;
	}

	public String getTypeOfToken() {
		return tokenType;
	}
	public String getValueOfToken() {
		return token;
	}
	

	
}
