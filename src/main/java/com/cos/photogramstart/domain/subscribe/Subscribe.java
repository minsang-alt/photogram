package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//JPA-Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할수 있는 API를 제공)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //디비에 테이블 생성 
@Table(
		uniqueConstraints= {
				@UniqueConstraint(
						name="subscribe_uk",
						columnNames= {"fromUserId","toUserId"}	
		)		
		}
		)
public class Subscribe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//번호증가전략이 데이터베이스를 따라간다.
	private int id;
	
	@JoinColumn(name="fromUserId")//이렇게 컬럼명 만들어! 니 맘대로 만들지 말고
	@ManyToOne
	private User fromUser;
	
	@JoinColumn(name="toUserId")
	@ManyToOne
	private User toUser;
	
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 INSERT 되기전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}
