package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Baseball baseball = new Baseball();
        do {
            baseball.pickRandomNumber();

        } while (true);
    }
}

class Baseball {
    private List<Integer> computerNumber = List.of(0, 0, 0);
    private List<Integer> playerNumber = List.of(0, 0, 0);
    private List<Integer> baseballCount = List.of(0, 0);

    public void pickRandomNumber() {
        for (int i = 0; i < 3;) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computerNumber.contains(randomNumber)) {
                computerNumber.set(i, randomNumber);
                i++;
            }
        }
    }

    public void getPlayerNumber() {
        String getPlayerNumber = null;
        try {
            InputStreamReader ir = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(ir);
            System.out.print("숫자를 입력해주세요 :");
            getPlayerNumber = br.readLine();
        } catch (Exception e) { }
        playerNumberList(getPlayerNumber);
    }
    public void playerNumberList(String getPlayerNumber) {
        int playerNum = Integer.parseInt(getPlayerNumber);
        for (int i = 0; i < 3; i++) {
            playerNumber.set(i, (playerNum % 10));
            playerNum /= 10;
        }
    }

    public void comparisonPlayerComputer() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                comparisonNumber(playerNumber.get(i), computerNumber.get(j), i, j);
            }
        }
    }
    public void comparisonNumber(int playerNumber, int computerNumber, int i, int j) {
        if (playerNumber == computerNumber) {
            if (i == j) {
                countBaseball(0);
            } else {
                countBaseball(1);
            }
        }
    }
    public void countBaseball(int i) {
        int count;
        count = baseballCount.get(i) + 1;
        baseballCount.set(i, count);
    }

    public void printBaseballScore() {
        if (baseballCount.get(0) == 0 && baseballCount.get(1) == 0) {
            System.out.print("낫싱");
        } else {
            printBall();
            printStrike();
        }
    }
    public void printStrike() {
        if (baseballCount.get(0) != 0) {
            System.out.printf("%d스트라이크", baseballCount.get(0));
        }
    }
    public void printBall() {
        if (baseballCount.get(1) != 0) {
            System.out.printf("%d볼 ", baseballCount.get(1));
        }
    }

    public boolean rightAnswer() {
        boolean success = false;
        if (baseballCount.get(0) == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            success = true;
        }
        return success;
    }
    public boolean gameRestartOrEnd() {
        String optionNumber = null;
        boolean option = false;
        try {
            InputStreamReader ir = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(ir);
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            optionNumber = br.readLine();
        } catch (Exception e) { }

        if (optionNumber.equals("1")) {
            option = true;
        }
        return option;
    }
}