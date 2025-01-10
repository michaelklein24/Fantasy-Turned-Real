INSERT INTO ssn_season (sequence, show, start_time, end_time, total_episodes)
VALUES ('47', 'SURVIVOR', '2025-02-15 20:00:00', '2025-05-15 22:00:00', 13);

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
(true, '2025-01-08', '2025-04-08', '$2a$10$z1o9X7lBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '123dbe1a-529b-4004-b36f-b7f98ef71d99', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48'),
(true, '2025-01-08', '2025-04-08', '$2a$10$y2p9X7pBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '223dbe1a-529b-4004-b36f-b7f98ef71d88', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9'),
(true, '2025-01-08', '2025-04-08', '$2a$10$x3q9X7rBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '323dbe1a-529b-4004-b36f-b7f98ef71d77', 'd41a8e2a-8b21-4af8-9b09-993cd06f3f80'),
(true, '2025-01-08', '2025-04-08', '$2a$10$a4s9X7tBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '423dbe1a-529b-4004-b36f-b7f98ef71d66', 'e71f6c2a-f3d3-42ea-8d17-1e24bc70dc58'),
(true, '2025-01-08', '2025-04-08', '$2a$10$b5u9X7vBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '523dbe1a-529b-4004-b36f-b7f98ef71d55', 'f92a7e9b-4823-4c19-9194-dc77b891da8d'),
(true, '2025-01-08', '2025-04-08', '$2a$10$k7m9X7pBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '623dbe1a-529b-4004-b36f-b7f98ef71d44', '8b7d9e9d-62e3-4f7b-a7b7-b2d76ec2d2f0'),
(true, '2025-01-08', '2025-04-08', '$2a$10$z6v9X7qBcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W', '723dbe1a-529b-4004-b36f-b7f98ef71d33', '9f82a0e7-df9c-4970-8766-d7d3c19d95e1');

INSERT INTO lge_league (season_sequence, create_time, league_id, name, season_show)
VALUES (47, '2025-01-08 14:38:53.763', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'Corner By The Bookshelf', 'SURVIVOR');

INSERT INTO lge_participant (time_joined, league_id, role, user_id)
VALUES
('2025-01-08 14:38:53.773', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'OWNER', '7db21f43-31e2-4fd7-8501-772039ad9f16'),
('2025-01-08 14:38:53.773', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'MEMBER', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48'),
('2025-01-08 14:39:00.123', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'MEMBER', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9');

INSERT INTO lge_invite (league_id, invitee_user_id, inviter_user_id, create_time, update_time, status)
VALUES
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'b18d2e3d-71d8-4a3e-9e4f-10f30c8bdf48', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:40:00.000', '2025-01-08 14:40:00.000', 'APPROVED'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'c34f7e1f-0938-4be8-a75e-6a8300d6f7b9', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:40:30.000', '2025-01-08 14:40:30.000', 'APPROVED'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'd41a8e2a-8b21-4af8-9b09-993cd06f3f80', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:41:00.000', '2025-01-08 14:41:00.000', 'PENDING'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'e71f6c2a-f3d3-42ea-8d17-1e24bc70dc58', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:41:30.000', '2025-01-08 14:41:30.000', 'PENDING'),
('5f4da728-9eea-4969-a9b7-e6270027b13a', 'f92a7e9b-4823-4c19-9194-dc77b891da8d', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:42:00.000', '2025-01-08 14:42:00.000', 'DECLINED');
