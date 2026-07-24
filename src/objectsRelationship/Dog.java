package objectsRelationship;

public class Dog {
    String name;
    String breed;

    Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    String speak() {
        return name + " says: Woof";
    }

    String fetch() {
        return name + " the " + breed + " fetches the ball!";
    }
}
