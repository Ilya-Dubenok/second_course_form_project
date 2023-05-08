package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.core.dto.VoteDTO;

import java.util.List;

public interface IVoteService {

    void save(VoteCreateDTO createDTO);

    List<VoteDTO> get();

}
