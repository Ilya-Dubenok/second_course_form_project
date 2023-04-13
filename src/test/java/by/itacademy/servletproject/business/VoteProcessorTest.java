package by.itacademy.servletproject.business;

import by.itacademy.servletproject.dto.IDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VoteProcessorTest {

    @Test
    @DisplayName("incrementVotesForPlayersOneTime")
    public void incrementVotesForPlayersOne() {

        VoteProcessor vp = new VoteProcessor();
        vp.incrementVotesForPlayers(new String[]{"2"});

        vp.getVotesForPlayersSorted().entrySet().forEach(
                x -> {
                    if (x.getKey().getId() == 2) {
                        Assertions.assertEquals(1, x.getValue());
                    } else {
                        Assertions.assertEquals(0, x.getValue());
                    }
                }

        );

    }


    @Test
    @DisplayName("incrementVotesForPlayersSeveralTimes")
    public void incrementVotesForPlayersSeveralTimes() {

        VoteProcessor vp = new VoteProcessor();
        vp.incrementVotesForPlayers(new String[]{"2", "2"});

        vp.getVotesForPlayersSorted().entrySet().forEach(
                x -> {
                    if (x.getKey().getId() == 2) {
                        Assertions.assertEquals(2, x.getValue());
                    } else {
                        Assertions.assertEquals(0, x.getValue());
                    }
                }

        );

    }


    @Test
    @DisplayName("incrementVotesForGenresOneTime")
    public void incrementVotesForGenresOne() {

        VoteProcessor vp = new VoteProcessor();
        vp.incrementVotesForGenres(new String[]{"2"});

        vp.getVotesForGenresSorted().entrySet().forEach(
                x -> {
                    if (x.getKey().getId() == 2) {
                        Assertions.assertEquals(1, x.getValue());
                    } else {
                        Assertions.assertEquals(0, x.getValue());
                    }
                }

        );

    }


    @Test
    @DisplayName("incrementVotesForGenresSeveralTimes")
    public void incrementVotesForGenresSeveral() {

        VoteProcessor vp = new VoteProcessor();
        vp.incrementVotesForGenres(new String[]{"2", "2"});

        vp.getVotesForGenresSorted().entrySet().forEach(
                x -> {
                    if (x.getKey().getId() == 2) {
                        Assertions.assertEquals(2, x.getValue());
                    } else {
                        Assertions.assertEquals(0, x.getValue());
                    }
                }

        );

    }

    @Test
    @DisplayName("getInfoWithCustomComparator")
    public void getInfoWithCustomComparator() {
        VoteProcessor voteProcessor = new VoteProcessor();
        voteProcessor.incrementVotesForPlayers(new String[]{"2","3","1","2"});
        List<IDTO> list = new ArrayList<>();
        voteProcessor.getVotesForPlayersSorted(
                Comparator.comparing(o -> o.getKey().getName())
        ).forEach((K, V) -> list.add(K));

        for (int i = 1; i < list.size(); i++) {
            Assertions.assertTrue(list.get(i).getName().compareTo(list.get(i - 1).getName()) > 0);

        }

    }
}
