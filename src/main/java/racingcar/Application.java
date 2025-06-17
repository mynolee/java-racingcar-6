package racingcar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    static int tryTimes;
    static String[] names;
    static List<String> carNames = new ArrayList<>();
    static Map<String, Integer> carScores = new LinkedHashMap<>();
    static List<String> winnersNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        printStartMessage();
        getName();
        isValidCarName(names);
        nameCar(names);
        setInitialScores(names);
        askTryTimes();
        isValidTryTimes(tryTimes);
        printRacingGameResult();
        getWinnersNames();
        printWinners(winnersNames);
    }

    private static void printStartMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    private static void getName() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        names = input.split(",");
    }

    private static void isValidCarName(String[] getNames) {

        for (String name : getNames) {
            name = name.trim();
            if (name.length() > 5 || name.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하만 가능합니다.");
            }
        }
    }

    private static void nameCar(String[] isValidCarName) {
        carNames.addAll(Arrays.asList(isValidCarName));
    }

    private static void setInitialScores(String[] isValidCar) {
        for (String name : isValidCar) {
            name = name.trim();
            carScores.put(name, 0);
        }
    }


    private static void askTryTimes() throws IOException {
        System.out.println("시도할 회수는 몇회인가요?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tryTimes = Integer.parseInt(br.readLine());

    }

    private static void isValidTryTimes(int isValidCounts) {
        try {
            if (isValidCounts <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 양의 정수여야 합니다.");
        }
    }

    private static void printRacingGameResult() {
        System.out.println("\n실행 결과");
        for (int i = 0; i < tryTimes; i++) {
            for (String name : carNames) {
                if (shouldStop()) {
                    carScores.put(name, carScores.get(name) + 1);
                }
                printCarProgress(name);
            }
            System.out.println();
        }
    }

    private static void printCarProgress(String name) {
        int score = carScores.get(name);
        System.out.print(name + " : ");
        for (int i = 0; i < score; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static boolean shouldStop() {
        return getRandomNumber() >= 4;
    }

    private static int getRandomNumber() {
        return (int) (Math.random() * 10);
    }

    private static void getWinnersNames() {
        int maxScore = Collections.max(carScores.values());

        for (Map.Entry<String, Integer> entry : carScores.entrySet()) {
            if (entry.getValue() == maxScore) {
                winnersNames.add(entry.getKey());
            }
        }
    }

    private static void printWinners(List<String> winners) {

        System.out.println("\n최종 우승자 : " + String.join(", ", winners));
    }
}
