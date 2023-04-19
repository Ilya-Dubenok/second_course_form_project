package by.itacademy.servletproject.daO.api;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;

import java.util.List;

public interface IVoteDao  {

    void save(VoteCreateDTO dto);

    List<VoteCreateDTO> get();


}
