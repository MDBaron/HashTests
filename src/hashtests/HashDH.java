package hashtests;


/************************************************************************************
 * @file HashSC.java
 *
 * @author  John Miller
 * @version 1.0, Fri Jul  8 11:06:40 EDT 2011
 */

import static java.lang.System.out;
import java.util.*;

/************************************************************************************
 *
 */
public class HashDH extends AbstractCollection
                    implements Collection
{
    private static final int EMPTY   = -1;
    private static final int DELETED = -2;

    class Bucket
    {
        int key;
        Bucket () { key = EMPTY; };
    } // Bucket inner class

    private final Bucket [] table;
    private final int       size;
    private final int       size2;
    private       int       count;

    /********************************************************************************
     *
     */
    public HashDH (int s, int s2)
    {
        table = new Bucket [size = s];
        for (int i = 0; i < size; i++) table [i] = new Bucket ();
        size2 = s2;
        count = 0;
    } // HashDH

    /********************************************************************************
     *
     */
    private int h (int key)
    {
        return key % size;
    } // h

    /********************************************************************************
     *
     */
    private int h2 (int key)
    {
        return key % size2;
    } // h2

    public int size ()
    {
        return size;
    } // size

    /********************************************************************************
     *
     */
    public Iterator iterator ()
    {
        throw new UnsupportedOperationException ();
    } // iterator

    /********************************************************************************
     *
     */
    public boolean add (int key)
    {
        final int i = h (key);
        final int c = h2 (key);
        int       j = i;
        int       key_j;
        do {
            key_j = table [j].key;
            if (key_j == EMPTY || key_j == DELETED) {
                table [j].key = key;
                return true;
            }; // if
            j = (j + c) % size;
        } while (j != i);
        return false;
    } // add

    /********************************************************************************
     *
     */
    public boolean contains (int key)
    {
        final int i = h (key);
        final int c = h2 (key);
        int       j = i;
        int       key_j;
        do {
            count++;
            key_j = table [j].key;
            if (key_j == EMPTY) return false;
            if (key_j == key)   return true;
            j = (j + c) % size;
        } while (j != i);
        return false;
    } // contains

    /********************************************************************************
     *
     */
    private void print ()
    {
        for (int i = 0; i < size; i++) {
            out.print ("@" + i + "\t[ ");
            out.print (table [i].key + " ");
            out.println ("]");
        }; // for
    } // print

    /********************************************************************************
     *
     */
    public static void main (String[] args)
    {
        HashDH ht = new HashDH (11, 3);
        for (int i = 1; i < 20; i += 2) ht.add (i);
        ht.print ();
        for (int i = 0; i < 20; i++) {
            out.println ("is " + i + " in: " + ht.contains (i));
        }; // for
        out.println ("Number of probes = " + ht.count);
    } // main

} // HashDH class
