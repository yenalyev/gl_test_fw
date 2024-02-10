package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
        private int id;
        private String name;
        private String email;
        private String gender;
        private String status;

        public static class Builder {
            private int id = 0;
            private String name = "";
            private String email = "";
            private String gender = "";
            private String status = "";

            public Builder setId(int id) {
                this.id = id;
                return this;
            }

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setEmail(String email) {
                this.email = email;
                return this;
            }

            public Builder setGender(String gender) {
                this.gender = gender;
                return this;
            }

            public Builder setStatus(String status) {
                this.status = status;
                return this;
            }


            public entity.User build() {
                entity.User user = new entity.User();
                user.id = this.id;
                user.name = this.name;
                user.email = this.email;
                user.gender = this.gender;
                user.status = this.status;
                return user;
            }
        }

        private User() {
        }

}
