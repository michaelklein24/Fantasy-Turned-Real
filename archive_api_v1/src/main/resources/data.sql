INSERT INTO season (season_id, title, show, season_number)
VALUES
(100, 'Survivor: Winners at War', 'SURVIVOR', 40),
(200, 'Vanuatu - Islands of Fire', 'SURVIVOR', 9);

INSERT INTO contestant (contestant_id, first_name, last_name, occupation, origin_city, origin_state, nick_name, biography, image)
VALUES
(100, 'Tony', 'Vlachos', 'Police Officer', 'Jersey City', 'New Jersey', 'The Spy Shack', 'Tony Vlachos is known for his aggressive gameplay and clever strategies.', NULL),
(200, 'Natalie', 'Anderson', 'Crossfit Trainer', 'Colombo', 'Sri Lanka', 'Missy', 'Natalie Anderson is a physical powerhouse and known for her resilience.', NULL),
(300, 'Jeremy', 'Collins', 'Firefighter', 'Foxborough', 'Massachusetts', 'Jeremy', 'Jeremy Collins is a strategic player and a strong alliance builder.', NULL),
(400, 'Michele', 'Fitzgerald', 'Salesperson', 'Freehold', 'New Jersey', 'Michele', 'Michele Fitzgerald is known for her adaptability and social skills.', NULL),
(500, 'Adam', 'Klein', 'Public Speaker', 'San Francisco', 'California', 'Adam', 'Adam Kleins mother inspired his Survivor journey, and hes known for his emotional gameplay.', NULL),
(600, 'Ben', 'Driebergen', 'Marine', 'Boise', 'Idaho', 'Ben', 'Ben Driebergen is known for his military background and his "spy bunker."', NULL),
(700, 'Sarah', 'Lacina', 'Police Officer', 'Cedar Rapids', 'Iowa', 'Sar-rah', 'Sarah Lacina is known for her strategic play and close alliances.', NULL),
(800, 'Sophie', 'Clarke', 'Healthcare Consultant', 'Willsboro', 'New York', 'Soph', 'Sophie Clarke is a strategic mastermind and known for her attention to detail.', NULL),
(900, 'Denise', 'Stapley', 'Therapist', 'Cedar Rapids', 'Iowa', 'The Queen Slayer', 'Denise Stapley is known for slaying the Queen and her stealthy gameplay.', NULL),
(1000, 'Kim', 'Spradlin-Wolfe', 'Realtor', 'San Antonio', 'Texas', 'Kim', 'Kim Spradlin-Wolfe is a dominant player and known for her control over the game.', NULL),
(1100, 'Tyson', 'Apostol', 'Sales', 'Lindon', 'Utah', 'Tyson', 'Tyson Apostol is a strategic and comedic player, known for his entertaining confessionals.', NULL),
(1200, 'Wendell', 'Holland', 'Furniture Company Owner', 'Philadelphia', 'Pennsylvania', 'Wendell', 'Wendell Holland is known for his laid-back demeanor and strategic moves.', NULL),
(1300, 'Yul', 'Kwon', 'Management Consultant', 'Manhattan', 'New York', 'Yul', 'Yul Kwon is a strategic mastermind and known for his calm and analytical approach.', NULL),
(1400, 'Chris', 'Daugherty', 'Construction Worker', 'South Vienna', 'Ohio', 'Daugherty', 'Chris Daugherty is known for his under-the-radar gameplay and strategic moves.', NULL),
(1500, 'Twila', 'Tanner', 'Highway Repair Worker', 'Marshall', 'Missouri', 'Twila', 'Twila Tanner is a no-nonsense player with a straight-shooting approach.', NULL),
(1600, 'Scout', 'Cloud Lee', 'Rancher', 'Stillwater', 'Oklahoma', 'Scout', 'Scout Cloud Lee is a strong and wise player, known for her leadership qualities.', NULL),
(1700, 'Eliza', 'Orlins', 'Law Student', 'Syracuse', 'New York', 'Eliza', 'Eliza Orlins is known for her vocal and emotional gameplay, often in tribal councils.', NULL),
(1800, 'Ami', 'Cusack', 'Barista', 'Golden, Colorado', 'Colorado', 'Ami', 'Ami Cusack is a fierce competitor and leader, known for her dominance.', NULL),
(1900, 'Leann', 'Slaby', 'Research Assistant', 'Kansas City', 'Missouri', 'Leann', 'Leann Slaby is a strategic player and known for her alliance with Ami.', NULL),
(2000, 'Julie', 'Berry', 'Youth Mentor', 'Gorham', 'Maine', 'Julie', 'Julie Berry is known for her social gameplay and her romantic involvement with another contestant.', NULL),
(2100, 'Chad', 'Crittenden', 'Teacher', 'Oakland', 'California', 'Chad', 'Chad Crittenden is a strong physical player and known for his athleticism.', NULL),
(2200, 'Rory', 'Freeman', 'Housing Case Manager', 'Des Moines', 'Iowa', 'Rory', 'Rory Freeman is known for his confrontations and determination.', NULL),
(2300, 'John P.', 'Kenney', 'Sales Manager', 'Los Angeles', 'California', 'John P.', 'John P. Kenney is known for his competitive spirit and strategic moves.', NULL);

