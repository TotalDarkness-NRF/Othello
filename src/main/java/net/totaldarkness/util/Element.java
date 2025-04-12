package net.totaldarkness.util;

/**
 * The Element interface representing an element that can accept a Visitor
 * allowing operations to be performed on elements;
 */
public interface Element {
    void accept(Visitor visitor);
}