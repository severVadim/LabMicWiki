package com.labMicWiki.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

   @Id
   @Column(name = "nick_name")
   private String nickName;

   @Column(name = "password")
   private String password;

   public User() {
   }

   public User(String nickName, String password) {
      this.nickName = nickName;
      this.password = password;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (!nickName.equals(user.nickName)) return false;
      return password.equals(user.password);
   }

   @Override
   public int hashCode() {
      int result = nickName.hashCode();
      result = 31 * result + password.hashCode();
      return result;
   }

   @Override
   public String toString() {
      return "User{" +
            "nickName='" + nickName + '\'' +
            ", password='" + password + '\'' +
            '}';
   }
}