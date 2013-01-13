package ac.biu.nlp.nlp.engineml.plugin;
import ac.biu.nlp.nlp.engineml.utilities.BIUTEEBaseException;

/**
 * Indicates an error/failure in the classes and
 * methods that handle plug-ins.
 * If the error is in the plug-in itself, the plug-in should throw
 * {@link PluginException}.
 * 
 * @author Asher Stern
 * @since Jan 27, 2012
 *
 */
@SuppressWarnings("serial")
public class PluginAdministrationException extends BIUTEEBaseException
{
	public PluginAdministrationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public PluginAdministrationException(String message)
	{
		super(message);
	}
}
