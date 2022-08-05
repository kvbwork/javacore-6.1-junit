package kvbdev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class MainTests {

    private static Collection<Person> testPersons = null;
    private static int expectedUnderageCount = -1;

    @BeforeAll
    public static void beforeAll() {
        testPersons = Collections.unmodifiableCollection(testPersonsSource());
        expectedUnderageCount = 144;    // count[0..18) * sex * education = 18 * 2 * 4 = 144
    }

    @AfterAll
    public static void afterAll() {
        testPersons = null;
    }

    private static Collection<Person> testPersonsSource() {
        ArrayList<Person> persons = new ArrayList<>();
        for (int age = 0; age < 100; age++) {
            for (Education edu : Education.values()) {
                for (Sex sex : Sex.values()) {
                    persons.add(new Person("Man" + age, "Family" + age, age, sex, edu));
                }
            }
        }
        return persons;
    }

    @Test
    public void testGeneratePersons_nonEmptyCollection() {
        // given:
        Collection<Person> randomPersons = null;
        int size = 42;

        // when:
        randomPersons = Main.generatePersons(size);

        // then:
        Assertions.assertNotNull(randomPersons);
        Assertions.assertInstanceOf(Collection.class, randomPersons);
        Assertions.assertFalse(randomPersons.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100})
    public void testGeneratePersonsPositiveSizeArg_success(int sizeArg) {
        // given:
        Collection<Person> randomPersons = null;

        // when:
        randomPersons = Main.generatePersons(sizeArg);

        // then:
        Assertions.assertEquals(sizeArg, randomPersons.size());
    }


    @Test
    public void testCountUnderage_testPersons_success() {
        // given:
        Collection<Person> persons = testPersons;
        long expected = expectedUnderageCount;

        // when:
        long count = Main.countUnderage(persons);

        // then:
        Assertions.assertEquals(expected, count);
    }

}