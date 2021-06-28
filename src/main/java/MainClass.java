import smile.data.DataFrame;

import java.lang.reflect.InvocationTargetException;

public class MainClass {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        ReadDataFrame rd = new ReadDataFrame();
        DataFrame df = rd.readDataCsv();
        DataFrame withoutNulls = rd.removeNulls(df);
    }
}
