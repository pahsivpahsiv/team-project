package use_case.set_check;

import entity.CardStuff;
import entity.GameState;
import entity.Player;
import use_case.no_sets.NormalSetChecker;
import use_case.no_sets.SetCheckerStrategy;
import use_case.no_sets.SetsOutputData;

import java.util.ArrayList;

/**
 * The Set Check Interactor.
 */
public class SetCheckInteractor implements SetCheckInputBoundary{

    private final SetCheckOutputBoundary setCheckPresenter;
    private final CardStuff cardStuff;
    private final SetCheckerStrategy setChecker;
    private final GameState gameState;

    public SetCheckInteractor(SetCheckOutputBoundary setCheckPresenter,
                              GameState gameState) {
        this.setCheckPresenter = setCheckPresenter;
        this.gameState = gameState;
        this.cardStuff = gameState.getCardStuff();

        if (this.gameState.getMode().equals(GameState.GameMode.ANTI_SET)) {
            this.setChecker = new AntiSetChecker(this.cardStuff);
        } else if (this.gameState.getMode().equals(GameState.GameMode.GHOST_SET)) {
            this.setChecker = new GhostSetChecker(this.cardStuff);
        } else {
            this.setChecker = new NormalSetChecker(this.cardStuff);
        }
    }

    @Override
    public void execute(SetCheckInputData inputData) {
        boolean result = setChecker.checkSet(inputData.getCards());
        if (result) {
            ArrayList<Integer> oldField = new ArrayList<>(inputData.getCards());
            ArrayList<Integer> newField = cardStuff.replaceCards(inputData.getCards());
            ArrayList<Integer> newCards = new ArrayList<>();
            for (int id : newField) {
                if (!oldField.contains(id)) {
                    newCards.add(id);
                }
            }

            Player player = gameState.getPlayer(inputData.getId());
            player.incrementScore();

            SetCheckOutputData output = new SetCheckOutputData(newField, newCards,
                    player.getScore(), player.getId());
            setCheckPresenter.prepareSuccessView(output);
        } else {
            if (this.gameState.getMode().equals(GameState.GameMode.ANTI_SET)) {
                setCheckPresenter.prepareFailView("This is not an anti-set.");
            } else if (this.gameState.getMode().equals(GameState.GameMode.GHOST_SET)) {
                setCheckPresenter.prepareFailView("This is not a ghost set.");
            } else {
                setCheckPresenter.prepareFailView("This is not a set.");
            }
        }
    }
}
