package poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestPokerValuation {
	
	public String checkValidityOfHand(String inputLine)
	{
		final String invalidInput = "INVALID INPUT";
		String[] splitLine = inputLine.split(";");
    	if(splitLine.length != 2){
    		return invalidInput;
    	}
    	String[] cardList = splitLine[0].split(",");
    	Set<String> cardSet = new HashSet<String>(Arrays.asList(cardList));
    	if(cardSet.size()!= 5){
    		return invalidInput;
    	}
    	for (String card: cardSet)
    	{
    		if (card.length() != 2)
    		{
    			return invalidInput;
    		}
    		char rank = card.toUpperCase().charAt(0);
    		char suit = card.toUpperCase().charAt(1);
    		if(!"A23456789TJQK".contains(""+rank)){
    			return invalidInput;
    		}
    		if(!"SHDC".contains(""+suit)){
    			return invalidInput;
    		}
    	}
    	
    	return "";

	}
	
	public void testInput(String filename) throws IOException{
        
		Hand pokerHand;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line="";
        String[] splitLine;
        
        while((line = br.readLine())!= null)
        { 
        	splitLine = line.toUpperCase().split(";");
        	
        	if(splitLine[1].trim().equals(checkValidityOfHand(line))){
        		System.out.println(line.toUpperCase());
            	System.out.println("Test Case passed");
        		continue;
        	}
        	else{
        		pokerHand = new Hand(splitLine[0]);
                if(splitLine[1].trim().equals(pokerHand.getPokerRank())){
                	System.out.println(line.toUpperCase());
                	System.out.println("Test Case passed");
                	
                }
                else{
                	System.out.println(line + " Test Case failed" + pokerHand.getPokerRank());
                }
        	}
        }
        
    }
	
	public static void main(String args[])
	{
		TestPokerValuation obj = new TestPokerValuation(); 
	    try{
	         obj.testInput("/home/shagun/workspace/poker/src/poker/testFile.txt");
	     }catch(Exception ex){
	         ex.printStackTrace();
	     }
	}
}