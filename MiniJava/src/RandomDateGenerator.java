// This module generates a random date.

import java.time.LocalDate;
import java.util.Random;

public class RandomDateGenerator {

    public static LocalDate generateRandomDate() {
        // Create a random number generator
        Random random = new Random();

        // Generate a random date between 1900 and 2023
        return LocalDate.of(
            random.nextInt(2023 - 2000) + 2000,
            random.nextInt(12) + 1,
            random.nextInt(28) + 1
        );
    }
    public static void main(String[] args) {
        // Generate a random date
        LocalDate randomDate = RandomDateGenerator.generateRandomDate();

        // Print the random date
        System.out.println(randomDate);
    }
}
