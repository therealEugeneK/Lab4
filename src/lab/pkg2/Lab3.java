package lab.pkg2;

import java.util.*;
import java.io.*;

public class Lab3 {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(new File("spotify.csv")); //reads in data from file
        PrintStream ps = new PrintStream(new File("ArtistsSorted-WeekOf09062020.txt")); //prints final report to file

        String line = "";

        final int maxArtistPossible = 19;

        String artists[] = new String[maxArtistPossible];
        int artistsCount[] = new int[maxArtistPossible];

        int currentIndex = 0;
        //reset count to zero
        for (int i = 0; i < artistsCount.length; i++) {
            artistsCount[i] = 0;
        }

        while (sc.hasNextLine()) {

            line = sc.nextLine(); //reads line
            String columns[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            //Artist will be at position 2
            String tmpArtist = columns[2];

            //remove quotes from the line
            tmpArtist = tmpArtist.replaceAll("\"", "");
            for (String art : tmpArtist.split(",")) { //checks for duplicate artists 
                boolean found = false;
                for (int i = 0; i < currentIndex; i++) {
                    if (art.equalsIgnoreCase(artists[i])) {
                        artistsCount[i]++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    artists[currentIndex] = art;
                    artistsCount[currentIndex] = 1;
                    currentIndex++;
                }
            }
        }

        LinkedList sortedArtists = new LinkedList(Arrays.asList(artists)); //converts the artists array to an artists LinkedList
        Collections.sort(sortedArtists); //alphabetically sorts the linkedlist in ascending order

        ps.printf("%-25s\n\n", "Artists Sorted"); //prints to output file
        for (Iterator i = sortedArtists.iterator(); i.hasNext();) { //iterates through the linkedlist and prints to the output file
            ps.println(i.next());

        }
    }
}
