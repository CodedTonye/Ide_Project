package objectsRelationship;

public class Person {
    String name;
    Dog pet;

    Person(String name, Dog pet) {
        this.name = name;
        this.pet = pet;
    }

    String interactWithPet() {
        return name + " plays with " + pet.name;
    }
}
