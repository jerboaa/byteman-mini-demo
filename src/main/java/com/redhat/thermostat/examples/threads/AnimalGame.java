package com.redhat.thermostat.examples.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.redhat.thermostat.examples.threads.animals.Processor;

public class AnimalGame {
    
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fout = new FileOutputStream("out.log")) {
            Processor p = new Processor(new PrintStream(fout));
            p.process();
        }
    }

}
