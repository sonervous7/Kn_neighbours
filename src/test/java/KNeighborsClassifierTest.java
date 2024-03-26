import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class KNeighborsClassifierTest {

    private KNeighborsClassifier model;

    @ParameterizedTest
    @MethodSource("provideParameters")
    void testWithDifferentParameters(int n_neighbors, Metrics metric) {
        KNeighborsClassifier model = new KNeighborsClassifier(n_neighbors, metric);
    }

    static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(3, Metrics.EUCLIDEAN),
                Arguments.of(3, Metrics.MANHATTAN),
                Arguments.of(5, Metrics.EUCLIDEAN),
                Arguments.of(5, Metrics.MANHATTAN),
                Arguments.of(7, Metrics.EUCLIDEAN),
                Arguments.of(7, Metrics.MANHATTAN)
        )
    }

    @Test
    void shouldFitWithG
}
