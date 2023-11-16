package com.example.please.service;

import com.example.please.entity.Note;

import com.example.please.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService implements IService<Note>{
    private final NoteRepository repository;


    @Override
    public List<Note> listAll() {
        return repository.findAll();
    }

    @Override
    public Note add(Note entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public void update(Note entity){
        repository.save(entity);
    }

    @Override
    public Note getById(Long id){
        Optional<Note> note = repository.findById(id);
        return note.orElse(null);
    }
}