INSERT INTO ssn_season (season_sequence, show, start_time, end_time, total_episodes)
VALUES ('46', 'SURVIVOR', '2025-02-28 20:00:00', '2025-05-22 22:00:00', 14),
('47', 'SURVIVOR', '2025-09-18 20:00:00', '2025-05-18 22:00:00', 13);

INSERT INTO usr_user (user_id, role, email, first_name, last_name)
VALUES
('7db21f43-31e2-4fd7-8501-772039ad9f16', 'USER', 'tony.stark@test.com', 'Tony', 'Stark'),
('b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48', 'USER', 'steve.rogers@test.com', 'Steve', 'Rogers'),
('c34f7e1f-0938-4be8-a75e-6a8300d6f7b9', 'USER', 'natasha.romanoff@test.com', 'Natasha', 'Romanoff'),
('d41a8e2a-8b21-4af8-9b09-993cd06f3f80', 'USER', 'bruce.banner@test.com', 'Bruce', 'Banner'),
('e71f6c2a-f3d3-42ea-8d17-1e24bc70dc58', 'USER', 'thor.odinson@test.com', 'Thor', 'Odinson'),
('f92a7e9b-4823-4c19-9194-dc77b891da8d', 'USER', 'clint.barton@test.com', 'Clint', 'Barton'),
('8b7d9e9d-62e3-4f7b-a7b7-b2d76ec2d2f0', 'USER', 'peter.parker@test.com', 'Peter', 'Parker'),
('9f82a0e7-df9c-4970-8766-d7d3c19d95e1', 'USER', 'wanda.maximoff@test.com', 'Wanda', 'Maximoff');


