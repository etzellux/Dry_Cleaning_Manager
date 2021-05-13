package gui.view;

/**
 * Class to store contact informations.
 */
public class Node
{
    String name;
    int id;
    Node nextNode;

    /** Constructs Node object with the given name and number.
     * @param name : name of the contact.
     * @param number : telephone number of the contact.
     */
    public Node(String name, String number)
    {
        this.name = name;
        this.id = id;
        this.nextNode = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return id;
    }

    public void setNumber(int id) {
        this.id = id;
    }

    public boolean equals(Node node)
    {
        return (this.name.equals(node.name) && this.id == node.id);
    }

    /**
     * @return String
     */
    public String toString()
    {
        return ("Name: " + this.name + "\nNumber: " + this.id);
    }
}
