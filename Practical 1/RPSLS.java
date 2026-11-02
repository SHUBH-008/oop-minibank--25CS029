import java.util.Random;
import java.util.Scanner;

public class RPSLS {

    public enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    public static int winner(Move a, Move b) {
        if (a == b) {
            return 0;
        }

        boolean aBeatsB = switch (a) {
            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD);
            case PAPER -> (b == Move.ROCK || b == Move.SPOCK);
            case ROCK -> (b == Move.LIZARD || b == Move.SCISSORS);
            case LIZARD -> (b == Move.SPOCK || b == Move.PAPER);
            case SPOCK -> (b == Move.SCISSORS || b == Move.ROCK);
        };

        return aBeatsB ? 1 : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Move[] moves = Move.values();

        int playerScore = 0;
        int computerScore = 0;
        final int TOTAL_ROUNDS = 5;

        System.out.println("=== Rock-Paper-Scissors-Lizard-Spock ===");
        System.out.println("Rules:");
        System.out.println(" - Scissors cuts Paper, decapitates Lizard");
        System.out.println(" - Paper covers Rock, disproves Spock");
        System.out.println(" - Rock crushes Lizard, crushes Scissors");
        System.out.println(" - Lizard poisons Spock, eats Paper");
        System.out.println(" - Spock smashes Scissors, vaporizes Rock\n");

        for (int round = 1; round <= TOTAL_ROUNDS; round++) {
            System.out.println("--- Round " + round + " of " + TOTAL_ROUNDS + " ---");
            Move computerMove = moves[random.nextInt(moves.length)];

            Move playerMove = null;
            while (playerMove == null) {
                System.out.print("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
                String input = scanner.next().toUpperCase().trim();
                try {
                    playerMove = Move.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move! Try again.");
                }
            }

            int result = winner(playerMove, computerMove);
            System.out.println("Player: " + playerMove + " vs Computer: " + computerMove);

            if (result == 1) {
                playerScore++;
                System.out.println("Round Winner: You win this round!");
            } else if (result == -1) {
                computerScore++;
                System.out.println("Round Winner: Computer wins this round!");
            } else {
                System.out.println("Round Winner: It's a tie!");
            }
            System.out.println("Current Score -> Player: " + playerScore + " | Computer: " + computerScore + "\n");
        }

        System.out.println("=== Game Over Summary ===");
        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "–" + computerScore);
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "–" + playerScore);
        } else {
            System.out.println("Series tied " + playerScore + "–" + computerScore);
        }

        scanner.close();
    }
}
