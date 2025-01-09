INSERT INTO ssn_season (sequence, show, start_time, end_time, total_episodes)
VALUES ('47', 'SURVIVOR', '2025-02-15 20:00:00', '2025-05-15 22:00:00', 13);

INSERT INTO usr_user (user_id, role, email, first_name, last_name)
VALUES ('7db21f43-31e2-4fd7-8501-772039ad9f16', 'USER', 'tony.stark@test.com', 'Tony', 'Stark');

INSERT INTO usr_password_history(active, created_date, expiry_date, encoded_password, password_id, user_id)
VALUES (true,'2025-01-08','2025-04-08','$2a$10$Wg96X7oUcUPMfUTfkxZuRueniAPfGPB3Vp.zzQctcN7ju2NhFL16W','7fd7be7a-529b-4004-b36f-b7f98ef71d97','7db21f43-31e2-4fd7-8501-772039ad9f16');

INSERT INTO lge_league (season_sequence, create_time, league_id, name, season_show)
VALUES (47, '2025-01-08 14:38:53.763', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'Corner By The Bookshelf', 'SURVIVOR');

INSERT INTO lge_participant (time_joined, league_id, role, user_id)
VALUES ('2025-01-08 14:38:53.773', '5f4da728-9eea-4969-a9b7-e6270027b13a', 'OWNER', '7db21f43-31e2-4fd7-8501-772039ad9f16');

-- Inserting data into lge_invite table
INSERT INTO lge_invite (league_id, invitee_user_id, create_time, update_time, status)
VALUES ('5f4da728-9eea-4969-a9b7-e6270027b13a', '7db21f43-31e2-4fd7-8501-772039ad9f16', '2025-01-08 14:38:53.773', '2025-01-08 14:38:53.773', 'PENDING');
