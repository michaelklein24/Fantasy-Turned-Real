INSERT INTO shw_show (show)
VALUES
('SURVIVOR'),
('TRAITORS'),
('RU_PAULS_DRAG_RACE');

INSERT INTO ssn_season (season_sequence, show, start_time, end_time, total_episodes)
VALUES ('46', 'SURVIVOR', '2025-02-28 20:00:00', '2025-05-22 22:00:00', 14),
('47', 'SURVIVOR', '2025-09-18 20:00:00', '2025-05-18 22:00:00', 13),
('1', 'TRAITORS', '2023-01-12 20:00:00', '2023-02-28 22:00:00', 12),
('2', 'TRAITORS', '2024-01-12 20:00:00', '2024-03-07 22:00:00', 12),
('3', 'TRAITORS', '2025-01-09 20:00:00', '2025-03-06 22:00:00', 12);

INSERT INTO ssn_episode (episode_sequence, season_sequence, air_time, name, show)
VALUES
(1, 46, '2025-02-28 20:00:00.000', 'This is Where the Legends Are Made', 'SURVIVOR'),
(2, 46, '2025-03-06 20:00:00.000', 'Scorpio Energy', 'SURVIVOR'),
(3, 46, '2025-03-13 20:00:00.000', 'Wackadoodles Win', 'SURVIVOR'),
(4, 46, '2025-03-20 20:00:00.000', 'Don''t Touch the Oven', 'SURVIVOR'),
(5, 46, '2025-03-27 20:00:00.000', 'Tiki Man', 'SURVIVOR'),
(6, 46, '2025-04-03 20:00:00.000', 'Cancel Christmas', 'SURVIVOR'),
(7, 46, '2025-04-10 20:00:00.000', 'Episode Several', 'SURVIVOR'),
(8, 46, '2025-04-17 20:00:00.000', 'Hide ''N Seek', 'SURVIVOR'),
(9, 46, '2025-04-24 20:00:00.000', 'Spicy Jeff', 'SURVIVOR'),
(10, 46, '2025-05-01 20:00:00.000', 'Run the Red Light', 'SURVIVOR'),
(11, 46, '2025-05-08 20:00:00.000', 'My Messy, Sweet Little Friend', 'SURVIVOR'),
(12, 46, '2025-05-15 20:00:00.000', 'Mamma Bear', 'SURVIVOR'),
(13, 46, '2025-05-22 20:00:00.000', 'Friends Going to War', 'SURVIVOR'),
-- Episodes for TRAITORS Season 3
(1, 3, '2025-01-09 20:00:00.000', 'Let Battle Commence', 'TRAITORS'),
(2, 3, '2025-01-09 21:00:00.000', 'Revenge Is a Dish Best Served Cold', 'TRAITORS'),
(3, 3, '2025-01-09 22:00:00.000', 'Nail in a Coffin', 'TRAITORS'),
(4, 3, '2025-01-16 20:00:00.000', 'I Will Bury You Under the Sand', 'TRAITORS'),
(5, 3, '2025-01-23 20:00:00.000', 'All This Murderous Power', 'TRAITORS'),
(6, 3, '2025-01-30 20:00:00.000', 'A Dysfunctional Family', 'TRAITORS'),
(7, 3, '2025-02-06 20:00:00.000', 'Til Death Us Do Part', 'TRAITORS'),
(8, 3, '2025-02-13 20:00:00.000', 'A B**** Is Lying', 'TRAITORS'),
(9, 3, '2025-02-20 20:00:00.000', 'A Silent Assassin', 'TRAITORS'),
(10, 3, '2025-02-27 20:00:00.000', 'The Grand Finale', 'TRAITORS'),
-- Episodes for TRAITORS Season 1
(1, 1, '2023-01-12 20:00:00.000', 'The Game Is Afoot', 'TRAITORS'),
(2, 1, '2023-01-12 21:00:00.000', 'Buried Alive', 'TRAITORS'),
(3, 1, '2023-01-12 22:00:00.000', 'Murder in the Dark', 'TRAITORS'),
(4, 1, '2023-01-19 20:00:00.000', 'Life or Death Situation', 'TRAITORS'),
(5, 1, '2023-01-26 20:00:00.000', 'Getting Away with Murder', 'TRAITORS'),
(6, 1, '2023-02-02 20:00:00.000', 'Suspicion and Sabotage', 'TRAITORS'),
(7, 1, '2023-02-09 20:00:00.000', 'The Mask is Slipping', 'TRAITORS'),
(8, 1, '2023-02-16 20:00:00.000', 'Cabins in the Woods', 'TRAITORS'),
(9, 1, '2023-02-23 20:00:00.000', 'Trust No One', 'TRAITORS'),
(10, 1, '2023-02-28 20:00:00.000', 'The Grand Finale', 'TRAITORS'),
-- Episodes for TRAITORS Season 2
(1, 2, '2024-01-12 20:00:00.000', 'Betrayers, Fakes and Fraudsters', 'TRAITORS'),
(2, 2, '2024-01-12 21:00:00.000', 'Welcome to the Dark Side', 'TRAITORS'),
(3, 2, '2024-01-12 22:00:00.000', 'Murder in Plain Sight', 'TRAITORS'),
(4, 2, '2024-01-19 20:00:00.000', 'The Funeral', 'TRAITORS'),
(5, 2, '2024-01-26 20:00:00.000', 'A Killer Move', 'TRAITORS'),
(6, 2, '2024-02-02 20:00:00.000', 'Backstab and Betrayal', 'TRAITORS'),
(7, 2, '2024-02-09 20:00:00.000', 'Blood on Their Hands', 'TRAITORS'),
(8, 2, '2024-02-16 20:00:00.000', 'Knives at Dawn', 'TRAITORS'),
(9, 2, '2024-02-23 20:00:00.000', 'A Game of Death', 'TRAITORS'),
(10, 2, '2024-02-29 20:00:00.000', 'The Weight of Deceit', 'TRAITORS'),
(11, 2, '2024-03-07 20:00:00.000', 'One Final Hurdle', 'TRAITORS'),
(12, 2, '2024-03-14 20:00:00.000', 'Reunion', 'TRAITORS');


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
    ('13m56789-op23-6q45-qr67-234567890123', 'Moriah', 'Gaynor'),
    ('8979e3e4-74a8-48d7-b5de-1b5a2c476d6a','Arie','Luyendyk'),
    ('e7123d99-001f-4b67-9924-1ba675de29fe','Cody','Calafiore'),
    ('0038e457-bdcf-440d-8c49-c929cfc3f3c3','Rachel','Reilly'),
    ('9828a4a9-15c3-4494-8a5d-26dc359cdf30','Amanda','Clark'),
    ('2efa631a-76b6-4023-bcaa-a3892a5ad33b','Azra','Valani'),
    ('c012b471-3e6e-48de-a323-5a2475b161a6','Michael','Davidson'),
    ('0eb14a3b-0690-413b-94aa-381c3f1c9b74','Brandi','Glanville'),
    ('ca9982d2-fc5f-4679-98b2-dced0b786672','Kate','Chastain'),
    ('eae15c2c-86a0-477c-aa8c-2b493f08476f','Ryan','Lochte'),
    ('f123634b-174c-479a-9530-b9a912a5cb83','Andie','Thurmond'),
    ('8443392b-8ced-4948-9cd3-5e46514a3d8a','Christian','de la Torre'),
    ('dffcb5c8-34ae-452e-a6ee-717556898293','Quentin','Jiles'),
    ('3dcea276-ebc6-44b3-9be2-4cc5e1ef3b47','Cirie','Fields'),
    ('f6172351-f1f8-4456-91be-07963b74178a','Kyle','Cooke'),
    ('7f6c70f2-6a81-4052-afa9-7090c8898380','Stephanie','LaGrossa'),
    ('9e732573-1556-4c1e-bfe3-a9c693ef8587','Anjelica','Conti'),
    ('70a11635-d703-4a8c-b9ec-ea99fd640f36','Geraldine','Moreno'),
    ('a7d919f4-f9f8-4451-977a-bc1c3f56d71b','Phaedra','Parks'),
    ('509d3469-99c5-401b-9b52-756c0e7676a4','Parvati','Shallow'),
    ('d8a572ee-cb7f-46ba-b2c7-4ba5499eabbe','Peter','Weber'),
    ('fe6363eb-55d2-49ff-99a2-7ff02b4f262d','Tamra','Judge'),
    ('ec529393-f5fb-46fa-93fa-8c476638e242','John','Bercow'),
    ('e4a5ae31-e382-4113-921e-094cd9b95ac2','Ekin-Su','Culculoglu'),
    ('bda46bdd-659e-4ec3-95de-1cbe71260fa3','Carsten','Bergersen'),
    ('4db2128e-8154-4700-bdaa-768c1654a33a','Pierzina','Pierzina'),
    ('a115120a-c825-4c37-99d1-e3d7d840706c','Dan','Gheesling'),
    ('962914a5-7bda-4f56-974f-0aa3f2a7ac6e','Sandra','Diaz-Twine'),
    ('0787312c-3874-4d57-a848-894a2c2c672f','Johnny','Devenanzio'),
    ('55245abd-7ccb-4fd7-b95b-aefde04149c3','Chris','Tamburello'),
    ('5264d8ec-8bed-4ff8-84be-6de39eb9341e','Marcus','Jordan'),
    ('61d8b407-bf80-4ce8-b55c-84597df65ef7','Peppermint',''),
    ('a6d0b849-6749-4327-87b4-f63cdcf66d12','Mercedes','Javid'),
    ('40fcf66b-0766-4b3c-90cb-9bfac85e9197','Kevin','Kreider'),
    ('c7b36056-2a97-44a3-af7b-850c78b1faaa','Maksim','Chmerkovskiy'),
    ('f33fc25f-2506-47fb-88a4-a3bea885673c','Larsa','Pippen'),
    ('2e05b5b5-5b8f-49d5-a8aa-02c213fb562f','Deontay','Wilder'),
    ('4b612e7c-da6d-44e6-8d57-7de5c59004d1','Sheree','Whitfield'),
    ('aab6ae24-d9db-49d6-90c2-77b99dc21df0','Trishelle','Cannatella'),
    ('86d1179b-ccf3-4c28-97d4-d394cecd48d1','Kate','Chastain'),
    ('a2912f45-6146-4b4a-99f5-17dd5c7d9997','Rob','Mariano'),
    ('9d423acf-caf3-4eb4-8404-e01c9419cde0','Dorinda','Medley'),
    ('73f1e19b-8706-4646-8e02-d32001918316','Chrishell','Stause'),
    ('85e18acd-5603-4c45-b484-06d36da8adc4','Britney','Haynes'),
    ('78dc552e-8a4c-4dd3-b611-ec879a2b1d72','Bob','the Drag Queen'),
    ('117d5868-bd8b-47ef-b8cd-93aae6255dc2','Danielle','Reyes'),
    ('05d0b6da-9a6c-4cf0-9697-7e53d7cbbc6d','Wells','Adams'),
    ('c25cfaec-3db6-47a6-af94-4afb3dbf8350','Chanel','Ayan'),
    ('c70326de-98be-4450-90b2-eba94e002a52','Dylan','Efron'),
    ('88e3a7cc-e863-4102-b2e6-da42df66d97b','Tony','Vlachos'),
    ('3abd0e05-f672-4701-9697-f64d221e4273','Jeremy','Collins'),
    ('fa1a6f81-dcf1-4598-a85c-f31cef28a665','Dolores','Catania'),
    ('f99d2a9b-6204-448c-b708-89379f0c49e0','Robyn','Dixon'),
    ('bb6428ae-14fd-4d48-ad10-98c2ae29e3d5','Bob','Harper'),
    ('e5abb982-fe1f-418c-85fe-f43d05bec0be','Ciara','Miller'),
    ('27af5d77-dd65-4b7a-aaa3-7ec40041204b','Ivar','Mountbatten'),
    ('101fad08-a50f-44ad-8a28-50a0924bd6d6','Carolyn','Wiger'),
    ('62a74c5a-9fca-49e8-a2f3-ab183ce2b241','Sam','Asghari'),
    ('6e4aed5f-b2ed-483d-bd31-20c81e58e55f','Tom','Sandoval'),
    ('04ced882-748a-4578-a29d-1cdf5bbd50b7','Nikki','Garcia');


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
    ('13m56789-op23-6q45-qr67-234567890123', '46', 'SURVIVOR'),
    ('8979e3e4-74a8-48d7-b5de-1b5a2c476d6a', '1', 'TRAITORS'),
    ('e7123d99-001f-4b67-9924-1ba675de29fe', '1', 'TRAITORS'),
    ('0038e457-bdcf-440d-8c49-c929cfc3f3c3', '1', 'TRAITORS'),
    ('9828a4a9-15c3-4494-8a5d-26dc359cdf30', '1', 'TRAITORS'),
    ('2efa631a-76b6-4023-bcaa-a3892a5ad33b', '1', 'TRAITORS'),
    ('c012b471-3e6e-48de-a323-5a2475b161a6', '1', 'TRAITORS'),
    ('0eb14a3b-0690-413b-94aa-381c3f1c9b74', '1', 'TRAITORS'),
    ('ca9982d2-fc5f-4679-98b2-dced0b786672', '1', 'TRAITORS'),
    ('eae15c2c-86a0-477c-aa8c-2b493f08476f', '1', 'TRAITORS'),
    ('f123634b-174c-479a-9530-b9a912a5cb83', '1', 'TRAITORS'),
    ('8443392b-8ced-4948-9cd3-5e46514a3d8a', '1', 'TRAITORS'),
    ('dffcb5c8-34ae-452e-a6ee-717556898293', '1', 'TRAITORS'),
    ('3dcea276-ebc6-44b3-9be2-4cc5e1ef3b47', '1', 'TRAITORS'),
    ('f6172351-f1f8-4456-91be-07963b74178a', '1', 'TRAITORS'),
    ('7f6c70f2-6a81-4052-afa9-7090c8898380', '1', 'TRAITORS'),
    ('9e732573-1556-4c1e-bfe3-a9c693ef8587', '1', 'TRAITORS'),
    ('70a11635-d703-4a8c-b9ec-ea99fd640f36', '1', 'TRAITORS'),
    ('a7d919f4-f9f8-4451-977a-bc1c3f56d71b', '2', 'TRAITORS'),
    ('509d3469-99c5-401b-9b52-756c0e7676a4', '2', 'TRAITORS'),
    ('d8a572ee-cb7f-46ba-b2c7-4ba5499eabbe', '2', 'TRAITORS'),
    ('fe6363eb-55d2-49ff-99a2-7ff02b4f262d', '2', 'TRAITORS'),
    ('ec529393-f5fb-46fa-93fa-8c476638e242', '2', 'TRAITORS'),
    ('e4a5ae31-e382-4113-921e-094cd9b95ac2', '2', 'TRAITORS'),
    ('bda46bdd-659e-4ec3-95de-1cbe71260fa3', '2', 'TRAITORS'),
    ('4db2128e-8154-4700-bdaa-768c1654a33a', '2', 'TRAITORS'),
    ('a115120a-c825-4c37-99d1-e3d7d840706c', '2', 'TRAITORS'),
    ('962914a5-7bda-4f56-974f-0aa3f2a7ac6e', '2', 'TRAITORS'),
    ('0787312c-3874-4d57-a848-894a2c2c672f', '2', 'TRAITORS'),
    ('55245abd-7ccb-4fd7-b95b-aefde04149c3', '2', 'TRAITORS'),
    ('5264d8ec-8bed-4ff8-84be-6de39eb9341e', '2', 'TRAITORS'),
    ('61d8b407-bf80-4ce8-b55c-84597df65ef7', '2', 'TRAITORS'),
    ('a6d0b849-6749-4327-87b4-f63cdcf66d12', '2', 'TRAITORS'),
    ('40fcf66b-0766-4b3c-90cb-9bfac85e9197', '2', 'TRAITORS'),
    ('c7b36056-2a97-44a3-af7b-850c78b1faaa', '2', 'TRAITORS'),
    ('f33fc25f-2506-47fb-88a4-a3bea885673c', '2', 'TRAITORS'),
    ('2e05b5b5-5b8f-49d5-a8aa-02c213fb562f', '2', 'TRAITORS'),
    ('4b612e7c-da6d-44e6-8d57-7de5c59004d1', '2', 'TRAITORS'),
    ('aab6ae24-d9db-49d6-90c2-77b99dc21df0', '2', 'TRAITORS'),
    ('86d1179b-ccf3-4c28-97d4-d394cecd48d1', '2', 'TRAITORS'),
    ('a2912f45-6146-4b4a-99f5-17dd5c7d9997', '3', 'TRAITORS'),
    ('9d423acf-caf3-4eb4-8404-e01c9419cde0', '3', 'TRAITORS'),
    ('73f1e19b-8706-4646-8e02-d32001918316', '3', 'TRAITORS'),
    ('85e18acd-5603-4c45-b484-06d36da8adc4', '3', 'TRAITORS'),
    ('78dc552e-8a4c-4dd3-b611-ec879a2b1d72', '3', 'TRAITORS'),
    ('117d5868-bd8b-47ef-b8cd-93aae6255dc2', '3', 'TRAITORS'),
    ('05d0b6da-9a6c-4cf0-9697-7e53d7cbbc6d', '3', 'TRAITORS'),
    ('c25cfaec-3db6-47a6-af94-4afb3dbf8350', '3', 'TRAITORS'),
    ('c70326de-98be-4450-90b2-eba94e002a52', '3', 'TRAITORS'),
    ('88e3a7cc-e863-4102-b2e6-da42df66d97b', '3', 'TRAITORS'),
    ('3abd0e05-f672-4701-9697-f64d221e4273', '3', 'TRAITORS'),
    ('fa1a6f81-dcf1-4598-a85c-f31cef28a665', '3', 'TRAITORS'),
    ('f99d2a9b-6204-448c-b708-89379f0c49e0', '3', 'TRAITORS'),
    ('bb6428ae-14fd-4d48-ad10-98c2ae29e3d5', '3', 'TRAITORS'),
    ('e5abb982-fe1f-418c-85fe-f43d05bec0be', '3', 'TRAITORS'),
    ('27af5d77-dd65-4b7a-aaa3-7ec40041204b', '3', 'TRAITORS'),
    ('101fad08-a50f-44ad-8a28-50a0924bd6d6', '3', 'TRAITORS'),
    ('62a74c5a-9fca-49e8-a2f3-ab183ce2b241', '3', 'TRAITORS'),
    ('6e4aed5f-b2ed-483d-bd31-20c81e58e55f', '3', 'TRAITORS'),
    ('04ced882-748a-4578-a29d-1cdf5bbd50b7', '3', 'TRAITORS');
