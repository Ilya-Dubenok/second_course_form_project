package by.itacademy.servletproject.daO.memory;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.daO.api.IVoteDao;

import java.util.ArrayList;
import java.util.List;

public class VoteMemoryDao implements IVoteDao {

    private final List<VoteCreateDTO> dtoList = new ArrayList<>();


    public VoteMemoryDao() {
    }


    @Override
    public void save(VoteCreateDTO dto) {
        dtoList.add(dto);

    }

    @Override
    public List<VoteCreateDTO> get() {
        List<VoteCreateDTO> res = new ArrayList<>(dtoList);
        return res;
    }
}
