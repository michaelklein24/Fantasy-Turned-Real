--insert into usr_user (user_id, email, first_name, last_name, username)
--values (1000, 'michaelklein1997@gmail.com', 'michael', 'klein', 'inklein1997'),
--(1001, 'jacksonsanders@gmail.com', 'jackson', 'sanders', 'jacksonsanders'),
--(1002, 'thomasvonbuettner@gmail.com', 'thomas', 'von buettner', 'thomasvonbuettner'),
--(1003, 'chrislynvonbuettner@gmail.com', 'chrislyn', 'von buettner', 'chrislynvonbuettner'),
--(1004, 'codyschultz@gmail.com', 'cody', 'schultz', 'codyschultz'),
--(1005, 'kellyschultz@gmail.com', 'kelly', 'schultz', 'kellyschultz');
--
--insert into usr_password_history (active, password_id, user_id, created_date, expiry_date, encoded_password)
--values (true, 1000, 1000, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6'),
--(true, 1001, 1001, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6'),
--(true, 1002, 1002, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6'),
--(true, 1003, 1003, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6'),
--(true, 1004, 1004, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6'),
--(true, 1005, 1005, '2024-06-26 12:20:02.842', '2024-09-24 12:20:02.842', '$2a$10$qufOfmjop6WTIGE3LLBOeutLW4HKDw3TVeBIlGsIkrRgpLIeccuE6');
--
--insert into usr_role (role_id, user_id, role)
--values (1000, 1000, 'USER'),
--(1001, 1001, 'USER'),
--(1002, 1002, 'USER'),
--(1003, 1003, 'USER'),
--(1004, 1004, 'USER'),
--(1005, 1005, 'USER');
--
--insert into lge_league (league_id, status, name)
--values (1000, 'NOT_STARTED', 'Corner By The Bookshelf'),
--(1001, 'IN_PROGRESS', 'Helloooo there'),
--(1002, 'COMPLETED', 'BOOM WOW POW!');
--
--insert into lge_league_user_details(league_id, user_id, league_role, total_points, placement)
--values (1000, 1000, 'ADMIN', 90, 1),
--(1000, 1001, 'USER', 85, 2),
--(1000, 1002, 'USER', 82, 3),
--(1000, 1003, 'USER', 70, 4),
--(1000, 1004, 'USER', 65, 5),
--(1000, 1005, 'USER', 12, 6),
--
--(1001, 1000, 'ADMIN', 90, 1),
--(1001, 1001, 'USER', 85, 2),
--(1001, 1002, 'USER', 82, 3),
--(1001, 1003, 'USER', 70, 4),
--(1001, 1004, 'USER', 65, 5),
--(1001, 1005, 'USER', 12, 6),
--
--(1002, 1000, 'ADMIN', 90, 1),
--(1002, 1001, 'USER', 85, 2)