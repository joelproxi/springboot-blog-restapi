package com.proxidev.springbootblogrestapi.entities;

import jakarta.persistence.*;
import lombok.*;

import com.proxidev.springbootblogrestapi.audits.DateTimeAudit;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
@Entity
public class Comment extends DateTimeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

}