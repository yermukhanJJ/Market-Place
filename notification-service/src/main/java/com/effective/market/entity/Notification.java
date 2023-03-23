package com.effective.market.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notification_tab")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "header", nullable = false, length = 50)
    private String header;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "notif_date", nullable = false)
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "notifications_profiles",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> profiles = new ArrayList<>();

}
