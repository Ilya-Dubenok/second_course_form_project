package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.ArtistCreateDTO;
import by.itacademy.servletproject.core.dto.ArtistDTO;

import java.util.Map;

public interface IArtistService extends ICRUDService<ArtistDTO, ArtistCreateDTO> {

     String validate(Integer[] artistIDS);

     void putVotes(Integer[] artistIDS) throws IllegalArgumentException;

     Map<ArtistDTO, Integer> getSortedVotesInfo();




}
