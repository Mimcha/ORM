package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph<String> socialNetwork = new Graph<>(); // создание графа

        // создание вершин-страниц социальной сети
        Vertex<String> petya = socialNetwork.createVertex("Петя");
        Vertex<String> olya = socialNetwork.createVertex("Оля");
        Vertex<String> dasha = socialNetwork.createVertex("Даша");
        Vertex<String> katya = socialNetwork.createVertex("Катя");

        // создание рёбер - добавления в друзья
        socialNetwork.createEdge(petya, olya);
        socialNetwork.createEdge(olya, dasha);
        socialNetwork.createEdge(dasha, petya);
        socialNetwork.createEdge(dasha, katya);

        Vertex<String> pasha = socialNetwork.createVertex("Паша");
        Vertex<String> kostya = socialNetwork.createVertex("Костя");

        socialNetwork.createEdge(pasha, kostya);

        // поиск достижимости между анкетами
        System.out.println(socialNetwork.isConnected(petya, olya)); // true
        System.out.println(socialNetwork.isConnected(petya, katya)); // true
        System.out.println(socialNetwork.isConnected(pasha, kostya)); // true
        System.out.println(socialNetwork.isConnected(dasha, kostya)); // false
    }
}

class Vertex<T> {
    private T value;
    private List<Vertex> adjacent = new ArrayList<>(); // список смежности

    public Vertex(T value) {
        this.value = value;
    }

    public List<Vertex> getAdjacent() {
        return adjacent;
    }

    public void addAdjacent(Vertex vertex) {
        adjacent.add(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return value.equals(vertex.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

class Graph<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> a, Vertex<T> b) {
        a.getAdjacent().add(b); // a добавляет b в друзья
        b.getAdjacent().add(a); // b добавляет a в друзья
    }

    public boolean isConnected(Vertex<T> a, Vertex<T> b) {
        return dfsFind(a, b, new HashSet<>()); // запускаем обход в глубину
    }

    private boolean dfsFind(Vertex<T> v, Vertex<T> target, Set<Vertex<T>> visited) {
        // Если текущая вершина равна целевой, то путь найден
        if (v.equals(target)) {
            return true;
        }

        // Отмечаем текущую вершину как посещенную
        visited.add(v);

        // Перебираем всех соседей текущей вершины
        for (Vertex adj : v.getAdjacent()) {
            if (!visited.contains(adj)) { // если сосед еще не посещен
                if (dfsFind(adj, target, visited)) { // рекурсивно ищем путь
                    return true; // если путь найден, возвращаем true
                }
            }
        }

        // Если ни один из соседей не привел к целевой вершине, возвращаем false
        return false;
    }
}
