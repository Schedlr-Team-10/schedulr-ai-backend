package com.schedule_ai_backend;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="post_upload")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @SequenceGenerator(name = "post_id_sequence", sequenceName = "post_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_sequence")

    private int post_id;
    private int user_id;
    private String image;
    private String description;

    @Column(name="face_post_id")
    private boolean facebook;

    @Column(name="insta_post_id")
    private boolean instagram;

    @Column(name="twitter_post_id")
    private boolean twitter;

    @Column(name="linked_in_post_id")
    private boolean linkedin;

    @Column(name = "post_upload_date")
    private LocalDateTime schedule;

    @Column(name = "ai_caption")
    private String caption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return post_id == post.post_id && user_id == post.user_id && facebook == post.facebook && instagram == post.instagram && twitter == post.twitter && linkedin == post.linkedin && Objects.equals(image, post.image) && Objects.equals(description, post.description) && Objects.equals(schedule, post.schedule) && Objects.equals(caption, post.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_id, user_id, image, description, facebook, instagram, twitter, linkedin, schedule, caption);
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", user_id=" + user_id +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", facebook=" + facebook +
                ", instagram=" + instagram +
                ", twitter=" + twitter +
                ", linkedin=" + linkedin +
                ", schedule=" + schedule +
                ", caption='" + caption + '\'' +
                '}';
    }
}