-- Insert episodes for each season
INSERT INTO episode (episode_id, title, air_date, season_id)
VALUES
(100, 'They Came At Us With Spears!', '2023-01-01', 200),
(200, 'Burly Girls, Bowheads, Young Studs and the Old Bunch', '2023-01-08', 200),
(300, 'Double Tribal, Double Trouble', '2023-01-01', 200),
(400, 'Now That''s a Reward!', '2023-01-08', 200),
(500, 'Earthquakes and Shake-Ups!', '2023-01-01', 200),
(600, 'Hog Tied', '2023-01-08', 200),
(700, 'Anger, Threats, Tears .. And Coffee', '2023-01-01', 200),
(800, 'Now the Battle Really Begins', '2023-01-08', 200),
(900, 'Gender Wars .. And It''s Getting Ugly', '2023-01-08', 200),
(1000, 'Culture Shock and Violent Storms!', '2023-01-08', 200),
(1100, 'Surprise! And.. Surprise Again!', '2023-01-08', 200),
(1200, 'Now Who''s In Charge Here?!', '2023-01-08', 200),
(1300, 'Eruptions of Volcanic Magnitude', '2023-01-08', 200),
(1400, 'Spirits and the Final Four', '2023-01-08', 200);


-- Insert contestant status for each contestant in each season
INSERT INTO contestant_status (contestant_status_id, status, contestant_id, episode_id, season_id)
VALUES
(100, 'ALIVE', 1400, 100, 200),
(200, 'ALIVE', 1500, 100, 200),
(300, 'ALIVE', 1600, 100, 200),
(400, 'ALIVE', 1700, 100, 200),
(500, 'ALIVE', 1800, 100, 200),
(600, 'ALIVE', 1900, 100, 200),
(700, 'ALIVE', 2000, 100, 200),
(800, 'ALIVE', 2100, 100, 200),
(900, 'ALIVE', 2200, 100, 200),
(1000, 'ALIVE', 2300, 100, 200);


-- Insert social accounts for contestants
INSERT INTO social_account (social_account_id, handle, application, contestant_id)
VALUES
(100, '@ChrisDaugherty', 'TWITTER', 1400),
(200, '@TwilaTanner', 'TWITTER', 1500),
(300, '@ScoutCloudLee', 'INSTAGRAM', 1600),
(400, '@ElizaOrlins', 'INSTAGRAM', 1700),
(500, '@AmiCusack', 'TWITTER', 1800),
(600, '@LeannSlaby', 'INSTAGRAM', 1900),
(700, '@JulieBerry', 'TWITTER', 2000),
(800, '@ChadCrittenden', 'INSTAGRAM', 2100),
(900, '@RoryFreeman', 'TWITTER', 2200),
(1000, '@JohnPKenney', 'INSTAGRAM', 2300);

INSERT INTO league (league_id, league_name, season_id)
VALUES
(100, 'Survivor League 2024', 200);