INSERT INTO usr_password_history (active, created_date, expiry_date, encoded_password, password_id, user_id)
VALUES
(true, '2025-01-08', '2025-04-08', '$2a$10$Wg96X7oUcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '7fd7be7a-529b-4004-b36f-b7f98ef71d97', '7db21f43-31e2-4fd7-8501-772039ad9f16'),
(true, '2025-01-08', '2025-04-08', '$2a$10$evVfMsxwvUSUTxtvU1C02ur5JC72kn7DKKlLAoAwTE64EzvhDYIfm', '123dbe1a-529b-4004-b36f-b7f98ef71d99', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48'),
(true, '2025-01-08', '2025-04-08', '$2a$10$y2p9X7pBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '223dbe1a-529b-4004-b36f-b7f98ef71d88', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9'),
(true, '2025-01-08', '2025-04-08', '$2a$10$x3q9X7rBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '323dbe1a-529b-4004-b36f-b7f98ef71d77', 'd41a8e2a-8b21-4af8-9b09-993cd06f3f80'),
(true, '2025-01-08', '2025-04-08', '$2a$10$a4s9X7tBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '423dbe1a-529b-4004-b36f-b7f98ef71d66', 'e71f6c2a-f3d3-42ea-8d17-1e24bc70dc58'),
(true, '2025-01-08', '2025-04-08', '$2a$10$b5u9X7vBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '523dbe1a-529b-4004-b36f-b7f98ef71d55', 'f92a7e9b-4823-4c19-9194-dc77b891da8d'),
(true, '2025-01-08', '2025-04-08', '$2a$10$k7m9X7pBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '623dbe1a-529b-4004-b36f-b7f98ef71d44', '8b7d9e9d-62e3-4f7b-a7b7-b2d76ec2d2f0'),
(true, '2025-01-08', '2025-04-08', '$2a$10$z6v9X7qBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '723dbe1a-529b-4004-b36f-b7f98ef71d33', '9f82a0e7-df9c-4970-8766-d7d3c19d95e1');

INSERT INTO lge_league (season_sequence, create_time, league_id, name, show)
VALUES
(47, '2025-01-08 14:38:53.763', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'Corner By The Bookshelf', 'SURVIVOR'),
(47, '2025-01-18 14:38:53.763', '276bfc66-edcc-4162-ab64-2ba02918092c', 'The ROCKstars', 'SURVIVOR');

INSERT INTO lge_participant (time_joined, league_id, role, user_id)
VALUES
('2025-01-08 14:38:53.773', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'OWNER', '7db21f43-31e2-4fd7-8501-772039ad9f16'),
('2025-01-08 14:38:53.773', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'MEMBER', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48'),
('2025-01-08 14:39:00.123', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'MEMBER', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9'),
('2025-01-18 14:38:53.773', '276bfc66-edcc-4162-ab64-2ba02918092c', 'OWNER', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48');

INSERT INTO lge_invite (league_id, invitee_user_id, inviter_user_id, create_time, update_time, status)
VALUES
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:40:00.000', '2025-01-08 14:40:00.000', 'APPROVED'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:40:30.000', '2025-01-08 14:40:30.000', 'APPROVED'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'd41a8e2a-8b21-4af8-9b09-993cd06f3f80', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:41:00.000', '2025-01-08 14:41:00.000', 'PENDING'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'e71f6c2a-f3d3-42ea-8d17-1e24bc70dc58', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:41:30.000', '2025-01-08 14:41:30.000', 'PENDING'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'f92a7e9b-4823-4c19-9194-dc77b891da8d', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:42:00.000', '2025-01-08 14:42:00.000', 'DECLINED'),
('276bfc66-edcc-4162-ab64-2ba02918092c', '7db21f43-31e2-4fd7-8501-772039ad9f16', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48', '2025-01-08 14:42:00.000', '2025-01-08 14:42:00.000', 'PENDING');


INSERT INTO ntf_notification(
acknowledged,
create_time,
update_time,
actions,
completed_action_label,
icon,
link,
message,
notification_id,
reference_id,
reference_type,
title,
user_id)
VALUES
(
false,
'2025-01-18 22:26:02.590',
'2025-01-18 22:26:02.588',
'[{"label":"Accept","httpMethod":"PUT","endpoint":"/league/276bfc66-edcc-4162-ab64-2ba02918092c/invite","requestBody":"{\"newStatus\": \"APPROVED\"}"},{"label":"Decline","httpMethod":"PUT","endpoint":"/league/276bfc66-edcc-4162-ab64-2ba02918092c/invite","requestBody":"{\"newStatus\": \"DECLINED\"}"}]',
NULL,
'group',
NULL,
'Steve has invited you to join the league: The ROCKstars',
'aac08e86-3939-49e1-9526-5da749b4abef',
'276bfc66-edcc-4162-ab64-2ba02918092c',
'LEAGUE_INVITE',
'League Invitation',
'7db21f43-31e2-4fd7-8501-772039ad9f16'
);

-- Insert contestants into the shw_contestant table
INSERT INTO shw_contestant (contestant_id, first_name, last_name)
VALUES
    ('1e4d1a2b-1234-4a56-9876-abcdef123456', 'Alysha', 'Welch'),
    ('2f5e2b3c-5678-4b67-8765-bcdef2345678', 'Andy', 'Rueda'),
    ('3g6f3c4d-9101-4c78-7654-cdef34567890', 'Anika', 'Dhar'),
    ('4h7g4d5e-1121-4d89-6543-def456789012', 'Caroline', 'Vidmar'),
    ('5i8h5e6f-3141-4e90-5432-ef5678901234', 'Gabe', 'Ortis'),
    ('6j9i6f7g-5161-4f01-4321-f67890123456', 'Jon', 'Lovett'),
    ('7k0j7g8h-7181-4g12-3210-678901234567', 'Kishan', 'Patel'),
    ('8l1k8h9i-9201-4h23-2109-789012345678', 'Kyle', 'Otswald'),
    ('9m2l9i0j-1221-4i34-1098-890123456789', 'Rachel', 'Lamont'),
    ('0n3m0j1k-3241-4j45-0987-901234567890', 'Rome', 'Cooney'),
    ('1o4n1k2l-5261-4k56-9876-012345678901', 'Sam', 'Phalen'),
    ('2p5o2l3m-7281-4l67-8765-123456789012', 'Sierra', 'Wright'),
    ('3q6p3m4n-9301-4m78-7654-234567890123', 'Solomon', 'Yi'),
    ('4r7q4n5o-1321-4n89-6543-345678901234', 'Sue', 'Smey'),
    ('5s8r5o6p-3341-4o90-5432-456789012345', 'Teeny', 'Chirichillo'),
    ('6t9s6p7q-5361-4p01-4321-567890123456', 'Terran', 'Foster'),
    ('7u0t7q8r-7381-4q12-3210-678901234567', 'Tiyana', 'Hallums'),
    ('12a34567-bc89-4d12-abcd-efg456789012', 'Jemila', 'Hussain-Adams'),
    ('22b45678-cd90-5e23-bcde-789012345678', 'Ben', 'Katzman'),
    ('33c56789-de01-6f34-cdef-234567890123', 'Jessica', 'Chong'),
    ('44d67890-ef12-7g45-dfgh-345678901234', 'Bhanu', 'Gopal'),
    ('55e78901-fg23-8h56-efgh-456789012345', 'Kenzie', 'Petty'),
    ('66f89012-gh34-9i67-ghij-567890123456', 'Charlie', 'Davis'),
    ('77g90123-hi45-0j78-ijk1-678901234567', 'Liz', 'Wilcox'),
    ('88h01234-jk56-1l89-lm12-789012345678', 'Maria', 'Shrime Gonzalez'),
    ('99i12345-kl67-2m90-mn23-890123456789', 'Q', 'Burdette'),
    ('10j23456-lm78-3n01-no34-901234567890', 'Venus', 'Vafa'),
    ('11k34567-mn89-4o12-op45-012345678901', 'Tiffany Nicole', 'Ervin'),
    ('12l45678-no01-5p23-pq56-123456789012', 'Tim', 'Spicer'),
    ('13m56789-op23-6q45-qr67-234567890123', 'Moriah', 'Gaynor');

-- Link contestants to season 47 in shw_season_contestant
INSERT INTO shw_season_contestant (contestant_id, season_sequence, show)
VALUES
    ('1e4d1a2b-1234-4a56-9876-abcdef123456', '47', 'SURVIVOR'),
    ('2f5e2b3c-5678-4b67-8765-bcdef2345678', '47', 'SURVIVOR'),
    ('3g6f3c4d-9101-4c78-7654-cdef34567890', '47', 'SURVIVOR'),
    ('4h7g4d5e-1121-4d89-6543-def456789012', '47', 'SURVIVOR'),
    ('5i8h5e6f-3141-4e90-5432-ef5678901234', '47', 'SURVIVOR'),
    ('6j9i6f7g-5161-4f01-4321-f67890123456', '47', 'SURVIVOR'),
    ('7k0j7g8h-7181-4g12-3210-678901234567', '47', 'SURVIVOR'),
    ('8l1k8h9i-9201-4h23-2109-789012345678', '47', 'SURVIVOR'),
    ('9m2l9i0j-1221-4i34-1098-890123456789', '47', 'SURVIVOR'),
    ('0n3m0j1k-3241-4j45-0987-901234567890', '47', 'SURVIVOR'),
    ('1o4n1k2l-5261-4k56-9876-012345678901', '47', 'SURVIVOR'),
    ('2p5o2l3m-7281-4l67-8765-123456789012', '47', 'SURVIVOR'),
    ('3q6p3m4n-9301-4m78-7654-234567890123', '47', 'SURVIVOR'),
    ('4r7q4n5o-1321-4n89-6543-345678901234', '47', 'SURVIVOR'),
    ('5s8r5o6p-3341-4o90-5432-456789012345', '47', 'SURVIVOR'),
    ('6t9s6p7q-5361-4p01-4321-567890123456', '47', 'SURVIVOR'),
    ('7u0t7q8r-7381-4q12-3210-678901234567', '47', 'SURVIVOR'),
    ('12a34567-bc89-4d12-abcd-efg456789012', '46', 'SURVIVOR'),
    ('22b45678-cd90-5e23-bcde-789012345678', '46', 'SURVIVOR'),
    ('33c56789-de01-6f34-cdef-234567890123', '46', 'SURVIVOR'),
    ('44d67890-ef12-7g45-dfgh-345678901234', '46', 'SURVIVOR'),
    ('55e78901-fg23-8h56-efgh-456789012345', '46', 'SURVIVOR'),
    ('66f89012-gh34-9i67-ghij-567890123456', '46', 'SURVIVOR'),
    ('77g90123-hi45-0j78-ijk1-678901234567', '46', 'SURVIVOR'),
    ('88h01234-jk56-1l89-lm12-789012345678', '46', 'SURVIVOR'),
    ('99i12345-kl67-2m90-mn23-890123456789', '46', 'SURVIVOR'),
    ('10j23456-lm78-3n01-no34-901234567890', '46', 'SURVIVOR'),
    ('11k34567-mn89-4o12-op45-012345678901', '46', 'SURVIVOR'),
    ('12l45678-no01-5p23-pq56-123456789012', '46', 'SURVIVOR'),
    ('13m56789-op23-6q45-qr67-234567890123', '46', 'SURVIVOR');
