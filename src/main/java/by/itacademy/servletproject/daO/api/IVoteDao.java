package by.itacademy.servletproject.daO.api;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.core.dto.VoteDTO;

import java.util.List;

public interface IVoteDao {

    void save(VoteDTO dto);

    List<VoteDTO> get();

}
