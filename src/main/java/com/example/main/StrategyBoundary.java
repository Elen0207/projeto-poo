package com.example.main;

import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;

public interface StrategyBoundary {
    Pane render() throws FileNotFoundException;
}