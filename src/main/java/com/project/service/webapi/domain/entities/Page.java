package com.project.service.webapi.domain.entities;

import java.io.Serializable;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "TB_PAGES")
@Getter @Setter @NoArgsConstructor
@ToString(exclude={"pageId"})
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "page_id")
	private UUID pageId;

    @Column(name = "content", columnDefinition="text", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

}
