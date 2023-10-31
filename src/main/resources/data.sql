INSERT INTO season (season_id, title)
VALUES
(1, 'Survivor: Winners at War'),
(2, 'Vanuatu - Islands of Fire');

INSERT INTO contestant (contestant_id, first_name, last_name, occupation, origin_city, origin_state, nick_name, biography, image)
VALUES
(1, 'Tony', 'Vlachos', 'Police Officer', 'Jersey City', 'New Jersey', 'The Spy Shack', 'Tony Vlachos is known for his aggressive gameplay and clever strategies.', NULL),
(2, 'Natalie', 'Anderson', 'Crossfit Trainer', 'Colombo', 'Sri Lanka', 'Missy', 'Natalie Anderson is a physical powerhouse and known for her resilience.', NULL),
(3, 'Jeremy', 'Collins', 'Firefighter', 'Foxborough', 'Massachusetts', 'Jeremy', 'Jeremy Collins is a strategic player and a strong alliance builder.', NULL),
(4, 'Michele', 'Fitzgerald', 'Salesperson', 'Freehold', 'New Jersey', 'Michele', 'Michele Fitzgerald is known for her adaptability and social skills.', NULL),
(5, 'Adam', 'Klein', 'Public Speaker', 'San Francisco', 'California', 'Adam', 'Adam Kleins mother inspired his Survivor journey, and hes known for his emotional gameplay.', NULL),
(6, 'Ben', 'Driebergen', 'Marine', 'Boise', 'Idaho', 'Ben', 'Ben Driebergen is known for his military background and his "spy bunker."', NULL),
(7, 'Sarah', 'Lacina', 'Police Officer', 'Cedar Rapids', 'Iowa', 'Sar-rah', 'Sarah Lacina is known for her strategic play and close alliances.', NULL),
(8, 'Sophie', 'Clarke', 'Healthcare Consultant', 'Willsboro', 'New York', 'Soph', 'Sophie Clarke is a strategic mastermind and known for her attention to detail.', NULL),
(9, 'Denise', 'Stapley', 'Therapist', 'Cedar Rapids', 'Iowa', 'The Queen Slayer', 'Denise Stapley is known for slaying the Queen and her stealthy gameplay.', NULL),
(10, 'Kim', 'Spradlin-Wolfe', 'Realtor', 'San Antonio', 'Texas', 'Kim', 'Kim Spradlin-Wolfe is a dominant player and known for her control over the game.', NULL),
(11, 'Tyson', 'Apostol', 'Sales', 'Lindon', 'Utah', 'Tyson', 'Tyson Apostol is a strategic and comedic player, known for his entertaining confessionals.', NULL),
(12, 'Wendell', 'Holland', 'Furniture Company Owner', 'Philadelphia', 'Pennsylvania', 'Wendell', 'Wendell Holland is known for his laid-back demeanor and strategic moves.', NULL),
(13, 'Yul', 'Kwon', 'Management Consultant', 'Manhattan', 'New York', 'Yul', 'Yul Kwon is a strategic mastermind and known for his calm and analytical approach.', NULL),
(14, 'Chris', 'Daugherty', 'Construction Worker', 'South Vienna', 'Ohio', 'Daugherty', 'Chris Daugherty is known for his under-the-radar gameplay and strategic moves.', NULL),
(15, 'Twila', 'Tanner', 'Highway Repair Worker', 'Marshall', 'Missouri', 'Twila', 'Twila Tanner is a no-nonsense player with a straight-shooting approach.', NULL),
(16, 'Scout', 'Cloud Lee', 'Rancher', 'Stillwater', 'Oklahoma', 'Scout', 'Scout Cloud Lee is a strong and wise player, known for her leadership qualities.', NULL),
(17, 'Eliza', 'Orlins', 'Law Student', 'Syracuse', 'New York', 'Eliza', 'Eliza Orlins is known for her vocal and emotional gameplay, often in tribal councils.', NULL),
(18, 'Ami', 'Cusack', 'Barista', 'Golden, Colorado', 'Colorado', 'Ami', 'Ami Cusack is a fierce competitor and leader, known for her dominance.', NULL),
(19, 'Leann', 'Slaby', 'Research Assistant', 'Kansas City', 'Missouri', 'Leann', 'Leann Slaby is a strategic player and known for her alliance with Ami.', NULL),
(20, 'Julie', 'Berry', 'Youth Mentor', 'Gorham', 'Maine', 'Julie', 'Julie Berry is known for her social gameplay and her romantic involvement with another contestant.', NULL),
(21, 'Chad', 'Crittenden', 'Teacher', 'Oakland', 'California', 'Chad', 'Chad Crittenden is a strong physical player and known for his athleticism.', NULL),
(22, 'Rory', 'Freeman', 'Housing Case Manager', 'Des Moines', 'Iowa', 'Rory', 'Rory Freeman is known for his confrontations and determination.', NULL),
(23, 'John P.', 'Kenney', 'Sales Manager', 'Los Angeles', 'California', 'John P.', 'John P. Kenney is known for his competitive spirit and strategic moves.', NULL);

-- Insert episodes for each season
INSERT INTO episode (episode_id, title, air_date, season_id)
VALUES
(1, 'They Came At Us With Spears!', '2023-01-01', 2),
(2, 'Burly Girls, Bowheads, Young Studs and the Old Bunch', '2023-01-08', 2),
(3, 'Double Tribal, Double Trouble', '2023-01-01', 2),
(4, 'Now That''s a Reward!', '2023-01-08', 2),
(5, 'Earthquakes and Shake-Ups!', '2023-01-01', 2),
(6, 'Hog Tied', '2023-01-08', 2),
(7, 'Anger, Threats, Tears .. And Coffee', '2023-01-01', 2),
(8, 'Now the Battle Really Begins', '2023-01-08', 2),
(9, 'Gender Wars .. And It''s Getting Ugly', '2023-01-08', 2),
(10, 'Culture Shock and Violent Storms!', '2023-01-08', 2),
(11, 'Surprise! And.. Surprise Again!', '2023-01-08', 2),
(12, 'Now Who''s In Charge Here?!', '2023-01-08', 2),
(13, 'Eruptions of Volcanic Magnitude', '2023-01-08', 2),
(14, 'Spirits and the Final Four', '2023-01-08', 2);


-- Insert contestant status for each contestant in each season
INSERT INTO contestant_status (contestant_status_id, eliminated, winner, contestant_id, season_id)
VALUES
(1, false, true, 14, 2),
(2, false, false, 15, 2),
(3, true, false, 16, 2),
(4, true, false, 17, 2),
(5, true, false, 18, 2),
(6, true, false, 19, 2),
(7, true, false, 20, 2),
(8, true, false, 21, 2),
(9, true, false, 22, 2),
(10, true, false, 23, 2);


-- Insert social accounts for contestants
INSERT INTO social_account (social_account_id, handle, application, contestant_id)
VALUES
(1, '@ChrisDaugherty', 'TWITTER', 14),
(2, '@TwilaTanner', 'TWITTER', 15),
(3, '@ScoutCloudLee', 'INSTAGRAM', 16),
(4, '@ElizaOrlins', 'INSTAGRAM', 17),
(5, '@AmiCusack', 'TWITTER', 18),
(6, '@LeannSlaby', 'INSTAGRAM', 19),
(7, '@JulieBerry', 'TWITTER', 20),
(8, '@ChadCrittenden', 'INSTAGRAM', 21),
(9, '@RoryFreeman', 'TWITTER', 22),
(10, '@JohnPKenney', 'INSTAGRAM', 23);