INSERT INTO users (user_id, username)
VALUES
(100, 'user3'),
(200, 'user4');

INSERT INTO roles (role_id, name)
VALUES
(100, 'league_admin'),
(200, 'league_participant'),
(300, 'admin');

INSERT INTO user_roles (user_id, role_id)
VALUES
(100, 200),
(200, 200);

INSERT INTO question (question_id, points, question_type, question_text, start_time, end_time, time_submitted, status, episode_id, league_id, submitter_user_id)
VALUES (100, 10, 'CONTESTANT_PICK', 'Which contestant will get voted off?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'OPEN', 100, 100, 100),
       (200, 10, 'TRIBE_PICK', 'Which tribe will win immunity?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 100, 100, 100),
       (300, 10, 'CONTESTANT_PICK', 'Which contestant will go to exinction island?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 100, 100, 100),
       (400, 10, 'TRIBE_PICK', 'Which tribe will lose immunity?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 100, 100, 100),
       (500, 10, 'CONTESTANT_PICK', 'Which contestant will get voted off?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'OPEN', 200, 100, 100),
       (600, 10, 'TRIBE_PICK', 'Which tribe will win immunity?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 200, 100, 100),
       (700, 10, 'CONTESTANT_PICK', 'Which contestant will go to exinction island?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 200, 100, 100),
       (800, 10, 'TRIBE_PICK', 'Which tribe will lose immunity?', '2023-11-16 07:00:00', '2023-11-23 07:00:00', '2023-11-05 11:00:00', 'INACTIVE', 200, 100, 100);

INSERT INTO answer (answer_id, answer, is_correct, time_submitted, question_id, user_id)
VALUES
(100,'Contestant A', null, '2023-11-24 07:00:00', 100, 100),
(200,'Contestant A', null, '2023-11-24 07:00:00', 100, 200),
(300,'Blue', null, '2023-11-24 07:00:00', 200, 100),
(400,'Blue', null, '2023-11-24 07:00:00', 200, 200),
(500,'Contestant A', null, '2023-11-24 07:00:00', 300, 100),
(600,'Contestant A', null, '2023-11-24 07:00:00', 300, 200),
(700,'Red', null, '2023-11-24 07:00:00', 400, 100),
(800,'Red', null, '2023-11-24 07:00:00', 400, 200),
(900,'Contestant A', null, '2023-11-24 07:00:00', 500, 100),
(1000,'Contestant A', null, '2023-11-24 07:00:00', 500, 200),
(1100,'Blue', null, '2023-11-24 07:00:00', 600, 100),
(1200,'Blue', null, '2023-11-24 07:00:00', 600, 200),
(1300,'Contestant A', null, '2023-11-24 07:00:00', 700, 100),
(1400,'Contestant A', null, '2023-11-24 07:00:00', 700, 200),
(1500,'Red', null, '2023-11-24 07:00:00', 800, 100),
(1600,'Red', null, '2023-11-24 07:00:00', 800, 200);

INSERT INTO league_users (leagues_league_id, users_user_id)
VALUES
(100, 200),
(100, 100);

INSERT INTO score (score_id, points_earned, answer_answer_id, episode_episode_id, league_league_id, user_user_id)
VALUES
(100, 10, 100, 100, 100, 100),
(200, 10, 200, 100, 100, 200),
(300, 10, 300, 100, 100, 100),
(400, 10, 400, 100, 100, 200),
(500, 10, 500, 100, 100, 100),
(600, 10, 600, 100, 100, 200),
(700, 10, 700, 100, 100, 100),
(800, 10, 800, 100, 100, 200),
(900, 10, 900, 200, 100, 100),
(1000, 10, 1000, 200, 100, 200),
(1100, 10, 1100, 200, 100, 100),
(1200, 10, 1200, 200, 100, 200),
(1300, 10, 1300, 200, 100, 100),
(1400, 10, 1400, 200, 100, 200),
(1500, 10, 1500, 200, 100, 100),
(1600, 10, 1600, 200, 100, 200);
