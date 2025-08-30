INSERT INTO users (user_id, username, email, password, phone_number, role, created_at)
VALUES
(1, 'Rahul Sharma', 'rahul@example.com', 'Password123', '9876543210', 'NORMALUSER', NOW()),
(2, 'Priya Nair', 'priya@example.com', 'SecurePass456', '9123456780', 'NORMALUSER', NOW()),
(3, 'Arjun Mehta', 'arjun@example.com', 'Arjun@789', '9988776655', 'ADMIN', NOW()),
(4, 'Sneha Kapoor', 'sneha@example.com', 'SnehaPass321', '9090909090', 'NORMALUSER', NOW()),
(5, 'Vikram Singh', 'vikram@example.com', 'Vikram@123', '9871234560', 'NORMALUSER', NOW());

INSERT INTO vaspack (pack_id, pack_title, description, price, launch_date, status, service_type)
VALUES
(1, 'Premium Music Pack', 'Unlimited access to premium songs and albums.', 199.99, '2023-05-01', TRUE, 'Entertainment'),
(2, 'Sports Updates Pack', 'Live scores and updates for all major sports.', 149.50, '2023-06-10', TRUE, 'Sports'),
(3, 'Movie Add-On Pack', 'Access to latest movies and classics.', 299.00, '2023-07-15', TRUE, 'Entertainment'),
(4, 'Kids Learning Pack', 'Educational games and videos for kids.', 99.00, '2023-08-05', TRUE, 'Education'),
(5, 'News Alerts Pack', 'Breaking news alerts and daily summaries.', 49.00, '2023-09-12', FALSE, 'News');


INSERT INTO feedback (feedback_id, overall_rating, quality_of_service_rating, value_for_money_rating, customer_support_rating, ease_of_use_rating, comment, pros, cons, feedback_time, user_id, vas_pack_id)
VALUES
(1, 5, 5, 4, 5, 5, 'Amazing pack, very useful and entertaining.', 'Great selection of songs', 'Slightly expensive', NOW(), 1, 1),
(2, 4, 4, 5, 4, 4, 'Good sports coverage, real-time updates are accurate.', 'Fast updates', 'Interface can improve', NOW(), 2, 2),
(3, 3, 3, 3, 4, 3, 'Movies are decent but collection could be better.', 'HD quality movies', 'Not enough regional movies', NOW(), 3, 3),
(4, 5, 5, 5, 5, 5, 'Kids learning pack is fantastic for my child.', 'Educational and fun', 'Needs more interactive games', NOW(), 4, 4),
(5, 2, 3, 2, 2, 3, 'News alerts pack is not very useful, notifications are delayed.', 'Covers all categories', 'Too many ads', NOW(), 5, 5);
