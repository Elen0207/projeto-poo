package com.example.main;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandProducer {
    private List<CommandExecution> executionList = new ArrayList<>();

    public void addExecution(CommandExecution exe) {
        executionList.add(exe);
    }

    public void executeCommand(String command) {
        for (CommandExecution exe : executionList) {
            exe.execute(command);
        }
    }
}