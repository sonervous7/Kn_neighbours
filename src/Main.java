import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int[] n_neighbours = new int[] {3, 2, 5, 6};

        ArrayList<double[]>  cancerAttributes = DataReader.readFeatures("test_data_cancer.txt");

        ArrayList<Integer> cancerLabels = DataReader.readLabels("test_data_cancer.txt");

        ArrayList<double[]> irisAttributes = DataReader.readFeatures("test_data_iris.txt");

        ArrayList<Integer> irisLabels = DataReader.readLabels("test_data_iris.txt");

        System.out.println(irisLabels);

        System.out.println(cancerLabels);
//
        for (double[] arr : cancerAttributes) {
            for (double item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        ArrayList<KNeighboursClassifier> allCases = new ArrayList<>();

        for (Metrics metric : Metrics.values()) {
            for (int item : n_neighbours) {
                KNeighboursClassifier classifier = new KNeighboursClassifier(item, metric, "example_file.txt");
                allCases.add(classifier);
            }
        }

        for (KNeighboursClassifier allCase : allCases) {
            System.out.println(allCase.getMetric() + " " + allCase.getN_neighbours());
        }




    }
}