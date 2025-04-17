package com.example.ChatBotService.repository;

import com.example.ChatBotService.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {
}
