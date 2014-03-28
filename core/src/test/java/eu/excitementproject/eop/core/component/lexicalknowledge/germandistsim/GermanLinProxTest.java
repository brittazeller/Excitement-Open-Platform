package eu.excitementproject.eop.core.component.lexicalknowledge.germandistsim;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalResource;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalResourceException;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalRule;
import eu.excitementproject.eop.common.component.lexicalknowledge.RuleInfo;

/**
 * This test class will test GermanLinProx lexical resource. 
 * 
 * Note that the test case will pass, if the underlying Jar (artifact) is not added on the project. 
 * 
 * @author Tae-Gil Noh 
 */
public class GermanLinProxTest {

	@Test
	public void test() {
        Logger.getRootLogger().setLevel(Level.INFO); // (hiding < INFO)
        Logger testLogger = Logger.getLogger(GermanLinProxTest.class.getName()); 

        LexicalResource<RuleInfo> lr = null; 
        try {
        	lr = new GermanLinProx(); 
        }
        catch(LexicalResourceException e)
        {
        	// Lexical Resource initialization failed: This mainly caused by 
        	testLogger.info("Test instance init failed: probably the model artifact is not added in POM. This is Okay! --- " + e.getMessage()); 
        	testLogger.info("This test will be ignored."); 
        }
        
		assumeNotNull(lr); 
		
		// Okay. The resource is ready. time to send some queries as test. 
		try {
			List<LexicalRule<? extends RuleInfo>> similarities_l = lr.getRulesForLeft("ewig", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r = lr.getRulesForRight("ewig", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
		

			List<LexicalRule<? extends RuleInfo>> similarities_l1 = lr.getRulesForLeft("Kampf", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l1)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r1 = lr.getRulesForRight("Kampf", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r1)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
					
			List<LexicalRule<? extends RuleInfo>> similarities_l2 = lr.getRulesForLeft("Rockstar", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l2)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r2 = lr.getRulesForRight("Rockstar", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r2)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
				
			List<LexicalRule<? extends RuleInfo>> similarities_l3 = lr.getRulesForLeft("Laos", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l3)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r3 = lr.getRulesForRight("Laos", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r3)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
					
			List<LexicalRule<? extends RuleInfo>> similarities_l4 = lr.getRulesForLeft("Brief", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l4)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r4 = lr.getRulesForRight("Brief", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r4)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
					
			List<LexicalRule<? extends RuleInfo>> similarities_l5 = lr.getRulesForLeft("entdecken", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l5)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r5 = lr.getRulesForRight("entdecken", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r5)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());	

			List<LexicalRule<? extends RuleInfo>> similarities_l6 = lr.getRulesForLeft("lesen", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l6)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r6 = lr.getRulesForRight("lesen", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r6)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
		
			List<LexicalRule<? extends RuleInfo>> similarities_l7 = lr.getRulesForLeft("anklagen", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l7)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r7 = lr.getRulesForRight("anklagen", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r7)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
		
			List<LexicalRule<? extends RuleInfo>> similarities_l8 = lr.getRulesForLeft("kooperativ", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l8)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r8 = lr.getRulesForRight("kooperativ", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r8)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
		
			List<LexicalRule<? extends RuleInfo>> similarities_l9 = lr.getRulesForLeft("irisch", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l9)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r9 = lr.getRulesForRight("irisch", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r9)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
		
			List<LexicalRule<? extends RuleInfo>> similarities_l10 = lr.getRulesForLeft("angegriffen", null); 
			System.out.println("left-2-right rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_l10)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());

			List<LexicalRule<? extends RuleInfo>> similarities_r10 = lr.getRulesForRight("angegriffen", null); 
			System.out.println("right-2-left rules: ");
			for (LexicalRule<? extends RuleInfo> similarity : similarities_r10)
				System.out.println("<" + similarity.getLLemma() + "," + similarity.getLPos() + ">" + " --> " + "<" + similarity.getRLemma() + "," + similarity.getRPos() + ">" + ": " + similarity.getConfidence());
								
			
		
		} catch (Exception e)
		{
			e.printStackTrace(); 
			fail(e.getMessage());  			
		}
		
		// Note that you *MUST* call close() all the time, other wise
		// the underlying redis-server does not closes!  
		try {
			lr.close(); 
		} catch (Exception e)
		{
			e.printStackTrace(); 
			fail(e.getMessage());  						
		}
		
	}
}
