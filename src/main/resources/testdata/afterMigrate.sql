insert into userApp values('00000000-b91c-4ef3-9e78-51c35c3b65da','Jaja', now()),
('00000000-5f19-40a5-8109-f3cadee4519b', 'Xi', now()) ON CONFLICT DO NOTHING;

insert into chat values ('00000000-aab4-4402-af57-bbce2b05fb63','testChat', now()) ON CONFLICT DO NOTHING;

insert into chat_users values ('00000000-aab4-4402-af57-bbce2b05fb63', '00000000-b91c-4ef3-9e78-51c35c3b65da') ON CONFLICT DO NOTHING;