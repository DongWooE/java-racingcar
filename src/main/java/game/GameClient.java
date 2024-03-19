package game;

import game.domain.*;
import game.view.InputView;

import java.util.List;

import static game.view.ResultView.printPlainMessage;
import static game.view.ResultView.printPlainMessages;

public class GameClient {

    private static final String EXECUTION_RESULT = "\n실행 결과";
    private static final String ASK_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ASK_GAME_COUNT_MESSAGE = "시도할 회수는 몇 회 인가요?";
    private static final String WINNER_POSTFIX = "가 최종 우승했습니다.";

    public static void main(String[] args) {
        List<Name> names = Name.fromNames(inputNames());
        TryNo gameCount = new TryNo(insertCount());
        printPlainMessage(EXECUTION_RESULT);
        NumberGenerator generator = new RandomNumberGenerator(10);
        Game game = new Game(generator, names);
        playGamesAndPrintResult(game, gameCount.getNumber());
        selectWinnersAndPrint(game.getCars());
    }

    private static String inputNames() {
        printPlainMessage(GameClient.ASK_CAR_NAME_MESSAGE);
        return InputView.insertValue();
    }

    private static int insertCount() {
        printPlainMessage(GameClient.ASK_GAME_COUNT_MESSAGE);
        return InputView.insertInt();
    }

    private static void playGamesAndPrintResult(Game game, int gameCount) {
        printPlainMessages(game.toStrings());
        for (int i = 1; i < gameCount; i++) {
            game.play();
            printPlainMessages(game.toStrings());
        }
    }

    private static void selectWinnersAndPrint(List<Car> cars) {
        printPlainMessage(new Winner().getWinners(cars) + WINNER_POSTFIX);
    }
}
