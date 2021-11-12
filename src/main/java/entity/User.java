package entity;

public class User {
        private int id;
        private String name;
        private String email;
        private String gender;
        private String status;
        private String createdAt;
        private String updateAt;

        public static class Builder {
            private int id = 0;
            private String name = "";
            private String email = "";
            private String gender = "";
            private String status = "";
            private String createdAt = "";
            private String updateAt = "";

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

            public Builder setCreateDate(String date) {
                this.createdAt = date;
                return this;
            }

            public Builder setUpdateDate(String date) {
                this.updateAt = date;
                return this;
            }

            public entity.User build() {
                entity.User user = new entity.User();
                user.id = this.id;
                user.name = this.name;
                user.email = this.email;
                user.gender = this.gender;
                user.status = this.status;
                user.createdAt = this.createdAt;
                user.updateAt = this.updateAt;
                return user;
            }
        }

        private User() {
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", gender='" + gender + '\'' +
                    ", status='" + status + '\'' +
                    ", createdAt=" + createdAt +
                    ", updateAt=" + updateAt +
                    '}';
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }

        public String getStatus() {
            return status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setStatus(String status) {
            this.status = status;
        }
}
