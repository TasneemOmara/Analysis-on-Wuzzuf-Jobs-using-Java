import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import java.net.URISyntaxException;
import smile.io.Read;


public class ReadDataFrame {
    public DataFrame readDataCsv() {
        CSVFormat f = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        DataFrame df = null;
        try {
            df = Read.csv("src/main/resources/Wuzzuf_Jobs.csv", f);
            System.out.println("First10 rows of the Dataframe:");
            System.out.println(df.toString(10));
            System.out.println("Structure of the Dataframe:");
            System.out.println(df.structure());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e2) {
            e2.printStackTrace();
        }
        return df;
    }
    public DataFrame removeNulls(DataFrame df){
        DataFrame df_withoutNull=df.omitNullRows();
        System.out.println("Rows before removing nulls are " + df.nrows());
        System.out.println("Rows without nulls are : "+ df_withoutNull.nrows());
        return df_withoutNull;
    }

}
