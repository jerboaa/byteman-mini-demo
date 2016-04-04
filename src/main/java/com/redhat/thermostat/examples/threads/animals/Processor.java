package com.redhat.thermostat.examples.threads.animals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Processor {
    
    private final String QUIT = "quit";
    private final String START = "start";
    private final String STOP = "stop";
    private final String CAT_PREFIX = "cat-";
    private final String DUCK_PREFIX = "duck-";
    private final Map<String, Animal> animals = new HashMap<>();
    private final PrintStream out;
    
    public Processor(PrintStream out) {
        this.out = out;
    }
    
    public void process() {
        printhelp();
        String cmdToken;
        String cmdArg;
        String line = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            line = scanner.nextLine().trim();
            String[] cmd = getTokens(line);
            if (cmd.length == 0) {
                continue;
            }
            if (cmd.length == 1 && cmd[0].equalsIgnoreCase(QUIT)) {
                shutdown();
                break;
            }
            if (cmd.length == 2) {
                cmdToken = cmd[0];
                cmdArg = cmd[1];
                if (cmdToken.equalsIgnoreCase(START)) {
                    Animal animal = createAnimal(cmdArg);
                    if (animal == null) {
                        continue;
                    }
                    if (!animals.containsKey(cmdArg)) {
                        animals.put(cmdArg, animal);
                    } else {
                        System.err.println(cmdArg + " already created and started. ignoring");
                        continue;
                    }
                    animal.start();
                }
                if (cmdToken.equalsIgnoreCase(STOP)) {
                    Animal animal = animals.remove(cmdArg);
                    if (animal == null) {
                        System.err.println("Animal '" + cmdArg + "' not alive!");
                        continue;
                    }
                    animal.terminate();
                    try {
                        animal.join();
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }
            } else {
                System.err.println("Unknown command tokens: " + line);
            }
        }
        scanner.close();
    }

    private void shutdown() {
        for (String animal: animals.keySet()) {
            Animal value = animals.get(animal);
            value.terminate();
        }
        System.out.println("bye now.");
    }

    private String[] getTokens(String line) {
        String[] tokens = line.split("\\s");
        List<String> result = new ArrayList<>();
        for (String t: tokens) {
            if (t.isEmpty()) {
                continue;
            }
            result.add(t);
        }
        return result.toArray(new String[]{});
    }

    private void printhelp() {
        System.out.println("Welcome!");
        System.out.println(" Use 'start duck-<name>' to start some duckling chatter.");
        System.out.println(" Use 'start cat-<name>' to start a cat.");
        System.out.println(" Use 'stop <animal>-<name>' to stop an animal.");
        System.out.println(" Use 'quit' to exit.");
    }

    private Animal createAnimal(String cmdArg) {
        if (cmdArg.startsWith(CAT_PREFIX)) {
            return new Cat(cmdArg, out);
        }
        if (cmdArg.startsWith(DUCK_PREFIX)) {
            return new Duck(cmdArg, out);
        }
        System.err.println("Unknown animal '" + cmdArg + "'");
        return null;
    }
}