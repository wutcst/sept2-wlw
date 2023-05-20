package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
            }
        }

        Command command = commands.get(word1);
        if(command != null) {
            command.setSecondWord(word2);
        }
        return command;
    }

    public void showCommands()
    {
        commands.showAll();
    }
}

