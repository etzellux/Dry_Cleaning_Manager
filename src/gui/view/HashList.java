package gui.view;

import java.util.Locale;

public class HashList
{
    int size;
    EmployeeData[] nodes;

    /**
     * Creates a ContactList object with given size.
     * @param size : size of the hash table.
     */
    public HashList(int size)
    {
        this.size = size;
        nodes = new EmployeeData[size];
    }

    /**
     * Produces hash code with the given key.
     * @param key : int value
     * @return hash code
     */
    int hashCode(int key)
    {
        return key % size;
    }

    public void insert(EmployeeData node)
    {
        int hashIndex = hashCode(node.getName().toUpperCase(Locale.ROOT).charAt(0));

        if(nodes[hashIndex] == null)
        {
            nodes[hashIndex] = node;
        }
        else
        {
            EmployeeData currentNode = nodes[hashIndex];

            while(currentNode.nextNode != null)
            {
                currentNode = currentNode.nextNode;
            }

            currentNode.nextNode = node;
        }
    }

    /**
     * Deletes node from hash table.
     * @param node : node to be deleted.
     */
    public void delete(EmployeeData node)
    {
        int hashIndex = hashCode(node.getName().toUpperCase(Locale.ROOT).charAt(0));

        if(nodes[hashIndex] != null)
        {
            EmployeeData currentNode = nodes[hashIndex];
            EmployeeData parentNode = nodes[hashIndex];

            if(currentNode.equals(node))
            {
                nodes[hashIndex] = null;
            }

            while(!currentNode.equals(node))
            {
                parentNode = currentNode;
                currentNode = currentNode.nextNode;
            }

            parentNode.nextNode = currentNode.nextNode;
        }
    }

    /**
     * Deletes node from hash table with the given name.
     * @param name : name value of the node.
     */
    public void delete(String name)
    {
        EmployeeData node = search(name);

        if(node != null)
        {
            delete(node);
        }
        else
        {

        }
    }

    /**
     * Searches node with the given name in the hash table.
     * @param name : name value of the node.
     * @return node if exists (else null)
     */
    public EmployeeData search(String name)
    {
        int hashIndex = hashCode(name.toUpperCase(Locale.ROOT).charAt(0));
        EmployeeData currentNode = nodes[hashIndex];

        if(currentNode == null)
        {
            return null;
        }
        else
        {
            while(currentNode != null)
            {
                if(currentNode.getName().equals(name))
                {
                    break;
                }
                currentNode = currentNode.nextNode;
            }
            return currentNode;
        }
    }

}