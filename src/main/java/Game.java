import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    public static String[] scanFilms() {
        try {
            //read a file films.txt
            File file = new File("films.txt");
            Scanner fileScanner = new Scanner(file);

            String allMovies = "";


            //
            while (fileScanner.hasNextLine()) {
                //if line is not blank add a line and semicolon
                String line = fileScanner.nextLine();
                if (!line.isBlank()) {
                    allMovies += line;
                    allMovies += ",";
                }

            }

            //split all String by semicolons
            //count number of films
            return allMovies.split(",");

        } catch (FileNotFoundException exception) {
            System.out.println("File wasn't found.");
            return new String[]{};
        }

    }


    /**
     * @param films array of films to choose from
     * @return random film from array
     */
    public static String getRandomFilm(String[] films) {

            int indexOfFilm = (int) (Math.random() * (films.length-1));
            return films[indexOfFilm];

    }

    public static String coverSigns(String randomFilm) {

        String hiddenFilm = "";

        for (int i = 0; i < randomFilm.length(); i++) {

            char space = randomFilm.charAt(i);

            if (space == ' ') {
                hiddenFilm += " ";
            } else {
                hiddenFilm += "_";
            }
        }

        System.out.println("Guess that title of film.");
        System.out.println(hiddenFilm);
        return hiddenFilm;

    }

    public static void guessLetter(String filmTitle, String covered) {

        Scanner scanner = new Scanner(System.in);

        try {
            int points = 10;
            boolean win = false;

            while (!win) {

                System.out.println("You have " + points + " chance of mistake. Chose one letter:");
                String guess = scanner.nextLine();
                if (filmTitle.contains(guess)) {

                    char[] coveredFilmsChars = covered.toCharArray();

                    for (int j = 0; j < filmTitle.length(); j++) {

                        char charFilmTitleInIndexJ = filmTitle.charAt(j);
                        char guessedChar = guess.charAt(0);
                        if (charFilmTitleInIndexJ == guessedChar) {
                            // change char under j index in coveredFilmsChars on guess char
                            coveredFilmsChars[j] = guessedChar;
                        }

                    }
                    // array as a String assign as new version of covered
                    covered = String.valueOf(coveredFilmsChars);
                    if (covered.equals(filmTitle)) {
                        win = true;

                    }

                } else {

                    points--;
                    if (points == 0) {
                        break;
                    }
                }

                System.out.println(covered);



            }
            if (win) {
                System.out.println("You Win! The chosen film is: " + filmTitle);
            } else {
                System.out.println("You lose. The chosen film is: " + filmTitle);
            }


        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("You push wrong key. Try again.");

        }

    }

    public static void main(String[] args) {

        String[] allFilms = scanFilms();
        String randomFilm = getRandomFilm(allFilms);
        //System.out.println(randomFilm);

        String coveredFilm = coverSigns(randomFilm);

        guessLetter(randomFilm, coveredFilm);

    }
}