package objectsRelationship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectsRelationshipTest {
    Dog dog;
    Person person;

    public ObjectsRelationshipTest() {
    }

    @BeforeEach
    public void setUp() {
        dog = new Dog("Scar", "Rottweiler");
        person = new Person("Peter", dog);
    }

    @Test
    public void dogHasNameAndBreedTest() {
        assertEquals("Scar", dog.name);
        assertEquals("Rottweiler", dog.breed);
    }

    @Test
    public void dogCanSpeakTest() {
        assertEquals("Scar says: Woof", dog.speak());
    }

    @Test
    public void dogCanFetchTest() {
        assertEquals("Scar the Rottweiler fetches the ball!", dog.fetch());
    }

    @Test
    public void personHasNameAndPetTest() {
        assertEquals("Peter", person.name);
        assertEquals(dog, person.pet);
    }

    @Test
    public void personCanInteractWithPetTest() {
        assertEquals("Peter plays with Scar", person.interactWithPet());
    }
}
