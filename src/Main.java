import java.util.ArrayList;

public class Main {

    private static final int[] K_VALUES = {3, 5, 7, 9, 11};
    public static void main(String[] args) {

        String filePathTrain = "train_data_cancer.txt";
        String filePathTest = "test_data_cancer.txt";

        ArrayList<double[]> trainFeaturesList = DataReader.readFeatures(filePathTrain);
        ArrayList<Integer> trainLabelList = DataReader.readLabels(filePathTrain);

        ArrayList<double[]> testFeaturesList = DataReader.readFeatures(filePathTest);
        ArrayList<Integer> testLabelList = DataReader.readLabels(filePathTest);

        double[][] trainFeatures = DataReader.doubleListToDoubleArr(trainFeaturesList);
        double[][] testFeatures = DataReader.doubleListToDoubleArr(testFeaturesList);

        int[] trainLabels = DataReader.intListToIntArr(trainLabelList);
        int[] testLabels = DataReader.intListToIntArr(testLabelList);

        DataWriter.clearFile("saved_results.txt");

        if (filePathTrain == "train_data_cancer.txt" ) {
            System.out.println("ZESTAW DANYCH: CANCER");
            DataWriter.saveResults("saved_results.txt", "ZESTAW DANYCH: CANCER");
        } else {
            System.out.println("ZESTAW DANYCH: IRIS");
            DataWriter.saveResults("saved_results.txt", "ZESTAW DANYCH: IRIS");
        }


        for (Metrics metric : Metrics.values()) {
            System.out.println("METRYKA: " + metric);
            DataWriter.saveResults("saved_results.txt", "METRYKA: " + metric.toString());
            for (int k : K_VALUES) {
                KNeighborsClassifier model = new KNeighborsClassifier(k, metric);

                model.fit(trainFeatures, trainLabels);
                int[] predictions = model.predict(testFeatures);
//              for (int j = 0; j < predictions.length; j ++) {
//                  System.out.println("Przewidywana etykieta: " + predictions[j] + ", Rzeczywista etykieta " + testLabels[j]);
//              }
                double accuracy = model.calculate_accuracy(testFeatures, testLabels);
                accuracy *= 100;
                String formatedAccuracy = String.format("%.3f", accuracy);


                System.out.println("Dokładność dla k= " + k + ": " + formatedAccuracy + "%");
                DataWriter.saveResults("saved_results.txt", "Dokładność dla k= " + k + ": " + formatedAccuracy + "%");
            }
        }



    }
}