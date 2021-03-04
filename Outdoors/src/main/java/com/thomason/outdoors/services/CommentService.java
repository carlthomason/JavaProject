package com.thomason.outdoors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thomason.outdoors.models.Comment;
import com.thomason.outdoors.repositories.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    // returns all the answers
    public List<Comment> allComment() {
        return commentRepository.findAll();
    }
    // creates a answer
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public Comment findAnswer(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            return null;
        }
    }
//    public List<Comment> commentByCamp(Long id){
//        List<Comment> comment = commentRepository.findByCampId(id);
//        return comment;
//    }
}