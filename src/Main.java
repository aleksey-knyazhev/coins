import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
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
        System.out.println(set);


        /* Известно, что числовой массив состоит из пар одинаковых чисел, но есть одно число в единственном экземпляре,
        необходимо найти это число за один проход по массиву */
        int[] numbersUnique = {3, 4, 0, 0, 2, 1, 4, 3, 1};
        System.out.println("Unique: " + xor(numbersUnique));



        /* Вам даны несколько монет номиналом от 1 до 9 (монеты могут повторяться). Ваша функция должна вернуть
        минимальную положительную целую сумму, которую нельзя заплатить этими монетами без сдачи.
        Если, например, у Вас есть 2 монеты номиналом 3, 5, функция должна вернуть 1, т.к. если заплатим монетой 3,
        то сдача будет 2, а если 5 - сдача будет 4. Без сдачи нельзя.
        Если у Вас есть 3 монеты номиналом 1, 2, 3, функция должна вернуть 7, т.к. все числа, что меньше,
        можно оплатить этими монетами без сдачи */


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

class AnagramDictionary {
    private final String[] words;
    private final String[] normalizedWords;

    public AnagramDictionary(String[] words) {
        /*
           Используйте слова для инициализации словаря
         */
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
        /*
            Необходимо вернуть все доступные анаграммы для заданного слова
         */
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
    private final int[] coins = {1, 2, 3};



}