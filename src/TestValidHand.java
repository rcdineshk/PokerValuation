import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestValidHand {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {

		String filename = "C:\\Users\\kobansal\\workspace\\poker\\src\\testFile.txt";
		Hand pokerHand;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line="";
        String[] splitLine;
        
        while((line = br.readLine())!= null)
        { 
        	splitLine = line.toUpperCase().split(";");

        	pokerHand = new Hand(splitLine[0]);
        	String output = pokerHand.checkValidityOfHand(line);
        	if ( output.length() == 0 )
        		output = pokerHand.getPokerRank();
        	assertEquals("Test Case passed", splitLine[1].trim(),  output);
        	assertEquals("Test Case passed", splitLine[1].trim(),  output);

        }
        br.close();
		//fail("Not yet implemented");
	}

}
