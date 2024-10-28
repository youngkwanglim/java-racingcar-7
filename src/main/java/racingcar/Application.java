package racingcar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Car> cars = InputHandler.inputCarNames();
        int tryCount = InputHandler.inputTryCount();

        gameStart(cars, tryCount);

        printWinner(cars);
    }

    private static void gameStart(List<Car> cars, int tryCount) {
        System.out.println("실행 결과");

        for (int i = 0; i < tryCount; i++) {
            for (Car car : cars) {
                car.move();
            }

            printProgress(cars);
        }
    }

    private static void printProgress(List<Car> cars) {
        for (Car car : cars) {
            System.out.print(car.getName() + " : ");
            for (int i = 0; i < car.getLocation(); i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printWinner(List<Car> cars) {
        int maxLocation = findMaxLocation(cars);

        List<String> winners = findWinners(cars, maxLocation);

        String finalWinner = String.join(", ", winners);
        System.out.println("최종 우승자 : " + finalWinner);
    }

    private static List<String> findWinners(List<Car> cars, int maxLocation) {
        List<String> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.getLocation() == maxLocation) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private static int findMaxLocation(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getLocation))
                .get()
                .getLocation();
    }
}
