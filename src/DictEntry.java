
public class DictEntry {
	
	//declaring the private variables
	private String c;
	private int s;
	
	//Class constructor
	public DictEntry(String config, int score){
		c = config;
		s = score;
	}
	
	//Returning String config
	public String getConfig(){
		return c;
	}
	
	//Returning int s which is the score 
	public int getScore(){
		return s;
	}
}
