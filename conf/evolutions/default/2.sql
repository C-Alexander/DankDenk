# --- !Ups

INSERT INTO `category` (`id`, `name`, description) VALUES (1, 'DANK', 'dank wank');

INSERT INTO `meme` (`id`, `name`, `url`) VALUES (1, 'Corgibutts', 'https://s-media-cache-ak0.pinimg.com/originals/53/fa/86/53fa868c5e7cf7dc1c54f1545a46cc05.jpg');
INSERT INTO `meme` (`id`, `name`, `url`) VALUES (2, 'Snek', 'https://78.media.tumblr.com/0088b8ef7b118849c3a5a67fd96ceda8/tumblr_o3uki1cBvU1ry7p63o2_1280.jpg');
INSERT INTO `meme` (`id`, `name`, `url`, `price`, `DTYPE`) VALUES (3, 'PaidSnek', 'https://78.media.tumblr.com/0088b8ef7b118849c3a5a67fd96ceda8/tumblr_o3uki1cBvU1ry7p63o2_1280.jpg', 1.0, 'PaidMeme');