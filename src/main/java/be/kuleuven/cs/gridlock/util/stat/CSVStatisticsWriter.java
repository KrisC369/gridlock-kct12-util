package be.kuleuven.cs.gridlock.util.stat;

import com.csvreader.CsvWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writes CSV statistics based on fixed output format.
 *
 * @author Kristof Coninx <kristof.coninx at student.kuleuven.be>
 */
public class CSVStatisticsWriter {

    private boolean headerWritten = false;
    private final String INTMARKER = "int";
    private final String FLOATMARKER = "float";

    /**
     * Parses the information to suitable format and then writes information to
     * csv output file.
     *
     * @param main The instance to collect data from.
     * @param outputFile The name of the outputfile to write to.
     * @param addedFieldsAndTypes all the extra non-standard config fields that
     * need to be appended to reccords.
     */
    public void parseCSV(IStatistical main, String outputFile, String... addedFieldsAndTypes) {
        Map<String, String> addedFields = formatAttributes(addedFieldsAndTypes);
        // before we open the file check to see if it already exists
        //boolean alreadyExists = new File(outputFile).exists();
        try {
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

            // if the file didn't already exist then we need to write out the header line
            if (!headerWritten) {
                for (IInfoAggregator eia : main.getInfoAggregators()) {
                    for (int i = 0; i < eia.getDataElements().length; i++) {
                        csvOutput.write(eia.getDataFieldID());
                    }
                }
                for (Entry<String, String> entry : addedFields.entrySet()) {
                    String[] split = entry.getKey().split(".");
                    csvOutput.write(split[split.length - 1]);
                }
                csvOutput.endRecord();
                headerWritten = true;
            }

            for (IInfoAggregator eia : main.getInfoAggregators()) {
                float[] data = eia.getDataElements();
                for (int i = 0; i < data.length; i++) {
                    csvOutput.write(String.valueOf(data[i]));
                }
            }
            for (Entry<String, String> entry : addedFields.entrySet()) {
                String[] split = entry.getKey().split(".");
                csvOutput.write(split[split.length - 1]);

                if (entry.getValue().startsWith(INTMARKER)) {
                    csvOutput.write(String.valueOf(main.getConfig().getInt(entry.getKey(), 0)));
                } else if (entry.getValue().startsWith(FLOATMARKER)) {
                    csvOutput.write(String.valueOf(main.getConfig().getInt(entry.getKey(), 0)));
                }
            }
            csvOutput.endRecord();
            csvOutput.close();
        } catch (IOException e) {
            Logger.getLogger(CSVStatisticsWriter.class.getName()).log(Level.INFO, "IO Exception writing csv file.");
        }
    }

    private static Map<String, String> formatAttributes(String... attributes) {
        if (attributes.length % 2 != 0) {
            throw new IllegalArgumentException("Number of attribute arguments must be even");
        }

        Map<String, String> attributesMap = new LinkedHashMap<String, String>(attributes.length / 2);

        for (int i = 0; i < attributes.length / 2; i++) {
            if (attributes[i * 2] == null) {
                throw new IllegalArgumentException("Cannot accept null as key");
            }
            if (attributesMap.containsKey(attributes[i * 2].toString())) {
                throw new IllegalArgumentException("Cannot accept duplicate key: " + attributes[i * 2]);
            }
            attributesMap.put(attributes[i * 2], attributes[i * 2 + 1]);
        }

        return attributesMap;
    }
}
