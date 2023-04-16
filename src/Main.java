import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /* Реализовать шифрование методом Цезаря */
        String message = "abc";
        int offset = 1;
        System.out.println("Source message: " + message + ", encrypted message: " + Caesar.encrypt(message, offset));



        /*  Дан некоторый массив из 0 и 1, к примеру int[] numbers = [0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0],
        необходимо отсортировать массив максимально быстрым способом */
        int[] numbersSort = {0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0};
        System.out.println("Array before sort: " + Arrays.toString(numbersSort));
        sort(numbersSort);
        System.out.println("Array after sort: " + Arrays.toString(numbersSort));



        /* Дан массив строк, необходимо вывести все дубликаты, при этом дубликатом являются те строки, которые содержат
        одни и те же символы, но могут быть просто в другом порядке */
        String[] words = new String[]{"самолет", "доска", "док", "летсамо", "скадо", "самокат"};
        AnagramDictionary dictionary = new AnagramDictionary(words);
        System.out.println(dictionary);

        Set<List<String>> set = new HashSet<>();
        for (String testWord : dictionary.getWords()) {
            List<String> anagrams = dictionary.getAnagrams(testWord);
            if (anagrams.size() > 1) {
                set.add(anagrams);
            }
        }
        System.out.println("Duplicates: " + set);



        /* Известно, что числовой массив состоит из пар одинаковых чисел, но есть одно число в единственном экземпляре,
        необходимо найти это число за один проход по массиву */
        int[] numbersWithUnique = {3, 4, 0, 0, 2, 1, 4, 3, 1};
        System.out.println("Array with unique: " + Arrays.toString(numbersWithUnique));
        System.out.println("Unique: " + xor(numbersWithUnique));



        /* Вам даны несколько монет номиналом от 1 до 9 (монеты могут повторяться). Ваша функция должна вернуть
        минимальную положительную целую сумму, которую нельзя заплатить этими монетами без сдачи.
        Если, например, у Вас есть 2 монеты номиналом 3, 5, функция должна вернуть 1, т.к. если заплатим монетой 3,
        то сдача будет 2, а если 5 - сдача будет 4. Без сдачи нельзя.
        Если у Вас есть 3 монеты номиналом 1, 2, 3, функция должна вернуть 7, т.к. все числа, что меньше,
        можно оплатить этими монетами без сдачи */
        int[] coins = {1, 2, 3};
        Wallet wallet = new Wallet(coins);
        System.out.println(wallet);
        System.out.println("Minimum value without change: " + wallet.withoutChange());
    }

    public static void sort(int[] numbers) {
        int current = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1) continue;
            numbers[i] = 1;
            numbers[current] = 0;
            current++;
        }
    }

    public static int xor(int[] numbers) {
        int unique = 0;
        for (int i : numbers) {
            unique = unique ^ i;
        }
        return unique;
    }
}

abstract class Caesar {
    public static String encrypt(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            result.append((char) (character + offset));
        }
        return result.toString();
    }
}

class AnagramDictionary {
    private final String[] words;
    private final String[] normalizedWords;

    public AnagramDictionary(String[] words) {
        this.words = words;
        this.normalizedWords = Arrays.stream(words).map(this::normalize).distinct().toArray(String[]::new);
    }

    public String[] getWords() {
        return words;
    }

    private String normalize(String word) {
        return word.chars()        // IntStream
                .sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public List<String> getAnagrams(String testWord) {
        ArrayList<String> result = new ArrayList<>();
        if (testWord == null || testWord.equals("")) {
            return result;
        }

        String normalizedTestWord = normalize(testWord);
        if (Arrays.asList(normalizedWords).contains(normalizedTestWord)) {
            for (String word : words) {
                if (normalizedTestWord.equals(normalize(word))) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "AnagramDictionary{" +
                "words=" + Arrays.toString(words) +
                ", normalizedWords=" + Arrays.toString(normalizedWords) +
                '}';
    }

}

class Wallet {
    private final int[] coins;
    private int total;

    public Wallet(int[] coins) {
        this.coins = Arrays.stream(coins).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        this.total = Arrays.stream(coins).sum();
    }

    public int withoutChange() {
        for (int i = 1; i <= total + 1; i++) {
            //System.out.println("i: " + i);
            if (check(i)) {
                return i;
            }
        }
        return 0;
    }

    private boolean check(int left) {
        for (int coin : coins) {
            if (coin > left) continue;
            if (coin == left) return false;

            left -= coin;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "coins=" + Arrays.toString(coins) +
                ", total=" + total +
                '}';
    }
}