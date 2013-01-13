package ac.biu.nlp.nlp.engineml.utilities.safemodel;
import java.io.Serializable;
import java.util.Vector;

import ac.biu.nlp.nlp.engineml.classifiers.LabeledSample;
import ac.biu.nlp.nlp.engineml.rteflow.systems.FeatureVectorStructureOrganizer;
import ac.biu.nlp.nlp.engineml.utilities.TeEngineMlException;

/**
 * Stores a {@link Vector} of {@link LabeledSample}s, along with system-configuration,
 * ensuring that the samples will be used only by system with the same configuration.
 * 
 * @see SafeSamplesUtils
 * 
 * @author Asher Stern
 * @since Aug 24, 2011
 *
 */
public class SafeSamples extends SafeModel<Vector<LabeledSample>> implements Serializable
{
	private static final long serialVersionUID = -8363047984780466124L;

	SafeSamples(FeatureVectorStructureOrganizer featureVectorStructure,
			Vector<LabeledSample> modelObject) throws TeEngineMlException
	{
		super(featureVectorStructure, modelObject);
	}

	public Vector<LabeledSample> getSamples()
	{
		return getModelObject();
	}
}
