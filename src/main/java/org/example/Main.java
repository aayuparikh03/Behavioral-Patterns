package org.example;

import java.util.ArrayList;
import java.util.List;

// Memento: Represents the saved state of the text
class TextMemento {
    private final String state;

    public TextMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator: The text editor whose state needs to be saved and restored
class TextEditor {
    private StringBuilder content = new StringBuilder();

    // Append text to the editor
    public void append(String text) {
        content.append(text);
    }

    // Save the current state as a memento
    public TextMemento save() {
        return new TextMemento(content.toString());
    }

    // Restore a saved state from a memento
    public void restore(TextMemento memento) {
        content = new StringBuilder(memento.getState());
    }

    // Get the current content
    public String getContent() {
        return content.toString();
    }
}

// Caretaker: Manages saved states (mementos)
class Caretaker {
    private final List<TextMemento> mementoList = new ArrayList<>();
    private int currentIndex = -1;

    // Save a new state
    public void saveState(TextMemento memento) {
        // Remove any "redo" states
        while (mementoList.size() > currentIndex + 1) {
            mementoList.remove(mementoList.size() - 1);
        }
        mementoList.add(memento);
        currentIndex++;
    }

    // Undo to the previous state
    public TextMemento undo() {
        if (currentIndex <= 0) {
            System.out.println("No previous state to undo to.");
            return null;
        }
        return mementoList.get(--currentIndex);
    }

    // Redo to the next state
    public TextMemento redo() {
        if (currentIndex >= mementoList.size() - 1) {
            System.out.println("No next state to redo to.");
            return null;
        }
        return mementoList.get(++currentIndex);
    }
}

// Client: Demonstrates usage of the Memento pattern
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        // Initial state
        editor.append("Hello");
        caretaker.saveState(editor.save());

        editor.append(" World");
        caretaker.saveState(editor.save());

        editor.append("! Welcome to the Memento Pattern.");
        caretaker.saveState(editor.save());

        System.out.println("Current Content: " + editor.getContent());

        // Undo
        editor.restore(caretaker.undo());
        System.out.println("After Undo: " + editor.getContent());

        editor.restore(caretaker.undo());
        System.out.println("After Undo: " + editor.getContent());

        // Redo
        editor.restore(caretaker.redo());
        System.out.println("After Redo: " + editor.getContent());
    }
}
