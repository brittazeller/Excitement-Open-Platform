/**
 * 
 */
package ac.biu.nlp.nlp.engineml.generic.truthteller.application;
import ac.biu.nlp.nlp.engineml.generic.truthteller.AnnotatorException;
import ac.biu.nlp.nlp.engineml.generic.truthteller.application.ct.ClauseTruthAnnotationRuleApplier;
import ac.biu.nlp.nlp.engineml.representation.AdditionalNodeInformation;
import ac.biu.nlp.nlp.instruments.parse.representation.basic.Info;
import ac.biu.nlp.nlp.instruments.parse.tree.AbstractConstructionNode;

/**
 * This is a common interface for all classes that apply a single annotation rule on a tree, 
 * like DefaultAnnotationRuleApplier and {@link ClauseTruthAnnotationRuleApplier}.<br>
 * The rule is predetermined for each instance, supposedly through the Ctor.<br>
 * It is assumed that the {@link #annotateTreeWithOneRule()} method technically just changes/replaces the {@link AdditionalNodeInformation} or {@link Info} of 
 * the given {@link AbstractConstructionNode} tree, without replacing the containing nodes, so there is no need for a return value nor to return node mapping. 
 * 
 * @author Amnon Lotan
 *
 * @since 1 May 2012
 */
public interface AnnotationRuleApplier<N extends AbstractConstructionNode<? extends Info, N>> {

	/**
	 * Annotated a tree, according to the prerogative of the implementation
	 * @param tree
	 * @return
	 * @throws AnnotatorException
	 */
	public void annotateTreeWithOneRule(N tree) throws AnnotatorException;
}
