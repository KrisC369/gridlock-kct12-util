package be.kuleuven.cs.gridlock.util.stat;

import be.kuleuven.cs.gridlock.configuration.Configuration;
import java.util.Collection;

/**
 * Interface representing entities that gather information and can be queried
 * for configurations and information aggregators.
 *
 * @author kristofc
 */
public interface IStatistical {
    /**
     * Returns the configuration.
     *
     * @return The configruation of the instance.
     */
    Configuration getConfig();

    /**
     * Returns the infoaggregators.
     *
     * @return A collection of info aggregators.
     */
    Collection<IInfoAggregator> getInfoAggregators();
}
