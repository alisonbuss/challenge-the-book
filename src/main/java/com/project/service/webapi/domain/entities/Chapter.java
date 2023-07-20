package com.project.service.webapi.domain.entities;

import java.io.Serializable;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "TB_CHAPTERS")
@Getter @Setter @NoArgsConstructor
@ToString(exclude={"chapterId"})
public class Chapter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chapter_id")
	private UUID chapterId;

    @Column(name = "chapter", nullable = false)
    private String chapter;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Page> pages = new ArrayList<>();

}
