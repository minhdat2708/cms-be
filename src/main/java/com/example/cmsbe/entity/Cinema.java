package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "cms_cinema")
public class Cinema implements Serializable {

    @Serial
    private static final long serialVersionUID = 8081573080109375485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "CINEMA_TYPE_ID", referencedColumnName = "id")
    private CinemaType cinemaType;

    @ManyToOne
    @JoinColumn(name = "PRODUCER_ID", referencedColumnName = "id")
    private Producer producer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cinemas")
    private List<Showtime> showtimes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cms_cinema_images",
            joinColumns = {
                @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "image_id")
            }
    )
    private Set<Image> cinemaImage;
}
