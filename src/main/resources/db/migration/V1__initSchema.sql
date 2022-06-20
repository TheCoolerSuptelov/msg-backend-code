CREATE TABLE chat (
  id UUID NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_chat PRIMARY KEY (id)
);

CREATE TABLE chat_users (
  chat_id UUID NOT NULL,
   users_id UUID NOT NULL,
   CONSTRAINT pk_chat_users PRIMARY KEY (chat_id, users_id)
);

CREATE TABLE message (
  id UUID NOT NULL,
   chat_id UUID,
   author_id UUID,
   text TEXT,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_message PRIMARY KEY (id)
);

CREATE TABLE "user" (
  id UUID NOT NULL,
   username VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user" ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE message ADD CONSTRAINT FK_MESSAGE_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES "user" (id);

ALTER TABLE message ADD CONSTRAINT FK_MESSAGE_ON_CHAT FOREIGN KEY (chat_id) REFERENCES chat (id);

ALTER TABLE chat_users ADD CONSTRAINT fk_chause_on_chat FOREIGN KEY (chat_id) REFERENCES chat (id);

ALTER TABLE chat_users ADD CONSTRAINT fk_chause_on_user FOREIGN KEY (users_id) REFERENCES "user" (id);