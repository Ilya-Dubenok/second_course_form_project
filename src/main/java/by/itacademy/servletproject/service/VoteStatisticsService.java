package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.*;
import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.api.IVoteService;
import by.itacademy.servletproject.service.api.IVoteStatisticsService;
import by.itacademy.servletproject.service.factory.GenreServiceFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VoteStatisticsService implements IVoteStatisticsService {

    private final IVoteService voteService;
    private final IGenreService genreService;
    private final IArtistService artistService;

    public VoteStatisticsService(IVoteService voteService, IGenreService genreService, IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public StatisticDTO getTop() {
        Map<String, Integer> genreTop = getGenreTop();
        Map<String, Integer> artistTop = getArtistTop();
        Map<LocalDateTime, String> aboutTop = getAboutTop();
        StatisticDTO dto = new StatisticDTO(genreTop, artistTop, aboutTop);
        return dto;
    }

    @Override
    public Map<String, Integer> getGenreTop() {
        List<VoteDTO> voteDTOS = voteService.get();

        List<GenreDTO> genreDTOList = genreService.get();
        Map<GenreDTO, Integer> collect1 = genreDTOList.stream().collect(Collectors.toMap(
                x -> x, x -> 0
        ));
        voteDTOS.stream().flatMap(x -> Arrays.stream(x.getGenres())).forEach(
                x -> collect1.computeIfPresent(genreService.get(x), (K, V) -> V + 1)
        );


        Map<String, Integer> res = new LinkedHashMap<>();
        collect1.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                .forEach(x -> res.put(
                        x.getKey().getName(), x.getValue())
                );


        return res;

    }

    @Override
    public Map<String, Integer> getArtistTop() {
        List<VoteDTO> voteDTOS = voteService.get();
        List<ArtistDTO> artistDTOS = artistService.get();
        Map<ArtistDTO, Integer> collect1 = artistDTOS.stream().collect(Collectors.toMap(
                x -> x, x -> 0
        ));

        voteDTOS.stream().map(VoteDTO::getArtist).forEach(
                x -> collect1.computeIfPresent(artistService.get(x), (K, V) -> V + 1)
        );


        Map<String, Integer> res = new LinkedHashMap<>();
        collect1.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                .forEach(x -> res.put(
                        x.getKey().getName(), x.getValue())
                );


        return res;
    }

    @Override
    public Map<LocalDateTime, String> getAboutTop() {
        List<VoteDTO> voteDTOS = voteService.get();

        Map<LocalDateTime, String> res = new LinkedHashMap<>();

        voteDTOS
                .stream()
                .sorted(
                        (o1, o2) -> o2.getTimeOfVote().compareTo(o1.getTimeOfVote())
                )
                .forEach(
                        x -> res.put(x.getTimeOfVote(), x.getAbout())
                );

        return res;

    }
}
