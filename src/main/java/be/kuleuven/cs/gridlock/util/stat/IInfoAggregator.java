package be.kuleuven.cs.gridlock.util.stat;

/**
 * An entity capable of aggregating data.
 *
 * @author Kristof Coninx <kristof.coninx at student.kuleuven.be>
 */
public interface IInfoAggregator {

    /**
     * Returns the aggregated information in a fully readable string format for
     * this entity.
     *
     * @return String containing data.
     */
    String getFinalEvalDataString();

    /**
     * Returns the instances ID for field identification.
     *
     * @return A string representing the id.
     */
    String getDataFieldID();

    /**
     * Returns all the data elements from this instance.
     *
     * @return An array of floats.
     */
    float[] getDataElements();
}
