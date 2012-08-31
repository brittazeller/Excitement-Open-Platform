package eu.excitement.entailment.core;

import java.util.List;
import org.apache.uima.jcas.JCas;
import eu.excitement.entailment.core.exceptions.ComponentException;
import eu.excitement.entailment.core.exceptions.EDAException;

/** 
 * Multiple Text and/or Hypothesis interfaces: 
 * Each of the interface defines a method, namely <code>processMultiT</code>, <code>processMultiH</code>, 
 * and <code>processMultiTH</code>. EDAs may choose to support them or not, since supporting 
 * multiple T/H interfaces are optional. Multiple Texts and Hypotheses are marked 
 * in the CAS by multiple EXCITEMENT.entailment.Pair annotations. 
 */
public interface EDAMultiT<T extends TEDecision> extends EDABasic<T> {
	
	public List<T> processMultiT(JCas aCas) throws EDAException, ComponentException;
	
}
