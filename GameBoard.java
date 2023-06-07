import java.util.Scanner;  
import java.util.ArrayList; // new add
import java.util.Collections;
import java.io.File; // new add
import java.io.FileNotFoundException;
public class GameBoard{

    Scanner sc = new Scanner(System.in);
    public static String[][] gameBoard =          {{" - ", " - ", " - ", " - ", " - "},
    {" - ", " - ", " - ", " - ", " - "},
    {" - ", " - ", " - ", " - ", " - "},
    {" - ", " - ", " - ", " - ", " - "},
    {" - ", " - ", " - ", " - ", " - "}};

    public int gameRow = 0;
    public boolean winGame;
    public String secretWord;
    public String guess;
    ArrayList<String> wrongLetters = new ArrayList<String>();

    public void GameBoardShow()
    {
        for(int row = 0; row < 5; row++)
        {
            for(int col = 0; col < 5; col++)
            {
                System.out.print(gameBoard[row][col]);
            }
            System.out.println("");
        }
    }

    public void NewWord()
    {
        try (//System.out.println("Enter a new secret word");
                //secretWord = sc.nextLine();
                //System.out.println("The secret word is " + secretWord);
        Scanner scanner = new Scanner(new File("Wordlist.txt"))) {
            ArrayList<String> wordList = new ArrayList<String>();
            while(scanner.hasNext())
            {
                // add scanner.nextLine() words to array 
                wordList.add(scanner.nextLine());
            }
            Collections.shuffle(wordList);
            secretWord = wordList.get(0).toLowerCase();
            System.out.println("The secret word is " + secretWord);
            //secretWord = "motor";
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(" \n \n \n \n \n \n \n \n \n \n \n \n");
    }

    public void CheckWord()
    {
        
        //System.out.println("You guessed "+ guess);

        while(!winGame && gameRow<5)
        {
        System.out.println("Enter a guess");
        guess = sc.nextLine().toLowerCase();
        for(int i =0; i < secretWord.length(); i++)
        {
            for(int k = 0; k < guess.length(); k++)
            {
               //System.out.println(secretWord.substring(i,i+1));
               //System.out.println(guess.substring(k,k+1));
      
                //if (secretWord.charAt(i)==guess.charAt(k))
                if(secretWord.substring(i,i+1).equals(guess.substring(k,k+1)))
                {
                   if(i==k)
                   {
                    gameBoard[gameRow][k] = " " + secretWord.substring(k,k+1).toUpperCase() + " ";
                    //break;

                    for (int z = i; z < guess.length(); z++)
                    {
                        if(secretWord.substring(i,i+1).equals(guess.substring(z,z+1)))
                        {
                            gameBoard[gameRow][i] = " " + secretWord.substring(k,k+1).toUpperCase() + " ";
                        }
                    }

                   }
                   else if (!gameBoard[gameRow][k].equals(" "+secretWord.substring(i,i+1).toUpperCase()+" "))
                   {
                        gameBoard[gameRow][k] = " " + guess.substring(k,k+1).toLowerCase() + " ";
                        //break;
                   }
                   
                }
                if(!secretWord.contains(guess.substring(k,k+1)) && !wrongLetters.contains(guess.substring(k,k+1)) && !winGame)
                {
                    wrongLetters.add(guess.substring(k,k+1));
                }
               
            }
        }
        if (gameBoard[gameRow][0].equals(" " + secretWord.substring(0,1).toUpperCase()+ " "))
        {
            if(gameBoard[gameRow][1].equals(" " + secretWord.substring(1,2).toUpperCase()+ " "))
            {
                if(gameBoard[gameRow][2].equals(" " + secretWord.substring(2,3).toUpperCase()+ " "))
                {
                    if (gameBoard[gameRow][3].equals(" " + secretWord.substring(3,4).toUpperCase()+ " "))
                    {
                        if(gameBoard[gameRow][4].equals(" " + secretWord.substring(4,5).toUpperCase()+ " "))
                        {
                            winGame=true;
                        }
                    }
                }
            }
        }
        System.out.println( "\n-------------------\n");
        GameBoardShow();
        if(!winGame)
        {
            System.out.println("The word does not contain: " + wrongLetters);
        }
        if(winGame)
        {
            System.out.println("Congrats! You won! Press \"Run\" to play again");
        }
        gameRow++;
        

        
    } 

    }
}
