package org.example;

// Iterator Interface
interface Iterator<T> {
    boolean hasNext(); // Check if there are more elements
    T next();          // Get the next element
}

// Aggregate Interface
interface Collection<T> {
    Iterator<T> createIterator(); // Return an iterator
}

// Concrete Iterator
class BookIterator implements Iterator<String> {
    private String[] books;
    private int position = 0;

    public BookIterator(String[] books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return position < books.length;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return books[position++];
    }
}

// Concrete Aggregate
class BookCollection implements Collection<String> {
    private String[] books;

    public BookCollection(String[] books) {
        this.books = books;
    }

    @Override
    public Iterator<String> createIterator() {
        return new BookIterator(books);
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        String[] books = {"Design Patterns", "Clean Code", "Refactoring", "Effective Java"};

        BookCollection bookCollection = new BookCollection(books);
        Iterator<String> iterator = bookCollection.createIterator();

        System.out.println("Book List:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
