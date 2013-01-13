package ac.biu.nlp.nlp.engineml.alignment;
import java.util.Set;

import org.apache.log4j.Logger;

import ac.biu.nlp.nlp.engineml.representation.ExtendedInfo;
import ac.biu.nlp.nlp.engineml.representation.ExtendedNode;
import ac.biu.nlp.nlp.engineml.rteflow.macro.SingleTreeEvaluations;
import ac.biu.nlp.nlp.engineml.utilities.parsetreeutils.TreeUtilities;
import ac.biu.nlp.nlp.instruments.parse.tree.TreeAndParentMap;
import eu.excitementproject.eop.common.codeannotations.NotThreadSafe;

/**
 * 
 * Given two parse-trees, one of the text (or generated from the text) and one of
 * the hypothesis, this class calculates which nodes, edges and "triples" of the
 * hypothesis parse tree exist (aligned to) in the text-parse-tree.
 * <P>
 * In addition to {@link AbstractAlignmentCalculator}, this class also returns
 * the information as {@link SingleTreeEvaluations}, which is a class that contains
 * exactly this information (the nodes, edges and triples aligned between the
 * text and the hypothesis).
 * 
 * @author Asher Stern
 * @since May 31, 2012
 *
 */
@NotThreadSafe
public class AlignmentCalculator extends AbstractAlignmentCalculator<ExtendedInfo, ExtendedNode>
{
	public AlignmentCalculator(
			AlignmentCriteria<ExtendedInfo, ExtendedNode> alignmentCriteria,
			TreeAndParentMap<ExtendedInfo, ExtendedNode> textTree,
			TreeAndParentMap<ExtendedInfo, ExtendedNode> hypothesisTree)
	{
		super(alignmentCriteria, textTree, hypothesisTree);
	}

	public SingleTreeEvaluations getEvaluations(Set<String> hypothesisLemmasLowerCase, int numberOfHypothesisNodes)
	{
		int missingNodes = getMissingSimilarNodes().size();
		int missingTriples = getMissingTriples().size();
		int coveredLemmas = TreeUtilities.findCoveredLemmasLowerCase(textTree,hypothesisLemmasLowerCase).size();
		int missingLemmas = hypothesisLemmasLowerCase.size()-coveredLemmas;
		
		double missingNodesPortion = ((double)missingNodes) / ((double)numberOfHypothesisNodes);
		double missingTriplesPortion = ((double)missingTriples) / ((double)(numberOfHypothesisNodes-1));
		double missingLemmasPortion = ((double)missingLemmas) / ((double)(hypothesisLemmasLowerCase.size()));

		return new SingleTreeEvaluations(missingNodes, missingTriples, missingLemmas, missingNodesPortion, missingTriplesPortion, missingLemmasPortion); 
	}


	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AlignmentCalculator.class);
}
