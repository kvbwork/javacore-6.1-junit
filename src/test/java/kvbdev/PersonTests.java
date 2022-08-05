package kvbdev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTests {

    @Test
    public void testAllArgsConstructor_newInstance() {
        // given:
        String name = "NAME";
        String family = "FAMILY";
        int age = 99;
        Sex sex = Sex.MAN;
        Education edu = Education.ELEMENTARY;

        // when:
        Person person = new Person(name, family, age, sex, edu);

        // then:
        Assertions.assertInstanceOf(Person.class, person);
    }

    @Test
    public void testAllGettersReturnsValues() {
        // given:
        String name = "NAME";
        String family = "FAMILY";
        int age = 99;
        Sex sex = Sex.MAN;
        Education edu = Education.ELEMENTARY;

        // when:
        Person person = new Person(name, family, age, sex, edu);

        // then:
        Assertions.assertEquals(name, person.getName());
        Assertions.assertEquals(family, person.getFamily());
        Assertions.assertEquals(age, person.getAge());
        Assertions.assertEquals(sex, person.getSex());
        Assertions.assertEquals(edu, person.getEducation());
    }

}
