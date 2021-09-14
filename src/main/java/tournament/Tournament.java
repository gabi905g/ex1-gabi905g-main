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
     * @return a list of players in order of their performance (best to worst)
     */
    public static int[] rankPlayers(int[][] scorecard, int sets, int games) {
        int[] playerRankings;
        // TODO: Your implementation goes here

        int[] lastSetScores = lastSetScores(scorecard, sets, games);
        for (int i = 0; i < lastSetScores.length; i++) {
            System.out.printf("Player %d: %d\n", i, lastSetScores[i]);
        }

        return null;
    }

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
