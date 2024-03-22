import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class KNeighborsClassifierTest {

    private KNeighborsClassifier model;

    @BeforeEach
    void beforeEach() {
        model = new KNeighborsClassifier(3, Metrics.EUCLIDEAN);
    }
}
