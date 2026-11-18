package entity;

/**
 * A simple entity representing a Set card. Set cards have 4 visual parameters and an id.
 */

public class Card {

    private final int id;
    private final int[] characteristics;

    /**
     * Creates a new card with the given id, colour, shading, number, and shape.
     * @param id this card's id
     */
    public Card(int id, int[] characteristics){
        this.id = id;
        this.characteristics = characteristics;
    }

    public int getId () { return this.id; }

    public int[] getCharacteristics() { return this.characteristics; }

}
