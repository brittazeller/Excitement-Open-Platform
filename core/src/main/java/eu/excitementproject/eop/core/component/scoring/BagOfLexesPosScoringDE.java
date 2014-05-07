package eu.excitementproject.eop.core.component.scoring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Logger;

import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalResource;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalResourceCloseException;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalResourceException;
import eu.excitementproject.eop.common.component.lexicalknowledge.LexicalRule;
import eu.excitementproject.eop.common.component.lexicalknowledge.RuleInfo;
import eu.excitementproject.eop.common.component.scoring.ScoringComponentException;
import eu.excitementproject.eop.common.configuration.CommonConfig;
import eu.excitementproject.eop.common.configuration.NameValueTable;
import eu.excitementproject.eop.common.exception.BaseException;
import eu.excitementproject.eop.common.exception.ConfigurationException;
import eu.excitementproject.eop.common.representation.partofspeech.GermanPartOfSpeech;
import eu.excitementproject.eop.common.representation.partofspeech.UnsupportedPosTagStringException;
import eu.excitementproject.eop.core.component.lexicalknowledge.derivbase.DerivBaseNotInstalledException;
import eu.excitementproject.eop.core.component.lexicalknowledge.derivbase.DerivBaseResource;
import eu.excitementproject.eop.core.component.lexicalknowledge.germanet.GermaNetRelation;

/**
 * The class <code>BagOfLexesPosScoring</code> extends
 * <code>BagOfLexesScoring</code>.
 * 
 * It adds POS tags into the queries to the lexical resources.
 * 
 * Both GermaNetWrapper and GermanTransDmResource can be used with or without POS information. 
 * 
 * @author Rui Wang
 * @since January 2013
 */
public class BagOfLexesPosScoringDE extends BagOfLexesScoringDE {
	
	static Logger logger = Logger.getLogger(BagOfLexesPosScoringDE.class.getName());
	
	/**
	 * the number of features
	 */
	private int numOfFeats = 0;

	@Override
	public int getNumOfFeats() {
		return numOfFeats;
	}
	
	protected boolean[] moduleFlags = new boolean[1];
	
	protected DerivBaseResource dbr = null;

	/**
	 * the constructor using parameters
	 * 
	 * @param isDS whether to use <code>GermanDistSim</code>
	 * @param isTdm whether to use <code>GermanTransDmResource</code>
	 * @param simMeasure the similarity measure used for GermanTransDm, either "cosine", "balapinc" or "all"
	 * @param isGN whether to use <code>GermaNetWrapper</code>
	 * @param germaNetRelations the array of GermaNet relations
	 * @param germaNetFilePath the file path to GermaNet
	 * @param isDB whether to use <code>DerivBaseResource</code>
	 * @param useScores cf. <code>DerivBaseResource</code>
	 * @param derivSteps cf. <code>DerivBaseResource</code>
	 * @throws ConfigurationException
	 * @throws LexicalResourceException
	 */
	public BagOfLexesPosScoringDE(boolean isDS, boolean isTdm, String simMeasure, boolean isGN, String[] germaNetRelations, 
			String germaNetFilePath, boolean isDB, boolean useScores, String derivSteps) throws ConfigurationException, LexicalResourceException{
		
		super(isDS, isTdm, simMeasure, isGN, germaNetRelations, germaNetFilePath, isDB);
		numOfFeats = super.getNumOfFeats();
		
		for (int i = 0; i < moduleFlags.length; i++) {
			moduleFlags[i] = false;
		}
		
		// initialize DerivBaseResource
		if (isDB) {
			//this.isDB = true;
			try {
				dbr = new DerivBaseResource(useScores, derivSteps);
				numOfFeats++;
				moduleFlags[0] = true;
			} catch (DerivBaseNotInstalledException e) {
				logger.warning("WARNING: DErivBase file was not found in the given path.");
				throw new LexicalResourceException(e.getMessage());
			} catch (BaseException e) {
				throw new LexicalResourceException(e.getMessage());
			}
			logger.info("Load DerivBaseResource done.");
		}
	}
	
	/**
	 * the constructor
	 * 
	 * @param config
	 *            the configuration
	 * @throws ConfigurationException
	 * @throws LexicalResourceException
	 */
	public BagOfLexesPosScoringDE(CommonConfig config)
			throws ConfigurationException, LexicalResourceException {
		super(config);
		numOfFeats = super.getNumOfFeats();
		
		for (int i = 0; i < moduleFlags.length; i++) {
			moduleFlags[i] = false;
		}
		
		NameValueTable comp = config.getSection("BagOfLexesScoring");
		
		// initialize DerivBaseResource
		if (null != comp.getString("DerivBaseResource")) {
			try {
				dbr = new DerivBaseResource(config);
				numOfFeats++;
				moduleFlags[0] = true;
			} catch (DerivBaseNotInstalledException e) {
				logger.warning("WARNING: DErivBase file was not found in the given path.");
				throw new LexicalResourceException(e.getMessage());
			} catch (BaseException e) {
				throw new LexicalResourceException(e.getMessage());
			}
			logger.info("Load DerivBaseResource done.");
		}
	}

