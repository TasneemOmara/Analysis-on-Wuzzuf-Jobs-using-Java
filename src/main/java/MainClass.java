import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.vector.BaseVector;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class MainClass {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        // testing if conflict exist
        ReadDataFrame rd = new ReadDataFrame();
        rd.generatePieChart(withoutNulls, "Company");
        rd.generateCategoryChart (withoutNulls, "Title","Top 10 popular jobs","Jobs", "Frequency of jobs");
        rd.generateCategoryChart (withoutNulls, "Location","Top 10 popular areas","Area", "Number of jobs in the area");



    }
}
