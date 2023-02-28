package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //디비에 테이블 생성
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//번호증가전략이 데이터베이스를 따라간다.
	private int id;
	
	private String caption; //사진설명
	private String postImageUrl;// 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장-DB에 그저장된 경로를 insert
	
	//user 정보가 들고있는 이미지들은 필요없다는 명시
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch= FetchType.EAGER)//이미지를 select하면 User정보를 같이 들고옴
	private User user;
	
	//이미지 좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")
	private List<Likes> likes;
	
	
	
	//이미지에 댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")
	private List<Comment> comments;
	
	
	private LocalDateTime createDate;
	
	@Transient//DB에 칼럼이 만들어지지 않는다.
	private boolean likeState;
	
	
	@Transient
	private int likeCount;
	
	@PrePersist // 디비에 INSERT 되기전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	//오브젝트를 콘솔에 출력할때 문제가 될 수 있어서  User부분을 출력되지 않게 함.
	/*
	 * @Override public String toString() 
	 * { return "Image [id=" + id + ", caption="
	 * + caption + ", postImageUrl=" + postImageUrl + ", createDate=" + createDate +
	 * "]"; }
	 */
	
	
}