	@Override
	public String getComponentName() {
		return "BagOfLexesPosScoring";
	}

	/**
	 * close the component by closing the lexical resources.
	 */
	@Override
	public void close() throws ScoringComponentException {
		try {
			super.close();
			if (null != dbr) {
				dbr.close();
			}
		} catch (LexicalResourceCloseException e) {
			throw new ScoringComponentException(e.getMessage());
		}
	}
	
	@Override
	public Vector<Double> calculateScores(JCas aCas)
			throws ScoringComponentException {
		// 1) how many words of H (extended with multiple relations) can be
		// found in T divided by the length of H
		Vector<Double> scoresVector = new Vector<Double>();

		try {
			JCas tView = aCas.getView("TextView");
			HashMap<String, Integer> tBag = countTokenPoses(tView);

			JCas hView = aCas.getView("HypothesisView");
			HashMap<String, Integer> hBag = countTokenPoses(hView);

			if (super.moduleFlags[0]) {
				//tempHelpCounter = 1;
				scoresVector.add(calculateSingleLexScore(tBag, hBag, gds));
			}
			if (super.moduleFlags[1]) {
				//tempHelpCounter = 2;
				scoresVector.add(calculateSingleLexScore(tBag, hBag, gnw));
			}
			if (super.moduleFlags[2]) {
				//tempHelpCounter = 3;
				scoresVector.add(calculateSingleLexScore(tBag, hBag, gtdm));
			}
			if (moduleFlags[0]) {
				//tempHelpCounter = 4;
				scoresVector.add(calculateSingleLexScore(tBag, hBag, dbr));
			}
		} catch (CASException e) {
			throw new ScoringComponentException(e.getMessage());
		}
		return scoresVector;
	}

	/**
	 * Count the lemmas and POSes contained in a text and store the counts in a
	 * HashMap
	 * 
	 * @param text
	 *            the input text represented in a JCas
	 * @return a HashMap represents the bag of lemmas and POSes contained in the
	 *         text, in the form of <Lemma ### POS, Frequency>
	 */
	protected HashMap<String, Integer> countTokenPoses(JCas text) {
		HashMap<String, Integer> tokenNumMap = new HashMap<String, Integer>();
		Iterator<Annotation> tokenIter = text.getAnnotationIndex(Token.type)
				.iterator();
		while (tokenIter.hasNext()) {
			Token curr = (Token) tokenIter.next();
			String tokenText = curr.getLemma().getValue() + " ### "
					+ curr.getPos().getPosValue();
			Integer num = tokenNumMap.get(tokenText);
			if (null == num) {
				tokenNumMap.put(tokenText, 1);
			} else {
				tokenNumMap.put(tokenText, num + 1);
			}
		}
		return tokenNumMap;
	}

