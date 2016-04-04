package com.redhat.thermostat.examples.threads.animals;

import java.io.PrintStream;

class Duck extends Animal {
    
    Duck(String thName, PrintStream out) {
        super(thName, "quack!", out);
    }
}