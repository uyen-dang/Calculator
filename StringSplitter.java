import java.util.*;

public class StringSplitter {
	private Queue<Character> characters;
	private String token;
	
	public static final String SPECIAL_CHARACTER = "()+-*/^";
	
	public StringSplitter(String line){
		characters = new LinkedList<Character>();
		for (int i = 0; i < line.length(); i++){
			characters.add(line.charAt(i));
		}
		findNextToken();
	}
	
	public boolean hasNext(){
		return token != null;
	}
	
	public String next(){
		checkToken();
		String result = token;
		findNextToken();
		return result;
	}
	
	public String peek(){
		checkToken();
		return token;
	}
	
	private void findNextToken(){
		while (!characters.isEmpty() && Character.isWhitespace(characters.peek())){
			characters.remove();
		}
		if (characters.isEmpty()){
			token = null;
		}else{
			token = "" + characters.remove();
			if (!SPECIAL_CHARACTER.contains(token)){
				boolean done = false;
				while (!characters.isEmpty() && !done){
					char ch = characters.peek();
					if (Character.isWhitespace(ch) || SPECIAL_CHARACTER.indexOf(ch) >= 0){
						done = true;
					}else{
						token = token + characters.remove();
					}
				}
			}
		}
	}
	
	private void checkToken(){
		if (!hasNext()){
			throw new NoSuchElementException();
		}
	}
}
