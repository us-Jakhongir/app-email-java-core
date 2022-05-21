public class Email {


        private String title;
        private String body;
        private User sender;
        private User receiver;
        private boolean read;

        public Email(String title, String body, User sender, User receiver, boolean read) {
            this.title = title;
            this.body = body;
            this.sender = sender;
            this.receiver = receiver;
            this.read = read;
        }

        public Email() {

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }

        public User getReceiver() {
            return receiver;
        }

        public void setReceiver(User receiver) {
            this.receiver = receiver;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        @Override
        public String toString() {
            return "Email{" +
                    "title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    ", sender=" + sender +
                    ", receiver=" + receiver +
                    ", isRead=" + read +
                    '}';
        }
}

