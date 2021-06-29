import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import smile.data.DataFrame;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import smile.io.Read;
import static java.util.stream.Collectors.toMap;


public class ReadDataFrame {
    private DataFrame df = null;
    public void ReadDataFrame () {
        CSVFormat f = CSVFormat.DEFAULT.withFirstRecordAsHeader();

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
        DataFrame df_withoutNull=df.omitNullRows();
        System.out.println("Rows before removing nulls are " + df.nrows());
        System.out.println("Rows without nulls are : "+ df_withoutNull.nrows());

    }

    public static void generatePieChart(DataFrame df, String col) {
        Map<String, Long> jobsMap = df.stream().collect(Collectors.groupingBy(row -> row.getString(col), Collectors.counting()));
        Map<String, Long> jobsMapSorted =  jobsMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        PieChart pie = new PieChartBuilder().width(1100).height(600).title("Pie Chart of number of jobs from each company").build();
        jobsMapSorted.forEach((k, v) -> pie.addSeries(k, v));

        pie.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        pie.getStyler().setHasAnnotations(true);
        new SwingWrapper(pie).displayChart();
        System.out.println(jobsMapSorted);


    }

    public static void generateCategoryChart (DataFrame df, String col, String graphTitle, String xLabel,String yLabel){
        Map<String, Long> frequencyMap = df.stream().collect(Collectors.groupingBy(row -> row.getString(col), Collectors.counting()));
        Map<String, Long> frequencyMapSorted= frequencyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10)
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        ArrayList<String> keyList = new ArrayList<String>(frequencyMapSorted.keySet());
        ArrayList<Long> valueList = new ArrayList<Long>(frequencyMapSorted.values());
        CategoryChart chart = new CategoryChartBuilder().height(600).width(1800).xAxisTitle(xLabel).yAxisTitle(yLabel).title(graphTitle).build();
        chart.addSeries(graphTitle,keyList, valueList);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        new SwingWrapper(chart).displayChart();
        System.out.println(frequencyMapSorted);

    }


}
