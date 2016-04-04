package com.redhat.thermostat.examples.threads.animals;

import java.io.PrintStream;

class Cat extends Animal {
    
    Cat(String thName, PrintStream out) {
        super(thName, "meouw!", out);
    }
}