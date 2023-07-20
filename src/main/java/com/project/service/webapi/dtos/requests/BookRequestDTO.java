package com.project.service.webapi.dtos.requests;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import com.project.service.webapi.domain.entities.Chapter;

public record BookRequestDTO(
    @NotBlank String title,
    @NotNull List<Chapter> chapters) {
}
