// Даны два Deque, представляющие два целых числа. Цифры хранятся
// в обратном порядке и каждый из их узлов содержит одну цифру.
// 2) Сложите два числа и верните сумму в виде связанного списка.
// Одно или два числа могут быть отрицательными.

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Scanner;

public class second {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp866");
        System.out.print("Введите первое число: ");
        Deque<Integer> firstNumDeque = StringToDeque(sc.nextLine());
        System.out.println(firstNumDeque);
        System.out.print("Введите второе число: ");
        Deque<Integer> secondNumDeque = StringToDeque(sc.nextLine());
        System.out.println(secondNumDeque);
        sc.close();
        Summation(firstNumDeque, secondNumDeque);
    }

    public static Deque<Integer> StringToDeque(String input) {
        Deque<Integer> deque = new ArrayDeque<>();
        char[] inputChar = input.toCharArray();
        if (input.charAt(0) != '-') {
            for (char c : inputChar) {
                deque.addFirst(Integer.parseInt(String.valueOf(c)));
            }
        } else {
            deque.addFirst(Integer.parseInt(String.valueOf(input.charAt(1))) * -1);
            if (inputChar.length > 2) {
                for (int i = 2; i < inputChar.length; i++) {
                    deque.addFirst(Integer.parseInt(String.valueOf(inputChar[i])));
                }
            }
        }
        return deque;
    }

    public static Integer DequeToInteger(Deque<Integer> deque) {
        int result = 0;
        if (deque.getLast() > 0) {
            for (int i = deque.size() - 1; i >= 0; --i) {
                int pos = (int) Math.pow(10, i);
                result += deque.pollLast() * pos;
            }
        } else {
            result += deque.pollLast() * Math.pow(10, deque.size());
            for (int i = deque.size() - 1; i >= 0; --i) {
                int pos = (int) Math.pow(10, i);
                result += (deque.pollLast() * -1) * pos;
            }
        }
        return result;
    }

    public static void Summation(Deque<Integer> firstDeque, Deque<Integer> secondDeque) {
        LinkedList<Integer> resultLinkedList = new LinkedList<>();
        Integer result = DequeToInteger(firstDeque) + DequeToInteger(secondDeque);
        while (result != 0) {
            resultLinkedList.addFirst(result % 10);
            result /= 10;
        }
        System.out.print("Результат сложения: ");
        System.out.println(resultLinkedList);
    }
}
