package com.matheustirabassi.pizzainbox.domain;


import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Getter(value = AccessLevel.PRIVATE)
  @Setter(value = AccessLevel.PRIVATE)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Getter(value = AccessLevel.PRIVATE)
  @Setter(value = AccessLevel.PRIVATE)
  private Date modificationDate;

  protected BaseEntity(@NonNull Date creationDate, Date modificationDate) {
    this.creationDate = creationDate;
    this.modificationDate = modificationDate;
  }

  @PrePersist
  @PreUpdate
  public void configureDateCreationAndModification() {
    this.modificationDate = new Date();
    if (this.creationDate == null) {
      this.creationDate = new Date();
    }
  }

}