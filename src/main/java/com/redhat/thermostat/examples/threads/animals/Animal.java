package com.redhat.thermostat.examples.threads.animals;

import java.io.PrintStream;

class Animal extends Thread {
    
    private final String sound;
    private boolean terminated = false;
    private final PrintStream out;
    
    Animal(String threadName, String sound, PrintStream out) {
        super(threadName);
        this.sound = sound;
        this.out = out;
    }
    
    @Override
    public void run() {
        while (!terminated) {
            doSound();
        }
    }

    private void doSound() {
        out.println(getName() + " " + sound);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
    }
    
    public void terminate() {
        this.terminated = true;
    }
}