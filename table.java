package org.example;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String source = "CACABABABCCCAABAC";

        System.out.println(hasRepeats(source, 4)); // true, тк ABAB встречается два раза
        System.out.println(hasRepeats(source, 5)); // false
    }

    public static boolean hasRepeats(String source, int size) {
        Set<LazyString> slices = new HashSet<>(); // множество всех подстрок длины size
        LazyString prev = null; // переменная для сохранения предыдущей подстроки

        for (int i = 0; i <= source.length() - size; i++) { // перебор всех мест старта подстроки
            LazyString slice;
            if (prev == null) {
                // Создаем первую подстроку через конструктор
                slice = new LazyString(source, i, i + size);
            } else {
                // Создаем остальные подстроки через сдвиг вправо
                slice = prev.shiftRight();
            }

            if (slices.contains(slice)) { // проверка на наличие повтора этой подстроки
                return true; // если уже встречали, значит повторы есть
            } else {
                slices.add(slice);  // иначе запоминаем подстроку и перебираем дальше
            }
            prev = slice; // обновляем переменную для предыдущей подстроки
        }
        return false; // если бы нашли, то вышли бы по return true, а значит повторов нет
    }
}

class LazyString {
    private String source; // ссылка на исходную строку
    private int start, end; // границы нашей подстроки
    private int hash; // запоминаем хеш чтобы не пересчитывать

    public LazyString(String source, int start, int end) {
        this.source = source;
        this.start = start;
        this.end = end;

        // Подсчет хеша
        for (int i = start; i < end; i++) {
            hash += source.charAt(i);
        }
    }

    public LazyString shiftRight() {
        // Создаем новую подстроку с новыми границами
        return new LazyString(source, start + 1, end + 1);
    }

    public int length() {
        return end - start;
    }

    public boolean equals(LazyString that) {
        if (this.length() != that.length()) {
            return false;
        }
        for (int i = 0; i < this.length(); i++) {
            if (this.source.charAt(this.start + i) != that.source.charAt(that.start + i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash; // хеш у нас всегда предпосчитан для каждого объекта, чтобы не тратить на это время
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyString that = (LazyString) o;
        return this.equals(that);
    }
}