	@Override
	protected double calculateSingleLexScore(HashMap<String, Integer> tBag,
			HashMap<String, Integer> hBag,
			LexicalResource<? extends RuleInfo> lex)
			throws ScoringComponentException {
		
		// BZ: my stuff for better DB integration:
		// Make hWordBag in matching format (short POSes, disambiguated lemmas)
		/*HashMap<String, Integer> hWordBag = new HashMap<String, Integer>();
		for (String hWord : hBag.keySet()) {
			String tmpWord = hWord.split(" ### ")[0];
			if (tmpWord.contains("|")) {
				tmpWord = tmpWord.split("|")[0];
			}
			String tmpPos =  hWord.split(" ### ")[1].substring(0,1);
			hWordBag.put(tmpWord.concat(" ### ").concat(tmpPos), hBag.get(hWord));
		}*/

		if (null == lex) {
			throw new ScoringComponentException(
					"WARNING: the specified lexical resource has not been properly initialized!");
		}
		
		double score = 0.0d;
		HashMap<String, Integer> tWordBag = new HashMap<String, Integer>();
		
		//System.out.println("###\nhBag = " + hWordBag.keySet());
		for (final Iterator<Entry<String, Integer>> iter = tBag.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<String, Integer> entry = iter.next();
			/*final*/ String word = entry.getKey();
			// BZ: my stuff! the following "if" ensures that no TT amgibuities remain; therefore make "word" non-final 
			/*if (word.contains("|")) {
				word = word.substring(0, word.indexOf("|")).concat(" ###").concat(word.substring(word.lastIndexOf(" ")));
			}*/
			final int counts = entry.getValue().intValue();
			try {
				/*tWordBag.put(word.substring(0, word.lastIndexOf("#") + 3), counts);*/// BZ: my line instead of following! ensure that POS is identical 
				tWordBag.put(word, counts); 
				String POS = word.split(" ### ")[1];
				//System.out.print("words from LexRule for word '" + word + "': ");
				for (LexicalRule<? extends RuleInfo> rule : lex
						.getRulesForLeft(word.split(" ### ")[0],
								new GermanPartOfSpeech(POS))) {
					/*String tokenText = rule.getRLemma() + " ### " + rule.getRPos();*///BZ: my line instead of following! ensure that POS is identical
					String tokenText = rule.getRLemma() + " ### " + POS; 
					if (tWordBag.containsKey(tokenText)) {
						int tmp = tWordBag.get(tokenText);
						tWordBag.put(tokenText, tmp + counts); 
					} else {
						/*if (hWordBag.containsKey(tokenText)) {// BZ: my stuff! ensure expansion only for h-containing words
							// originally, only "tWordBag.put(tokenText, counts);"
							tWordBag.put(tokenText, counts + 10);
							int tmp = hWordBag.get(tokenText);
							hWordBag.put(tokenText, tmp + 10);
							System.out.print("\nsomething added!!! \t\t\t");
						}*/
						tWordBag.put(tokenText, counts);
					}
					/*System.out.print(tokenText + ", ");*/
				}
				/*System.out.println();*/
			} catch (LexicalResourceException e) {
				throw new ScoringComponentException(e.getMessage());
			} catch (UnsupportedPosTagStringException e) {
				throw new ScoringComponentException(e.getMessage());
			}
		}

		/*
		// BZ trial: iterate over hBag and expand it with a complementary resource!
		//tempHelpCounter ids: 1 = gds, 2 = gnw, 3 = gtdm, 4 = dbr
		HashMap<String, Integer> hWordBag = new HashMap<String, Integer>();
		for (final Iterator<Entry<String, Integer>> iter = hBag.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<String, Integer> entry = iter.next();
			final String word = entry.getKey();
			final int counts = entry.getValue().intValue();
			try {
				hWordBag.put(word, counts);
				String POS = word.split(" ### ")[1];
				//System.out.println("---tWordBag :" + tWordBag.toString());
				// instead of "lex" for tBag, choose another resource for hBag
				if (tempHelpCounter == 1) {
						lex = dbr;
				} else if (tempHelpCounter == 2) {
						lex = dbr;
				} else if (tempHelpCounter == 3) {
					 	lex = gtdm;
				} else if (tempHelpCounter == 4) {
						lex = gds; //gnw
				}
				for (LexicalRule<? extends RuleInfo> rule : lex
						.getRulesForRight(word.split(" ### ")[0],
								new GermanPartOfSpeech(POS))) {
					String tokenText = rule.getLLemma() + " ### " + POS;  
					//System.out.println("tokenText: " + tokenText);
					if (hWordBag.containsKey(tokenText)) {
						int tmp = hWordBag.get(tokenText);
						hWordBag.put(tokenText, tmp + counts); 
					} else {
						hWordBag.put(tokenText, counts);  
					}
				}
			} catch (LexicalResourceException e) {
				throw new ScoringComponentException(e.getMessage());
			} catch (UnsupportedPosTagStringException e) {
				throw new ScoringComponentException(e.getMessage());
			}
		}*/
				

		score = calculateSimilarity(tWordBag, hBag).get(0); //hWordBag).get(0);//
		
//		System.out.println("resource: "+lex.toString()+" no of rules: "+numberOfRules);

		return score;
	}

	@Override
	protected double calculateSingleLexScoreWithGermaNetRelation(
			HashMap<String, Integer> tBag, HashMap<String, Integer> hBag,
			GermaNetRelation gnr) throws ScoringComponentException {
		if (null == gnw) {
			throw new ScoringComponentException(
					"WARNING: the specified lexical resource has not been properly initialized!");
		}

		double score = 0.0d;
		HashMap<String, Integer> tWordBag = new HashMap<String, Integer>();

		for (final Iterator<Entry<String, Integer>> iter = tBag.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<String, Integer> entry = iter.next();
			final String word = entry.getKey();
			final int counts = entry.getValue().intValue();
			try {
				tWordBag.put(word, counts);
				String POS = word.split(" ### ")[1];
				for (LexicalRule<? extends RuleInfo> rule : gnw
						.getRulesForLeft(word.split(" ### ")[0],
								new GermanPartOfSpeech(POS), gnr)) {
					String tokenText = rule.getRLemma() + " ### " + POS;
					if (tWordBag.containsKey(tokenText)) {
						int tmp = tWordBag.get(tokenText);
						tWordBag.put(tokenText, tmp + counts);
					} else {
						tWordBag.put(tokenText, counts);
					}
				}
			} catch (LexicalResourceException e) {
				throw new ScoringComponentException(e.getMessage());
			} catch (UnsupportedPosTagStringException e) {
				throw new ScoringComponentException(e.getMessage());
			}
		}

		score = calculateSimilarity(tWordBag, hBag).get(0);

		return score;
	}
	
	/**
	 * help variable to check whether DB is activated, or not; for
	 * hypothesis expansion tests.
	 */
	//private boolean isDB = false;
	/**
	 * help variable to check which resource is being activated at a 
	 * specific time point; for hypothesis expansion tests.
	 */
	//private int tempHelpCounter = 0;
	

}
