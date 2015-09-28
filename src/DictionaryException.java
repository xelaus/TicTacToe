
public class DictionaryException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//String message;
	
	public DictionaryException(String message){
	super(message);
	}

	public String getMessage()
    {
        return super.getMessage();
    }
}
