import java.util.LinkedList;

public class Dictionary implements DictionaryADT{

	//Declaring the private variables
	
	//This will be used to reach the hash-table from other methods inside this class
	LinkedList<DictEntry>[] array;
	
	//This will be used to track the number of elements inside the hash-table 
	//Every time something is added to the list, tote is going to be incremented by 1
	//And decreased by 1, if something is removed.
	private int tote = 0;
	
	//This will be used in the hash-function to create a better hash-value
	private int size;
	
	
	//Constructor for the Dictionary class
	public Dictionary (int size){
		
		this.size=size;
		
		//Creating an array of linked lists
		this.array = new LinkedList[size];
        for( int i = 0; i < array.length; i++ )
            array[ i ] = new LinkedList<DictEntry>( );
       
	}
	
	
	//A private method for hash-function returns a value according to the input and array size 
	private int hashfunc (String s){
		
		//initialize the variable u, with the value of first character of the input
		int u = s.charAt(0);
		
		//Creating an Integer according to the input and the array size
		for (int i=1; i < s.length(); i++) {
		    u = (u*33 + s.charAt(i)) % size;
		}
		
		//System.out.println( u + " this is the hash-value");
		return u;
	}
	
	
	//insert method that inserts the input into the hash-table(array of linked lists)
	public int insert (DictEntry pair) throws DictionaryException {
		
		/*Initialize the variable. Get the string from input and put it inside the hashfunc method
		  to get a hash-value according to the string
		*/
    	int hk = hashfunc(pair.getConfig());
    	
    	
		//If the slot in the hash-table is empty add the DictEntry to the list.
		if(array[hk].isEmpty()){
			array[hk].add(pair);
			tote++;
			return 0;
			
		}
		
		//Otherwise check the list if the input exists first, if not then add the input
		else {
			
			//Declare an int variable to keep track of where we are in the list.
			int loc=1;
					
			//Check if the item exists until loc is same as lists' size.
			while(array[hk].size() != loc && !array[hk].get(loc-1).getConfig().equals(pair.getConfig())){
				
				//System.out.println("in while loop at insert");
				loc++;
			}
			
			//check if the item is same as the input in the current location of loc 
			//if it is, throw an exception
			if(array[hk].get(loc-1).getConfig().equals(pair.getConfig())){
					
				//System.out.println("Checking if exists at insert");
				throw new DictionaryException("exists");
				}
			
			//if not, that means that the item does not exist so add it to the list and return 1
			else {
				array[hk].add(pair);
				tote++;
				return  1;
			}
		}		
    }
	
	
	//Method for removing items from the hash-table
	public void remove(String config) throws DictionaryException{
		
		//Get the hash-value of the input
		int hk = hashfunc(config);
		
		//If the list is empty throw an exception, if not check
		if(!array[hk].isEmpty()){
			
			//Initialize the loc variable to keep track of the location we are in, in the list
			int loc=1;
			
			//Check if the item exists until loc is same as lists' size.
			while(array[hk].size() != loc && !array[hk].get(loc-1).getConfig().equals(config)){
				loc++;
			}
			
			//check if the item is same as the input in the current location by using loc 
			//if it is remove the item, if not throw an exception.
			if(array[hk].get(loc-1).getConfig().equals(config)){
				
				array[hk].remove(loc-1);	
				tote--;
			}
		
		else { throw new DictionaryException("Does not exist!");	}
		}
		else { throw new DictionaryException("Does not exist!"); }
    	
	}
	
	
	//Method for finding an item on the hash-table
	public int find(String config){
		
		//Get the hash-value of the input
		int hk = hashfunc(config);
		
		//If the list is empty return -1, if not check
		if(!array[hk].isEmpty()){
			
			//Initialize the loc variable to keep track of the location we are in, in the list
			int loc=1;
			
			//Check if the item exists until loc is same as lists' size.
			while(array[hk].size() !=loc && !array[hk].get(loc-1).getConfig().equals(config)){
				loc++;
			}
			
			//check if the item is same as the input in the current location by using loc 
			//if it is return the score of the item, if not return -1.
			if(array[hk].get(loc-1).getConfig().equals(config)){
				
				return array[hk].get(loc-1).getScore();
			}
		
		else { return -1; }
		}
		else { return -1; }
	}
	
	
	//Method that returns the number of elements in the hash-table.
	 public int numElements(){
		 return tote;
	 }
}
