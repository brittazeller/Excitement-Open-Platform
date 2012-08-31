package eu.excitement.entailment.core.helpers;

import java.util.List;
import eu.excitement.entailment.core.EDABasic;
import eu.excitement.entailment.core.TEDecision;
import eu.excitement.entailment.core.exceptions.ComponentException;
import eu.excitement.entailment.core.exceptions.EDAException;

import org.apache.uima.jcas.JCas; 

public abstract class MultipleTHModeHelper<T extends TEDecision> {

	abstract public void setEDA(EDABasic<T> eda);
	abstract public List<T> processMultiT(JCas aCas) throws EDAException, ComponentException;
	abstract public List<T> processMultiH(JCas aCas) throws EDAException, ComponentException;
	
}
