package entity;

/**
 * A simple entity representing a Set card. Set cards have 4 visual parameters and an id.
 */

public class Card {

    private final int id;
    private final String colour;
    private final String shape;
    private final String number;
    private final String shading;

    /**
     * Creates a new card with the given id, colour, shading, number, and shape.
     * @param id this card's id
     * @param colour the colour of the objects on the card
     * @param shape the shape of the objects on the card
     * @param number the number of the objects on the card
     * @param shading the shading of the objects on the card
     */
    public Card(int id, String colour, String shape, String number, String shading){
//         maybe need a non-negative and non-empty check
        this.id = id;
        this.colour = colour;
        this.shape = shape;
        this.number = number;
        this.shading = shading;
    }

    public int getId () {return this.id;}

    public String getColour () {return this.colour;}

    public String getShape () {return this.shape;}

    public String getNumber () {return this.number;}

    public String getShading () {return this.shading;}

}
