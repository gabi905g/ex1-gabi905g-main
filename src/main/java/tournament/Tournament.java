// Gabriela Piekarczyk 97744619
/*
- given 2D array called scorecard that has one row per player with scores per game
- rank the performance of each player
    first based on amount of points (between 0 and 5)
    if tied, compare number of points to determine whether out-performed
- the function will take 2D array scores, sets and games as arguments
    will return length P, first entry is the player who winds the tournament
 */

package tournament;

public class Tournament {

    /**
     * @param scorecard the scores in the tournament and is not null; {@code scorecard[i][j]} represents the score of Player i in Game j. Each row in this array has {@code sets * games} entries, and each entry has a value between 0 and 5 (limits inclusive).
     * @param sets the number of sets played in the tournament, {@code sets > 0}
     * @param games the number of games per set, {@code games > 0}
     * @return a list of players in  order of their performance (best to worst)
     */

    public static int[] rankPlayers(int[][] scorecard, int sets, int games) {
        int[] playerRankings; //declaration of array that displays the properly ordered ranking of the player
        int numPlayers = scorecard.length;
        playerRankings = new int[numPlayers]; // initializing array with length of amount of players (rows of scorecard)

// populating array in numerical order starting from zero
        for(int a = 0; a<numPlayers; a++) {
            playerRankings[a] = a;
        }

// populating array lastSetScores table using lastSetScores method
        int[] lastSetScores = lastSetScores(scorecard, sets, games);


/* Using the Bubble Sort Algorithm to properly rank the order of the players from greatest to least
 *      Compares adjacent numbers works by repeatedly swapping the elements if they are in wrong order
 */
        for(int a=0; a<numPlayers; a++) {
            for (int b=1 ; b<numPlayers-a; b++) {

                // In the case where last set score is obviously greater
                if(lastSetScores[b] > lastSetScores[b-1]) {

                    // Re-arrange lastSetScores array by utilizing integer variable as placeholder
                    int virtual = lastSetScores[b-1];
                    lastSetScores[b-1] = lastSetScores[b];
                    lastSetScores[b] = virtual;

                    // Re-arrange playerRankings array by utilizing integer variable as placeholder
                    int OrgPlayerID = playerRankings[b-1];
                    playerRankings[b-1] = playerRankings[b];
                    playerRankings[b] = OrgPlayerID;

                    // Swap the ROWS of the scorecard array by populating temporary array as placeholder
                    int temp[] = new int[sets*games];
                    for (int c = 0; c<sets*games; c++) {
                        temp[c] = scorecard[b-1][c];
                        scorecard[b-1][c] = scorecard[b][c];
                        scorecard[b][c] = temp[c];
                    }

                }

                // In the case where last set score of two players are tied
                else if(lastSetScores[b-1]==lastSetScores[b]) {

                    // Start with game number SG and work backwards to see when a player outperformed the one tied with
                    int d=sets*games-1;
                    while(d>=0) {

                        // If the last two games are tied, decrease the value of d so to iterate to next value
                        if (scorecard[b-1][d]==scorecard[b][d]) {
                            d--;
                        }

                        // If the game of one player is greater OR the last two indexes of scorecard are compared (swapping based on player index number)
                        else if ((scorecard[b][d]>scorecard[b-1][d]) || (d == 0)) {

                            // Swap the ROWS of the scorecard array by populating temporary array as placeholder
                            int temp2[] = new int[sets*games];
                            for  (int c = 0; c<sets*games; c++) {
                                temp2[c] = scorecard[b-1][c];
                                scorecard[b-1][c]=scorecard[b][c];
                                scorecard[b][c]=temp2[c];
                            }

                            // Re-arrange the ROWS of the lastSetScore array
                            int virtual = lastSetScores[b-1];
                            lastSetScores[b-1] = lastSetScores[b];
                            lastSetScores[b] = virtual;

                            // Re-arranging the ROWS of the playerRanking array
                            int OrgPlayerID = playerRankings[b-1];
                            playerRankings[b-1] = playerRankings[b];
                            playerRankings[b] = OrgPlayerID;

                            // Decreasing the count of d
                            d--;
                            break;
                        }
                        // If (scorecard[b][d]>scorecard[b-1][d]) || (d == 0)) is always false
                        else {
                            break;
                        }
                    }
                }
            }
        }

        return playerRankings;

    }
    // This function gives the sum of the last set score
    private static int[] lastSetScores(int[][] scorecard, int sets, int games) {
        int[] lastSetScores;

        int numPlayers = scorecard.length;
        lastSetScores = new int[numPlayers];

        for (int i = 0; i < numPlayers; i++){
            int playerScore = 0;
            for (int j = (sets - 1) *games; j < (sets * games); j++){
               playerScore += scorecard[i][j];
            }
            lastSetScores[i] = playerScore;
        }

        return lastSetScores;
    }

}